package br.edu.ifsp.boliche;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        jogarSpare();
        jogo.jogar(3);
        jogarVezes(17, 0);
        assertEquals(16, jogo.pontuacao());
    }

    @Test
    void partidaComUmStrikeSeguidoDe3E4DevePontuar24() {
        jogarStrike();
        jogo.jogar(3);
        jogo.jogar(4);
        jogarVezes(16, 0);
        assertEquals(24, jogo.pontuacao());
    }

    @Test
    void partidaComSpareEmTodosOsFramesDevePontuar150() {
        jogarVezes(21, 5);
        assertEquals(150, jogo.pontuacao());
    }

    @Test
    void jogoPerfeitoCom12StrikesDevePontuar300() {
        jogarVezes(12, 10);
        assertEquals(300, jogo.pontuacao());
    }

    @Test
    void partidaMistaDevePontuar167() {
        int[] jogadas = {10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1};
        for (int pinos : jogadas) {
            jogo.jogar(pinos);
        }
        assertEquals(167, jogo.pontuacao());
    }

    @Test
    void jogarQuantidadeNegativaDePinosDeveLancarIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(-1));
    }

    @Test
    void jogarMaisDe10PinosDeveLancarIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> jogo.jogar(11));
    }

    private void jogarStrike() {
        jogo.jogar(10);
    }

    private void jogarSpare() {
        jogo.jogar(5);
        jogo.jogar(5);
    }
}
