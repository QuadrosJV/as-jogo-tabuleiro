package estrutura;

import model.RegistroRodada;

public class FilaHistorico {

    private NoFila frente;
    private NoFila tras;
    private int tamanho;
    private int capacidade;

    public FilaHistorico(int capacidade) {
        this.frente = null;
        this.tras = null;
        this.tamanho = 0;
        this.capacidade = capacidade;
    }

    public FilaHistorico() {
        this(20);
    }

    public void enfileirar(RegistroRodada registro) {
        NoFila novo = new NoFila(registro);

        if (tamanho >= capacidade) {
            desenfileirar();
        }

        if (estaVazia()) {
            frente = novo;
            tras = novo;
        } else {
            tras.setProximo(novo);
            tras = novo;
        }

        tamanho++;
    }

    public RegistroRodada desenfileirar() {
        if (estaVazia()) {
            return null;
        }

        RegistroRodada registro = frente.getRegistro();
        frente = frente.getProximo();
        tamanho--;

        if (estaVazia()) {
            tras = null;
        }

        return registro;
    }

    public void mostrarHistorico() {
        if (estaVazia()) {
            System.out.println("Nenhuma rodada registrada.");
            return;
        }

        String linha = "+--------+------------------+-------------+----------------------+------------------------+";

        System.out.println(linha);
        System.out.printf("| %-6s | %-16s | %-11s | %-20s | %-22s |%n",
                "Rodada", "Jogador", "Dados", "Casa Destino", "Efeito");
        System.out.println(linha);

        NoFila atual = frente;

        while (atual != null) {
            RegistroRodada r = atual.getRegistro();

            System.out.printf("| %-6d | %-16s | %-11s | %-20s | %-22s |%n",
                    r.getNumeroRodada(),
                    cortarTexto(r.getNomeJogador(), 16),
                    r.getDadosFormatado(),
                    cortarTexto(r.getCasaDestino(), 20),
                    cortarTexto(r.getEfeitoAplicado(), 22));

            atual = atual.getProximo();
        }

        System.out.println(linha);
        System.out.println("Total de registros: " + tamanho + " / " + capacidade);
    }

    private String cortarTexto(String texto, int max) {
        if (texto.length() > max) {
            return texto.substring(0, max - 2) + "..";
        }
        return texto;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public NoFila getFrente() {
        return frente;
    }

    public NoFila getTras() {
        return tras;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
