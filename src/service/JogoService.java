package service;

import model.Jogo;
import model.Jogador;
import model.Imovel;
import model.Carta;
import model.TipoCarta;
import model.RegistroRodada;
import estrutura.NoCasa;
import util.Dado;
import util.Leitor;
import java.util.List;
import java.util.ArrayList;

public class JogoService {

    private Jogo jogo;
    private Dado dado;
    private Leitor leitor;
    private double salarioPassagemInicio = 200.0;
    private double maiorAluguelCobrado = 0.0;

    public JogoService(Jogo jogo) {
        this.jogo = jogo;
        this.dado = new Dado();
        this.leitor = new Leitor();
        carregarImoveisDoTabuleiro();
    }

    public void iniciarPartida() {
        if (!jogo.getGerenciadorJogadores().podeIniciarPartida()) {
            System.out.println(">> Erro: O jogo precisa ter entre 2 e 6 jogadores cadastrados.");
            return;
        }

        jogo.setEmAndamento(true);
        jogo.setRodadaAtual(1);
        System.out.println("\n===================================");
        System.out.println("        O JOGO VAI COMEÇAR!        ");
        System.out.println("===================================");

        while (jogo.isEmAndamento()) {
            if (jogo.getRodadaAtual() > jogo.getConfiguracao().getMaxRodadas()) {
                System.out.println("\n>> Limite maximo de " + jogo.getConfiguracao().getMaxRodadas() + " rodadas atingido!");
                jogo.setEmAndamento(false);
                break;
            }
            
            System.out.println("\n--- RODADA " + jogo.getRodadaAtual() + " ---");
            
            List<Jogador> jogadoresAtivos = getJogadoresAtivos();
            
            if (jogadoresAtivos.size() <= 1) {
                jogo.setEmAndamento(false);
                break;
            }

            for (Jogador jogador : jogadoresAtivos) {
                if (jogador.isAtivo()) {
                    processarTurno(jogador);
                }
            }

            jogo.setRodadaAtual(jogo.getRodadaAtual() + 1);
            
            // Pausa entre rodadas
            System.out.println("\n===================================");
            String continuar = leitor.lerTexto("Pressione ENTER para proxima rodada ou digite 'S' para sair: ");
            if (continuar.equalsIgnoreCase("S")) {
                jogo.setEmAndamento(false);
            }
        }

        verificarVencedor();
    }
    
    private void carregarImoveisDoTabuleiro() {
        NoCasa atual = jogo.getTabuleiro().getCasas().getCabeca();
        if (atual == null) return;
        
        do {
            if (atual.getTipo().equals("IMOVEL")) {
                // Se não foi cadastrado no gerenciador ainda, cadastra com valores padrao
                if (jogo.getGerenciadorImoveis().buscarPorNome(atual.getNome()) == null) {
                    Imovel padrao = new Imovel(atual.getNome(), atual.getPosicao(), 300.0, 50.0, "Padrao");
                    jogo.getGerenciadorImoveis().cadastrar(padrao);
                }
            }
            atual = atual.getNext();
        } while (atual != jogo.getTabuleiro().getCasas().getCabeca());
    }

    private List<Jogador> getJogadoresAtivos() {
        List<Jogador> ativos = new ArrayList<>();
        for (Jogador j : jogo.getGerenciadorJogadores().listar()) {
            if (j.isAtivo()) {
                ativos.add(j);
            }
        }
        return ativos;
    }

