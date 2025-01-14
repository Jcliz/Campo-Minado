package jcliz.github.com.modelo;

import jcliz.github.com.modelo.modelo.Tabuleiro;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

        tabuleiro.abrir(3, 3);
        tabuleiro.marcar(4, 4);
        tabuleiro.marcar(4, 5);

        System.out.println(tabuleiro);
    }
}
