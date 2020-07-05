package com.github.gustavosr8.sssn.individuo;

import java.awt.Color;

import com.github.gustavosr8.sssn.alimento.IAlimento;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public interface IDisputa {
	public void conflitar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind, IComensal outro);

	public void aoPediremParaCompartilhar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind, IComensal outro);
	
	// Boolean: false se fugiu da luta ou perdeu.
    public boolean passoLuta(IAmbiente ambiente, IComensal ind);
    
    public Color getCor();
}
