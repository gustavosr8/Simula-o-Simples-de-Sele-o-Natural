package com.github.gustavosr8.sssn.ambiente;

import com.github.gustavosr8.sssn.IObjeto;

public interface ICasa {
	public IObjeto[] getObjetos();
	public void adicionar(IObjeto o);
	public void remover(IObjeto o);
}
