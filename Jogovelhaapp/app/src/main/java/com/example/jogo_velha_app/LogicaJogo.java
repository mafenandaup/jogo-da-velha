package com.example.jogo_velha_app;

import android.widget.Button;
import android.widget.TextView;

public class LogicaJogo {

    private final int [] [] tabelaJogo; //refazendo o array c as linhas e colunas

    private Button play_again;
    private Button home;


    private TextView vez_jogador;
    private String player1;

    private boolean gameOver;

    private String player2;
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
        if (gameOver) return false;
        if (i < 0 || i > 2 || j < 0 || j > 2) return false;

        if (tabelaJogo[i][j] == 0) {
            tabelaJogo[i][j] = jogador; // como 0 é espaço livre, X é representado por 1, e O, por 2
           if (jogador == 1){
               vez_jogador.setText("É a vez de " + player2); // muda do jogador 1 pro 2
               jogador = 2;
           }else{
               vez_jogador.setText("É a vez de " + player1);
               jogador = 1;
           }
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

    public int checkVencedor() {
        // verificando as linhas
        for (int i = 0; i < 3; i++) {
            if (tabelaJogo[i][0] != 0 &&
                    tabelaJogo[i][0] == tabelaJogo[i][1] &&
                    tabelaJogo[i][1] == tabelaJogo[i][2]) {
                return tabelaJogo[i][0]; // vai retornar o número 1 ou 2, que indica os n°s das marcações
            }
        }

        // aqui colunas (verifica cada posição, que nem o outro)
        for (int j = 0; j < 3; j++) {
            if (tabelaJogo[0][j] != 0 &&
                    tabelaJogo[0][j] == tabelaJogo[1][j] &&
                    tabelaJogo[1][j] == tabelaJogo[2][j]) {
                return tabelaJogo[0][j];
            }
        }

        // diagonais
        if (tabelaJogo[0][0] != 0 &&
                tabelaJogo[0][0] == tabelaJogo[1][1] &&
                tabelaJogo[1][1] == tabelaJogo[2][2]) {
            return tabelaJogo[0][0];
        }

        if (tabelaJogo[0][2] != 0 &&
                tabelaJogo[0][2] == tabelaJogo[1][1] &&
                tabelaJogo[1][1] == tabelaJogo[2][0]) {
            return tabelaJogo[0][2];
        }

        // Nenhum vencedor
        return 0; // caso ninguém tenha vencido
    }

    public void setGameOver(boolean over) {
        this.gameOver = over;
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
    public String getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }



    public String getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public TextView getVez_jogador() {
            return vez_jogador;
        }

        public void setVez_jogador(TextView vez_jogador) {
            this.vez_jogador = vez_jogador;
        }

        public Button getHome() {
            return home;
        }

        public void setHome(Button home) {
            this.home = home;
        }

        public Button getPlay_again() {
            return play_again;
        }

        public void setPlay_again(Button play_again) {
            this.play_again = play_again;
        }

}
