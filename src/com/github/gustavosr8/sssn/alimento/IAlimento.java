package com.github.gustavosr8.sssn.alimento;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.individuo.IComensal;

public interface IAlimento extends IObjeto {
	public IComensal[] getAlimentando();
	public void setAlimentando(IComensal[] c);
	public void terminarDeComerImediatamente(IAmbiente ambiente);
}
