package menu;

import java.util.Scanner;
import model.Jogo;
import service.JogoService;

public class Menu {

    private Scanner scanner;
    private Jogo jogo;
    private JogoService jogoService;
    private MenuJogadores menuJogadores;
    private MenuImoveis menuImoveis;

    public Menu() {
        scanner = new Scanner(System.in);
        jogo = new Jogo();
        jogoService = new JogoService(jogo);
        menuJogadores = new MenuJogadores(jogo.getConfiguracao().getSaldoInicial());
        menuImoveis = new MenuImoveis();
        
        // Passa o gerenciador de jogadores pro menu de jogadores usar a mesma instancia
        // (Isso exigiria mudar o MenuJogadores, mas para simplificar vamos fazer os menus 
        // acessarem os gerenciadores criados na classe Jogo)
        
        // Vamos recriar os menus passando os gerenciadores corretos.
        // Preciso ajustar MenuJogadores e MenuImoveis para receberem o Gerenciador via construtor
    }

    public void exibir() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===================================");
            System.out.println("     MONOPOLY - JOGO DE TABULEIRO  ");
            System.out.println("===================================");
            System.out.println("1 - Iniciar Novo Jogo");
            System.out.println("2 - Gerenciar Jogadores");
            System.out.println("3 - Gerenciar Imoveis");
            System.out.println("4 - Mostrar Tabuleiro");
            System.out.println("5 - Historico de Rodadas");
            System.out.println("0 - Sair");
            System.out.println("===================================");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    menuJogadores.exibir(jogo.getGerenciadorJogadores());
                    break;
                case 3:
                    menuImoveis.exibir(jogo.getGerenciadorImoveis());
                    break;
                case 4:
                    mostrarTabuleiro();
                    break;
                case 5:
                    mostrarHistorico();
                    break;
                case 0:
                    System.out.println("Saindo do jogo...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
    }

    private void iniciarJogo() {
        if (!jogo.getGerenciadorJogadores().podeIniciarPartida()) {
            System.out.println("Para iniciar o jogo, acesse 'Gerenciar Jogadores' e cadastre entre 2 e 6 jogadores.");
            return;
        }

        jogoService.iniciarPartida();
    }

    private void mostrarTabuleiro() {
        jogo.getTabuleiro().mostrarDetalhado();
    }

    private void mostrarHistorico() {
        jogo.getHistorico().exibir();
    }
}
