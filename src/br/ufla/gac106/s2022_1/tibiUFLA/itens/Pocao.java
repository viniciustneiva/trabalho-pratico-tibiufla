package br.ufla.gac106.s2022_1.tibiUFLA.itens;

public class Pocao extends Item {

    private static final int eficacia = 50;
    public static final String nome = "PocaoDeVida";

    public Pocao( String descricao, double peso, String imagePath) {
        super(nome, descricao, peso, imagePath);
    }

    public int getEficacia() {
        return eficacia;
    }

    @Override
    public String getDescricaoCompleta() {
        String descricao = super.getDescricaoCompleta();
        descricao += "Sua eficácia é de "+ eficacia + " pontos de vida.";
        return descricao;
    }
    
}
