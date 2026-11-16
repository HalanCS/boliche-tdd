package br.edu.ifsp.boliche;

public class Jogo {

    private static final int TOTAL_FRAMES = 10;
    private static final int MAX_PINOS = 10;
    private static final int MAX_JOGADAS = 21;

    private final int[] jogadas = new int[MAX_JOGADAS];
    private int jogadaAtual = 0;
    private int frameAtual = 1;
    private int jogadaNoFrame = 1;

    public void jogar(int pinos) {
        validarEstadoDaPartida();
        validarQuantidadeDePinos(pinos);
        validarSomaDoFrame(pinos);

        jogadas[jogadaAtual++] = pinos;
        avancarFrame(pinos);
    }

    public int pontuacao() {
        int pontos = 0;
        int indice = 0;
        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {
            if (ehStrike(indice)) {
                pontos += MAX_PINOS + bonusDoStrike(indice);
                indice += 1;
            } else if (ehSpare(indice)) {
                pontos += MAX_PINOS + bonusDoSpare(indice);
                indice += 2;
            } else {
                pontos += somaDoFrame(indice);
                indice += 2;
            }
        }
        return pontos;
    }

    public boolean encerrado() {
        if (frameAtual < TOTAL_FRAMES) {
            return false;
        }
        if (jogadaNoFrame == 3) {
            int primeira = jogadas[jogadaAtual - 2];
            int segunda = jogadas[jogadaAtual - 1];
            return primeira + segunda < MAX_PINOS;
        }
        return jogadaNoFrame > 3;
    }

    private void validarEstadoDaPartida() {
        if (encerrado()) {
            throw new IllegalStateException("A partida já foi encerrada.");
        }
    }

    private void validarQuantidadeDePinos(int pinos) {
        if (pinos < 0 || pinos > MAX_PINOS) {
            throw new IllegalArgumentException("Quantidade de pinos deve estar entre 0 e 10: " + pinos);
        }
    }

    private void validarSomaDoFrame(int pinos) {
        if (frameAtual < TOTAL_FRAMES && jogadaNoFrame == 2 && jogadas[jogadaAtual - 1] + pinos > MAX_PINOS) {
            throw new IllegalArgumentException("Soma das jogadas do frame não pode exceder 10.");
        }
        if (frameAtual == TOTAL_FRAMES && jogadaNoFrame == 2 && jogadas[jogadaAtual - 1] < MAX_PINOS
                && jogadas[jogadaAtual - 1] + pinos > MAX_PINOS) {
            throw new IllegalArgumentException("Soma das duas primeiras jogadas do 10º frame sem strike não pode exceder 10.");
        }
        if (frameAtual == TOTAL_FRAMES && jogadaNoFrame == 3 && jogadas[jogadaAtual - 2] == MAX_PINOS
                && jogadas[jogadaAtual - 1] < MAX_PINOS && jogadas[jogadaAtual - 1] + pinos > MAX_PINOS) {
            throw new IllegalArgumentException("No 10º frame após strike, se a 2ª jogada não for 10, a soma da 2ª e 3ª não pode exceder 10.");
        }
    }

    private void avancarFrame(int pinos) {
        if (frameAtual < TOTAL_FRAMES) {
            if (jogadaNoFrame == 1 && pinos < MAX_PINOS) {
                jogadaNoFrame = 2;
            } else {
                frameAtual++;
                jogadaNoFrame = 1;
            }
        } else {
            jogadaNoFrame++;
        }
    }

    private boolean ehStrike(int indice) {
        return jogadas[indice] == MAX_PINOS;
    }

    private int bonusDoStrike(int indice) {
        return jogadas[indice + 1] + jogadas[indice + 2];
    }

    private boolean ehSpare(int indice) {
        return jogadas[indice] + jogadas[indice + 1] == MAX_PINOS;
    }

    private int bonusDoSpare(int indice) {
        return jogadas[indice + 2];
    }

    private int somaDoFrame(int indice) {
        return jogadas[indice] + jogadas[indice + 1];
    }
}
