# Componente `CDisputa`

(TODO CDisputa)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um objeto capaz de disputar por alimento com outros.
Interface | `IDisputa`

### Interfaces

Interfaces associadas a esse componente:

(TODO IDisputa)

```java
public interface IDisputa {
    public void incitar(IDisputa outro);
    public boolean passoLuta();
    public void aoGanhar();
}
```

## Detalhamento das Interfaces

### Interface `IDisputa`

Método | Objetivo
------ | --------
`incitar` | Incita uma disputa com outro objeto. Se o outro objeto aceita a disputa, ambos chamam `passoLuta` intercaladamente. Caso contrário, `aoGanhar` é chamado imediatamente neste objeto.
`passoLuta` | Realiza lógica de um passo de luta. Caso este objeto perca nesse passo, deve retornar `false`. Um passo de luta não corresponde a um passo de lógica, sendo que n passos de luta podem acontecer entre um de lógica e outro.
`aoGanhar` | Deve ser chamado ao ganhar sobre o adversário.
