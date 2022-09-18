package br.ufla.gac106.s2022_1.tibiUFLA.Salas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufla.gac106.s2022_1.baseJogo.EntidadeGrafica;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Npc;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Item;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe é parte da aplicação "World of Zuul".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localização no cenário do jogo. Ele é conectado aos 
 * outros ambientes através de saídas. As saídas são nomeadas como norte, sul, leste 
 * e oeste. Para cada direção, o ambiente guarda uma referência para o ambiente vizinho, 
 * ou null se não há saída naquela direção.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */
public class Ambiente extends EntidadeGrafica {
    // descrição do ambiente
    
    private String descricao;
    // ambientes visinhos de acordo com a direção
    private HashMap<String, Ambiente> saidas;
    private List<Item> items;
    private Npc npc;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele não tem saidas. 
     * "descricao" eh algo como "uma cozinha" ou "um jardim aberto".
     * @param descricao A descrição do ambiente.
     */
    public Ambiente(String descricao, Item item, Npc npc, String imagePath){
        super(imagePath);
        this.descricao = descricao;
        saidas = new HashMap<String,Ambiente>();
        this.items = new ArrayList<Item>();
        if(item != null){
            this.items.add(item);
        }
        this.npc = npc;
    }
    
    
    public Ambiente(String descricao, String imagePath)  {
        this(descricao,null,null, imagePath);
    }

    public Ambiente(String descricao, Npc npc, String imagePath)  {
        this(descricao,null, npc, imagePath);
    }
    /**
     * Define as saídas do ambiente. Cada direção ou leva a um outro ambiente ou é null 
     * (indicando que não tem nenhuma saída para lá).
     * @param norte A saída norte.
     * @param leste A saída leste.
     * @param sul A saída sul.
     * @param oeste A saída oeste.
     */
    public void ajustarSaida(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }

    public String getSaidas() {
        String textoSaidas = "";
        for (String direcao : saidas.keySet()) {
            textoSaidas = textoSaidas + direcao + " ";
        }
        return textoSaidas;
    }

    public Ambiente getAmbiente(String direcao) {
        return saidas.get(direcao);
    }
    
    /**
     * @return A descrição do ambiente.
     */
    public String getDescricao() {
        return descricao;
    }
    
    public String getDescricaoLonga() {
        String texto = "Você está na " + getDescricao();
        String itemsNaSala = "";
        for (int i = 0; i < items.size(); i++) {
            itemsNaSala += items.get(i).getDescricaoCompleta() + "\n";
        }
        if(items.size() > 0 ){
            texto = items.size() > 1 ? "Temos os seguintes itens por aqui:\n " + itemsNaSala : "Temos o seguinte item por aqui: "+ itemsNaSala;
        } else{
            texto += " e não temos items por aqui!\n";
        } 
        
        return texto;
    }

    public boolean roomHasItem(){
        return this.items.size() > 0 ? true : false;
    }

//    public Item seeItem(){
//        if(roomHasItem()) return this.item;
//        else return null;
//    }

    public Item removerItem(String nome){
        if(roomHasItem()) {
            for (int i = 0; i < this.items.size(); i++) {
                if(this.items.get(i).getNome().equals(nome)){
                    Item aux = this.items.get(i);
                    this.items.remove(i);
                    return aux;
                }
                
            }
        }
        return null;
    }

    public void atribuirNovoItem(Item item){
        this.items.add(item);
    }

    public Npc getNpc() {
        return this.npc;
    }

    public String getNome() {
        return this.descricao;
    }
}