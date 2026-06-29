package model;

import estrutura.ListaCircularDupla;
import estrutura.NoCasa;

public class Tabuleiro {

    private ListaCircularDupla casas;

    public Tabuleiro() {
        casas = new ListaCircularDupla();
    }

    public void inicializar() {
        casas.adicionarCasa("Inicio", "INICIO");
        casas.adicionarCasa("Rua das Flores", "IMOVEL");
        casas.adicionarCasa("Avenida Brasil", "IMOVEL");
        casas.adicionarCasa("Imposto de Renda", "IMPOSTO");
        casas.adicionarCasa("Praca Central", "IMOVEL");
        casas.adicionarCasa("Sorte ou Reves", "SORTE_REVES");
        casas.adicionarCasa("Rua XV", "IMOVEL");
        casas.adicionarCasa("Leilao Municipal", "LEILAO");
        casas.adicionarCasa("Rua Beira Mar", "IMOVEL");
        casas.adicionarCasa("Restituicao IR", "RESTITUICAO");
        casas.adicionarCasa("Avenida Atlantica", "IMOVEL");
        casas.adicionarCasa("Prisao", "PRISAO");
        casas.adicionarCasa("Rua do Comercio", "IMOVEL");
        casas.adicionarCasa("Sorte ou Reves", "SORTE_REVES");
        casas.adicionarCasa("Avenida Paulista", "IMOVEL");
        casas.adicionarCasa("Imposto Predial", "IMPOSTO");
        casas.adicionarCasa("Rua Augusta", "IMOVEL");
        casas.adicionarCasa("Leilao Federal", "LEILAO");
        casas.adicionarCasa("Rua Oscar Freire", "IMOVEL");
        casas.adicionarCasa("Restituicao FGTS", "RESTITUICAO");
        casas.adicionarCasa("Avenida Copacabana", "IMOVEL");
        casas.adicionarCasa("Sorte ou Reves", "SORTE_REVES");
        casas.adicionarCasa("Rua da Consolacao", "IMOVEL");
        casas.adicionarCasa("Avenida Ipiranga", "IMOVEL");
    }

    public void mostrar() {
        casas.mostrarTabuleiro();
    }

    public void mostrarDetalhado() {
        NoCasa atual = casas.getCabeca();

        if (atual == null) {
            System.out.println("Tabuleiro vazio.");
            return;
        }

        System.out.println("\n===================================================");
        System.out.println("   TABULEIRO - LISTA DUPLAMENTE LIGADA CIRCULAR");
        System.out.println("===================================================");

        NoCasa inicio = atual;
        int numero = 1;

        do {
            System.out.printf("%2d - %-15s (%s)%n", numero, atual.getTipo(), atual.getNome());
            atual = atual.getNext();
            numero++;
        } while (atual != inicio);

        System.out.println("--------------------------------------------------");
        System.out.println("A proxima casa apos a ultima e: " + inicio.getNome() + ".");
        System.out.println("===================================================");
    }

    public NoCasa getCasaPorPosicao(int posicao) {
        return casas.buscarPorPosicao(posicao);
    }

    public NoCasa getCasaPorNome(String nome) {
        return casas.buscarCasa(nome);
    }

    public ListaCircularDupla getCasas() {
        return casas;
    }

    public int getTotalCasas() {
        return casas.getTamanho();
    }
}
