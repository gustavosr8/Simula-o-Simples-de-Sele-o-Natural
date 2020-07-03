package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedade;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeInexistente;

public class Individuo implements IReproducao, IComensal, IObjeto {
	@Override
	public String[] getPropriedades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPropriedade(String nome) throws ErroPropriedadeInexistente {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPropriedade(String nome, String valor) throws ErroPropriedade {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exibir(IDisplay display) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passo(IAmbiente ambiente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aoTerminarDeComer(float energiaAlimento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDisputa getDisputa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gene getGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int escolherParceiro(IReproducao[] x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Gene aoReproduzir(IReproducao x) {
		// TODO Auto-generated method stub
		return null;
	}
}
