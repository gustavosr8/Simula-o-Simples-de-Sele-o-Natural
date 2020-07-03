package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public interface IIndividuo extends IObjeto, IComensal, IReproducao {
	// Retorna true se continua vivo.
	public boolean perderEnergia(IAmbiente ambiente, float e);
}
