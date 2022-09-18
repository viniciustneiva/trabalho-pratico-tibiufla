package br.ufla.gac106.s2022_1.tibiUFLA.itens;

public class Sword extends Item implements Gear {

    private int damage;

    public Sword(String nome, String descricao, double peso, int damage,String imagePath) {
        super(nome, descricao, peso,imagePath);
        this.damage = damage;
    }

    @Override
    public int getAditionalDamage() {
        return damage;
    }

    @Override
    public int getAditionalArmor() {
        return 10;
    }

    @Override
    public String getDescricaoCompleta() {
        String descricao = super.getDescricaoCompleta();
        descricao += "Ataque adicional: "+ this.damage + "\n";
        descricao += "Defesa adicional: " + getAditionalArmor();
        return descricao;
    }
    
}
