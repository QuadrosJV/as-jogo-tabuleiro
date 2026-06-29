package model;

public class Carta {

    private String descricao;
    private TipoCarta tipo;
    private double valor;

    public Carta(String descricao, TipoCarta tipo, double valor) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String toString() {
        return "[" + tipo.getDescricao() + "] " + descricao + " (R$" + String.format("%.2f", valor) + ")";
    }
}
