package estrutura;

public class ListaCircularDupla {

    private NoCasa cabeca;
    private int tamanho;

    public ListaCircularDupla() {
        cabeca = null;
        tamanho = 0;
    }

    public void adicionarCasa(String nome, String tipo) {
        NoCasa novo = new NoCasa(nome, tipo, tamanho);

        if (cabeca == null) {
            cabeca = novo;
            cabeca.setNext(cabeca);
            cabeca.setPrev(cabeca);
        } else {
            NoCasa ultimo = cabeca.getPrev();

            ultimo.setNext(novo);
            novo.setPrev(ultimo);
            novo.setNext(cabeca);
            cabeca.setPrev(novo);
        }

        tamanho++;
    }

    public NoCasa buscarCasa(String nome) {
        if (cabeca == null) {
            return null;
        }

        NoCasa atual = cabeca;

        for (int i = 0; i < tamanho; i++) {
            if (atual.getNome().equalsIgnoreCase(nome)) {
                return atual;
            }
            atual = atual.getNext();
        }

        return null;
    }

    public NoCasa buscarPorPosicao(int posicao) {
        if (cabeca == null) {
            return null;
        }

        NoCasa atual = cabeca;

        for (int i = 0; i < tamanho; i++) {
            if (atual.getPosicao() == posicao) {
                return atual;
            }
            atual = atual.getNext();
        }

        return null;
    }

    public NoCasa avancar(NoCasa atual, int casas) {
        if (atual == null) {
            return null;
        }

        NoCasa resultado = atual;

        for (int i = 0; i < casas; i++) {
            resultado = resultado.getNext();
        }

        return resultado;
    }

    public NoCasa retroceder(NoCasa atual, int casas) {
        if (atual == null) {
            return null;
        }

        NoCasa resultado = atual;

        for (int i = 0; i < casas; i++) {
            resultado = resultado.getPrev();
        }

        return resultado;
    }

    public void mostrarTabuleiro() {
        if (cabeca == null) {
            System.out.println("Tabuleiro vazio.");
            return;
        }

        NoCasa atual = cabeca;

        for (int i = 0; i < tamanho; i++) {
            System.out.print(atual.getTipo() + " (" + atual.getNome() + ")");

            if (i < tamanho - 1) {
                System.out.print(" -> ");
            }

            atual = atual.getNext();
        }

        System.out.println(" -> [volta ao INICIO]");
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public NoCasa getCabeca() {
        return cabeca;
    }

    public int getTamanho() {
        return tamanho;
    }
}
