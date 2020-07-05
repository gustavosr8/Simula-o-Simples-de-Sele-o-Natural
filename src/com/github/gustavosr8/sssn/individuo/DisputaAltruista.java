package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.alimento.IAlimento;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public class DisputaAltruista implements IDisputa {
	@Override
	public void conflitar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind, IComensal outro) {
		outro.getDisputa().aoPediremParaCompartilhar(ambiente, alimentoDisputado, outro, ind);
	}

	@Override
	public void aoPediremParaCompartilhar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind,
			IComensal outro) {
		IComensal[] alimentando = { ind, outro };
		alimentoDisputado.setAlimentando(alimentando);
		alimentoDisputado.terminarDeComerImediatamente(ambiente);
	}

	@Override
	public boolean passoLuta(IAmbiente ambiente, IComensal ind) {
		return false;
	}
}
