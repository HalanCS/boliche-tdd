package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int jogadaAtual = 0;

    public void jogar(int pinos) {
        jogadas[jogadaAtual++] = pinos;
    }

    public int pontuacao() {
        int pontos = 0;
        int indice = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (jogadas[indice] + jogadas[indice + 1] == 10) {
                pontos += 10 + jogadas[indice + 2];
                indice += 2;
            } else {
                pontos += jogadas[indice] + jogadas[indice + 1];
                indice += 2;
            }
        }
        return pontos;
    }
}
