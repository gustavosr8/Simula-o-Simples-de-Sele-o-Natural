package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;

public interface IComensal extends IPropHolder {
	public void aoTerminarDeComer(double e);
	public IDisputa getDisputa();
	// Retorna true se continua vivo.
	public boolean perderEnergia(IAmbiente ambiente, float e);
}
