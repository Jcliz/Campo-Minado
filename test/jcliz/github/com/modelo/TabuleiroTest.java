package jcliz.github.com.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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
        tabuleiro.abrir(1, 1);

        assertTrue(tabuleiro.getCampos().stream()
                .anyMatch
                        (c -> c.getLinha() == 1 &&
                                c.getColuna() == 1 &&
                                c.isAberto()));
    }

    @Test
    void testeAbrirCampoInexistente() {
        tabuleiro.abrir(8, 7);

        assertFalse(tabuleiro.getCampos().stream()
                .anyMatch(Campo::isAberto));
    }

    @Test
    void testeMarcar() {
        tabuleiro.marcar(1,1);

        assertTrue(tabuleiro.getCampos()
                .stream().anyMatch(c -> c.getLinha() == 1 &&
                        c.getColuna() == 1 &&
                        c.isMarcado()));
    }

    @Test
    void testMarcarInexistente() {
        tabuleiro.marcar(8,8);

        assertFalse(tabuleiro.getCampos()
                .stream().anyMatch(Campo::isMarcado));
    }

    @Test
    void testeObjetivoAlcancado() {
        tabuleiro.getCampos().stream()
                .filter(Campo::isMinado)
                .forEach(c -> tabuleiro.marcar(c.getLinha(), c.getColuna()));

        tabuleiro.getCampos().stream()
                .filter(c -> !c.isMinado())
                .forEach(Campo::abrir);

        assertTrue(tabuleiro.objetivoAlcancado());
    }

    @Test
    void testeObjetivoNaoAlcancado() {
        tabuleiro.getCampos().stream()
                .filter(c -> !c.isMinado())
                .forEach(Campo::abrir);

        assertFalse(tabuleiro.objetivoAlcancado());
    }

    @Test
    void testeReiniciar() {
        boolean minasAntes = tabuleiro.getCampos().stream()
                .anyMatch(Campo::isMinado);


        tabuleiro.getCampos().getFirst().abrir();
        tabuleiro.marcar(1, 1);

        tabuleiro.reiniciar();

        assertTrue(tabuleiro.getCampos().stream()
                .allMatch
                        (campo -> !campo.isAberto() && !campo.isMarcado()));

        Set<Integer> minasDepois = new HashSet<>();
        for (int i = 0; i < tabuleiro.getCampos().size(); i++) {
            if (tabuleiro.getCampos().get(i).isMinado()) {
                minasDepois.add(i);
            }
        }

        assertNotEquals(minasAntes,  minasDepois);
    }
}