package com.github.gustavosr8.sssn.individuo;

public interface IReproducao {
	public Gene getGene();
    public int escolherParceiro(IReproducao[] x);
    public Gene aoReproduzir(IReproducao x);
}
