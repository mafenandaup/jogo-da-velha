package com.example.jogo_velha_app;

public class LogicaJogo {

    private final int [] [] tabelaJogo; //refazendo o array c as linhas e colunas
    private int jogador = 1; // como o espaço livre é 0, o jogador X será representado por 1 e o jogador O será 2

    LogicaJogo(){
        tabelaJogo = new int[3][3];
        for (int i=0; i<3; i++){  // i representa as linhas, j representa as colunas, como mencionado no arquivo da tabela
            for (int j=0; j<3; j++){
               tabelaJogo [i][j] = 0;
            }
        }
    }

    public synchronized boolean atualizarTabela(int i, int j) {
        // valida limites
        if (i < 0 || i > 2 || j < 0 || j > 2) return false;

        if (tabelaJogo[i][j] == 0) {
            tabelaJogo[i][j] = jogador; // como 0 é espaço livre, X é representado por 1, e O, por 2
            jogador = (jogador == 1) ? 2 : 1;
            return true;
        } else {
            return false;
        }
    }

    public void limparTabela(){
        for (int i=0; i<3; i++) {  // i representa as linhas, j representa as colunas, como mencionado no arquivo da tabela
            for (int j = 0; j < 3; j++) {
                tabelaJogo[i][j] = 0;
            }
        }
    }

    public int[][] getTabelaJogo() {
        return tabelaJogo;
    }

    public int getJogador() {
        return jogador;
    }

    public void setJogador(int jogador) {
        if (jogador == 1 || jogador == 2) {
            this.jogador = jogador;
        }
    }
}
