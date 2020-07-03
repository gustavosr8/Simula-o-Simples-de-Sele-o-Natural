package com.github.gustavosr8.sssn.ambiente;

import java.util.ArrayList;

import com.github.gustavosr8.sssn.IObjeto;

class Casa implements ICasa {
	private ArrayList<IObjeto> mObjetos = new ArrayList<IObjeto>();

	public Casa() {
	}

	@Override
	public IObjeto[] getObjetos() {
		return (IObjeto[]) mObjetos.toArray();
	}

	@Override
	public void adicionar(IObjeto o) {
		mObjetos.add(o);
	}

	@Override
	public void remover(IObjeto o) {
		mObjetos.remove(o);
	}
}
