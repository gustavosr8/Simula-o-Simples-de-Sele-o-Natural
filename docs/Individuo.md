# Componente `Individuo`

TODO

Campo | Valor
----- | -----
Classe | `<caminho completo da classe com pacotes>`
Autores | `<nome dos membros que criaram o componente>`
Objetivo | Representa um indivíduo na simulação.
Interface | `IIndividuo`

### Interfaces

Interfaces associadas a esse componente:

TODO

```
public interface IGene {
    public float getVelocidade();
    public float getTamanho();
    public float getAltruismo();
}

public interface IComensal {
    public boolean getAgressivo();
    public float getEnergia();
    public void setEnergiva(float e);

    public void tentarComer(IAlimento alimento);
    public void terminarDeComer();
    public boolean estaComendo();
}

public interface IIndividuo extends IGene, IComensal {}
```

## Detalhamento das Interfaces

### Interface `IGene`

Interface para passar as características genéticas do indivíduo adiante.

Método | Objetivo
------ | --------
`getVelocidade` | Retorna a velocidade do indivíduo.
`getTamanho` | Retorna o tamanho do indivíduo.
`getAltruismo` | Retorna o altruísmo do indivíduo.

### Interface `IComensal`

Interface para objetos capazes de comer alimento.

Método | Objetivo
------ | --------
`getAgressivo` | Retorna `true` se e só se for agressivo.
`getEnergia` | Returna a quantidade de energia atual.
`setEnergia` | Define a energia.
`tentarComer` | Tenta comer um alimento.
`terminarDeComer` | Termina de comer um alimento.
`estaComendo` | Retorna `true` se e só se está atualmente comendo um alimento.
