# Componente `Ambiente`

TODO

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa o ambiente de simulação.
Interface | `IAmbiente`

### Interfaces

Interfaces associadas a esse componente:

TODO

```
public interface IAmbiente {
    public void setMultCusto(float m);
    public void setEnergiaPAlimento(float e);
    public void setTempoAliment(float t);
    public void setDim(int altura, int largura);
    public void setOferta(float o);
    public void setEnergiaReprod(float);
    public void setBoundsVelocidade(float min, float max);
    public void setBoundsTamanho(float min, float max);
    public void setBoundsAltruísmo(float min, float max);

    public IIndividuo[][] getIndividuos();
    public IAlimento[][] getAlimentos();

    public void passo();
    public void proximaRodada();
    public void reiniciar();
}
```

## Detalhamento das Interfaces

### Interface `IAmbiente`

Interface para passar as características genéticas do indivíduo adiante.

Método | Objetivo
------ | --------
`setMultCusto` | Define o multiplicador de custo de movimento.
`setEnergiaPAlimento` | Define o valor energético do alimento.
`setTempoAliment` | Define o tempo de alimentação.
`setDim` | Define as dimensões do tabuleiro.
`setOferta` | Define a quantidade de alimento por rodada.
`setEnergiaReprod` | Define a energia mínima para se reproduzir.
`setBoundsVelocidade` | Define mínimo e máximo para velocidade.
`setBoundsTamanho` | Define mínimo e máximo para tamanho.
`setBoundsAltruísmo` | Define mínimo e máximo para altruísmo.
`getIndividuos` | Retorna a grade de indivíduos.
`getAlimentos` | Retorna a grade de alimentos.
`passo` | Realiza um passo de lógica de simulação.
`proximaRodada` | Pula para a próxima rodada.
`reiniciar` | Reinicia o ambiente.
