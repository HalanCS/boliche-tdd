# Lista de testes planejados – classe `Jogo` (boliche)

Ordem planejada: do mais simples ao mais completo. Cada item vira (pelo menos) um teste,
escrito antes do código de produção (ciclo red → green → refactor).

Legenda: `[ ]` pendente · `[x]` feito

## Partidas de exemplo (pontuação)

- [x] 1. Sarjeta: 20 jogadas de 0 → 0
- [x] 2. Só uns: 20 jogadas de 1 → 20
- [x] 3. Um spare: 5, 5, 3 e 17 zeros → 16
- [x] 4. Um strike: 10, 3, 4 e 16 zeros → 24
- [x] 5. Spare em todos os frames: 21 jogadas de 5 → 150
- [x] 6. Jogo perfeito: 12 jogadas de 10 → 300
- [x] 7. Partida mista: 10 · 7,3 · 9,0 · 10 · 0,8 · 8,2 · 0,6 · 10 · 10 · 10,8,1 → 167

## V1 – quantidade de pinos inválida

- [x] V1a. `jogar(-1)` → `IllegalArgumentException`
- [x] V1b. `jogar(11)` → `IllegalArgumentException`

## V2 – soma do frame inválida

- [x] V2a. Frames 1 a 9: 7 e depois 4 → `IllegalArgumentException`
- [x] V2b. 10º frame sem strike: 7 e depois 4 → `IllegalArgumentException`
- [x] V2c. 10º frame com strike e 2ª jogada ≠ 10: 10, 5, 6 → `IllegalArgumentException`
- [x] V2d. 10º frame com strike: 10, 10, qualquer valor é aceito (ex.: 10, 10, 7)
- [x] V2e. 10º frame com spare: a 3ª jogada pode ser qualquer valor (ex.: 5, 5, 10)

## V3 – fim da partida

- [ ] V3a. `encerrado()` é `false` no início da partida
- [ ] V3b. `encerrado()` é `true` após 20 jogadas sem strike/spare no 10º frame
- [ ] V3c. Com spare no 10º frame, `encerrado()` só é `true` após a jogada extra
- [ ] V3d. Com strike no 10º frame, `encerrado()` só é `true` após as duas jogadas extras
- [ ] V3e. Jogada após o fim da partida → `IllegalStateException`
- [ ] V3f. Jogada após as jogadas extras (ex.: após jogo perfeito) → `IllegalStateException`
