package jcliz.github.com.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTest {
    private Tabuleiro tabuleiro;

    @BeforeEach
    void iniciarTabuleiro() {
        tabuleiro = new Tabuleiro(6, 6, 6);
    }

    @Test
    void testeToString() {
        System.out.println(tabuleiro);
    }

    @Test
    void testeAbrir1() {
        tabuleiro.abrir(1,1);

        assertTrue(tabuleiro.getCampos().stream()
                .anyMatch
                        (c -> c.getLinha() == 1 && c.getColuna() == 1 && c.isAberto()));
    }

    @Test
    void testeAbrirCampoInexistente() {
        tabuleiro.abrir(8, 7);

        assertFalse(tabuleiro.getCampos().stream()
                .anyMatch(Campo::isAberto));
    }
}
