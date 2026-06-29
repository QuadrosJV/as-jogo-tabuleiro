import model.Prisao;
import model.Jogador;
import model.TipoPersonagem;

public class TestePrisao {

    public static void main(String[] args) {

        System.out.println("=== TESTE DO SISTEMA DE PRISAO ===\n");

        Prisao prisao = new Prisao();
        
        Jogador carlos = new Jogador("Carlos", 1500.0, TipoPersonagem.CONSTRUTOR);
        Jogador maria = new Jogador("Maria", 1500.0, TipoPersonagem.ESPECULADOR);
        Jogador pedro = new Jogador("Pedro", 1500.0, TipoPersonagem.ADVOGADO);
        Jogador ana = new Jogador("Ana", 20.0, TipoPersonagem.NEGOCIANTE); // Sem dinheiro para fiança

        System.out.println("--- Colocando jogadores na prisao ---");
        prisao.entrarNaPrisao(carlos);
        prisao.entrarNaPrisao(maria);
        prisao.entrarNaPrisao(pedro);
        prisao.entrarNaPrisao(ana);
        
        System.out.println();
        prisao.mostrarFila();
        
        System.out.println("\n--- Teste 1: Pagando Fianca (Maria) ---");
        // Maria tenta sair pagando fiança
        prisao.tentarSair(maria, true, 0, 0);
        
        System.out.println("\n--- Teste 2: Tentativa sem dinheiro (Ana) ---");
        // Ana tenta sair pagando fiança mas não tem dinheiro
        prisao.tentarSair(ana, true, 0, 0);
        
        System.out.println("\n--- Teste 3: Habilidade do Advogado (Pedro) ---");
        // Pedro usa a saída grátis do Advogado automaticamente (tenta rolar dados, mas a habilidade ativa antes)
        prisao.tentarSair(pedro, false, 2, 4);
        
        System.out.println("\n--- Teste 4: Tirando Dados Iguais (Carlos) ---");
        // Carlos tenta rolando dados e consegue iguais
        prisao.tentarSair(carlos, false, 6, 6);
        
        System.out.println("\n--- Fila apos as tentativas iniciais ---");
        prisao.mostrarFila();
        
        System.out.println("\n--- Teste 5: Cumprindo pena ate o fim (Ana) ---");
        // Ana tenta 3 vezes com dados diferentes
        prisao.tentarSair(ana, false, 1, 2);
        prisao.tentarSair(ana, false, 3, 5);
        prisao.tentarSair(ana, false, 2, 6);
        
        System.out.println("\n--- Fila Final ---");
        prisao.mostrarFila();
    }
}
