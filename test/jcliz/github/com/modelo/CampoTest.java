package jcliz.github.com.modelo;

import jcliz.github.com.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTest {
    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Direita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Cima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia1Baixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoRealDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoFalso() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> campo.abrir());
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,2);
        campo12.minar();

        Campo campo22 = new Campo(2,2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeToStringMarcado() {
        Campo campo22 = new Campo(2,2);
        campo22.alternarMarcacao();

        System.out.println(campo22);
        assertTrue(campo22.isFechado());
    }

    @Test
    void testeToStringAbertoEMinado() {
        Campo campo22 = new Campo(2,2);
        campo22.abrir();
        campo22.minar();

        System.out.println(campo22);
        assertTrue(campo22.isMinado() && campo22.isAberto());
    }

    @Test
    void testeToStringAbertoComMinasNaVizinhanca() {
        Campo campo22 = new Campo(2,2);
        campo22.abrir();

        Campo campo12 = new Campo(1,2);
        campo22.adicionarVizinho(campo12);
        campo12.minar();

        System.out.println(campo22);
        assertTrue(campo22.isAberto() && campo22.minasNaVizinanca() > 0);
    }

    @Test
    void testeToStringAberto() {
        Campo campo22 = new Campo(2,2);
        campo22.abrir();

        System.out.println(campo22);
        assertTrue(campo22.isAberto());
    }

    @Test
    void testeToStringElse() {
        Campo campo22 = new Campo(2,2);

        System.out.println(campo22);
        assertFalse(campo22.isAberto() && campo22.isMinado() && campo22.minasNaVizinanca() > 0);
    }

    @Test
    void testeoObjetivoAlcancado1() {
        Campo campo22 = new Campo(2,2);
        campo22.minar();
        campo22.alternarMarcacao();

        assertTrue(campo22.objetivoAlcancado());
    }

    @Test
    void testeoObjetivoAlcancado2() {
        Campo campo22 = new Campo(2,2);
        campo22.abrir();

        assertTrue(campo22.objetivoAlcancado());
    }

    @Test
    void testeReiniciar() {
        Campo campo22 = new Campo(2,2);
        campo22.minar();
        campo22.alternarMarcacao();

        campo22.reiniciar();

        assertFalse(campo22.isAberto() && campo22.isMinado() && campo22.isMarcado());
    }
}