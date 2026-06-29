package model;

public enum TipoCarta {

    GANHO("Ganho"),
    PERDA("Perda"),
    AVANCO("Avanco"),
    RETROCESSO("Retrocesso"),
    PRISAO("Prisao");

    private String descricao;

    TipoCarta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
