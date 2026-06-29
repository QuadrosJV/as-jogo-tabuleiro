package estrutura;

import model.Jogador;

public class NoFilaPrisao {

    private Jogador jogador;
    private int tentativasSair;
    private NoFilaPrisao proximo;

    public NoFilaPrisao(Jogador jogador) {
        this.jogador = jogador;
        this.tentativasSair = 0;
        this.proximo = null;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getTentativasSair() {
        return tentativasSair;
    }

    public void incrementarTentativas() {
        this.tentativasSair++;
    }

    public NoFilaPrisao getProximo() {
        return proximo;
    }

    public void setProximo(NoFilaPrisao proximo) {
        this.proximo = proximo;
    }
}
