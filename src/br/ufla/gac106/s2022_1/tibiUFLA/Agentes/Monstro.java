package br.ufla.gac106.s2022_1.tibiUFLA.Agentes;

import br.ufla.gac106.s2022_1.Util.RandomNumber;

public class Monstro extends SerVivo {
    public Monstro(String nome, int vida){
        super(nome, vida);
    }

    public boolean isAlive(){
        return this.getVida() > 0;
    }

    @Override
    public String atacar(SerVivo player) {
        int damage = RandomNumber.generateNumber(50);
        player.receberDano(damage);
        return "Monstro "+getNome()+" Atacou "+player.getNome()+" Com um ataque de "+damage+" de dano\nVida atual do "+player.getNome()+" "+player.getVida();
    }

    @Override
    public String receberDano(int dano) {
        // Este trecho ainda está igual no mostro e no player, mas vai mudar mais pra frente de acordo com os itens
        int novaVida = getVida() - dano;
		if (novaVida <= 0) {
			super.atualizaVida(0);
		}
        super.atualizaVida(novaVida);
		return this.getNome()+" recebeu um ataque de "+dano+" e agora está com "+ getVida()+" de vida.\nVida atual do monstro é de: "+getVida();
    }

}
