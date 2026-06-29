package model;

public class RegistroRodada {

    private int numeroRodada;
    private String nomeJogador;
    private int[] dadosLancados;
    private String casaDestino;
    private String efeitoAplicado;

    public RegistroRodada(int numeroRodada, String nomeJogador, int[] dadosLancados,
                          String casaDestino, String efeitoAplicado) {
        this.numeroRodada = numeroRodada;
        this.nomeJogador = nomeJogador;
        this.dadosLancados = dadosLancados;
        this.casaDestino = casaDestino;
        this.efeitoAplicado = efeitoAplicado;
    }

    public int getNumeroRodada() {
        return numeroRodada;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public int[] getDadosLancados() {
        return dadosLancados;
    }

    public String getDadosFormatado() {
        return dadosLancados[0] + " + " + dadosLancados[1] + " = " + (dadosLancados[0] + dadosLancados[1]);
    }

    public String getCasaDestino() {
        return casaDestino;
    }

    public String getEfeitoAplicado() {
        return efeitoAplicado;
    }

    public String toString() {
        return "Rodada " + numeroRodada + " | " + nomeJogador
                + " | Dados: " + getDadosFormatado()
                + " | Casa: " + casaDestino
                + " | Efeito: " + efeitoAplicado;
    }
}
