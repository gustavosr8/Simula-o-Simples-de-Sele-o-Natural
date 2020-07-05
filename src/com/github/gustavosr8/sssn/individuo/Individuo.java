package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.alimento.Alimento;
import com.github.gustavosr8.sssn.alimento.IAlimento;
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
		alvo = ambiente.maisProximo(getPosicao(), Alimento.class);
		
		if(alvo.getPosicao().equals(getPosicao())) {
			if(((Alimento) alvo).getAlimentando() != null) {
				IComensal[] oponentes = ((Alimento) alvo).getAlimentando(); 
				//aqui tem que criar a disputa   <------------------------------------------------------------------------------------
			}
		}
		
		Posicao futura = new Posicao(getPosicao().x,getPosicao().y);
		
		int DeltaX = (alvo.getPosicao().x - getPosicao().x);
		int DeltaY = (alvo.getPosicao().y - getPosicao().y);
		double incremento = mVelocidade.get();
		
		if(DeltaX == 0) {
			if(DeltaY>0) {
				futura.y -= (Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
			}else {
				futura.y += (Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
			}
		
		
		}else if(DeltaY==0) {
			
			if(DeltaX>0) {
				futura.x-=(Math.abs(DeltaX)<incremento)?Math.abs(DeltaX):incremento;
			}else {
				futura.x+=(Math.abs(DeltaX)<incremento)?Math.abs(DeltaX):incremento;
			}
		
		
		}else if(DeltaX>0) {
			futura.x-=(Math.abs(DeltaX)<incremento)?Math.abs(DeltaX):incremento;
			if(DeltaY>0) {
				futura.y-=(Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
			}else {
				futura.y+=(Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
			}
		
		
		}else if(DeltaX<0) {
			futura.x+=(Math.abs(DeltaX)<incremento)?Math.abs(DeltaX):incremento;
			if(DeltaY>0) {
				futura.y-=(Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
			}else {
				futura.y+=(Math.abs(DeltaY)<incremento)?Math.abs(DeltaY):incremento;
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
		
		mEnergiaArmazenada.set(NEnergia);
		mPosicao = f;
		
	}
	
	@Override
	public void aoTerminarDeComer(double e) {
		
		mEnergiaArmazenada.set(mEnergiaArmazenada.get()+e);
		
	}
	
	@Override
	public IDisputa getDisputa() {
		return mDisputa;
	}
	
	@Override
	public Gene getGene() {
		Gene g = new Gene(mGeneVelocidade.get(), mGeneTamanho.get(), mGeneAltruismo.get());
		return g;
	}
	
	@Override
	public int escolherParceiro(IReproducao[] x) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Gene aoReproduzir(IReproducao x) {
		
		double velF = (mGeneVelocidade.get()+x.getGene().velocidade)/2;
		double tamF = (mGeneTamanho.get()+x.getGene().tamanho)/2;
		double altF = (mGeneAltruismo.get()+x.getGene().altruismo)/2;
		
		
		Gene filho=new Gene(velF,tamF,altF);
		
		return filho;
	}
}
