package jcliz.github.com.modelo.modelo;

import jcliz.github.com.modelo.excecao.ExplosaoException;

import java.util.List;
import java.util.ArrayList;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean aberto;
    private boolean minado;
    private boolean marcado;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        if(marcado) {
            return "x";
        } else if (aberto && minado) {
            return "*";
        } else if (aberto && minasNaVizinanca() > 0) {
            return Long.toString(minasNaVizinanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaLinha + deltaColuna;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;

        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;

        } else {
            return false;
        }
    }

    public void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    public boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;

        } else {
            return false;
        }
    }

    public boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar() {
        minado = true;
    }

    public boolean objetivoAlcancado() {
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public long minasNaVizinanca() {
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    public void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isFechado() {
        return !isAberto();
    }

/*
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
*/

    public boolean isMinado() {
        return minado;
    }
}
