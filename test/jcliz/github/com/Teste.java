package jcliz.github.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Teste {
    @Test
    void testarSeIgualaTres() {
        int x = 2 + 10 - 9;

        assertEquals(3, x);
    }

    @Test
    void testarSeIgualaDois() {
        int a = 1 + 1;
        assertEquals(2, a);
    }
}