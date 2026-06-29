package estrutura;

public class NoCasa {

    private String nome;
    private String tipo;
    private int posicao;
    private NoCasa next;
    private NoCasa prev;

    public NoCasa(String nome, String tipo, int posicao) {
        this.nome = nome;
        this.tipo = tipo;
        this.posicao = posicao;
        this.next = null;
        this.prev = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public NoCasa getNext() {
        return next;
    }

    public void setNext(NoCasa next) {
        this.next = next;
    }

    public NoCasa getPrev() {
        return prev;
    }

    public void setPrev(NoCasa prev) {
        this.prev = prev;
    }

    public String toString() {
        return tipo + " (" + nome + ")";
    }
}
