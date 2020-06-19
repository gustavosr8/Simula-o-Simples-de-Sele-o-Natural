# Componente `CReproducao`

(TODO CComensal)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um objeto capaz de se reproduzir.
Interface | `IReproducao`

### Interfaces

Interfaces associadas a esse componente:

(TODO IReproducao)

```java
public interface IReproducao {
    public Gene getGene();
    public int escolherParceiro(IReproducao[] x);
    public Gene aoReproduzir(IReproducao x);
}
```

## Detalhamento das Interfaces

### Interface `IReproducao`

Método | Objetivo
------ | --------
`getGene` | Retorna o [Gene](Gene.md) do objeto atual.
`escolherParceiro` | Escolhe um parceiro dentre os disponíveis.
`aoReproduzir` | Retorna o [Gene](Gene.md) resultante da reprodução com o outro parceiro.
