# Componente `CCasa`

(TODO CAmbiente)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa uma casa em uma posição do tabuleiro.
Interface | `ICasa`

### Interfaces

Interfaces associadas a esse componente:

(TODO ICasa)

```java
public interface ICasa {
    public CAlimento[] getAlimentos();
    public CIndividuo[] getIndividuos();

    public void adicionar(CAlimento a);
    public void adicionar(CIndividuo i);
    public void remover(CAlimento a);
    public void remover(CIndividuo i);
}
```

## Detalhamento das Interfaces

### Interface `ICasa`

Método | Objetivo
------ | --------
`getAlimentos` | Retorna os [CAlimentos](CAlimento.md) nesta casa.
`getIndividuos` | Retorna os [CIndividuos](CIndividuo.md) nesta casa.
`adicionar` | Adiciona um objeto a esta casa.
`remover` | Remove um objeto desta casa.
