package model;

import estrutura.FilaPrisao;
import estrutura.NoFilaPrisao;

public class Prisao {

    private FilaPrisao fila;
    private double valorFianca;
    private int maxTentativas;

    public Prisao() {
        this.fila = new FilaPrisao();
        this.valorFianca = 50.0;
        this.maxTentativas = 3;
    }

    public void entrarNaPrisao(Jogador jogador) {
        if (!estaPreso(jogador)) {
            jogador.setPreso(true);
            fila.enfileirar(jogador);
            System.out.println(">> ALERTA: " + jogador.getNome() + " foi preso!");
        }
    }

    public void tentarSair(Jogador jogador, boolean pagouFianca, int dado1, int dado2) {
        NoFilaPrisao noPreso = fila.buscar(jogador);

        if (noPreso == null) {
            System.out.println(">> Erro: Jogador " + jogador.getNome() + " nao esta na prisao.");
            return;
        }

        System.out.println("--- Tentativa de Saida da Prisao: " + jogador.getNome() + " ---");

        // Regra do Advogado
        if (jogador.getTipoPersonagem() == TipoPersonagem.ADVOGADO && jogador.podeSairDaPrisaoGratis()) {
            jogador.usarSaidaPrisaoGratis();
            liberarJogador(jogador);
            System.out.println("Habilidade de ADVOGADO ativada! Saida gratuita utilizada.");
            return;
        }

        // Regra de Fiança
        if (pagouFianca) {
            if (jogador.getSaldo() >= valorFianca) {
                jogador.pagarDinheiro(valorFianca);
                liberarJogador(jogador);
                System.out.println("Fianca de R$" + valorFianca + " paga com sucesso! Liberdade concedida.");
            } else {
                System.out.println("Saldo insuficiente para pagar fianca. Saldo atual: R$" + String.format("%.2f", jogador.getSaldo()));
                // Nao conta como tentativa, apenas falhou em pagar
            }
            return;
        }

        // Regra de Dados Iguais
        noPreso.incrementarTentativas();
        System.out.println("Tentativa " + noPreso.getTentativasSair() + " de " + maxTentativas);
        System.out.println("Dados lancados: " + dado1 + " e " + dado2);

        if (dado1 == dado2) {
            liberarJogador(jogador);
            System.out.println("Dados iguais! Liberdade concedida.");
        } else {
            System.out.println("Dados diferentes. Continua preso.");

            // Regra de saida apos 3 tentativas falhas
            if (noPreso.getTentativasSair() >= maxTentativas) {
                System.out.println("Limite de tentativas atingido. Cumprimeto da pena encerrado.");
                liberarJogador(jogador);
            }
        }
    }

    private void liberarJogador(Jogador jogador) {
        jogador.setPreso(false);
        fila.remover(jogador);
        System.out.println(">> " + jogador.getNome() + " saiu da prisao!");
    }

    public void mostrarFila() {
        System.out.println("=== FILA DA PRISAO ===");
        if (fila.estaVazia()) {
            System.out.println("A prisao esta vazia.");
            return;
        }

        NoFilaPrisao atual = fila.getFrente();
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + "º | " + atual.getJogador().getNome() 
                + " | Tentativas: " + atual.getTentativasSair() + "/" + maxTentativas);
            atual = atual.getProximo();
            posicao++;
        }
    }

    public boolean estaPreso(Jogador jogador) {
        return fila.buscar(jogador) != null;
    }

    public FilaPrisao getFila() {
        return fila;
    }

    public double getValorFianca() {
        return valorFianca;
    }
}