    public void processarTurno(Jogador jogador) {
        System.out.println("\nTurno de: " + jogador.getNome() + " | Saldo: R$" + String.format("%.2f", jogador.getSaldo()));

        // Tratar prisao
        if (jogo.getPrisao().estaPreso(jogador)) {
            System.out.println(">> Voce esta na prisao!");
            String opcao = leitor.lerTexto("Deseja pagar fianca de R$" + jogo.getPrisao().getValorFianca() + "? (S/N): ");
            boolean pagar = opcao.equalsIgnoreCase("S");
            
            int[] dados = dado.jogarDois();
            jogo.getPrisao().tentarSair(jogador, pagar, dados[0], dados[1]);
            
            if (jogo.getPrisao().estaPreso(jogador)) {
                jogo.getHistorico().registrar(new RegistroRodada(jogo.getRodadaAtual(), jogador.getNome(), dados, "Prisao", "Tentou sair e falhou"));
                return; // Termina o turno se continua preso
            }
            // Se saiu, continua o turno normalmente
        }

        int[] dados = dado.jogarDois();
        int somaDados = dado.soma(dados);
        System.out.println("Dados lancados: " + dados[0] + " + " + dados[1] + " = " + somaDados);

        NoCasa casaAtual = jogo.getTabuleiro().getCasaPorPosicao(jogador.getPosicaoAtual());
        NoCasa casaDestino = jogo.getTabuleiro().getCasas().avancar(casaAtual, somaDados);
        
        // Verificar se completou volta
        if (casaDestino.getPosicao() < casaAtual.getPosicao()) {
            jogador.incrementarVoltas();
            double salarioComBonus = jogador.calcularSalario(salarioPassagemInicio);
            jogador.receberDinheiro(salarioComBonus);
            System.out.println(">> Passou pelo INICIO! Recebeu salario de R$" + String.format("%.2f", salarioComBonus));
        }

        jogador.setPosicaoAtual(casaDestino.getPosicao());
        double saldoAntes = jogador.getSaldo();
        String efeito = executarAcaoCasa(jogador, casaDestino);

        System.out.println("Saldo Final: R$" + String.format("%.2f", jogador.getSaldo()));

        // Registra historico
        jogo.getHistorico().registrar(new RegistroRodada(
            jogo.getRodadaAtual(), 
            jogador.getNome(), 
            dados, 
            casaDestino.toString(), 
            efeito
        ));

        // Regra de falencia
        if (!jogador.isAtivo()) {
            System.out.println("\n====================================");
            System.out.println("\nJOGADOR FALIDO\n");
            System.out.println(jogador.getNome() + " foi declarado falido.\n");

            if (!jogador.getListaImoveis().isEmpty()) {
                System.out.println("Imoveis devolvidos ao banco:\n");
                for (Imovel i : jogador.getListaImoveis()) {
                    System.out.println("- " + i.getNome());
                    i.setDono(null);
                }
                System.out.println("\nTodos os imoveis voltaram a ficar disponiveis.\n");
                jogador.getListaImoveis().clear();
            }

            jogo.getGerenciadorJogadores().remover(jogador.getNome());
            System.out.println(jogador.getNome() + " foi removido da partida.");
            System.out.println("\n====================================");
        }
    }

