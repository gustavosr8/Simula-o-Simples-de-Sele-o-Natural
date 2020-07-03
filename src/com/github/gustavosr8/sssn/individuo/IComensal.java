package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.ui.props.IPropriedades;

public interface IComensal extends IPropriedades {
	public void aoTerminarDeComer(float energiaAlimento);
	public IDisputa getDisputa();
}
