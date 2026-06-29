import java.io.ByteArrayInputStream;
import model.Jogo;
import model.Jogador;
import model.TipoPersonagem;
import service.JogoService;

public class TesteJogoCompleto {

    public static void main(String[] args) {
        System.out.println("=== TESTE AUTOMATIZADO DO JOGO COMPLETO ===");

        // Simulando entradas do usuario:
        // Turnos irao fazer perguntas como "Deseja comprar? (S/N)", "Pagar fianca? (S/N)" e "Pressione ENTER para proxima"
        // Vamos preencher o buffer com respostas 'S' e 'N' para simular decisoes.
        String entradasSimuladas = "";
        for(int i=0; i<100; i++) {
            entradasSimuladas += "S\n160\n0\n";
        }
        
        System.setIn(new ByteArrayInputStream(entradasSimuladas.getBytes()));

        // Instancia o jogo
        Jogo jogo = new Jogo();
        
        // Testa o cadastro de jogadores (GerenciadorJogadores)
        System.out.println("\n--- Cadastrando Jogadores ---");
        jogo.getGerenciadorJogadores().cadastrar(new Jogador("Carlos", 500.0, TipoPersonagem.ESPECULADOR));
        jogo.getGerenciadorJogadores().cadastrar(new Jogador("Maria", 500.0, TipoPersonagem.NEGOCIANTE));
        jogo.getGerenciadorJogadores().cadastrar(new Jogador("Pedro", 500.0, TipoPersonagem.ADVOGADO));
        
        System.out.println("Quantidade cadastrada: " + jogo.getGerenciadorJogadores().getQuantidadeCadastrada());
        System.out.println("Pode Iniciar: " + jogo.getGerenciadorJogadores().podeIniciarPartida());

        // Inicia Service
        JogoService service = new JogoService(jogo);
        
        // Simula o inicio da partida
        jogo.setEmAndamento(true);
        jogo.setRodadaAtual(1);
        
        System.out.println("\n--- Simulando Rodadas ---");
        
        // Rodar 3 rodadas forçadas
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n*** INICIO DA RODADA " + i + " ***");
            for (Jogador j : jogo.getGerenciadorJogadores().listar()) {
                if (j.isAtivo()) {
                    service.processarTurno(j);
                }
            }
        }
        
        System.out.println("\n--- Exibindo Relatorio Final via JogoService ---");
        service.verificarVencedor();

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}
