package model;

public enum TipoPersonagem {

    ESPECULADOR("Especulador"),
    NEGOCIANTE("Negociante"),
    ADVOGADO("Advogado"),
    CONSTRUTOR("Construtor");

    private String descricao;

    TipoPersonagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
