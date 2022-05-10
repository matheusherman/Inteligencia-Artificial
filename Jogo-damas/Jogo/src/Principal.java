import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static final Integer[][] MovBrancas = new Integer[32][32];
    static final Integer[][] MovNegras = new Integer[32][32];
    static final Integer[][] MatBrancas = new Integer[32][32];
    static final Integer[][] MatNegras = new Integer[32][32];

    static final Integer[][] MovDama1 = {

            {4, 9, 13, 18, 22, 27, 31},                 //0
            {5, 10, 14, 19, 23, null, null},            //1
            {6, 11, 15, null, null, null, null},        //2
            {7, null, null, null, null, null, null},    //3
            {0, 9, 13, 18, 22, 27, 31},                 //4
            {1, 10, 14, 19, 23, null, null},            //5
            {2, 11, 15, null, null, null, null},        //6
            {3, null, null, null, null, null, null},    //7
            {12, 17, 21, 26, 30, null, null},           //8
            {0, 4, 9, 13, 18, 22, 27, 31},              //9
            {1, 5, 14, 19, 23, null, null},             //10
            {2, 6, 11, 15, null, null, null},           //11
            {8, 17, 21, 26, 30, null, null},            //12
            {0, 4, 9, 18, 22, 27, 31},                  //13
            {1, 5, 10, 19, 23, null, null},             //14
            {2, 6, 11, null, null, null, null},         //15
            {20, 25, 29, null, null, null, null},       //16
            {8, 12, 21, 26, 30, null, null},            //17
            {0, 4, 9, 13, 22, 27, 31},                  //18
            {1, 5, 10, 14, 23, null, null},             //19
            {16, 25, 29, null, null, null, null},       //20
            {8, 12, 17, 26, 30, null, null},            //21
            {0, 4, 9, 13, 18, 27, 31},                  //22
            {1, 5, 10, 14, 19, null, null},             //23
            {28, null, null, null, null, null, null},   //24
            {16, 20, 29, null, null, null, null},       //25
            {8, 12, 17, 21, 30, null, null},            //26
            {0, 4, 9, 13, 18, 22, 31},                  //27
            {24, null, null, null, null, null, null},   //28
            {16, 20, 25, null, null, null, null},       //29
            {8, 12, 17, 21, 26, null, null},            //30
            {0, 4, 9, 13, 18, 22, 27}                   //31
    };

    static final Integer[][] MovDama2 = {

            {null, null, null, null, null, null},    //0
            {4, 8, null, null, null, null},          //1
            {5, 9, 12, null, null, null},            //2
            {6, 10, 13, 17, 20, 24},                 //3
            {1, 8, null, null, null, null},          //4
            {2, 9, 12, 16, null, null},              //5
            {3, 10, 13, 17, 20, 24},                 //6
            {11, 14, 18, 21, 25, 28},                //7
            {1, 4, null, null, null, null},          //8
            {2, 5, 12, 16, null, null},              //9
            {3, 6, 13, 17, 20, 24},                  //10
            {7, 14, 18, 21, 25, 28},                 //11
            {2, 5, 9, 16, null, null},               //12
            {3, 6, 10, 17, 20, 24},                  //13
            {7, 11, 18, 21, 25, 28},                 //14
            {19, 22, 26, 29, null, null},            //15
            {2, 5, 9, 12, null, null},               //16
            {3, 6, 10, 13, 20, 24},                  //17
            {7, 11, 14, 21, 25, 28},                 //18
            {15, 22, 26, 21, null, null},            //19
            {3, 6, 10, 13, 17, 24},                  //20
            {7, 11, 14, 18, 25, 28},                 //21
            {15, 19, 26, 29, null, null},            //22
            {27, 30, null, null, null, null},        //23
            {3, 6, 10, 13, 17, 20},                  //24
            {7, 11, 14, 18, 21, 28},                 //25
            {15, 19, 22, 29, null, null},            //26
            {23, 30, null, null, null, null},        //27
            {7, 11, 14, 18, 21, 25},                 //28
            {15, 19, 22, 26, null, null},            //29
            {23, 27, null, null, null, null},        //30
            {null, null, null, null, null, null},    //31
    };

    static Integer[] Tabuleiro = {  1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    0, 0, 0, 0,
                                    0, 0, 0, 0,
                                    -1, -1, -1, -1,
                                    -1, -1, -1, -1,
                                    -1, -1, -1, -1};

    final static int BRANCAS = 37;
    final static int NEGRAS = -37;
    int vinteLances = 0;
    static Integer aux[] = new Integer[32];
    static int prof = 4; //profundidade de busca na a¶rvore
    static int player;
    static int de = 0;
    static int para = 0;
    static int mat = 0;
    static int mat2 = 0;
    static int matou = 0;
    static boolean matarmaisuma = false;
    static boolean r = false;
    static boolean invalido = true;
    static Scanner in;

    public static void main(String[] args) {

        Inicializa();
        ImprimeTabuleiro();
        player = BRANCAS;

        System.out.print(alfaBeta(Tabuleiro, prof, player, -1000000, +1000000));
        ImprimeTabuleiro();
        ArrayList<Integer[]> l = gerarJogadas(Tabuleiro, BRANCAS);
        in = new Scanner(System.in);

        while (invalido) {
            System.out.print("\nCom qual cor você deseja jogar? (1 para Brancas, 2 para Negras)\n");
            if (in.nextInt() == 1) {
                r = true;
                invalido = false;
            } else if (in.nextInt() == 2) {
                r = false;
                invalido = false;
            } else
                System.out.println("Entrada inválida");
        }
        while (testaVitoria()) {
            while (r) {
                EntradaUsuario();
            }
            alfaBeta(Tabuleiro, prof, player, -1000000, +1000000);
            Tabuleiro = aux;
            player = player * (-1);
            ImprimeTabuleiro();
            EntradaUsuario();
            ImprimeTabuleiro();
            player = player * (-1);
        }
    }

    private static void EntradaUsuario() {
        invalido = true;
        while (invalido) {
            System.out.print("\nDigite a casa da peça que deseja mover: ");
            de = in.nextInt() - 1;
            if (de > 31 || de < 0)
                System.out.println("Casa não existe");
            else if (Tabuleiro[de] == 0)
                System.out.println("Casa vazia");
            else if (Tabuleiro[de] < 0)
                System.out.println("Casa contém peça do adversário");
            else
                invalido = false;
        }

        invalido = true;
        while (invalido) {
            System.out.print("\nDigite a casa para qual deseja mover: \n");
            para = in.nextInt() - 1;
            if (de > 31 || de < 0)
                System.out.println("Casa não existe");
            else if (Tabuleiro[para] != 0)
                System.out.println("Casa não vazia");
            else
                invalido = false;
        }

        invalido = true;
        while (invalido) {
            System.out.print("\nMatou uma peça? (Sim - 1 / Não - 0) \n");
            matou = in.nextInt();
            if (matou == 1 || matou == 0)
                invalido = false;
            else
                System.out.println("Entrada inválida");
        }

        if (matou == 1) {
            invalido = true;
            while (invalido) {
                System.out.print("\nDigite a casa da peca morta\n");
                mat = in.nextInt() - 1;
                if (de > 31 || de < 0)
                    System.out.println("Casa não existe");
                else if (Tabuleiro[mat] == 0)
                    System.out.println("Casa vazia");
                else if (Tabuleiro[mat] > 0)
                    System.out.println("Casa contém uma peça sua");
                else
                    invalido = false;
            }
        }

        do {
            if (matou == 0)
                Tabuleiro = Jogar(Tabuleiro, de, para, null);
            else
                Tabuleiro = Jogar(Tabuleiro, de, para, mat);
            ImprimeTabuleiro();
            if (matou == 1) {
                invalido = true;
                while (invalido) {
                    System.out.print("\nPode matar mais uma peça? (Sim - 1 / Não - 0) \n");
                    mat2 = in.nextInt();
                    if (mat2 == 1) {
                        matarmaisuma = true;

                        de = para;
                        {
                            invalido = true;
                            while (invalido) {
                                System.out.print("\nDigite a casa para qual deseja mover: \n");
                                para = in.nextInt() - 1;
                                if (de > 31 || de < 0)
                                    System.out.println("Casa não existe");
                                else if (Tabuleiro[para] != 0)
                                    System.out.println("Casa não vazia");
                                else
                                    invalido = false;
                            }
                        }

                        {
                            invalido = true;
                            while (invalido) {
                                System.out.print("\nDigite a casa da peca morta\n");
                                mat = in.nextInt() - 1;
                                if (de > 31 || de < 0)
                                    System.out.println("Casa não existe");
                                else if (Tabuleiro[mat] == 0)
                                    System.out.println("Casa vazia");
                                else if (Tabuleiro[mat] > 0)
                                    System.out.println("Casa contém uma peça sua");
                                else
                                    invalido = false;
                            }
                        }

                        invalido = false;
                    } else if (mat2 == 0) {
                        matarmaisuma = false;
                        invalido = false;
                    } else
                        System.out.println("Entrada inválida");
                }
            }
            if (!matarmaisuma) {
                player = player * (-1);
                r = false;
            }
        }
        while (matarmaisuma);
    }

    static boolean testaVitoria() {
        ArrayList<Integer[]> l = gerarJogadas(Tabuleiro, player);
        if (l.size() == 0)
            return false;
        boolean r = true;

        for (int i = 0; i < 32; i++) {
            if (Tabuleiro[i] == 1 || Tabuleiro[i] == 2)
                break;
            if (i == 32)
                return false;
        }

        for (int i = 0; i < 32; i++) {
            if (Tabuleiro[i] == -1 || Tabuleiro[i] == -2)
                break;
            if (i == 32)
                return false;
        }

        return true;
    }

    static ArrayList<Integer[]> gerarJogadas(final Integer t[], int p) {
        ArrayList<Integer[]> a = new ArrayList<Integer[]>();

        //gerando jogadas para as brancas
        if (p == BRANCAS) {
            for (int i = 0; i < 32; i++) {
                if (t[i] != null)
                    if (t[i] == 1) {
                        for (int j = 0; j < 32; j++) {
                            if (MovBrancas[i][j] != null)
                                if (t[j] == 0) {
                                    a.add(Jogar(t, i, j, null));
                                } else if (t[j] < 0) {
                                    if (MatBrancas[i][j] != null)
                                        if (t[MatBrancas[i][j]] == 0) {
                                            a.add(Jogar(t, i, MatBrancas[i][j], j));
                                        }
                                }
                        }
                    }
                    //jogadas com a dama
                    else if (t[i] == 2) {
                        for (int j = 0; j < 7; j++) {
                            if (MovDama1[i][j] != null)
                                if (t[MovDama1[i][j]] == 0) {
                                    int z = j;
                                    boolean c = true;
                                    if ((z < 7) && (z > -1)) {
                                        while (MovDama1[i][z] < i) {
                                            if (t[MovDama1[i][z]] == 1) {
                                                c = false;
                                            }
                                            z++;
                                            if (z == 7) {
                                                break;
                                            }
                                        }
                                    }
                                    if (z < 7 && z > -1) {
                                        while (MovDama1[i][z] > i) {
                                            if (t[MovDama1[i][z]] == 1)
                                                c = false;
                                            z--;
                                            if (z < 0)
                                                break;
                                        }
                                        if (c) {
                                            a.add(Jogar(t, i, MovDama1[i][j], null));
                                        }
                                    }
                                }


                        }

                        for (int j = 0; j < 6; j++) {
                            if (MovDama2[i][j] != null)
                                if (t[MovDama2[i][j]] == 0) {
                                    int z = j;
                                    boolean c = true;
                                    while (MovDama2[i][z] < i) {
                                        if (t[MovDama2[i][z]] == 1)
                                            c = false;
                                        z++;
                                        if (z == 6)
                                            break;
                                    }

                                    while (MovDama2[i][z] > i) {
                                        if (t[MovDama2[i][z]] == 1)
                                            c = false;
                                        z--;
                                        if (z < 0)
                                            break;
                                    }
                                    if (c) {
                                        a.add(Jogar(t, i, MovDama2[i][j], null));
                                    }
                                }
                        }
                    }
            }
        }

        //gerando as jogadas para Negras
        else {
            for (int i = 0; i < 32; i++) {
                if (t[i] != null)
                    if (t[i] == -1) {
                        for (int j = 0; j < 32; j++) {
                            if (MovNegras[i][j] != null)
                                if (t[j] == 0) {
                                    a.add(Jogar(t, i, j, null));
                                } else if (t[j] > 0) {
                                    if (MatNegras[i][j] != null)
                                        if (t[MatNegras[i][j]] == 0) {
                                            a.add(Jogar(t, i, MatNegras[i][j], j));
                                        }
                                }
                        }
                    } else if (t[i] == -2) {
                        for (int j = 0; j < 7; j++) {
                            if (MovDama1[i][j] != null)
                                if (t[MovDama1[i][j]] == 0) {
                                    int z = j;
                                    boolean c = true;
                                    while (MovDama1[i][z] < i) {
                                        if (MovDama1[i][z] == -1)
                                            c = false;
                                        z++;
                                        if (z == 6)
                                            break;
                                    }

                                    while (MovDama1[i][z] > i) {
                                        if (MovDama1[i][z] == -1)
                                            c = false;
                                        z--;
                                        if (z < 0)
                                            break;
                                    }
                                    if (c) {
                                        a.add(Jogar(t, i, MovDama1[i][j], null));
                                    }
                                }
                        }

                        for (int j = 0; j < 6; j++) {
                            if (MovDama2[i][j] != null)
                                if (t[MovDama2[i][j]] == 0) {
                                    int z = j;
                                    boolean c = true;
                                    if (MovDama2[i][z] != null) {
                                        while (MovDama2[i][z] < i) {
                                            if (MovDama2[i][z] == 1)
                                                c = false;
                                            z++;
                                            if (z == 7 || MovDama2[i][z] == null)
                                                break;
                                        }
                                    }
                                    while (MovDama2[i][z] > i) {
                                        if (MovDama2[i][z] == 1)
                                            c = false;
                                        z--;
                                        if (z < 0)
                                            break;
                                    }
                                    if (c) {
                                        a.add(Jogar(t, i, MovDama2[i][j], null));
                                    }
                                }
                        }

                    }
            }
        }
        return a;
    }

    static Integer[] Jogar(Integer t[], int o, int d, Integer m) {
        Integer f[] = new Integer[32];
        for (int i = 0; i < 32; i++) {
            f[i] = t[i];
        }
        if (m != null)
            f[m] = 0;
        if (d >= 28 && d <= 31 && f[o] == 1)
            f[d] = 2;
        else if (d >= 0 && d <= 3 && f[o] == -1)
            f[d] = -2;
        else
            f[d] = f[o];
        f[o] = 0;
        return f;
    }


    static Integer alfaBeta(Integer t[], int profundidade, int p, int alfa, int beta) {

        if (p == BRANCAS) {
            int max;
            ArrayList<Integer[]> l = gerarJogadas(t, BRANCAS);
            if (profundidade != 0) {
                for (int i = 0; i < l.size(); i++) {
                    max = alfaBeta(l.get(i), profundidade - 1, NEGRAS, alfa, beta);
                    if (max > alfa) {
                        alfa = max;
                        if (profundidade == prof)
                            aux = l.get(i);
                    }
                    if (max >= beta) {
                        return max;
                    }
                }
            } else {

                for (int i = 0; i < l.size(); i++) {
                    max = heuristica(l.get(i));
                    if (max > alfa) {
                        alfa = max;
                    }
                }
            }
            return alfa;
        } else {
            int min;
            ArrayList<Integer[]> l = gerarJogadas(t, NEGRAS);
            if (profundidade != 0) {
                for (int i = 0; i < l.size(); i++) {
                    min = alfaBeta(l.get(i), profundidade - 1, BRANCAS, alfa, beta);
                    if (min < beta) {
                        beta = min;
                        if (profundidade == prof)
                            aux = l.get(i);
                    }
                    if (min <= alfa) {
                        return min;
                    }
                }
            } else {
                for (int i = 0; i < l.size(); i++) {
                    min = heuristica(l.get(i));
                    if (min < beta) {
                        beta = min;
                    }
                }
            }
            return beta;
        }


    }

    static int heuristica(Integer t[]) {
        int y = 0;
        for (int i = 0; i < 32; i++) {
            y = y + t[i];
        }
        for (int i = 24; i < 28; i++) {
            if (t[i] == 1)
                y = y + 1;
        }
        for (int i = 4; i < 8; i++) {
            if (t[i] == -1)
                y = y - 1;
        }

        return y;
    }

    static void Inicializa() {
        MovBrancas[0][4] = 1;
        MovBrancas[1][4] = 1;
        MovBrancas[1][5] = 1;
        MovBrancas[2][5] = 1;
        MovBrancas[2][6] = 1;
        MovBrancas[3][6] = 1;
        MovBrancas[3][7] = 1;
        MovBrancas[4][8] = 1;
        MovBrancas[4][9] = 1;
        MovBrancas[5][9] = 1;
        MovBrancas[5][10] = 1;
        MovBrancas[6][10] = 1;
        MovBrancas[6][11] = 1;
        MovBrancas[7][11] = 1;
        MovBrancas[8][12] = 1;
        MovBrancas[9][12] = 1;
        MovBrancas[9][13] = 1;
        MovBrancas[10][13] = 1;
        MovBrancas[10][14] = 1;
        MovBrancas[11][14] = 1;
        MovBrancas[11][15] = 1;
        MovBrancas[12][16] = 1;
        MovBrancas[12][17] = 1;
        MovBrancas[13][17] = 1;
        MovBrancas[13][18] = 1;
        MovBrancas[14][18] = 1;
        MovBrancas[14][19] = 1;
        MovBrancas[15][19] = 1;
        MovBrancas[16][20] = 1;
        MovBrancas[17][20] = 1;
        MovBrancas[17][21] = 1;
        MovBrancas[18][21] = 1;
        MovBrancas[18][22] = 1;
        MovBrancas[19][22] = 1;
        MovBrancas[19][23] = 1;
        MovBrancas[20][24] = 1;
        MovBrancas[20][25] = 1;
        MovBrancas[21][25] = 1;
        MovBrancas[21][26] = 1;
        MovBrancas[22][26] = 1;
        MovBrancas[22][27] = 1;
        MovBrancas[23][27] = 1;
        MovBrancas[24][28] = 1;
        MovBrancas[25][28] = 1;
        MovBrancas[25][29] = 1;
        MovBrancas[26][29] = 1;
        MovBrancas[26][30] = 1;
        MovBrancas[27][30] = 1;
        MovBrancas[27][31] = 1;


        MovNegras[4][0] = 1;
        MovNegras[4][1] = 1;
        MovNegras[5][1] = 1;
        MovNegras[5][2] = 1;
        MovNegras[6][2] = 1;
        MovNegras[6][3] = 1;
        MovNegras[7][3] = 1;
        MovNegras[8][4] = 1;
        MovNegras[9][4] = 1;
        MovNegras[9][5] = 1;
        MovNegras[10][5] = 1;
        MovNegras[10][6] = 1;
        MovNegras[11][6] = 1;
        MovNegras[11][7] = 1;
        MovNegras[12][8] = 1;
        MovNegras[12][9] = 1;
        MovNegras[13][9] = 1;
        MovNegras[13][10] = 1;
        MovNegras[14][10] = 1;
        MovNegras[14][11] = 1;
        MovNegras[15][11] = 1;
        MovNegras[16][12] = 1;
        MovNegras[17][12] = 1;
        MovNegras[17][13] = 1;
        MovNegras[18][13] = 1;
        MovNegras[18][14] = 1;
        MovNegras[19][14] = 1;
        MovNegras[19][15] = 1;
        MovNegras[20][16] = 1;
        MovNegras[20][17] = 1;
        MovNegras[21][17] = 1;
        MovNegras[21][18] = 1;
        MovNegras[22][18] = 1;
        MovNegras[22][19] = 1;
        MovNegras[23][19] = 1;
        MovNegras[24][20] = 1;
        MovNegras[25][20] = 1;
        MovNegras[25][21] = 1;
        MovNegras[26][21] = 1;
        MovNegras[26][22] = 1;
        MovNegras[27][22] = 1;
        MovNegras[27][23] = 1;
        MovNegras[28][24] = 1;
        MovNegras[28][25] = 1;
        MovNegras[29][25] = 1;
        MovNegras[29][26] = 1;
        MovNegras[30][26] = 1;
        MovNegras[30][27] = 1;
        MovNegras[31][27] = 1;

        MatBrancas[0][4] = 9;
        MatBrancas[1][4] = 8;
        MatBrancas[1][5] = 10;
        MatBrancas[2][5] = 9;
        MatBrancas[2][6] = 11;
        MatBrancas[3][6] = 10;
        MatBrancas[4][9] = 13;
        MatBrancas[5][9] = 12;
        MatBrancas[5][10] = 14;
        MatBrancas[6][10] = 13;
        MatBrancas[6][11] = 15;
        MatBrancas[7][11] = 14;
        MatBrancas[8][12] = 17;
        MatBrancas[9][12] = 16;
        MatBrancas[9][13] = 18;
        MatBrancas[10][13] = 17;
        MatBrancas[10][14] = 19;
        MatBrancas[11][14] = 18;
        MatBrancas[12][17] = 21;
        MatBrancas[13][17] = 20;
        MatBrancas[13][18] = 22;
        MatBrancas[14][18] = 21;
        MatBrancas[14][19] = 23;
        MatBrancas[15][19] = 22;
        MatBrancas[16][20] = 25;
        MatBrancas[17][20] = 24;
        MatBrancas[17][21] = 26;
        MatBrancas[18][21] = 25;
        MatBrancas[18][22] = 27;
        MatBrancas[19][22] = 26;
        MatBrancas[20][25] = 29;
        MatBrancas[21][25] = 28;
        MatBrancas[21][26] = 30;
        MatBrancas[22][26] = 29;
        MatBrancas[22][27] = 31;
        MatBrancas[23][27] = 30;

        MatNegras[8][4] = 1;
        MatNegras[9][4] = 0;
        MatNegras[9][5] = 2;
        MatNegras[10][5] = 1;
        MatNegras[10][6] = 3;
        MatNegras[11][6] = 2;
        MatNegras[12][9] = 5;
        MatNegras[13][9] = 4;
        MatNegras[13][10] = 6;
        MatNegras[14][10] = 5;
        MatNegras[14][11] = 7;
        MatNegras[15][11] = 6;
        MatNegras[16][12] = 9;
        MatNegras[17][12] = 8;
        MatNegras[17][13] = 10;
        MatNegras[18][13] = 9;
        MatNegras[18][14] = 11;
        MatNegras[19][14] = 10;
        MatNegras[20][17] = 13;
        MatNegras[21][17] = 12;
        MatNegras[21][18] = 14;
        MatNegras[22][18] = 13;
        MatNegras[22][19] = 15;
        MatNegras[23][19] = 14;
        MatNegras[24][20] = 17;
        MatNegras[25][20] = 16;
        MatNegras[25][21] = 18;
        MatNegras[26][21] = 17;
        MatNegras[26][22] = 19;
        MatNegras[27][22] = 18;
        MatNegras[28][25] = 21;
        MatNegras[29][25] = 20;
        MatNegras[29][26] = 22;
        MatNegras[30][26] = 21;
        MatNegras[30][27] = 23;
        MatNegras[31][27] = 22;


    }

    static void ImprimeTabuleiro() {

        String[] TabuleiroImp = new String[32];
        TabuleiroImp = AjeitaTabuleiroImpressao(Tabuleiro);

        System.out.println("+-------------------------------+");
        System.out.println("| " + TabuleiroImp[31] + " |   | " + TabuleiroImp[30] + " |   | " + TabuleiroImp[29] + " |   | " + TabuleiroImp[28] + " |   |");
        System.out.println("+-------------------------------+");
        System.out.println("|   | " + TabuleiroImp[27] + " |   | " + TabuleiroImp[26] + " |   | " + TabuleiroImp[25] + " |   | " + TabuleiroImp[24] + " |");
        System.out.println("+-------------------------------+");
        System.out.println("| " + TabuleiroImp[23] + " |   | " + TabuleiroImp[22] + " |   | " + TabuleiroImp[21] + " |   | " + TabuleiroImp[20] + " |   |");
        System.out.println("+-------------------------------+");
        System.out.println("|   | " + TabuleiroImp[19] + " |   | " + TabuleiroImp[18] + " |   | " + TabuleiroImp[17] + " |   | " + TabuleiroImp[16] + " |");
        System.out.println("+-------------------------------+");
        System.out.println("| " + TabuleiroImp[15] + " |   | " + TabuleiroImp[14] + " |   | " + TabuleiroImp[13] + " |   | " + TabuleiroImp[12] + " |   |");
        System.out.println("+-------------------------------+");
        System.out.println("|   | " + TabuleiroImp[11] + " |   | " + TabuleiroImp[10] + " |   | " + TabuleiroImp[9] + " |   | " + TabuleiroImp[8] + " |");
        System.out.println("+-------------------------------+");
        System.out.println("| " + TabuleiroImp[7] + " |   | " + TabuleiroImp[6] + " |   | " + TabuleiroImp[5] + " |   | " + TabuleiroImp[4] + " |   |");
        System.out.println("+-------------------------------+");
        System.out.println("|   | " + TabuleiroImp[3] + " |   | " + TabuleiroImp[2] + " |   | " + TabuleiroImp[1] + " |   | " + TabuleiroImp[0] + " |");
        System.out.println("+-------------------------------+");
    }

    static String[] AjeitaTabuleiroImpressao(Integer[] a) {
        String[] TabuleiroImp = new String[32];
        for (int i = 0; i < Tabuleiro.length; i++) {
            if (Tabuleiro[i] == 0)
                TabuleiroImp[i] = " ";
            else if (Tabuleiro[i] == 1)
                TabuleiroImp[i] = "@";
            else if (Tabuleiro[i] == 2)
                TabuleiroImp[i] = "O";
            else if (Tabuleiro[i] == -1)
                TabuleiroImp[i] = "*";
            else if (Tabuleiro[i] == -2)
                TabuleiroImp[i] = "#";
        }

        return TabuleiroImp;
    }
}


	