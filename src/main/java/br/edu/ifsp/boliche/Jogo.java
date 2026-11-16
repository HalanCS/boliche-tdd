package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int jogadaAtual = 0;

    public void jogar(int pinos) {
        if (pinos < 0 || pinos > 10) {
            throw new IllegalArgumentException("Quantidade de pinos deve estar entre 0 e 10: " + pinos);
        }
        jogadas[jogadaAtual++] = pinos;
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