    private String executarAcaoCasa(Jogador jogador, NoCasa casa) {
        System.out.println("Caiu na casa: " + casa.toString());

        switch (casa.getTipo()) {
            case "IMOVEL":
                Imovel imovel = jogo.getGerenciadorImoveis().buscarPorNome(casa.getNome());
                if (imovel == null) {
                    return "Imovel nao cadastrado";
                }
                
                // Valida regra de visita e valorizacao
                imovel.visitar();
                System.out.println(">> Imovel visitado! Multiplicador de valorizacao atual: " + imovel.getMultiplicador() + "x");
                
                if (imovel.temDono()) {
                    if (imovel.getDono() == jogador) {
                        System.out.println("Este imovel ja e seu.");
                        return "Visitou proprio imovel";
                    } else {
                        Jogador dono = imovel.getDono();
                        
                        // Calculo do aluguel considerando o multiplicador
                        double aluguelBase = imovel.getAluguelBase();
                        double aluguelComMultiplicador = imovel.getAluguelAtual(); // aluguelBase * multiplicador
                        
                        System.out.println(">> Calculo de Aluguel:");
                        System.out.println("   Aluguel Base: R$" + String.format("%.2f", aluguelBase));
                        System.out.println("   Aplicando Multiplicador (" + imovel.getMultiplicador() + "x): R$" + String.format("%.2f", aluguelComMultiplicador));
                        
                        double aluguel = aluguelComMultiplicador;
                        
                        // Habilidades passivas
                        double aluguelAntesBonusConstrutor = aluguel;
                        aluguel = dono.calcularAluguelComoProprietario(aluguel);
                        if (aluguel > aluguelAntesBonusConstrutor) {
                            System.out.println("   Bonus do dono CONSTRUTOR (+15%): R$" + String.format("%.2f", aluguel));
                        }
                        
                        double aluguelAntesDescontoNegociante = aluguel;
                        aluguel = jogador.calcularAluguel(aluguel);
                        if (aluguel < aluguelAntesDescontoNegociante) {
                            System.out.println("   Desconto do pagante NEGOCIANTE (-10%): R$" + String.format("%.2f", aluguel));
                        }
                        
                        System.out.println(">> TOTAL a pagar: R$" + String.format("%.2f", aluguel) + " para " + dono.getNome());
                        jogador.pagarDinheiro(aluguel);
                        dono.receberDinheiro(aluguel);
                        
                        if (aluguel > maiorAluguelCobrado) {
                            maiorAluguelCobrado = aluguel;
                        }
                        
                        return "Pagou aluguel de " + aluguel;
                    }
                } else {
                    System.out.println("Imovel disponivel.");
                    System.out.println("Valor de Compra: R$" + String.format("%.2f", imovel.getPreco()));
                    System.out.println("Aluguel Base Inicial: R$" + String.format("%.2f", imovel.getAluguelBase()));
                    
                    if (jogador.getSaldo() >= imovel.getPreco()) {
                        String resp = leitor.lerTexto("Deseja comprar? (S/N): ");
                        if (resp.equalsIgnoreCase("S")) {
                            jogador.pagarDinheiro(imovel.getPreco());
                            jogador.adicionarImovel(imovel);
                            System.out.println(">> Compra realizada!");
                            return "Comprou imovel";
                        }
                    } else {
                        System.out.println("Saldo insuficiente para compra.");
                    }
                    return "Decidiu nao comprar";
                }

            case "IMPOSTO":
                double imposto = jogador.calcularImposto(100.0);
                System.out.println(">> Pagou imposto de R$" + String.format("%.2f", imposto));
                jogador.pagarDinheiro(imposto);
                return "Pagou imposto";

            case "RESTITUICAO":
                System.out.println(">> Recebeu restituicao de R$ 100.00");
                jogador.receberDinheiro(100.0);
                return "Recebeu restituicao";

            case "PRISAO":
                jogo.getPrisao().entrarNaPrisao(jogador);
                return "Foi preso";

            case "LEILAO":
                return realizarLeilao();

            case "SORTE_REVES":
                Carta carta = jogo.getBaralho().sacarCarta();
                System.out.println(">> Tirou carta: " + carta.toString());
                
                if (carta.getTipo() == TipoCarta.GANHO) {
                    jogador.receberDinheiro(carta.getValor());
                } else if (carta.getTipo() == TipoCarta.PERDA) {
                    jogador.pagarDinheiro(carta.getValor());
                } else if (carta.getTipo() == TipoCarta.AVANCO) {
                    NoCasa atual = jogo.getTabuleiro().getCasaPorPosicao(jogador.getPosicaoAtual());
                    NoCasa destino = jogo.getTabuleiro().getCasas().avancar(atual, (int) carta.getValor());
                    jogador.setPosicaoAtual(destino.getPosicao());
                    System.out.println("Avancou para " + destino.toString());
                } else if (carta.getTipo() == TipoCarta.RETROCESSO) {
                    NoCasa casaRetrocesso = jogo.getTabuleiro().getCasaPorPosicao(jogador.getPosicaoAtual());
                    NoCasa destino = jogo.getTabuleiro().getCasas().retroceder(casaRetrocesso, (int) carta.getValor());

                    // Se o destino tem posicao maior que a posicao atual, o jogador
                    // cruzou o INICIO retrocedendo (deu a volta pela "casa zero")
                    if (destino.getPosicao() > casaRetrocesso.getPosicao()) {
                        System.out.println(">> O jogador passou pelo INICIO durante um retrocesso.");
                        System.out.println(">> Retrocessos nao dao direito ao salario.");
                    }

                    jogador.setPosicaoAtual(destino.getPosicao());
                    System.out.println("Recuou para " + destino.toString());
                } else if (carta.getTipo() == TipoCarta.PRISAO) {
                    jogo.getPrisao().entrarNaPrisao(jogador);
                }
                return "Carta: " + carta.getTipo().getDescricao();

            case "INICIO":
                return "Parou no inicio";

            default:
                return "Nenhuma acao";
        }
    }

