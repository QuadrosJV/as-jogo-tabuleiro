package estrutura;

import model.Carta;

public class NoPilha {

    private Carta carta;
    private NoPilha abaixo;

    public NoPilha(Carta carta) {
        this.carta = carta;
        this.abaixo = null;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public NoPilha getAbaixo() {
        return abaixo;
    }

    public void setAbaixo(NoPilha abaixo) {
        this.abaixo = abaixo;
    }
}
