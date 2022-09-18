package br.ufla.gac106.s2022_1.tibiUFLA.Agentes;

import java.util.ArrayList;

import br.ufla.gac106.s2022_1.Util.RandomNumber;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Gear;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Item;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Pocao;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe é parte da aplicação "World of Zuul".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.
 *
 * Um "Ambiente" representa uma localização no cenário do jogo. Ele é conectado
 * aos
 * outros ambientes através de saídas. As saídas são nomeadas como norte, sul,
 * leste
 * e oeste. Para cada direção, o ambiente guarda uma referência para o ambiente
 * vizinho,
 * ou null se não há saída naquela direção.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido e adaptado por Julio
 *         César Alves)
 */
public class Player extends SerVivo{
	// descrição do ambiente
	private static final double pesoMaximo = 40.0;
	private ArrayList<Item> arrayItems;

	public Player(String nome) {
		super(nome);
		this.arrayItems = new ArrayList<Item>();
	}
	

	public boolean addItem(Item item) {
		if(getPesoTotal() + item.getPeso() <= pesoMaximo){
			this.arrayItems.add(item);
			return true;
		}
		return false;
	}

	public Item removeItem(Item item) {
		arrayItems.remove(item);
		return item;
	}

	public Item buscarItemPorNome(String name) {
		for (Item item : arrayItems) {
			if (item.getNome().equals(name)) {
				return item;
			}
		}
		return null;
	}

	public String allItemsOnBackpack() {
		String items = "";

		for (Item item : arrayItems) {
			items += "Item: " + item.getNome() + "\n";
		}

		return items;
	}

	public double getPesoTotal() {
		double pesoTotal = 0;
		for (Item item : arrayItems) {
			pesoTotal += item.getPeso();
		}
		return pesoTotal;
	}

	@Override
	public String atacar(SerVivo monstro) {
		int damage = RandomNumber.generateNumber(30);
		for (Item item : arrayItems) {
			if(item instanceof Gear){
				Gear gear = (Gear) item;
				damage += gear.getAditionalDamage();
			}
		}
        monstro.receberDano(damage);
        return "Player "+getNome()+" Atacou "+monstro.getNome()+" Com um ataque de "+damage+" de dano.\nVida atual do monstro é de: "+monstro.getVida();
	}

	@Override
	public String receberDano(int dano) {
		for (Item item : arrayItems) {
			if(item instanceof Gear){
				Gear gear = (Gear) item;
				dano -= gear.getAditionalArmor();
			}
		}
		int vida = getVida();

		int novaVida = vida - (dano<0 ? 0 : dano);
		if (novaVida <= 0) {
			super.atualizaVida(0);
		}
		super.atualizaVida(novaVida);
		return this.getNome()+"Recebeu um ataque de "+dano+" e agora está com "+ getVida()+" de vida.\nVida atual do player é de: "+getVida();
	}

	public boolean usarPocao() {
		for (Item item : arrayItems) {
			if(item instanceof Pocao){
				Pocao pocao = (Pocao) item;
				this.atualizaVida(this.getVida()+pocao.getEficacia());
				return true;
			}
		}
		return false;
	}

}
