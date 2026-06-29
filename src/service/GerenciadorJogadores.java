package service;

import java.util.ArrayList;
import java.util.List;
import model.Jogador;
import model.TipoPersonagem;

public class GerenciadorJogadores {

    private List<Jogador> jogadores;
    private final int MAX_JOGADORES = 6;
    private final int MIN_JOGADORES = 2;

    public GerenciadorJogadores() {
        this.jogadores = new ArrayList<>();
    }

    public boolean cadastrar(Jogador jogador) {
        if (jogadores.size() >= MAX_JOGADORES) {
            System.out.println("Erro: Limite maximo de " + MAX_JOGADORES + " jogadores atingido.");
            return false;
        }

        if (buscarPorNome(jogador.getNome()) != null) {
            System.out.println("Erro: Ja existe um jogador com o nome '" + jogador.getNome() + "'.");
            return false;
        }

        jogadores.add(jogador);
        return true;
    }

    public List<Jogador> listar() {
        return jogadores;
    }

    public boolean editar(String nomeAntigo, String novoNome, TipoPersonagem novoPersonagem) {
        Jogador jogador = buscarPorNome(nomeAntigo);

        if (jogador == null) {
            System.out.println("Erro: Jogador '" + nomeAntigo + "' nao encontrado.");
            return false;
        }

        if (!nomeAntigo.equalsIgnoreCase(novoNome) && buscarPorNome(novoNome) != null) {
            System.out.println("Erro: Ja existe outro jogador com o nome '" + novoNome + "'.");
            return false;
        }

        jogador.setNome(novoNome);
        jogador.setTipoPersonagem(novoPersonagem);
        return true;
    }

    public boolean remover(String nome) {
        Jogador jogador = buscarPorNome(nome);

        if (jogador == null) {
            System.out.println("Erro: Jogador '" + nome + "' nao encontrado.");
            return false;
        }

        jogadores.remove(jogador);
        return true;
    }

    public Jogador buscarPorNome(String nome) {
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getNome().equalsIgnoreCase(nome)) {
                return jogadores.get(i);
            }
        }
        return null;
    }

    public int getQuantidadeCadastrada() {
        return jogadores.size();
    }

    public boolean podeIniciarPartida() {
        return jogadores.size() >= MIN_JOGADORES && jogadores.size() <= MAX_JOGADORES;
    }
}
