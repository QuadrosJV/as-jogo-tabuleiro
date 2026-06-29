import model.RegistroRodada;
import model.HistoricoRodadas;

public class TesteFila {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA FILA MANUAL (HISTORICO) ===\n");

        // Criando historico com capacidade de apenas 3 registros para testar remocao
        HistoricoRodadas historico = new HistoricoRodadas(3);

        System.out.println("Adicionando 1ª rodada...");
        historico.registrar(new RegistroRodada(1, "Carlos", new int[]{3, 4}, "Rua XV", "Comprou imovel"));
        
        System.out.println("Adicionando 2ª rodada...");
        historico.registrar(new RegistroRodada(2, "Maria", new int[]{1, 2}, "Prisao", "Foi presa (Carta)"));
        
        System.out.println("Adicionando 3ª rodada...");
        historico.registrar(new RegistroRodada(3, "Pedro", new int[]{5, 5}, "Avenida Brasil", "Pagou aluguel"));

        System.out.println("\n--- Historico Atual (Cheio) ---");
        historico.exibir();

        System.out.println("\nAdicionando 4ª rodada (deve remover a 1ª automaticamente)...");
        historico.registrar(new RegistroRodada(4, "Carlos", new int[]{2, 1}, "Sorte ou Reves", "Ganhou R$ 50"));

        System.out.println("\n--- Historico Atualizado ---");
        historico.exibir();

        System.out.println("\nAdicionando 5ª rodada (deve remover a 2ª)...");
        historico.registrar(new RegistroRodada(5, "Maria", new int[]{6, 6}, "Leilao Municipal", "Comprou por 100"));

        System.out.println("\n--- Historico Final ---");
        historico.exibir();
        
        System.out.println("\nTestando desenfileirar manual...");
        RegistroRodada r1 = historico.getFila().desenfileirar();
        System.out.println("Removeu: Rodada " + r1.getNumeroRodada());
        
        RegistroRodada r2 = historico.getFila().desenfileirar();
        System.out.println("Removeu: Rodada " + r2.getNumeroRodada());
        
        System.out.println("Historico vazio apos remocoes? " + historico.getFila().estaVazia());
        System.out.println("Tamanho atual: " + historico.getFila().getTamanho());
    }
}
