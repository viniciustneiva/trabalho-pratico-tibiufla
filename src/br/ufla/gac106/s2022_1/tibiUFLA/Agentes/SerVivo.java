package br.ufla.gac106.s2022_1.tibiUFLA.Agentes;


public abstract class SerVivo {
    private String nome;
    private int vida;
    

    public SerVivo(String nome){
        this.nome = nome;
        this.vida = 100;
    }

    public SerVivo(String nome, int vida){
        this.nome = nome;
        this.vida = vida;
    }

    protected abstract String atacar(SerVivo serVivo);
    protected abstract String receberDano(int dano);

    public String getNome() {
		return this.nome;
	}
    
    public int getVida(){
        return this.vida <= 0 ? 0 : this.vida;
    }

    protected void atualizaVida(int vida){
        this.vida = vida;
    }
}
