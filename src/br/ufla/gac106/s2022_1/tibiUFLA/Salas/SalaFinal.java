package br.ufla.gac106.s2022_1.tibiUFLA.Salas;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Monstro;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Npc;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Item;

public class SalaFinal extends Ambiente {

    private Monstro boss;

    public SalaFinal(String descricao, Item item, Npc npc, Monstro monstro, String imagePath) {
        super(descricao, item, npc, imagePath);
        this.boss = monstro;
    }
 
    @Override
    public String getDescricaoLonga(){
        String descricao = super.getDescricaoLonga();
       
        descricao += "\nVocê deverá enfrentar o boss "+boss.getNome()+" para conseguir sair da sala";

        return descricao;
    }

    public Monstro getBoss(){
        return this.boss;
    }
}
