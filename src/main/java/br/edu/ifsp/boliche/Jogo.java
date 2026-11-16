package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int jogadaAtual = 0;
    private int frameAtual = 1;
    private int jogadaNoFrame = 1;

    public void jogar(int pinos) {
        if (pinos < 0 || pinos > 10) {
            throw new IllegalArgumentException("Quantidade de pinos deve estar entre 0 e 10: " + pinos);
        }
        if (frameAtual < 10 && jogadaNoFrame == 2 && jogadas[jogadaAtual - 1] + pinos > 10) {
            throw new IllegalArgumentException("Soma das jogadas do frame não pode exceder 10.");
        }
        if (frameAtual == 10 && jogadaNoFrame == 2 && jogadas[jogadaAtual - 1] < 10
                && jogadas[jogadaAtual - 1] + pinos > 10) {
            throw new IllegalArgumentException("Soma das duas primeiras jogadas do 10º frame sem strike não pode exceder 10.");
        }
        if (frameAtual == 10 && jogadaNoFrame == 3 && jogadas[jogadaAtual - 2] == 10
                && jogadas[jogadaAtual - 1] < 10 && jogadas[jogadaAtual - 1] + pinos > 10) {
            throw new IllegalArgumentException("No 10º frame após strike, se a 2ª jogada não for 10, a soma da 2ª e 3ª não pode exceder 10.");
        }
        jogadas[jogadaAtual++] = pinos;
        if (frameAtual < 10) {
            if (jogadaNoFrame == 1 && pinos < 10) {
                jogadaNoFrame = 2;
            } else {
                frameAtual++;
                jogadaNoFrame = 1;
            }
        } else {
            jogadaNoFrame++;
        }
    }

    public int pontuacao() {
        int pontos = 0;
        int indice = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (ehStrike(indice)) {
                pontos += 10 + bonusDoStrike(indice);
                indice += 1;
            } else if (ehSpare(indice)) {
                pontos += 10 + bonusDoSpare(indice);
                indice += 2;
            } else {
                pontos += somaDoFrame(indice);
                indice += 2;
            }
        }
        return pontos;
    }

    public boolean encerrado() {
        return frameAtual == 10 && jogadaNoFrame > 2;
    }

    private boolean ehStrike(int indice) {
        return jogadas[indice] == 10;
    }

    private int bonusDoStrike(int indice) {
        return jogadas[indice + 1] + jogadas[indice + 2];
    }

    private boolean ehSpare(int indice) {
        return jogadas[indice] + jogadas[indice + 1] == 10;
    }

    private int bonusDoSpare(int indice) {
        return jogadas[indice + 2];
    }

    private int somaDoFrame(int indice) {
        return jogadas[indice] + jogadas[indice + 1];
    }
}
