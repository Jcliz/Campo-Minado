package jcliz.github.com;

import jcliz.github.com.modelo.Tabuleiro;
import jcliz.github.com.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
        new TabuleiroConsole(tabuleiro);
    }
}