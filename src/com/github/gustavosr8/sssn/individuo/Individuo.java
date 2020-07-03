package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.ErroProp;

public class Individuo implements IReproducao, IComensal, IObjeto {
	Gene mGene;

	int mVelocidade;
	int mTamanho;
	IDisputa mIDisputa;

	@Override
	public Prop[] props() {
		// TODO Auto-generated method stub
		return null;
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
	public Posicao getPosicao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void aoMover(Posicao f) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void aoTerminarDeComer(double e) {
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
