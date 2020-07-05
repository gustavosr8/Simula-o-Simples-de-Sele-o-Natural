package com.github.gustavosr8.sssn;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;

public interface IObjeto extends IPropHolder {
	public void exibir(IDisplay display);
	public void passo(IAmbiente ambiente);
	public Posicao getPosicao();
	public void updatePosicao(Posicao f);
}
