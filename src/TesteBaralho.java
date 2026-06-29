import model.Baralho;
import model.Carta;

public class TesteBaralho {

    public static void main(String[] args) {

        System.out.println("=== TESTE DO SISTEMA DE BARALHO (PILHA) ===\n");

        Baralho baralho = new Baralho();

        System.out.println("Cartas no baralho: " + baralho.cartasRestantes());
        System.out.println();

        System.out.println("--- Sacando todas as 15 cartas ---");
        for (int i = 1; i <= 15; i++) {
            Carta carta = baralho.sacarCarta();
            System.out.println("Carta " + i + ": " + carta);
        }

        System.out.println("\nCartas restantes: " + baralho.cartasRestantes());
        System.out.println("Esta vazio? " + baralho.estaVazio());

        System.out.println("\n--- Sacando mais 3 cartas (deve reconstruir automaticamente) ---");
        for (int i = 1; i <= 3; i++) {
            Carta carta = baralho.sacarCarta();
            System.out.println("Carta " + i + ": " + carta);
        }

        System.out.println("\nCartas restantes apos reconstrucao: " + baralho.cartasRestantes());

        System.out.println("\n--- Teste de empilhar/desempilhar manual ---");
        estrutura.PilhaCartas pilhaTeste = new estrutura.PilhaCartas();
        System.out.println("Pilha vazia? " + pilhaTeste.estaVazia());

        pilhaTeste.empilhar(new Carta("Carta Teste 1", model.TipoCarta.GANHO, 100.0));
        pilhaTeste.empilhar(new Carta("Carta Teste 2", model.TipoCarta.PERDA, 50.0));
        pilhaTeste.empilhar(new Carta("Carta Teste 3", model.TipoCarta.AVANCO, 3.0));

        System.out.println("Tamanho da pilha: " + pilhaTeste.getTamanho());
        System.out.println("Topo: " + pilhaTeste.espiar());

        System.out.println("\nDesempilhando:");
        while (!pilhaTeste.estaVazia()) {
            System.out.println("  " + pilhaTeste.desempilhar());
        }
        System.out.println("Pilha vazia? " + pilhaTeste.estaVazia());
    }
}
