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

public class Individuo implements IReproducao, IComensal, IObjeto {
	private PropDouble mGeneVelocidade;
	private PropDouble mGeneTamanho;
	private PropDouble mGeneAltruismo;

	private PropDouble mVelocidade;
	private PropDouble mTamanho;
	private PropAltruismo mPropAltruismo = new PropAltruismo();

	private PropDouble mGastoEnergetico;
	private PropDouble mEnergiaArmazenada;

	private IDisputa mDisputa = new DisputaAgressivo();

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
			if (x.equals("1"))
				mDisputa = new DisputaAltruista();
			else if (x.equals("0"))
				mDisputa = new DisputaAgressivo();
			else
				throw new ErroPropTipoInvalido("A propriedade deve ser 1 ou 0");
		}
	}

	public Individuo(Posicao posicao, Gene gene, double gastoEnergetico, double energiaInicial) {
		mPosicao = posicao;

		mGeneVelocidade = new PropDouble("Velocidade (gene)", gene.velocidade, 0.0, 1e5);
		mGeneTamanho = new PropDouble("Tamanho (gene)", gene.tamanho, 0.0, 1e5);
		mGeneAltruismo = new PropDouble("Altruísmo (gene)", gene.altruismo, 0.0, 1e5);

		mVelocidade = new PropDouble("Velocidade", gene.velocidade, 0.0, 1e5);
		mTamanho = new PropDouble("Tamanho", gene.tamanho, 0.0, 1e5);

		mGastoEnergetico = new PropDouble("Custo de movimentação", gastoEnergetico, 0.0, 1e5);
		mEnergiaArmazenada = new PropDouble("Energia", energiaInicial, 0.0, 1e5);
	}

	// IPorpHolder

	@Override
	public Prop[] props() {
		Prop[] props = { mVelocidade, mTamanho, mPropAltruismo, mGeneVelocidade, mGeneTamanho, mGeneAltruismo,
				mGastoEnergetico, mEnergiaArmazenada };
		return props;
	}

	// IObjeto

	@Override
	public void exibir(IDisplay display) {
		display.desenharCirculo(mPosicao, 0.5, getDisputa().getCor());
		display.desenharTexto(mPosicao,
				Integer.toString((int) mEnergiaArmazenada.get()) + "/" + Integer.toString((int) mTamanho.get()));
	}

	@Override
	public void passo(IAmbiente ambiente) {

		IObjeto alvo = null;
		alvo = ambiente.maisProximo(getPosicao(), Alimento.class);

		if (alvo.getPosicao().equals(getPosicao())) {
			if (((IAlimento) alvo).getAlimentando() != null) {
				IComensal[] oponentes = ((IAlimento) alvo).getAlimentando();
				for (IComensal o : oponentes) {
					getDisputa().conflitar(ambiente, (IAlimento) alvo, this, o);
				}
			}
		}

		Posicao futura = new Posicao(getPosicao().x, getPosicao().y);

		int DeltaX = (alvo.getPosicao().x - getPosicao().x);
		int DeltaY = (alvo.getPosicao().y - getPosicao().y);
		double incremento = mVelocidade.get();

		if (DeltaX == 0) {
			if (DeltaY > 0) {
				futura.y -= (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			} else {
				futura.y += (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			}

		} else if (DeltaY == 0) {

			if (DeltaX > 0) {
				futura.x -= (Math.abs(DeltaX) < incremento) ? Math.abs(DeltaX) : incremento;
			} else {
				futura.x += (Math.abs(DeltaX) < incremento) ? Math.abs(DeltaX) : incremento;
			}

		} else if (DeltaX > 0) {
			futura.x -= (Math.abs(DeltaX) < incremento) ? Math.abs(DeltaX) : incremento;
			if (DeltaY > 0) {
				futura.y -= (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			} else {
				futura.y += (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			}

		} else if (DeltaX < 0) {
			futura.x += (Math.abs(DeltaX) < incremento) ? Math.abs(DeltaX) : incremento;
			if (DeltaY > 0) {
				futura.y -= (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			} else {
				futura.y += (Math.abs(DeltaY) < incremento) ? Math.abs(DeltaY) : incremento;
			}
		}

		ambiente.mover(this, futura);
	}

	@Override
	public Posicao getPosicao() {
		return mPosicao;
	}

	@Override
	public void updatePosicao(Posicao f) {
		mPosicao = f;
	}

	// IComensal

	@Override
	public void aoTerminarDeComer(double e) {

		mEnergiaArmazenada.set(mEnergiaArmazenada.get() + e);

	}

	@Override
	public IDisputa getDisputa() {

		return mDisputa;
	}

	@Override
	public boolean perderEnergia(IAmbiente ambiente, float e) {

		double NEnergia = mEnergiaArmazenada.get() - e;

		if (NEnergia <= 0) {
			ambiente.remover(this);
			return false;
		}

		mEnergiaArmazenada.set(NEnergia);
		return true;
	}

	// IReproducao

	@Override
	public Gene getGene() {
		Gene g = new Gene(mGeneVelocidade.get(), mGeneTamanho.get(), mGeneAltruismo.get());
		return g;
	}

	@Override
	public int escolherParceiro(IReproducao[] x) {
		// Escolhe o primeiro que puder
		return 0;
	}

	@Override
	public Gene aoReproduzir(IReproducao x) {

		double velF = (mGeneVelocidade.get() + x.getGene().velocidade) / 2;
		double tamF = (mGeneTamanho.get() + x.getGene().tamanho) / 2;
		double altF = (mGeneAltruismo.get() + x.getGene().altruismo) / 2;

		Gene filho = new Gene(velF, tamF, altF);

		return filho;
	}

	@Override
	public String getNome() {
		return "Indivíduo";
	}
}
