package estrutura;

import model.RegistroRodada;

public class NoFila {

    private RegistroRodada registro;
    private NoFila proximo;

    public NoFila(RegistroRodada registro) {
        this.registro = registro;
        this.proximo = null;
    }

    public RegistroRodada getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroRodada registro) {
        this.registro = registro;
    }

    public NoFila getProximo() {
        return proximo;
    }

    public void setProximo(NoFila proximo) {
        this.proximo = proximo;
    }
}
