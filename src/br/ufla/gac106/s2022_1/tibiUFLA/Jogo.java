package br.ufla.gac106.s2022_1.tibiUFLA;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;



import br.ufla.gac106.s2022_1.baseJogo.Tela;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Monstro;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Npc;
import br.ufla.gac106.s2022_1.tibiUFLA.Agentes.Player;
import br.ufla.gac106.s2022_1.tibiUFLA.Salas.Ambiente;
import br.ufla.gac106.s2022_1.tibiUFLA.Salas.SalaFinal;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Helmet;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Item;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Pocao;
import br.ufla.gac106.s2022_1.tibiUFLA.itens.Sword;

/**
 * Essa é a classe principal da aplicacao "World of Zull".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.
 *
 * Usuários podem caminhar em um cenário. E é tudo! Ele realmente precisa ser
 * estendido para fazer algo interessante!
 *
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o método "jogar".
 *
 * Essa classe principal cria e inicializa todas as outras: ela cria os ambientes,
 * cria o analisador e começa o jogo. Ela também avalia e  executa os comandos que
 * o analisador retorna.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */

public class Jogo {
    // analisador de comandos do jogo
    private Analisador analisador;
    // ambiente onde se encontra o jogador
    private Ambiente ambienteAtual;
    
    private Tela tela;
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    
    private Player player;
    
