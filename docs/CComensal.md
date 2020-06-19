# Componente `CComensal`

(TODO CComensal)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um objeto capaz de se alimentar.
Interface | `IComensal`

### Interfaces

Interfaces associadas a esse componente:

(TODO IComensal)

```java
public interface IComensal {
    public void comecarComer(CAlimento a);
    public void aoTerminarDeComer(float e);

    public CDisputa getCDisputa();
}
```

## Detalhamento das Interfaces

### Interface `IComensal`

Método | Objetivo
------ | --------
`comecarComer` | Começa a comer um [CAlimento](CAlimento.md).
`aoTerminarDeComer` | Deve ser chamado ao terminar de ser alimentado.
`getDisputa` | Retorna o [CDisputa](CDisputa.md) associado ao comensal.
