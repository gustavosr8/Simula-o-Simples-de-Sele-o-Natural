# Componente `CIndividuo`

(TODO CIndividuo)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um indivíduo da simulação.
Interface | `IIndividuo`

### Interfaces

Interfaces associadas a esse componente:

(TODO IIndividuo)

```java
public interface IIndividuo {
    public CReproducao getReproducao();
    public CComensal getComensal();
    public void aoMover(Posicao pos);
    public void exibir(CDisplay display);
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
`getReproducao` | Retorna o [CReproducao](CReproducao.md) associado ao indivíduo.
`getComensal` | Retorna o [CComensal](CComensal.md) associado ao indivíduo.
`aoMover` | Chamado após o indivíduo ser movido.
`exibir` | Exibe o indivíduo na tela.

### Interface `IPropriedades`

Interface para o usuário gerenciar as propriedades de um componente.

Método | Objetivo
------ | --------
`getPropriedades` | Retorna o nome das propriedades deste componente.
`getPropriedade` | Retorna o valor de dada propriedade.
`setPropriedade` | Define o valor de uma propriedade.
