package model;

import estrutura.PilhaCartas;
import java.util.Random;

public class Baralho {

    private PilhaCartas pilha;
    private Carta[] todasCartas;
    private int totalCartas;

    public Baralho() {
        pilha = new PilhaCartas();
        totalCartas = 15;
        todasCartas = new Carta[totalCartas];
        criarCartas();
        embaralhar();
    }

    private void criarCartas() {
        todasCartas[0] = new Carta("Voce ganhou na loteria!", TipoCarta.GANHO, 200.0);
        todasCartas[1] = new Carta("Bonus salarial! Parabens!", TipoCarta.GANHO, 150.0);
        todasCartas[2] = new Carta("Encontrou dinheiro no chao!", TipoCarta.GANHO, 50.0);

        todasCartas[3] = new Carta("Multa de transito!", TipoCarta.PERDA, 100.0);
        todasCartas[4] = new Carta("Conta do hospital!", TipoCarta.PERDA, 150.0);
        todasCartas[5] = new Carta("Seu carro quebrou!", TipoCarta.PERDA, 80.0);

        todasCartas[6] = new Carta("Vento a favor! Avance 3 casas.", TipoCarta.AVANCO, 3.0);
        todasCartas[7] = new Carta("Carona VIP! Avance 2 casas.", TipoCarta.AVANCO, 2.0);
        todasCartas[8] = new Carta("Atalho secreto! Avance 5 casas.", TipoCarta.AVANCO, 5.0);

        todasCartas[9] = new Carta("Buraco na estrada! Volte 2 casas.", TipoCarta.RETROCESSO, 2.0);
        todasCartas[10] = new Carta("Desvio obrigatorio! Volte 3 casas.", TipoCarta.RETROCESSO, 3.0);
        todasCartas[11] = new Carta("GPS quebrou! Volte 1 casa.", TipoCarta.RETROCESSO, 1.0);

        todasCartas[12] = new Carta("Flagrado em irregularidade! Va para a prisao.", TipoCarta.PRISAO, 0.0);
        todasCartas[13] = new Carta("Mandado de prisao! Va direto para a cadeia.", TipoCarta.PRISAO, 0.0);
        todasCartas[14] = new Carta("Divida com a receita! Pague ou va preso.", TipoCarta.PERDA, 200.0);
    }

    public void embaralhar() {
        pilha.limpar();

        // copia o array para embaralhar
        Carta[] copia = new Carta[totalCartas];
        for (int i = 0; i < totalCartas; i++) {
            copia[i] = todasCartas[i];
        }

        // embaralha usando Fisher-Yates
        Random random = new Random();
        for (int i = totalCartas - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Carta temp = copia[i];
            copia[i] = copia[j];
            copia[j] = temp;
        }

        // empilha as cartas embaralhadas
        for (int i = 0; i < totalCartas; i++) {
            pilha.empilhar(copia[i]);
        }
    }

    public Carta sacarCarta() {
        if (pilha.estaVazia()) {
            System.out.println(">> Baralho acabou! Recriando e embaralhando...");
            embaralhar();
        }

        return pilha.desempilhar();
    }

    public boolean estaVazio() {
        return pilha.estaVazia();
    }

    public int cartasRestantes() {
        return pilha.getTamanho();
    }

    public PilhaCartas getPilha() {
        return pilha;
    }
}
