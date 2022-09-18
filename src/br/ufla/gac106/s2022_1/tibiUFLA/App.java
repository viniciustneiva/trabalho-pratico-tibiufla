package br.ufla.gac106.s2022_1.tibiUFLA;

import br.ufla.gac106.s2022_1.baseJogo.Tela;

public class App {
    public static void main(String[] args) {
        Jogo jogo = new Jogo(Tela.getInstance());	
        try {
            jogo.jogar();
        } catch (Exception e) {
            
        }	
		
    }
}
