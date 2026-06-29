import model.Jogador;
import model.Imovel;
import model.TipoPersonagem;

public class TesteJogador {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA CLASSE JOGADOR ===\n");

        Jogador especulador = new Jogador("Carlos", 1500.0, TipoPersonagem.ESPECULADOR);
        Jogador negociante = new Jogador("Maria", 1500.0, TipoPersonagem.NEGOCIANTE);
        Jogador advogado = new Jogador("Pedro", 1500.0, TipoPersonagem.ADVOGADO);
        Jogador construtor = new Jogador("Ana", 1500.0, TipoPersonagem.CONSTRUTOR);

        System.out.println("--- Jogadores criados ---");
        System.out.println(especulador);
        System.out.println(negociante);
        System.out.println(advogado);
        System.out.println(construtor);

        System.out.println("\n--- Teste: receber e pagar dinheiro ---");
        especulador.receberDinheiro(500);
        System.out.println(especulador.getNome() + " recebeu R$500 -> Saldo: R$" + String.format("%.2f", especulador.getSaldo()));
        especulador.pagarDinheiro(200);
        System.out.println(especulador.getNome() + " pagou R$200 -> Saldo: R$" + String.format("%.2f", especulador.getSaldo()));

        System.out.println("\n--- Teste: adicionar e remover imovel ---");
        Imovel casa1 = new Imovel("Rua das Flores", 1, 300.0, 50.0, "Verde");
        Imovel casa2 = new Imovel("Avenida Brasil", 2, 400.0, 70.0, "Verde");
        construtor.adicionarImovel(casa1);
        construtor.adicionarImovel(casa2);
        System.out.println(construtor.getNome() + " tem " + construtor.getListaImoveis().size() + " imoveis");
        System.out.println("Patrimonio: R$" + String.format("%.2f", construtor.calcularPatrimonio()));
        construtor.removerImovel(casa1);
        System.out.println("Removeu " + casa1.getNome() + " -> " + construtor.getListaImoveis().size() + " imovel(is)");
        System.out.println("Patrimonio: R$" + String.format("%.2f", construtor.calcularPatrimonio()));

        double salarioBase = 200.0;
        double impostoBase = 100.0;
        double aluguelBase = 80.0;

        System.out.println("\n--- Habilidade: ESPECULADOR ---");
        System.out.println("Salario base: R$" + salarioBase);
        System.out.println("Salario do Especulador (+20%): R$" + String.format("%.2f", especulador.calcularSalario(salarioBase)));
        System.out.println("Imposto base: R$" + impostoBase);
        System.out.println("Imposto do Especulador (+10%): R$" + String.format("%.2f", especulador.calcularImposto(impostoBase)));

        System.out.println("\n--- Habilidade: NEGOCIANTE ---");
        System.out.println("Aluguel base: R$" + aluguelBase);
        System.out.println("Aluguel do Negociante (-10%): R$" + String.format("%.2f", negociante.calcularAluguel(aluguelBase)));

        System.out.println("\n--- Habilidade: ADVOGADO ---");
        System.out.println("Pode sair da prisao gratis? " + advogado.podeSairDaPrisaoGratis());
        advogado.usarSaidaPrisaoGratis();
        System.out.println("Usou a saida gratis.");
        System.out.println("Pode sair da prisao gratis de novo? " + advogado.podeSairDaPrisaoGratis());

        System.out.println("\n--- Habilidade: CONSTRUTOR ---");
        System.out.println("Aluguel base do imovel: R$" + aluguelBase);
        System.out.println("Aluguel como proprietario Construtor (+15%): R$" + String.format("%.2f", construtor.calcularAluguelComoProprietario(aluguelBase)));

        System.out.println("\n--- Comparacao de salario entre personagens ---");
        System.out.println("Especulador: R$" + String.format("%.2f", especulador.calcularSalario(salarioBase)));
        System.out.println("Negociante:  R$" + String.format("%.2f", negociante.calcularSalario(salarioBase)));
        System.out.println("Advogado:    R$" + String.format("%.2f", advogado.calcularSalario(salarioBase)));
        System.out.println("Construtor:  R$" + String.format("%.2f", construtor.calcularSalario(salarioBase)));
    }
}
