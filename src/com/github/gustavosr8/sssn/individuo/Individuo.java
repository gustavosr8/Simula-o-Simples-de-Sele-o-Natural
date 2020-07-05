package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.alimento.Alimento;
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
	
	private PropDouble mGastoEnergetico;
	private PropDouble mEnergiaArmazenada;
	
	private IDisputa mDisputa;
	
	private Posicao mPosicao;

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
		Prop[] props = { mVelocidade, mTamanho, mPropAltruismo, mGeneVelocidade, mGeneTamanho, mGeneAltruismo, mGastoEnergetico, mEnergiaArmazenada };
		return props;
	}
	
	@Override
	public void exibir(IDisplay display) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void passo(IAmbiente ambiente) {
		
		IObjeto alvo = null;
		alvo = ambiente.maisProximo(getPosicao(), this);
		Posicao futura = new Posicao(getPosicao().x,getPosicao().y);
		
		if(alvo.getPosicao().equals(getPosicao())) {
			if(alvo instanceof Alimento) {
				if(((Alimento) alvo).getAlimentando() != null) {
					IComensal[] outro = ((Alimento) alvo).getAlimentando();
					//criar uma disputa entre ambos
				}
			}
		}
		
		int DeltaX = (alvo.getPosicao().x - getPosicao().x);
		int DeltaY = (alvo.getPosicao().y - getPosicao().y);
		
		if(DeltaX == 0) {
			if(DeltaY>0) {
				futura.y--;
			}else {
				futura.y++;
			}
		}else if(DeltaY==0) {
			
			if(DeltaX>0) {
				futura.x--;
			}else {
				futura.x++;
			}
		}else if(DeltaX>0) {
			futura.x--;
			if(DeltaY>0) {
				futura.y--;
			}else {
				futura.y++;
			}
		}else if(DeltaX<0) {
			futura.x++;
			if(DeltaY>0) {
				futura.y--;
			}else {
				futura.y++;
			}
		}
		
		aoMover(futura);		
	}
	
	@Override
	public Posicao getPosicao() {
		
		return mPosicao;
	
	}
	
	
	@Override
	public void aoMover(Posicao f){
		
		int DeltaX = Math.abs(f.x - getPosicao().x);
		int DeltaY = Math.abs(f.y - getPosicao().y);
		
		Double NEnergia = mEnergiaArmazenada.get() - ((DeltaY+DeltaX)*mGastoEnergetico.get());
		
		mEnergiaArmazenada.setValue(Double.toString(NEnergia));
		mPosicao = f;
		
	}
	
	@Override
	public void aoTerminarDeComer(double e) {
		
		mEnergiaArmazenada.setValue(Double.toString(mEnergiaArmazenada.get()+e));
		
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
