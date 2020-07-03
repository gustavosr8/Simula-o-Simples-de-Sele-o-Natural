package com.github.gustavosr8.sssn.ambiente;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.IPropriedades;

public interface IAmbiente extends IPropriedades {
	public ICasa[][] casas();
    public void mover(IObjeto i, Posicao alvo);
    public void passo();
    public void proximaRodada();
    public void reiniciar();    
}
