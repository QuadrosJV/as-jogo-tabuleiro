package model;

public class Imovel {

    private String nome;
    private int posicaoNoTabuleiro;
    private double preco;
    private double aluguel;
    private String grupo;
    private Jogador dono;
    private int nivelMelhoria;
    private double multiplicador;

    public Imovel(String nome, int posicaoNoTabuleiro, double preco, double aluguel, String grupo) {
        this.nome = nome;
        this.posicaoNoTabuleiro = posicaoNoTabuleiro;
        this.preco = preco;
        this.aluguel = aluguel;
        this.grupo = grupo;
        this.dono = null;
        this.nivelMelhoria = 0;
        this.multiplicador = 1.0;
    }

    public void visitar() {
        if (multiplicador < 2.0) {
            multiplicador += 0.1;
            // Corrigir imprecisao de ponto flutuante
            multiplicador = Math.round(multiplicador * 10.0) / 10.0;
            
            if (multiplicador > 2.0) {
                multiplicador = 2.0;
            }
        }
    }

    public double getAluguelAtual() {
        return aluguel * multiplicador;
    }

    public boolean temDono() {
        return dono != null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicaoNoTabuleiro() {
        return posicaoNoTabuleiro;
    }

    public void setPosicaoNoTabuleiro(int posicaoNoTabuleiro) {
        this.posicaoNoTabuleiro = posicaoNoTabuleiro;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getAluguelBase() {
        return aluguel;
    }

    public void setAluguelBase(double aluguel) {
        this.aluguel = aluguel;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public int getNivelMelhoria() {
        return nivelMelhoria;
    }

    public void setNivelMelhoria(int nivelMelhoria) {
        this.nivelMelhoria = nivelMelhoria;
    }
}
