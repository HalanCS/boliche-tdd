package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {

    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = new Jogo();
    }

    private void jogarVezes(int vezes, int pinos) {
        for (int i = 0; i < vezes; i++) {
            jogo.jogar(pinos);
        }
    }

    @Test
    void partidaNaSarjetaCom20JogadasDeZeroDevePontuarZero() {
        jogarVezes(20, 0);
        assertEquals(0, jogo.pontuacao());
    }

    @Test
    void partidaCom20JogadasDeUmDevePontuar20() {
        jogarVezes(20, 1);
        assertEquals(20, jogo.pontuacao());
    }

    @Test
    void partidaComUmSpareSeguidoDe3DevePontuar16() {
        jogo.jogar(5);
        jogo.jogar(5);
        jogo.jogar(3);
        jogarVezes(17, 0);
        assertEquals(16, jogo.pontuacao());
    }
}
