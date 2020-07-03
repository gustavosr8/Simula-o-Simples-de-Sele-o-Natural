package com.github.gustavosr8.sssn;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.IPropriedades;

public interface IObjeto extends IPropriedades {
	public void exibir(IDisplay display);
	public void passo(IAmbiente ambiente);
}
