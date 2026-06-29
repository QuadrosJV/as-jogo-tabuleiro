import model.Imovel;
import service.GerenciadorImoveis;
import model.Jogador;
import model.TipoPersonagem;

public class TesteCRUDImoveis {

    public static void main(String[] args) {

        System.out.println("=== TESTE DO CRUD DE IMOVEIS ===\n");

        GerenciadorImoveis gerenciador = new GerenciadorImoveis();

        System.out.println("--- Teste 1: Cadastrando Imoveis ---");
        gerenciador.cadastrar(new Imovel("Rua das Flores", 1, 300.0, 50.0, "Verde"));
        gerenciador.cadastrar(new Imovel("Avenida Brasil", 2, 400.0, 70.0, "Amarelo"));
        
        System.out.println("Quantidade cadastrada: " + gerenciador.getQuantidadeCadastrada());

        System.out.println("\n--- Teste 2: Validacao - Nome Duplicado ---");
        gerenciador.cadastrar(new Imovel("Rua das Flores", 3, 500.0, 80.0, "Azul"));

        System.out.println("\n--- Teste 3: Validacao - Valores Invalidos ---");
        gerenciador.cadastrar(new Imovel("Praca Central", 4, -100.0, 50.0, "Vermelho"));
        gerenciador.cadastrar(new Imovel("Praca Central", 4, 100.0, -10.0, "Vermelho"));

        System.out.println("\n--- Teste 4: Listando ---");
        for (Imovel imv : gerenciador.listar()) {
            System.out.println(imv.getNome() + " | R$" + imv.getPreco() + " | Aluguel Base: R$" + imv.getAluguelBase() + " | Atual: R$" + imv.getAluguelAtual());
        }

        System.out.println("\n--- Teste 5: Editando Imovel ---");
        gerenciador.editar("Rua das Flores", "Rua das Flores Renomeada", 350.0, 60.0);
        
        System.out.println("\nApos edicao:");
        for (Imovel imv : gerenciador.listar()) {
            System.out.println(imv.getNome() + " | R$" + imv.getPreco() + " | Aluguel: R$" + imv.getAluguelBase());
        }

        System.out.println("\n--- Teste 6: Removendo Imovel ---");
        gerenciador.remover("Avenida Brasil");
        System.out.println("Quantidade apos remocao: " + gerenciador.getQuantidadeCadastrada());

        System.out.println("\n--- Teste 7: Validacao - Remover Imovel com Dono ---");
        Imovel imovelRestante = gerenciador.buscarPorNome("Rua das Flores Renomeada");
        Jogador j = new Jogador("Carlos", 1000.0, TipoPersonagem.CONSTRUTOR);
        imovelRestante.setDono(j);
        
        gerenciador.remover("Rua das Flores Renomeada");
    }
}
