package model;

import service.GerenciadorJogadores;
import service.GerenciadorImoveis;

public class Jogo {

    private GerenciadorJogadores gerenciadorJogadores;
    private GerenciadorImoveis gerenciadorImoveis;
    private Tabuleiro tabuleiro;
    private Baralho baralho;
    private HistoricoRodadas historico;
    private Configuracao configuracao;
    private Prisao prisao;
    private int rodadaAtual;
    private boolean emAndamento;

    public Jogo() {
        gerenciadorJogadores = new GerenciadorJogadores();
        gerenciadorImoveis = new GerenciadorImoveis();
        tabuleiro = new Tabuleiro();
        baralho = new Baralho();
        historico = new HistoricoRodadas(50);
        configuracao = new Configuracao();
        prisao = new Prisao();
        rodadaAtual = 0;
        emAndamento = false;
        
        tabuleiro.inicializar();
    }

    public GerenciadorJogadores getGerenciadorJogadores() {
        return gerenciadorJogadores;
    }

    public GerenciadorImoveis getGerenciadorImoveis() {
        return gerenciadorImoveis;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public HistoricoRodadas getHistorico() {
        return historico;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public Prisao getPrisao() {
        return prisao;
    }

    public int getRodadaAtual() {
        return rodadaAtual;
    }

    public void setRodadaAtual(int rodadaAtual) {
        this.rodadaAtual = rodadaAtual;
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }
}
