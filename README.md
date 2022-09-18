[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=8176603&assignment_repo_type=AssignmentRepo)
## Trabalho de PPOO - Jogo Aventure

Neste jogo temos jogador, monstros, sendo estes seres vivos que podem atacar e ser atacado, além de NPCs, items e cidades.

O jogador pode andar entre as cidades existentes, observar o ambiente (cidade) em que se encontra para ver se existe algum item naquela cidade e também para saber sua localização atual.

O jogador pode pegar o item, caso exista algum no ambiente e o peso do item junto aos items que o jogador está carregando não exceda sua capacidade máxima e também pode fazer uso dos itens adquiridos.

# Comandos

```
# ir [direcao]: guia o jogador para a direção escolhida, caso ela exista nas direções da cidade atual (norte, noroeste, sul, sudeste, nordeste, sudoeste, oeste, leste)
# sair: encerra o jogo
# ajuda: Imprime informações e ajuda sobre os comandos do jogo e aonde ele está
# observar: Vê tudo que está ao redor daquele ambiente.
# remover [item]: tenta remover um item do inventário do jogador caso ele exista e seja o mesmo nome digitado
# pegar [item]: tenta pegar um item do ambiente caso ele exista e seja o mesmo nome digitado
# conversar: verifica se o ambiente tem um NPC, caso ele exista no ambiente, a dica do npc é exibida
# usar [item]: tenta utilizar um item do inventário caso ele exista e seja o mesmo nome digitado
# lutar [nomeMonstro]: caso a sala seja a final e o nome do monstro seja igual ao digitado, inicia uma luta com o monstro
```

# Sobre o Jogo

Existem três itens disponíveis que o jogador pode utilizar, Poção, Sword e Helmet. A poção restaura sua vida automáticamente caso seus pontos de vida baixe para menos de 50. A Sword e o Helmet oferecem valores de defesa e ataque, afetando os valores de danos causados e recebidos durante um combate. Temos um boss final que garante o fim do jogo, para ganhar, é necessário encontrá-lo e lutar contra ele, causando dano até que seus pontos de vida cheguem a zero. Com sua derrota o jogo acaba, buscar e equipar itens é uma boa estratégia para ganhar o jogo, mas se o jogador estiver com MUITA sorte, ele conseguirá derrotar o monstro sem nenhum item. O jogador pode encontrar um NPC que é uma entidade viva no jogo mas não funciona como um ser vivo, porque ele não participa de combates, tem apenas a função interativa com o jogador de oferecer dicas.

OBS: Para conseguir a poção é necessário conversar com o NPC, já que o comando 'observar' não informa se existe uma poção no ambiente.
##  Seção 4.7

O polimorfismo foi utilizado majoritariamente na parte de Itens. Utilizamos para abstrair a mecânica de cada item, e dividir por exemplo, a funcionalidade de um item defensivo e um item ofensivo. Diferenciando também a poção dos outros. Houve o uso também das variáveis polimórficas na criação dos ambientes com itens. 

##  Seção 4.9

Para o salvamento de arquivos, foi optado por salvar todos os comandos do jogador, e ao carregar estes comandos o jogador chega ao ponto em que parou.

Caso exista um jogo prévio, ao começar outro jogo terá a opção de continuar de onde parou, perguntando ao usuário se quer continuar ou não. caso deseja, basta responder "sim".


![UML](/doc/UML_PPOO_TIBIUFLA.png)