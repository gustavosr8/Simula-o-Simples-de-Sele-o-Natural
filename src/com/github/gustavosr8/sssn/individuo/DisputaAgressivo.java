package com.github.gustavosr8.sssn.individuo;

import java.awt.Color;

import com.github.gustavosr8.sssn.alimento.IAlimento;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public class DisputaAgressivo implements IDisputa {
	@Override
	public void conflitar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind, IComensal outro) {
		IComensal ganhador = null;
		while (ganhador == null) {
			if (!outro.getDisputa().passoLuta(ambiente, outro))
				ganhador = ind;
			else if (!passoLuta(ambiente, ind))
				ganhador = outro;
		}

		IComensal[] alimentando = { ganhador };
		alimentoDisputado.setAlimentando(alimentando);
		alimentoDisputado.terminarDeComerImediatamente(ambiente);
	}

	@Override
	public void aoPediremParaCompartilhar(IAmbiente ambiente, IAlimento alimentoDisputado, IComensal ind,
			IComensal outro) {
		alimentoDisputado.terminarDeComerImediatamente(ambiente);
	}

	@Override
	public boolean passoLuta(IAmbiente ambiente, IComensal ind) {
		return ind.perderEnergia(ambiente, 1.0);
	}
	
	@Override
	public Color getCor() {
		return Color.RED;
	}
}
