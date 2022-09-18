package br.ufla.gac106.s2022_1.tibiUFLA.itens;

public class Helmet extends Item implements Gear {

    private int defense;

    public Helmet(String nome, String descricao, double peso, int defense,String imagePath) {
        super(nome, descricao, peso, imagePath);
        this.defense = defense;
    }

    @Override
    public int getAditionalDamage() {
        return 10;
    }

    @Override
    public int getAditionalArmor() {
        return defense;
    }

    @Override
    public String getDescricaoCompleta() {
        String descricao = super.getDescricaoCompleta();
        descricao += "Ataque adicional: "+ getAditionalDamage() + "\n";
        descricao += "Defesa adicional: " + this.defense;
        return descricao;
    }
    
}
