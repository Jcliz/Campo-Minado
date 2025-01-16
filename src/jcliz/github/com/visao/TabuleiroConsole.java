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

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    //método que realiza a execução do jogo
    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                cicloDoJogo();

                System.out.println("Outra partida? (S/n) ");
                String resposta = leitor.nextLine();

                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;

                    //default = S
                } else {
                    tabuleiro.reiniciar();
                }
            }

        } catch (SairException e) {
            System.out.println("Fechando o jogo, até mais!");
        } finally {
            leitor.close();
        }
    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado = capturarValorDigitado("""
                        Digite (x, y) ou "sair":
                        """);

                //transforma nos números separados por vírgula em um array e logo em seguida em int
                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                digitado = capturarValorDigitado("""
                        [1] - Abrir
                        [2] - (Des)Marcar
                        """);

                if ("1".equals(digitado)) {
                    //chama o primeiro numero e depois o segundo
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)) {
                    tabuleiro.marcar(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhou!");

        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Perdeu!");
        }
    }

    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = leitor.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }

        return digitado;
    }
}
