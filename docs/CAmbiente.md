# Componente `CAmbiente`

(TODO CAmbiente)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa o ambiente de simulação.
Interface | `IAmbiente`

### Interfaces

Interfaces associadas a esse componente:

(TODO IAmbiente)

```java
public interface IAmbiente {
    public CCasa[][] casas();
    public void mover(CIndividuo i, Posicao alvo);
    public void passo();
    public void proximaRodada();
    public void reiniciar();
}
```

(TODO IPropriedades)

```java
public interface IPropriedades {
    public String[] getPropriedades();
    public String getPropriedade(String nome);
    public void setPropriedade(String nome, String valor);
}
```

## Detalhamento das Interfaces

### Interface `IAmbiente`

Método | Objetivo
------ | --------
`casas` | Retorna as casas presentes no tabuleiro.<br>Veja: [CCasa](CCasa.md)
`mover` | Move um indivíduo de sua [Posicao](Posicao.md) atual para outra.
`passo` | Realiza um passo de lógica de simulação.
`proximaRodada` | Pula para a próxima rodada.
`reiniciar` | Reinicia o ambiente.

### Interface `IPropriedades`

Interface para o usuário gerenciar as propriedades de um componente.

Método | Objetivo
------ | --------
`getPropriedades` | Retorna o nome das propriedades deste componente.
`getPropriedade` | Retorna o valor de dada propriedade.
`setPropriedade` | Define o valor de uma propriedade.
