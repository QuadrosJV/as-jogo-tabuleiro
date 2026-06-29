package model;

import estrutura.FilaHistorico;

public class HistoricoRodadas {

    private FilaHistorico fila;
    private int totalRodadas;

    public HistoricoRodadas(int capacidade) {
        fila = new FilaHistorico(capacidade);
        totalRodadas = 0;
    }

    public HistoricoRodadas() {
        this(20); // Capacidade padrão
    }

    public void registrar(RegistroRodada registro) {
        fila.enfileirar(registro);
        totalRodadas++;
    }

    public void exibir() {
        System.out.println("\n================ HISTORICO DE RODADAS ================");
        fila.mostrarHistorico();
        System.out.println("Total de rodadas jogadas: " + totalRodadas);
        System.out.println("======================================================");
    }

    public int getTotalRodadas() {
        return totalRodadas;
    }

    public FilaHistorico getFila() {
        return fila;
    }
}
