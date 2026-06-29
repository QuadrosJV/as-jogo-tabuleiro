package estrutura;

import model.Jogador;

public class FilaPrisao {

    private NoFilaPrisao frente;
    private NoFilaPrisao tras;
    private int tamanho;

    public FilaPrisao() {
        this.frente = null;
        this.tras = null;
        this.tamanho = 0;
    }

    public void enfileirar(Jogador jogador) {
        NoFilaPrisao novo = new NoFilaPrisao(jogador);

        if (estaVazia()) {
            frente = novo;
            tras = novo;
        } else {
            tras.setProximo(novo);
            tras = novo;
        }

        tamanho++;
    }

    public Jogador desenfileirar() {
        if (estaVazia()) {
            return null;
        }

        Jogador jogador = frente.getJogador();
        frente = frente.getProximo();
        tamanho--;

        if (estaVazia()) {
            tras = null;
        }

        return jogador;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public NoFilaPrisao getFrente() {
        return frente;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void remover(Jogador jogador) {
        if (estaVazia()) {
            return;
        }

        // Se for o primeiro
        if (frente.getJogador().equals(jogador)) {
            desenfileirar();
            return;
        }

        NoFilaPrisao atual = frente;
        while (atual.getProximo() != null) {
            if (atual.getProximo().getJogador().equals(jogador)) {
                atual.setProximo(atual.getProximo().getProximo());
                tamanho--;
                
                // Se removeu o ultimo, ajusta o tras
                if (atual.getProximo() == null) {
                    tras = atual;
                }
                return;
            }
            atual = atual.getProximo();
        }
    }

    public NoFilaPrisao buscar(Jogador jogador) {
        NoFilaPrisao atual = frente;
        while (atual != null) {
            if (atual.getJogador().equals(jogador)) {
                return atual;
            }
            atual = atual.getProximo();
        }
        return null;
    }
}
