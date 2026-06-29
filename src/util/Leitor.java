package util;

public class Leitor {

    private java.util.Scanner scanner;

    public Leitor() {
        scanner = new java.util.Scanner(System.in);
    }

    public int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public double lerDecimal(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextDouble();
    }

    public void limparBuffer() {
        scanner.nextLine();
    }
}
