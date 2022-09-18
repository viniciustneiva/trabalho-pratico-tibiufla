package br.ufla.gac106.s2022_1.tibiUFLA.Agentes;

public class Npc {
    private String nome;
    private String dica;

    public Npc(String nome, String dica){
        this.nome= nome;
        this.dica = dica;
    }

    public String getDicaDoNpc(){
        return "Me chamo "+this.nome+". Minha dica é a seguinte:"+this.dica;
    }

    public String getNomeNpc(){
        return this.nome;
    }
}