    public Jogo(Tela tela)  {
        this.tela = tela;
        criarAmbientes();
        analisador = new Analisador(this.tela);
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {
        Ambiente edron, thais, ankrahmun, darashia, carlin, tarif, calpheon, drigher, lostLand, saida;
        Item item;
        Npc npc;
        Monstro monstro;
    
        
        // cria os ambientes
        edron = new Ambiente("Cidade de Edron", "Util/bg/scenario/edron.jpeg");

        item = new Sword("FireSword", "Provides a small amount of red light.",16.00, 50, "Util/bg/items/firesword.png");
        npc = new Npc("Akman",  "Nesta sala, você poderá encontrar uma arma "+
            "que lhe ajudará a infligir grande dano aos seus inimigos");
        ankrahmun = new Ambiente("Cidade de Ankrahmun",item, npc, "Util/bg/scenario/ankrahmun.png");
        
        item = new Helmet("WingedHelmet", "This helmet belongs to Hermes.",16.00, 15,"Util/bg/items/wingedHelmet.png");
        npc = new Npc("Kristem", " Nesta sala, se procurar bem, " +
            "irá encontrar um item que irá aumentar sua defesa quando for enfrentar um inimigo");
        thais = new Ambiente("Cidade de Thais",item, npc, "Util/bg/scenario/thais.png");

        darashia = new Ambiente("Cidade de Darashia", "Util/bg/scenario/darashia.jpeg");
        
        carlin = new Ambiente("Cidade de Carlin", "Util/bg/scenario/carlin.png");

        item = new Pocao(" Este itém irá recuperar 40% de sua vida", 10,"Util/bg/items/pocao.png");
        npc = new Npc("Bartali",  "Sua aventura está difícil, mas nesta sala, se encontra um item que irá "+
            "lhe ajudar a recuperar sua vida");
        tarif = new Ambiente("Cidade de Tarif",item,npc, "Util/bg/scenario/tarif.jpeg");

        npc = new Npc("Marcov", " Enfrente o monstro nessa sala, e juntos sairemos deste lugar");
        monstro = new Monstro("Garmoth", 500);
        saida= new SalaFinal("Cidade de Heidel", null, npc, monstro, "Util/bg/scenario/saida.png");

        calpheon = new Ambiente("Cidade de calpheon", "Util/bg/scenario/calpheon.jpeg");
        drigher = new Ambiente("Cidade de drigher", "Util/bg/scenario/drigher.png");
        lostLand = new Ambiente("Cidade de lostLand", "Util/bg/scenario/lostland.jpeg");
        
        // inicializa as saidas dos ambientes
        edron.ajustarSaida("sul",ankrahmun);
        edron.ajustarSaida("oeste",carlin);

        carlin.ajustarSaida("leste",edron);
        carlin.ajustarSaida("sul",thais);
        carlin.ajustarSaida("norte",calpheon);

        ankrahmun.ajustarSaida("sul",darashia);
        ankrahmun.ajustarSaida("norte",edron);
        ankrahmun.ajustarSaida("noroeste",carlin);

        darashia.ajustarSaida("norte",ankrahmun);
        darashia.ajustarSaida("sul", lostLand);

        tarif.ajustarSaida("oeste", thais);
        tarif.ajustarSaida("sul", darashia);
        tarif.ajustarSaida("descer", carlin);

        thais.ajustarSaida("norte",carlin);
        thais.ajustarSaida("sudoeste", saida);
        thais.ajustarSaida("noroeste", ankrahmun);

        calpheon.ajustarSaida("norte", drigher);
        calpheon.ajustarSaida("oeste", carlin);
        
        lostLand.ajustarSaida("leste", calpheon);
        lostLand.ajustarSaida("norte", ankrahmun);
        
        drigher.ajustarSaida("norte", tarif);
        drigher.ajustarSaida("sul", lostLand);
        

        carlin.ajustarSaida("sudeste", ankrahmun);
        ankrahmun.ajustarSaida("sudoeste", thais);
        thais.ajustarSaida("nordeste", saida);
        

        ambienteAtual = carlin;  // o jogo comeca em frente à reitoria
        
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */

    private String desejaCarregarJogo(){
        tela.continuarMensagem("Olá, vi que tem um jogo salvo, deseja carregá-lo? [S / N]");
        return tela.obterComando();
    }
    public void jogar()  {
        File file = new File("save.txt");
        if(file.exists() && file.length() > 0){
            String continuar = desejaCarregarJogo();
            if(continuar.equals("S") || continuar.equals("s")){
                String[] infos = lerArquivo().split("\n");
                carregarJogo(infos);
            }else if(continuar.equals("N") || continuar.equals("n")) {
                novoJogo();
            }
        }
        else{
            limparArquivo();
            novoJogo();
        }
    }
    
    private void novoJogo(){
        String nomeDoPlayer = solicitaNomeDoPlayer();
        limparArquivo();
        this.player = new Player(nomeDoPlayer);
    
        salvar(nomeDoPlayer);
        imprimirBoasVindas();
    
        tela.ambienteAtualMudou(ambienteAtual);
    
        boolean terminado = false;
    
        // Entra no loop de comando principal. Aqui nós repetidamente lemos comandos e
        // os executamos até o jogo terminar.
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        tela.continuarMensagem("Obrigado por jogar. Até mais!");
    }
    
    private String solicitaNomeDoPlayer() {
        tela.continuarMensagem("Bem Vindo aventureiro, qual o seu nome? ");
        return tela.obterComando();
    }

    

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        tela.continuarMensagem("Bem-vindo "+this.player.getNome()+" ao TibiUFLA!");
        tela.continuarMensagem("TibiUFLA eh um novo jogo de aventura, incrivelmente chato.");
        tela.continuarMensagem("Digite 'ajuda' se voce precisar de ajuda.");
        tela.continuarMensagem("Digite 'observar' para análisar o que há ao seu redor");
        // tela.continuarMensagem(ambienteAtual.getSaidas());
        
        imprimirLocalizacaoAtual();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando)  {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            tela.continuarMensagem("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        String salvarTxt = palavraDeComando;
        salvarTxt += comando.temSegundaPalavra() ? " " + comando.getSegundaPalavra() + "" : "";
        salvar(salvarTxt);
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar(comando);
        }
        else if (palavraDeComando.equals("remover")) {
            removerItem(comando);
        }
        else if (palavraDeComando.equals("pegar")) {
            pegar(comando);
        }
        else if(palavraDeComando.equals("conversar")){
            conversar();
        }
        else if(palavraDeComando.equals("usar")){
            usarItem(comando);
        }
        else if(palavraDeComando.equals("lutar")){
            lutar(comando);
        }

        return querSair;
    }
    
    private void salvar(String info){
        String infosArquivo = lerArquivo();
        try {
            if(infosArquivo.equals("ERROR!")){
                infosArquivo = "";
            }
            FileWriter arq = new FileWriter("save.txt");
            arq.write(infosArquivo + info + "\n");
            arq.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private String lerArquivo(){
        try {
            BufferedReader arq = new BufferedReader(new FileReader("save.txt"));
            
            String linha = arq.readLine();
            String data = linha != null ? linha + "\n" : "";
            while (linha != null) {
                linha = arq.readLine();
                data += linha != null ? linha + "\n" : "";
            }
            arq.close();
            return data;
        }
        catch (Exception e) {
            System.out.println("Error reading file " + e);
            return "ERROR!";
        }
    }

    private void limparArquivo(){
        Path path = Paths.get("save.txt");
        try {
            FileChannel.open(path, StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING)
              .close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void carregarJogo(String[] infos){
        limparArquivo();
        String nomeDoPlayer = infos[0];
        salvar(nomeDoPlayer);
        this.player = new Player(nomeDoPlayer);
        tela.ambienteAtualMudou(ambienteAtual);
        for (int i = 0; i < infos.length; i++) {
            Comando comando = analisador.pegarComandoComParametro(infos[i]);
            processarComando(comando);
        }
        boolean terminado = false;
        while (! terminado) {

            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        tela.continuarMensagem("Obrigado por jogar. Até mais!");
    }
    
    
    private void usarItem(Comando comando) {
        if(!comando.temSegundaPalavra()){
            tela.continuarMensagem("O que você deseja usar? ");
        }else{
            String segundaPalavra = comando.getSegundaPalavra();
            if(segundaPalavra.equals(Pocao.nome)){
                if(this.player.usarPocao()){
                    removerItemDaMochila(Pocao.nome, true);
                    tela.continuarMensagem("Você usou a poçao e agora está com "+this.player.getVida() + "pontos de vida!");
                }else{
                    tela.continuarMensagem("Você não possui poção para utilizar agora.");
                }
            }else{
                tela.continuarMensagem("Não conheço este item ... você quis dizer PocaoDeVida ?");
            }
        }
    }

    private void conversar() {
        Npc npc = ambienteAtual.getNpc();
        if(npc != null){
            tela.continuarMensagem(npc.getDicaDoNpc());
            tela.continuarMensagem(this.ambienteAtual.getDescricaoLonga());
        }else{
            tela.continuarMensagem("Não existe nenhum NPC nesta sala para conversar");
        }
    }

    /**
     * Exibe informações de ajuda.
     * Aqui nós imprimimos algo bobo e enigmático e a lista de  palavras de comando
     */
    private void imprimirAjuda()  {
        tela.continuarMensagem("Voce esta perdido. Voce esta sozinho. Voce caminha");
        tela.continuarMensagem("pela "+ambienteAtual.getDescricao());
        tela.continuarMensagem("");
        tela.continuarMensagem("Suas palavras de comando sao:");

        tela.continuarMensagem(analisador.getComandos());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saída para lá entra no novo ambiente,
     * caso contrário imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando)  {
        // se não há segunda palavra, não sabemos pra onde ir...
        if(!comando.temSegundaPalavra()) {
            tela.continuarMensagem("Ir pra onde?");
            tela.continuarMensagem(ambienteAtual.getSaidas());
    
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        if(ambienteAtual.getAmbiente(direcao) != null) {
            proximoAmbiente = ambienteAtual.getAmbiente(direcao);
        }
        if (proximoAmbiente == null) {
            tela.continuarMensagem("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;
            tela.ambienteAtualMudou(ambienteAtual);
            tela.continuarMensagem(ambienteAtual.getDescricao());
            
        }
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver se nós queremos
     * realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrário.
     */
    private boolean sair(Comando comando)  {
        if(comando.temSegundaPalavra()) {
            tela.continuarMensagem("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nós realmente queremos sair
        }
    }
    
    private void observar(Comando comando){
        imprimirLocalizacaoAtual();
        if(this.ambienteAtual.getNpc() != null){
            tela.continuarMensagem(" Por aqui se encontra o npc "+this.ambienteAtual.getNpc().getNomeNpc());
        }else{
            tela.continuarMensagem("Não exite nenhum NPC para ajuda-lo aqui.");
            tela.continuarMensagem("Você pode se locomover para "+ambienteAtual.getSaidas());
        }
    }

    private boolean removerItem(Comando comando){
        if(!comando.temSegundaPalavra()){
            tela.continuarMensagem("Remover o que?");
            return false;
        }
        else{
            if(removerItemDaMochila(comando.getSegundaPalavra(), false) != null){
                tela.continuarMensagem("Removido o item " +comando.getSegundaPalavra() + " do jogador " + this.player.getNome() + "\n");
                return true;
            }else{
                tela.continuarMensagem("Você não possui item : "+comando.getSegundaPalavra()+" na sua mochila.");
                showItems();
                return false;
            }
        }
    }

    private Item removerItemDaMochila(String nomeDoItem, boolean consumido) {
        Item item = this.player.buscarItemPorNome(nomeDoItem);

        if (item != null) {
            this.player.removeItem(item);
            tela.jogadorDescartouItem(item);
            if(!consumido){
                ambienteAtual.atribuirNovoItem(item);
            }
            return item;
        }
        return null;
    }

    private void imprimirLocalizacaoAtual() {
        tela.continuarMensagem("Você está em "+ambienteAtual.getDescricao());
    }

    private boolean pegar(Comando comando){
        if(!comando.temSegundaPalavra()){
            tela.continuarMensagem("Pegar o que?");
            return false;
        }
        else{
            Item item = this.ambienteAtual.removerItem(comando.getSegundaPalavra());
            if (item != null) {
                if(this.player.addItem(item)) {
                    tela.jogadorPegouItem(item);
                    tela.continuarMensagem("O jogador " + this.player.getNome() + ": pegou o item " + item.getNome() + "\n");
                    tela.continuarMensagem(item.getDescricaoCompleta());
                }else{
                    ambienteAtual.atribuirNovoItem(item);
                    tela.continuarMensagem("O jogador não pôde pegar o item, porque excede a sua capacidade máxima");
                    tela.continuarMensagem("Capacidade do jogador: " + this.player.getPesoTotal());
                    tela.continuarMensagem("Peso do Item: " + item.getPeso());
                }
            }
            else tela.continuarMensagem("Não existe este item no ambiente.\n");
            return true;
        }
    }

    private void showItems(){
        tela.continuarMensagem(this.player.allItemsOnBackpack());
    }

    private void lutar(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            tela.continuarMensagem("Lutar contra quem ?");
        } else {
            if (ambienteAtual instanceof SalaFinal) {
                SalaFinal salaFinal = (SalaFinal) ambienteAtual;
                Monstro boss = salaFinal.getBoss();
                if (boss != null) {
                    if(boss.getNome().equals(comando.getSegundaPalavra())){
                        tela.continuarMensagem("Iniciando a luta ... ");
                        boolean fightIsOver = false;
                        do {
                            esperarTurno();
                            tela.continuarMensagem(boss.atacar(this.player));
                            if (player.getVida() > 0) {
                                esperarTurno();
                                if(player.getVida() < 50) {
                                    if(player.usarPocao()){
                                        removerItemDaMochila(Pocao.nome, true);
                                        tela.continuarMensagem(player.getNome() + " usou uma poção e agora está com " +
                                        player.getVida() + " pontos de vida");
                                    }
                                }else{
                                    tela.continuarMensagem(player.atacar(boss));
                                }
                            } else {
                                tela.continuarMensagem("Você morreu neste ultimo ataque");
                                fightIsOver = true;
                                tela.continuarMensagem("=== Game Over ===");
                            }

                            if (!boss.isAlive()) {
                                fightIsOver = true;
                                tela.continuarMensagem("Você conseguiu derrotar o chefe!");
                                tela.continuarMensagem(" === Vitória !! ===");
                            }
                            tela.continuarMensagem("");
                        } while (!fightIsOver);
                    }else{
                        tela.continuarMensagem("Não tem nenhum monstro nessa sala com este nome.");
                    }
                } else {
                    tela.continuarMensagem("Nao tem boss aqui");
                }
            }else{
                tela.continuarMensagem("Você não está em uma sala que precise lutar");
            }
        }
    }

    private void esperarTurno() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            tela.continuarMensagem("Erro ao esperar");
        }
    }
}
