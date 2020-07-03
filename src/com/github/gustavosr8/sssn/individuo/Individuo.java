package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.ErroPropTipoInvalido;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.PropDouble;
import com.github.gustavosr8.sssn.ui.props.ErroProp;

public class Individuo implements IReproducao, IComensal, IObjeto {
	private PropDouble mGeneVelocidade;
	private PropDouble mGeneTamanho;
	private PropDouble mGeneAltruismo;
	
	private PropDouble mVelocidade;
	private PropDouble mTamanho;
	private PropAltruismo mPropAltruismo;
	
	private IDisputa mDisputa;

	private class PropAltruismo extends Prop {
		@Override
		public String getKey() {
			return "Altruísta";
		}

		@Override
		public String getValue() {
			if (mDisputa instanceof DisputaAltruista)
				return Boolean.toString(true);
			else if (mDisputa instanceof DisputaAgressivo)
				return Boolean.toString(false);
			else
				return "?";
		}

		@Override
		public void setValue(String x) throws ErroProp {
			try {
				boolean b = Boolean.parseBoolean(x);
				mDisputa = b ? new DisputaAltruista() : new DisputaAgressivo();
			} catch (NumberFormatException e) {
				throw new ErroPropTipoInvalido("A propriedade deve ser true/false");
			}
		}
	}
	
	@Override
	public Prop[] props() {
		Prop[] props = { mVelocidade, mTamanho, mPropAltruismo, mGeneVelocidade, mGeneTamanho, mGeneAltruismo };
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
		return mDisputa;
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
