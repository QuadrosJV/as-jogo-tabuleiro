package service;

import java.util.ArrayList;
import java.util.List;
import model.Imovel;

public class GerenciadorImoveis {

    private List<Imovel> imoveis;
    private final int MAX_IMOVEIS = 40;

    public GerenciadorImoveis() {
        this.imoveis = new ArrayList<>();
    }

    public boolean cadastrar(Imovel imovel) {
        if (imoveis.size() >= MAX_IMOVEIS) {
            System.out.println("Erro: Limite maximo de " + MAX_IMOVEIS + " imoveis atingido.");
            return false;
        }
        
        if (buscarPorNome(imovel.getNome()) != null) {
            System.out.println("Erro: Ja existe um imovel cadastrado com o nome '" + imovel.getNome() + "'.");
            return false;
        }

        if (imovel.getPreco() <= 0) {
            System.out.println("Erro: O valor de compra deve ser maior que zero.");
            return false;
        }

        if (imovel.getAluguelBase() <= 0) {
            System.out.println("Erro: O valor do aluguel base deve ser maior que zero.");
            return false;
        }

        imoveis.add(imovel);
        return true;
    }

    public List<Imovel> listar() {
        return imoveis;
    }

    public boolean editar(String nomeAntigo, String novoNome, double novoPreco, double novoAluguel) {
        Imovel imovel = buscarPorNome(nomeAntigo);
        
        if (imovel == null) {
            System.out.println("Erro: Imovel '" + nomeAntigo + "' nao encontrado.");
            return false;
        }

        if (!nomeAntigo.equalsIgnoreCase(novoNome) && buscarPorNome(novoNome) != null) {
            System.out.println("Erro: Ja existe outro imovel cadastrado com o nome '" + novoNome + "'.");
            return false;
        }

        if (novoPreco <= 0) {
            System.out.println("Erro: O valor de compra deve ser maior que zero.");
            return false;
        }

        if (novoAluguel <= 0) {
            System.out.println("Erro: O valor do aluguel base deve ser maior que zero.");
            return false;
        }

        imovel.setNome(novoNome);
        imovel.setPreco(novoPreco);
        imovel.setAluguelBase(novoAluguel);
        return true;
    }

    public boolean remover(String nome) {
        Imovel imovel = buscarPorNome(nome);
        
        if (imovel == null) {
            System.out.println("Erro: Imovel '" + nome + "' nao encontrado.");
            return false;
        }

        if (imovel.temDono()) {
            System.out.println("Erro: Nao e possivel remover um imovel que ja tem dono.");
            return false;
        }

        imoveis.remove(imovel);
        return true;
    }

    public Imovel buscarPorNome(String nome) {
        for (int i = 0; i < imoveis.size(); i++) {
            if (imoveis.get(i).getNome().equalsIgnoreCase(nome)) {
                return imoveis.get(i);
            }
        }
        return null;
    }

    public int getQuantidadeCadastrada() {
        return imoveis.size();
    }
}
