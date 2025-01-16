package jcliz.github.com;

import jcliz.github.com.modelo.Tabuleiro;
import jcliz.github.com.visao.TabuleiroConsole;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        int linhas = 0;
        int colunas = 0;
        int bombas = 0;

        System.out.println("-_-_-_-_- C A M P O   M I N A D O -_-_-_-_-");
        boolean continuar = true;

        while (continuar) {
            System.out.println("""
                    Opções:
                    [1] - Quantidade de linhas
                    [2] - Quantidade de colunas
                    [3] - Quantidade de bombas
                    [4] - Começar
                    
                    [0] - Sair
                    """);

            try {
                switch (leitor.nextInt()) {
                    case 1:
                        System.out.println("\n" + "Selecione quantas linhas você deseja:");
                        linhas = leitor.nextInt();
                        break;

                    case 2:
                        System.out.println("\n" + "Selecione quantas colunas deseja:");
                        colunas = leitor.nextInt();
                        break;

                    case 3:
                        System.out.println("\n" + "Selecione quantas bombas deseja:");
                        bombas = leitor.nextInt();
                        break;

                    case 4:
                        if (linhas > 0 && colunas > 0 && bombas > 0) {
                            System.out.println("\n" + "Começando o jogo, boa sorte!");
                            Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, bombas);
                            new TabuleiroConsole(tabuleiro);
                            continuar = false;
                            break;

                        } else {
                            System.out.println("""

                                    Finalize a seleção de campos.
                                    """);
                        }
                        break;

                    case 0:
                        System.out.println("Agredecemos pelo acesso!");
                        System.exit(0);

                    default:
                        System.out.println("""
                                
                                Opção inválida!
                                """);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("""
                        
                        Entrada inválida.
                        """);
            }
        }
        leitor.close();
    }
}