package model;

public class Configuracao {

    private int quantidadeJogadores;
    private double saldoInicial;
    private int maxRodadas;
    private int totalCasas;

    public Configuracao() {
        quantidadeJogadores = 2;
        saldoInicial = 1500.0;
        maxRodadas = 50;
        totalCasas = 24;
    }

    public int getQuantidadeJogadores() {
        return quantidadeJogadores;
    }

    public void setQuantidadeJogadores(int quantidadeJogadores) {
        this.quantidadeJogadores = quantidadeJogadores;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public int getMaxRodadas() {
        return maxRodadas;
    }

    public void setMaxRodadas(int maxRodadas) {
        this.maxRodadas = maxRodadas;
    }

    public int getTotalCasas() {
        return totalCasas;
    }

    public void setTotalCasas(int totalCasas) {
        this.totalCasas = totalCasas;
    }
}