    private String realizarLeilao() {
        System.out.println(">> BEM-VINDO AO LEILAO!");
        List<Imovel> disponiveis = new ArrayList<>();
        for (Imovel imv : jogo.getGerenciadorImoveis().listar()) {
            if (!imv.temDono()) {
                disponiveis.add(imv);
            }
        }
        
        if (disponiveis.isEmpty()) {
            System.out.println(">> Nao ha imoveis sem dono disponiveis para leilao no momento.");
            return "Passou pelo Leilao (Vazio)";
        }
        
        // Pega um aleatorio
        int randIdx = (int) (Math.random() * disponiveis.size());
        Imovel imovelLeiloado = disponiveis.get(randIdx);
        
        System.out.println(">> Imovel do Leilao: " + imovelLeiloado.getNome());
        System.out.println(">> Valor de Mercado: R$" + String.format("%.2f", imovelLeiloado.getPreco()));
        
        List<Jogador> participantes = getJogadoresAtivos();
        if (participantes.isEmpty()) return "Passou pelo Leilao (Sem ativos)";
        
        double lanceMinimo = imovelLeiloado.getPreco() * 0.5;
        double lanceAtual = lanceMinimo;
        Jogador vencedor = null;
        boolean primeiroLanceRecebido = false;
        int idx = 0;
        
        System.out.println(">> O lance minimo (50%) e de R$" + String.format("%.2f", lanceMinimo));
        
        while (participantes.size() > 1 || (!primeiroLanceRecebido && participantes.size() == 1)) {
            // Condicao de parada se so sobrou um e ele ja cobriu o lance (ou os outros desistiram apos ele cobrir)
            if (participantes.size() == 1 && primeiroLanceRecebido) {
                break;
            }

            Jogador atual = participantes.get(idx);
            System.out.println("\n[Leilao] Vez de: " + atual.getNome() + " | Saldo: R$" + String.format("%.2f", atual.getSaldo()));
            System.out.println("Lance a ser batido: R$" + String.format("%.2f", lanceAtual));
            
            double oferta = leitor.lerDecimal("Informe sua oferta (ou 0 para passar): ");
            leitor.limparBuffer(); // limpa o enter apos lerDecimal
            
            if (oferta <= 0) {
                System.out.println(atual.getNome() + " passou!");
                participantes.remove(idx);
                if (idx >= participantes.size()) {
                    idx = 0;
                }
            } else {
                if (oferta > atual.getSaldo()) {
                    System.out.println("Saldo insuficiente! Voce saiu do leilao.");
                    participantes.remove(idx);
                    if (idx >= participantes.size()) {
                        idx = 0;
                    }
                } else if (oferta < lanceMinimo || (primeiroLanceRecebido && oferta <= lanceAtual)) {
                    System.out.println("A oferta deve ser MAIOR que R$" + String.format("%.2f", (primeiroLanceRecebido ? lanceAtual : lanceMinimo - 0.01)));
                    // nao incrementa idx, tenta novamente
                } else {
                    lanceAtual = oferta;
                    vencedor = atual;
                    primeiroLanceRecebido = true;
                    System.out.println(">> " + atual.getNome() + " esta liderando com R$" + String.format("%.2f", lanceAtual));
                    
                    idx++;
                    if (idx >= participantes.size()) {
                        idx = 0;
                    }
                }
            }
        }
        
        if (vencedor != null) {
            System.out.println("\n>> LEILAO ENCERRADO! Vencedor: " + vencedor.getNome() + " por R$" + String.format("%.2f", lanceAtual));
            vencedor.pagarDinheiro(lanceAtual);
            vencedor.adicionarImovel(imovelLeiloado);
            return "Venceu leilao de " + imovelLeiloado.getNome();
        } else {
            System.out.println("\n>> LEILAO ENCERRADO! Ninguem arrematou o imovel.");
            return "Leilao sem lances";
        }
    }

    public void verificarVencedor() {
        List<Jogador> todos = jogo.getGerenciadorJogadores().listar();
        
        // Ordena por patrimonio descrecente
        for (int i = 0; i < todos.size() - 1; i++) {
            for (int j = 0; j < todos.size() - 1 - i; j++) {
                if (todos.get(j).calcularPatrimonio() < todos.get(j + 1).calcularPatrimonio()) {
                    Jogador temp = todos.get(j);
                    todos.set(j, todos.get(j + 1));
                    todos.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\n===================================");
        System.out.println("      RELATORIO FINAL DA PARTIDA     ");
        System.out.println("===================================");
        
        System.out.println("\n>> CLASSIFICACAO POR PATRIMONIO:");
        for (int i = 0; i < todos.size(); i++) {
            Jogador j = todos.get(i);
            String status = j.isAtivo() ? "" : " (FALIDO)";
            System.out.println((i+1) + "o LUGAR: " + j.getNome() + status);
            System.out.println("   Patrimonio Total: R$" + String.format("%.2f", j.calcularPatrimonio()));
            System.out.println("   Voltas Completas: " + j.getVoltasCompletas());
        }
        
        System.out.println("\n>> ESTATISTICAS GERAIS:");
        System.out.println("Maior aluguel cobrado na partida: R$" + String.format("%.2f", maiorAluguelCobrado));
        System.out.println("Rodadas jogadas: " + (jogo.getRodadaAtual() - 1));
        
        System.out.println("\n>> ULTIMOS REGISTROS DO HISTORICO:");
        jogo.getHistorico().exibir();
        
        System.out.println("\n===================================");
        if (!todos.isEmpty()) {
            System.out.println(" PARABENS " + todos.get(0).getNome().toUpperCase() + " PELA VITORIA!");
        }
        System.out.println("===================================");
    }

    public Jogo getJogo() {
        return jogo;
    }
}
