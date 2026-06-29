package menu;

import java.util.List;
import model.Jogador;
import model.TipoPersonagem;
import service.GerenciadorJogadores;
import util.Leitor;

public class MenuJogadores {

    private Leitor leitor;
    private double saldoInicial;

    public MenuJogadores(double saldoInicial) {
        this.leitor = new Leitor();
        this.saldoInicial = saldoInicial;
    }

    public void exibir(GerenciadorJogadores gerenciador) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===================================");
            System.out.println("       GERENCIAR JOGADORES         ");
            System.out.println("===================================");
            System.out.println("1 - Cadastrar Jogador");
            System.out.println("2 - Listar Jogadores");
            System.out.println("3 - Editar Jogador");
            System.out.println("4 - Remover Jogador");
            System.out.println("5 - Verificar Status para Iniciar");
            System.out.println("0 - Voltar");
            System.out.println("===================================");
            
            opcao = leitor.lerInteiro("Escolha uma opcao: ");
            leitor.limparBuffer();

            switch (opcao) {
                case 1:
                    cadastrar(gerenciador);
                    break;
                case 2:
                    listar(gerenciador);
                    break;
                case 3:
                    editar(gerenciador);
                    break;
                case 4:
                    remover(gerenciador);
                    break;
                case 5:
                    verificarStatus(gerenciador);
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void cadastrar(GerenciadorJogadores gerenciador) {
        System.out.println("\n--- Cadastrar Novo Jogador ---");
        
        if (gerenciador.getQuantidadeCadastrada() >= 6) {
            System.out.println("Limite maximo de 6 jogadores atingido.");
            return;
        }

        String nome = leitor.lerTexto("Nome do Jogador: ");
        TipoPersonagem tipo = escolherPersonagem();

        Jogador novoJogador = new Jogador(nome, saldoInicial, tipo);
        
        if (gerenciador.cadastrar(novoJogador)) {
            System.out.println(">> Jogador '" + nome + "' cadastrado com sucesso como " + tipo.getDescricao() + "!");
        } else {
            System.out.println(">> Falha ao cadastrar jogador.");
        }
    }

    private TipoPersonagem escolherPersonagem() {
        while (true) {
            System.out.println("Escolha a classe do personagem:");
            System.out.println("1 - Especulador (+20% salario, +10% imposto)");
            System.out.println("2 - Negociante (-10% aluguel a pagar)");
            System.out.println("3 - Advogado (1 saida gratis da prisao)");
            System.out.println("4 - Construtor (+15% aluguel a receber)");
            
            int escolha = leitor.lerInteiro("Opcao: ");
            leitor.limparBuffer();
            
            switch (escolha) {
                case 1: return TipoPersonagem.ESPECULADOR;
                case 2: return TipoPersonagem.NEGOCIANTE;
                case 3: return TipoPersonagem.ADVOGADO;
                case 4: return TipoPersonagem.CONSTRUTOR;
                default: System.out.println("Opcao invalida! Tente novamente.");
            }
        }
    }

    private void listar(GerenciadorJogadores gerenciador) {
        System.out.println("\n--- Lista de Jogadores (" + gerenciador.getQuantidadeCadastrada() + "/6) ---");
        
        List<Jogador> lista = gerenciador.listar();
        
        if (lista.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }

        System.out.println("+----------------------+----------------------+----------------+");
        System.out.printf("| %-20s | %-20s | %-14s |%n", "Nome", "Personagem", "Saldo Inicial");
        System.out.println("+----------------------+----------------------+----------------+");
        
        for (int i = 0; i < lista.size(); i++) {
            Jogador j = lista.get(i);
            System.out.printf("| %-20s | %-20s | R$%-12.2f |%n", 
                j.getNome(), 
                j.getTipoPersonagem().getDescricao(), 
                j.getSaldo());
        }
        
        System.out.println("+----------------------+----------------------+----------------+");
    }

    private void editar(GerenciadorJogadores gerenciador) {
        System.out.println("\n--- Editar Jogador ---");
        String nomeAtual = leitor.lerTexto("Nome atual do Jogador que deseja editar: ");
        
        Jogador jogador = gerenciador.buscarPorNome(nomeAtual);
        if (jogador == null) {
            System.out.println("Erro: Jogador '" + nomeAtual + "' nao encontrado.");
            return;
        }
        
        System.out.println("Jogador encontrado! Informe os novos dados:");
        String novoNome = leitor.lerTexto("Novo Nome (ou repita o atual): ");
        TipoPersonagem novoPersonagem = escolherPersonagem();
        
        if (gerenciador.editar(nomeAtual, novoNome, novoPersonagem)) {
            System.out.println(">> Jogador atualizado com sucesso!");
        } else {
            System.out.println(">> Falha ao editar jogador.");
        }
    }

    private void remover(GerenciadorJogadores gerenciador) {
        System.out.println("\n--- Remover Jogador ---");
        String nome = leitor.lerTexto("Nome do Jogador que deseja remover: ");
        
        if (gerenciador.remover(nome)) {
            System.out.println(">> Jogador '" + nome + "' removido com sucesso!");
        } else {
            System.out.println(">> Falha ao remover jogador.");
        }
    }

    private void verificarStatus(GerenciadorJogadores gerenciador) {
        System.out.println("\n--- Status para Iniciar Partida ---");
        System.out.println("Jogadores cadastrados: " + gerenciador.getQuantidadeCadastrada());
        if (gerenciador.podeIniciarPartida()) {
            System.out.println("STATUS: PRONTO! A partida pode ser iniciada.");
        } else {
            System.out.println("STATUS: PENDENTE. E necessario ter entre 2 e 6 jogadores.");
        }
    }
}
