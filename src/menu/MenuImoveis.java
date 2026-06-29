package menu;

import java.util.List;
import model.Imovel;
import service.GerenciadorImoveis;
import util.Leitor;

public class MenuImoveis {

    private Leitor leitor;

    public MenuImoveis() {
        this.leitor = new Leitor();
    }

    public void exibir(GerenciadorImoveis gerenciador) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===================================");
            System.out.println("       GERENCIAR IMOVEIS           ");
            System.out.println("===================================");
            System.out.println("1 - Cadastrar Imovel");
            System.out.println("2 - Listar Imoveis");
            System.out.println("3 - Editar Imovel");
            System.out.println("4 - Remover Imovel");
            System.out.println("0 - Voltar ao Menu Principal");
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
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void cadastrar(GerenciadorImoveis gerenciador) {
        System.out.println("\n--- Cadastrar Novo Imovel ---");
        
        if (gerenciador.getQuantidadeCadastrada() >= 40) {
            System.out.println("Limite maximo de 40 imoveis atingido.");
            return;
        }

        String nome = leitor.lerTexto("Nome do Imovel: ");
        double preco = leitor.lerDecimal("Valor de Compra (R$): ");
        double aluguel = leitor.lerDecimal("Aluguel Base (R$): ");
        leitor.limparBuffer();
        
        String grupo = leitor.lerTexto("Grupo (Cor ou Categoria): ");

        Imovel novoImovel = new Imovel(nome, 0, preco, aluguel, grupo);
        
        if (gerenciador.cadastrar(novoImovel)) {
            System.out.println(">> Imovel '" + nome + "' cadastrado com sucesso!");
        } else {
            System.out.println(">> Falha ao cadastrar imovel.");
        }
    }

    private void listar(GerenciadorImoveis gerenciador) {
        System.out.println("\n--- Lista de Imoveis (" + gerenciador.getQuantidadeCadastrada() + "/40) ---");
        
        List<Imovel> lista = gerenciador.listar();
        
        if (lista.isEmpty()) {
            System.out.println("Nenhum imovel cadastrado.");
            return;
        }

        System.out.println("+--------------------------------+-------------+-------------+---------------+");
        System.out.printf("| %-30s | %-11s | %-11s | %-13s |%n", "Nome", "Compra (R$)", "Aluguel (R$)", "Grupo");
        System.out.println("+--------------------------------+-------------+-------------+---------------+");
        
        for (int i = 0; i < lista.size(); i++) {
            Imovel imv = lista.get(i);
            System.out.printf("| %-30s | %-11.2f | %-11.2f | %-13s |%n", 
                cortarTexto(imv.getNome(), 30), 
                imv.getPreco(), 
                imv.getAluguelBase(), 
                cortarTexto(imv.getGrupo(), 13));
        }
        
        System.out.println("+--------------------------------+-------------+-------------+---------------+");
    }

    private void editar(GerenciadorImoveis gerenciador) {
        System.out.println("\n--- Editar Imovel ---");
        String nomeAtual = leitor.lerTexto("Nome atual do Imovel que deseja editar: ");
        
        Imovel imovel = gerenciador.buscarPorNome(nomeAtual);
        if (imovel == null) {
            System.out.println("Erro: Imovel '" + nomeAtual + "' nao encontrado.");
            return;
        }
        
        System.out.println("Imovel encontrado! Informe os novos dados:");
        String novoNome = leitor.lerTexto("Novo Nome (ou repita o atual): ");
        double novoPreco = leitor.lerDecimal("Novo Valor de Compra (R$): ");
        double novoAluguel = leitor.lerDecimal("Novo Aluguel Base (R$): ");
        leitor.limparBuffer();
        
        if (gerenciador.editar(nomeAtual, novoNome, novoPreco, novoAluguel)) {
            System.out.println(">> Imovel atualizado com sucesso!");
        } else {
            System.out.println(">> Falha ao editar imovel.");
        }
    }

    private void remover(GerenciadorImoveis gerenciador) {
        System.out.println("\n--- Remover Imovel ---");
        String nome = leitor.lerTexto("Nome do Imovel que deseja remover: ");
        
        if (gerenciador.remover(nome)) {
            System.out.println(">> Imovel '" + nome + "' removido com sucesso!");
        } else {
            System.out.println(">> Falha ao remover imovel.");
        }
    }

    private String cortarTexto(String texto, int max) {
        if (texto.length() > max) {
            return texto.substring(0, max - 2) + "..";
        }
        return texto;
    }
}
