package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {

    private String nome;
    private double saldo;
    private int posicaoAtual;
    private List<Imovel> listaImoveis;
    private TipoPersonagem tipoPersonagem;
    private int voltasCompletas;
    private boolean preso;
    private int turnosPreso;
    private boolean usouSaidaPrisaoGratis;

    public Jogador(String nome, double saldoInicial, TipoPersonagem tipoPersonagem) {
        this.nome = nome;
        this.saldo = saldoInicial;
        this.posicaoAtual = 0;
        this.listaImoveis = new ArrayList<>();
        this.tipoPersonagem = tipoPersonagem;
        this.voltasCompletas = 0;
        this.preso = false;
        this.turnosPreso = 0;
        this.usouSaidaPrisaoGratis = false;
    }

    public double calcularPatrimonio() {
        double total = saldo;

        for (int i = 0; i < listaImoveis.size(); i++) {
            total += listaImoveis.get(i).getPreco();
        }

        return total;
    }

    public void adicionarImovel(Imovel imovel) {
        listaImoveis.add(imovel);
        imovel.setDono(this);
    }

    public void removerImovel(Imovel imovel) {
        listaImoveis.remove(imovel);
        imovel.setDono(null);
    }

    public void receberDinheiro(double valor) {
        saldo += valor;
    }

    public void pagarDinheiro(double valor) {
        saldo -= valor;
    }

    // ESPECULADOR recebe 20% a mais no salario
    public double calcularSalario(double salarioBase) {
        if (tipoPersonagem == TipoPersonagem.ESPECULADOR) {
            return salarioBase * 1.20;
        }
        return salarioBase;
    }

    // ESPECULADOR paga 10% a mais de imposto
    public double calcularImposto(double valorImposto) {
        if (tipoPersonagem == TipoPersonagem.ESPECULADOR) {
            return valorImposto * 1.10;
        }
        return valorImposto;
    }

    // NEGOCIANTE paga 10% menos de aluguel
    public double calcularAluguel(double valorAluguel) {
        if (tipoPersonagem == TipoPersonagem.NEGOCIANTE) {
            return valorAluguel * 0.90;
        }
        return valorAluguel;
    }

    // ADVOGADO pode sair da prisao sem pagar uma unica vez
    public boolean podeSairDaPrisaoGratis() {
        if (tipoPersonagem == TipoPersonagem.ADVOGADO && !usouSaidaPrisaoGratis) {
            return true;
        }
        return false;
    }

    public void usarSaidaPrisaoGratis() {
        usouSaidaPrisaoGratis = true;
    }

    // CONSTRUTOR aumenta aluguel dos imoveis em 15%
    public double calcularAluguelComoProprietario(double aluguelBase) {
        if (tipoPersonagem == TipoPersonagem.CONSTRUTOR) {
            return aluguelBase * 1.15;
        }
        return aluguelBase;
    }

    public boolean isAtivo() {
        return saldo > 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public List<Imovel> getListaImoveis() {
        return listaImoveis;
    }

    public TipoPersonagem getTipoPersonagem() {
        return tipoPersonagem;
    }

    public void setTipoPersonagem(TipoPersonagem tipoPersonagem) {
        this.tipoPersonagem = tipoPersonagem;
    }

    public int getVoltasCompletas() {
        return voltasCompletas;
    }

    public void setVoltasCompletas(int voltasCompletas) {
        this.voltasCompletas = voltasCompletas;
    }

    public void incrementarVoltas() {
        voltasCompletas++;
    }

    public boolean isPreso() {
        return preso;
    }

    public void setPreso(boolean preso) {
        this.preso = preso;
    }

    public int getTurnosPreso() {
        return turnosPreso;
    }

    public void setTurnosPreso(int turnosPreso) {
        this.turnosPreso = turnosPreso;
    }

    public String toString() {
        return nome + " [" + tipoPersonagem.getDescricao() + "] - R$" + String.format("%.2f", saldo);
    }
}
