import model.Jogador;
import model.TipoPersonagem;
import service.GerenciadorJogadores;

public class TesteCRUDJogadores {

    public static void main(String[] args) {

        System.out.println("=== TESTE DO CRUD DE JOGADORES ===\n");

        GerenciadorJogadores gerenciador = new GerenciadorJogadores();

        System.out.println("--- Teste 1: Status Inicial ---");
        System.out.println("Pode iniciar partida? " + gerenciador.podeIniciarPartida());

        System.out.println("\n--- Teste 2: Cadastrando Jogadores ---");
        gerenciador.cadastrar(new Jogador("Carlos", 1500.0, TipoPersonagem.CONSTRUTOR));
        System.out.println("Pode iniciar com 1 jogador? " + gerenciador.podeIniciarPartida());
        
        gerenciador.cadastrar(new Jogador("Maria", 1500.0, TipoPersonagem.ESPECULADOR));
        System.out.println("Pode iniciar com 2 jogadores? " + gerenciador.podeIniciarPartida());
        
        gerenciador.cadastrar(new Jogador("Pedro", 1500.0, TipoPersonagem.ADVOGADO));
        
        System.out.println("\n--- Teste 3: Validacao - Nome Duplicado ---");
        gerenciador.cadastrar(new Jogador("Carlos", 1500.0, TipoPersonagem.NEGOCIANTE));

        System.out.println("\n--- Teste 4: Listando Jogadores ---");
        for (Jogador j : gerenciador.listar()) {
            System.out.println(j.getNome() + " | Classe: " + j.getTipoPersonagem().getDescricao());
        }

        System.out.println("\n--- Teste 5: Editando Jogador ---");
        gerenciador.editar("Carlos", "Carlos Eduardo", TipoPersonagem.NEGOCIANTE);
        
        System.out.println("\nApos edicao:");
        for (Jogador j : gerenciador.listar()) {
            System.out.println(j.getNome() + " | Classe: " + j.getTipoPersonagem().getDescricao());
        }

        System.out.println("\n--- Teste 6: Removendo Jogador ---");
        gerenciador.remover("Maria");
        System.out.println("Quantidade apos remocao: " + gerenciador.getQuantidadeCadastrada());
        System.out.println("Pode iniciar com " + gerenciador.getQuantidadeCadastrada() + " jogadores? " + gerenciador.podeIniciarPartida());
        
        System.out.println("\n--- Teste 7: Limite Maximo ---");
        gerenciador.cadastrar(new Jogador("J1", 1500.0, TipoPersonagem.CONSTRUTOR));
        gerenciador.cadastrar(new Jogador("J2", 1500.0, TipoPersonagem.CONSTRUTOR));
        gerenciador.cadastrar(new Jogador("J3", 1500.0, TipoPersonagem.CONSTRUTOR));
        gerenciador.cadastrar(new Jogador("J4", 1500.0, TipoPersonagem.CONSTRUTOR));
        gerenciador.cadastrar(new Jogador("J5", 1500.0, TipoPersonagem.CONSTRUTOR)); // Deve bater o limite
        System.out.println("Quantidade final: " + gerenciador.getQuantidadeCadastrada());
    }
}
