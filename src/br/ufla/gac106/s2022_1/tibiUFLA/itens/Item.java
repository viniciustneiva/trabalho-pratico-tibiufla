package br.ufla.gac106.s2022_1.tibiUFLA.itens;

import br.ufla.gac106.s2022_1.baseJogo.EntidadeGrafica;

public abstract class Item extends EntidadeGrafica{
    private String nome;
    private String descricao;
    private double peso;

    public Item(String nome, String descricao, double peso, String imagePath){
        super(imagePath);
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
    }

    public String getNome(){
        return this.nome;
    }

    public double getPeso() {
        return peso;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getDescricaoCompleta() {
        String descricao = "Item: " + getNome() + "\n";
        descricao += "Peso do Item: " + getPeso() + "\n";
        descricao += "Descricao do Item: " + getDescricao() + "\n";
        return descricao;
    }
}