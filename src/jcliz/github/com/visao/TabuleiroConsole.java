package jcliz.github.com.visao;

import jcliz.github.com.excecao.ExplosaoException;
import jcliz.github.com.excecao.SairException;
import jcliz.github.com.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner leitor = new Scanner(System.in);
