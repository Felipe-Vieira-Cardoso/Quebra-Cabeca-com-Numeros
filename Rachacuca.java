
import java.util.Random;
import java.util.Scanner;

public class Rachacuca {

    //Escreve o tabuleiro
    public static void escreveTab(int tab[][]) {
        System.out.println("\n-------------");
        for (int lin = 0; lin < tab.length; lin++) {
            for (int col = 0; col < tab[0].length; col++) {
                if (tab[lin][col] == 0) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| " + tab[lin][col] + " ");
                }
            }
            System.out.println("|\n-------------");
        }
    }

    //Embaralha o tabuleiro usando o nivel de dificuldade informado pelo usuario
    public static void embaralhador(int tab[][], int dificuldade) {
        Random gerador = new Random();
        //Define a quantidade de embaralhadas
        int max = 0;
        switch (dificuldade) {
            case 1 ->
                max = 20;
            case 2 ->
                max = 40;
            case 3 ->
                max = 80;
        }
        //Posiçao do inicial  do 0 no matriz
        int lin = 2, col = 2;
        //Guarda direçao inversa a anterior 
        int dirInver = 0;
        //Embaralha o tabuleiro
        for (int cont = 0; cont < max; cont++) {
            int direcao = gerador.nextInt(1, 5);
            //Verifica se a direçao é diferente do inverso da anterior
            if (direcao != dirInver) {
                switch (direcao) {
                    case 1 -> {
                        //Verifica se a peça pode ser movida para cima
                        if (lin - 1 >= 0) {
                            tab[lin][col] = tab[lin - 1][col];
                            tab[lin - 1][col] = 0;
                            lin--;
                            dirInver = 3;
                        } else {
                            cont--;
                        }
                    }
                    case 2 -> {
                        //Verifica se a peça pode ser movida para esquerda
                        if (col - 1 >= 0) {
                            tab[lin][col] = tab[lin][col - 1];
                            tab[lin][col - 1] = 0;
                            col--;
                            dirInver = 4;
                        } else {
                            cont--;
                        }
                    }
                    case 3 -> {
                        //Verifica se a peça pode ser movida para baixo
                        if (lin + 1 < tab.length) {
                            tab[lin][col] = tab[lin + 1][col];
                            tab[lin + 1][col] = 0;
                            lin++;
                            dirInver = 1;
                        } else {
                            cont--;
                        }
                    }
                    case 4 -> {
                        //Verifica se a peça pode ser movida para direita
                        if (col + 1 < tab.length) {
                            tab[lin][col] = tab[lin][col + 1];
                            tab[lin][col + 1] = 0;
                            col++;
                            dirInver = 2;
                        } else {
                            cont--;
                        }
                    }
                }
            } else {
                cont--;
            }
        }
        escreveTab(tab);
        System.out.println("Tabuleiro embaralhado, Boa sorte!");
        //Controladores da quantidade de movimentos 
        int mov = 0, mov1 = 0;
        jogar(tab, mov, mov1);
    }

    //Usuario move as peças até que o tabuleiro esteja ordenado corretamente
    public static void jogar(int tab[][], int mov, int mov1) {
        Scanner entrada = new Scanner(System.in);
        //Le a peça informada pelo usuario
        int pec;
        do {
            System.out.println("Digite [0] para voltar ao menu\n");
            System.out.print("Informe a peca que deseja mover: ");
            pec = entrada.nextInt();
            //Caso o usuario digite 0 volta ao menu
            if (pec == 0) {
                menu();
            }
        } while (pec < 1 || pec > 8);

        int pecL = 0, pecC = 0;
        //Encontra a posiçao da peça informada pelo usuario
        for (int lin = 0; lin < tab.length; lin++) {
            for (int col = 0; col < tab[0].length; col++) {
                if (tab[lin][col] == pec) {
                    pecL = lin;
                    pecC = col;
                }
            }
        }
        //Verifica se a peça pode ser movida ou seja se é uma peça adjacente ao espaço
        for (int lin = 0; lin < tab.length; lin++) {
            for (int col = 0; col < tab[0].length; col++) {
                if (tab[lin][col] == 0) {
                    if (col == pecC) {
                        if (lin - 1 == pecL || lin + 1 == pecL) {
                            tab[lin][col] = pec;
                            tab[pecL][pecC] = 0;
                            mov++;
                            break;
                        }
                    }
                    if (lin == pecL) {
                        if (col - 1 == pecC || col + 1 == pecC) {
                            tab[lin][col] = pec;
                            tab[pecL][pecC] = 0;
                            mov++;
                            break;
                        }
                    }
                }
            }
        }
        //Exibe mensagem de erro caso a peça não seja adjacente
        if (mov == mov1) {
            System.out.print("\nEssa peca nao e adjacente ao espaco!!!");
        }
        mov1 = mov;
        escreveTab(tab);
        System.out.println("Movimentos: " + mov);
        //Verifica se o tabuleiro está corretamente ordenado ou nao
        if (tab[0][0] != 1 || tab[0][1] != 2 || tab[0][2] != 3 || tab[1][0] != 4 || tab[1][1] != 5 || tab[1][2] != 6 || tab[2][0] != 7 || tab[2][1] != 8 || tab[2][2] != 0) {
            jogar(tab, mov, mov1);
        } else {
            System.out.println("Voce venceu!!!");
        }
    }

    //Exibe o menu para o usuario
    public static void menu() {
        Scanner entrada = new Scanner(System.in);
        //Tabuleiro
        int tab[][] = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};
        System.out.println("\n[1] - Novo jogo\n[2] - Como jogar\n[3] - Sair");
        int opc = entrada.nextInt();
        //Exibe as devidas informações de acordo com a escolha do usuario
        switch (opc) {
            case 1 -> {
                escreveTab(tab);
                //Opções de dificuldade
                System.out.println("[1] - Facil\n[2] - Medio\n[3] - Dificil");
                int dificuldade = entrada.nextInt();
                //Caso de entrada invalida
                while (dificuldade != 1 && dificuldade != 2 && dificuldade != 3) {
                    System.out.print("Informaçao invalida!! Informe outro numero: ");
                    dificuldade = entrada.nextInt();
                }
                embaralhador(tab, dificuldade);
            }
            case 2 -> {
                //Instruçoes de como jogar
                System.out.println("Ola querido jogador!! Voce acaba de adentrar ao manual de instrucao do jogo Racha Cuca.");
                System.out.println("Desejamos a voce um incrivel aprendizado. Boa sorte!");
                System.out.println();
                System.out.println("Este quebra-cabecas possui 9 posicoes, sendo 8 pecas com numeros e um espaco vazio para \n"
                        + "movimenta-las. O objetivo do jogo e arranjar as pecas em ordem, da esquerda para a direita, de cima a \n"
                        + "baixo, deixando a posicao inferior direita do tabuleiro vazia, ou seja, as 8 pecas devem ser deslizadas no \n"
                        + "tabuleiro, fazendo-se quantos movimentos fossem necessarios, a fim de serem deixadas em uma \n"
                        + "sequencia crescente, ficando apenas o nono quadrado vazio. Mas, muito cuidado: somente as pecas \n"
                        + "adjacentes ao espaco vazio podem ser movidas.");
                System.out.println("\nPrimeiramente, voce ira receber um menu com tais informacoes: \n"
                        + "[1] - Novo jogo (Iniciara uma nova partida)\n[2] - Como jogar (Manual de instrucoes)\n[3] - Sair (Encerra a partida)\n"
                        + "Selecione um numero com a respectiva acao desejada!");
                System.out.println();
                System.out.println("Ao selecionar o numero 1 - > Novo Jogo, voce recebera alguns itens:\n"
                        + "Primeiramente, voce conhecera o tabuleiro devidamente ordenado: \n");
                System.out.println("-------------\n"
                        + "| 1 | 2 | 3 |\n"
                        + "-------------\n"
                        + "| 4 | 5 | 6 |\n"
                        + "-------------\n"
                        + "| 7 | 8 |   |\n"
                        + "-------------");
                System.out.println("Em seguida, deve-se escolher a dificuldade do jogo: \n"
                        + "1 - Facil\n2 - Medio\n3 - Dificil\n"
                        + "OBS: Quanto mais dificil a modalidade, mais embaralhado estara as pecas do jogo!\n"
                        + "\nLogo apos o jogador selecionar a dificuldade, automaticamente, ele recebera o tabuleiro embaralhado.\n"
                        + "E, somente assim, a partida sera iniciada com sucesso!\n"
                        + "-------------\n"
                        + "| 1 | 3 |   |\n"
                        + "-------------\n"
                        + "| 4 | 2 | 5 |\n"
                        + "-------------\n"
                        + "| 7 | 8 | 6 |\n"
                        + "-------------");
                System.out.println();
                System.out.println("Como dito anteriormente, o objetivo do jogo e organizar as pecas em ordem crescente\n"
                        + "Entao, o jogador ira escolher uma peca para movimentar.\n"
                        + "Informe a peca que deseja mover: 3\n"
                        + "-------------\n"
                        + "| 1 |   | 3 |\n"
                        + "-------------\n"
                        + "| 4 | 2 | 5 |\n"
                        + "-------------\n"
                        + "| 7 | 8 | 6 |\n"
                        + "-------------\n");
                System.out.println("Movimentos: 1");
                System.out.println("A cada movimento realizado, o jogo ira contabilizar o numero de jogadas.");
                System.out.println("Informe a peca que deseja mover: 5\n");
                System.out.println("-------------\n"
                        + "| 1 | 2 | 3 |\n"
                        + "-------------\n"
                        + "| 4 | 5 |   |\n"
                        + "-------------\n"
                        + "| 7 | 8 | 6 |\n"
                        + "-------------\n");
                System.out.println("Movimentos: 4");
                System.out.println("Informe a peca que deseja mover: 6\n");
                System.out.println("-------------\n"
                        + "| 1 | 2 | 3 |\n"
                        + "-------------\n"
                        + "| 4 | 5 | 6  |\n"
                        + "-------------\n"
                        + "| 7 | 8 |   |\n"
                        + "-------------\n");
                System.out.println("Movimentos: 5\n"
                        + "Voce ganhou!\n");
                System.out.println("O objetivo do jogo foi alcancado com sucesso.\n");
                int a;
                do {
                    System.out.println("Digite [0] para voltar ao menu");
                    a = entrada.nextInt();
                } while (a != 0);
                menu();
            }
            case 3 -> {
                //Saiu do jogo
                System.out.println("\nVoce saiu do jogo!");
                System.exit(0);
            }
            default -> {
                //Caso de entrada invalida
                while (opc != 1 && opc != 2 && opc != 3) {
                    System.out.print("Informaçao invalida!! Informe outro numero: ");
                    opc = entrada.nextInt();
                }
            }
        }
    }

    public static void main(String[] args) {
        //Chama o metodo menu
        menu();
    }
}
