# Componente `CAlimento`

(TODO CAlimento)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um alimento.
Interface | `IAlimento`

### Interfaces

Interfaces associadas a esse componente:

(TODO IAlimento)

```java
public interface IAlimento {
    public void comecarAlimentar(CComensal c);
    public CComensal alimentando();
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

### Interface `IAlimento`

Método | Objetivo
------ | --------
`comecarAlimentar` | Começa a alimentar um [CComensal](CComensal.md).
`alimentando` | Retorna o objeto atualmente sendo alimento, ou `null` se não houver.
`exibir` | Exibe o alimento na tela.

### Interface `IPropriedades`

Interface para o usuário gerenciar as propriedades de um componente.

Método | Objetivo
------ | --------
`getPropriedades` | Retorna o nome das propriedades deste componente.
`getPropriedade` | Retorna o valor de dada propriedade.
`setPropriedade` | Define o valor de uma propriedade.
