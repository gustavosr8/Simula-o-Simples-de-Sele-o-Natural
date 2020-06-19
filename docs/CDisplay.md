# Componente `CDisplay`

(TODO CDisplay)

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Componente utilizado para exibir informações na tela.
Interface | `IDisplay`

### Interfaces

Interfaces associadas a esse componente:

(TODO IComensal)

```java
public interface IDisplay {
    public void desenharCirculo(Posicao pos, int r);
    public void desenharLosango(Posicao pos, int r);
    public void desenharTexto(Posicao pos, String texto);
}
```

## Detalhamento das Interfaces

### Interface `IComensal`

Método | Objetivo
------ | --------
`desenharCirculo` | Desenha um circulo em dada posição no tabuleiro.
`desenharLosango` | Desenha um losango em dada posição no tabuleiro.
`desenharTexto` | Desenha texto em dada posição no tabuleiro.
