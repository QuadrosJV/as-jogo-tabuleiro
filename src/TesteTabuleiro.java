import estrutura.ListaCircularDupla;
import estrutura.NoCasa;
import model.Tabuleiro;

public class TesteTabuleiro {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA LISTA CIRCULAR DUPLA ===\n");

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.inicializar();

        System.out.println("Total de casas: " + tabuleiro.getTotalCasas());
        System.out.println();

        System.out.println("--- Tabuleiro completo ---");
        tabuleiro.mostrar();
        System.out.println();

        System.out.println("--- Busca por nome ---");
        NoCasa prisao = tabuleiro.getCasaPorNome("Prisao");
        if (prisao != null) {
            System.out.println("Encontrou: " + prisao + " na posicao " + prisao.getPosicao());
        }
        System.out.println();

        System.out.println("--- Busca por posicao ---");
        NoCasa casa5 = tabuleiro.getCasaPorPosicao(5);
        if (casa5 != null) {
            System.out.println("Posicao 5: " + casa5);
        }
        System.out.println();

        System.out.println("--- Teste de navegacao (avancar 3 casas a partir do Inicio) ---");
        NoCasa inicio = tabuleiro.getCasaPorPosicao(0);
        NoCasa destino = tabuleiro.getCasas().avancar(inicio, 3);
        System.out.println("De: " + inicio);
        System.out.println("Avancou 3 -> " + destino);
        System.out.println();

        System.out.println("--- Teste de navegacao (retroceder 2 casas a partir da posicao 5) ---");
        NoCasa volta = tabuleiro.getCasas().retroceder(casa5, 2);
        System.out.println("De: " + casa5);
        System.out.println("Retrocedeu 2 -> " + volta);
        System.out.println();

        System.out.println("--- Teste circular (avancar 24 casas = volta ao inicio) ---");
        NoCasa voltaCompleta = tabuleiro.getCasas().avancar(inicio, 24);
        System.out.println("De: " + inicio);
        System.out.println("Avancou 24 -> " + voltaCompleta);
        System.out.println("Voltou ao inicio? " + (voltaCompleta == inicio));
    }
}
