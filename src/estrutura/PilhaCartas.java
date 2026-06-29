package estrutura;

import model.Carta;

public class PilhaCartas {

    private NoPilha topo;
    private int tamanho;

    public PilhaCartas() {
        topo = null;
        tamanho = 0;
    }

    public void empilhar(Carta carta) {
        NoPilha novo = new NoPilha(carta);
        novo.setAbaixo(topo);
        topo = novo;
        tamanho++;
    }

    public Carta desempilhar() {
        if (estaVazia()) {
            return null;
        }

        Carta carta = topo.getCarta();
        topo = topo.getAbaixo();
        tamanho--;
        return carta;
    }

    public Carta espiar() {
        if (topo != null) {
            return topo.getCarta();
        }
        return null;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void limpar() {
        topo = null;
        tamanho = 0;
    }

    public NoPilha getTopo() {
        return topo;
    }

    public int getTamanho() {
        return tamanho;
    }
}
