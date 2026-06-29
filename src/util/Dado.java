package util;

import java.util.Random;

public class Dado {

    private Random random;

    public Dado() {
        random = new Random();
    }

    public int jogar() {
        return random.nextInt(6) + 1;
    }

    public int[] jogarDois() {
        int[] dados = new int[2];
        dados[0] = random.nextInt(6) + 1;
        dados[1] = random.nextInt(6) + 1;
        return dados;
    }

    public int soma(int[] dados) {
        return dados[0] + dados[1];
    }

    public boolean saoIguais(int[] dados) {
        return dados[0] == dados[1];
    }
}
