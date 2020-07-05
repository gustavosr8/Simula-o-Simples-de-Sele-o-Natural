package com.github.gustavosr8.sssn.individuo;

import com.github.gustavosr8.sssn.alimento.Alimento;
import com.github.gustavosr8.sssn.alimento.IAlimento;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.PropDouble;
import com.github.gustavosr8.sssn.ui.props.PropInt;

public class Individuo implements IIndividuo {
	private PropDouble mGeneVelocidade;
	private PropDouble mGeneTamanho;
	private PropDouble mGeneAltruismo;

	private PropDouble mVelocidade;
	private PropDouble mTamanho;
	private PropAltruismo mPropAltruismo = new PropAltruismo();

	private PropDouble mGastoEnergetico;
	private PropDouble mEnergia;

	private IDisputa mDisputa;
	
	private Posicao mPosicao;
	
	private class PropAltruismo extends PropInt {
		public PropAltruismo() {
			super("Altruísta", 0, 0, 1);
		}

		@Override
		public String getValue() {
			return mDisputa instanceof DisputaAltruista ? "1" : mDisputa instanceof DisputaAgressivo ? "0" : "?";
		}

		@Override
		public void setValue(String x) throws ErroProp {
			super.setValue(x);
			mDisputa = get() == 1 ? new DisputaAltruista() : new DisputaAgressivo();
		}
	}

	public Individuo(Posicao posicao, IDisputa disputa, Gene gene, double gastoEnergetico, double energiaInicial) {
		mPosicao = posicao;
		mDisputa = disputa;

		mGeneVelocidade = new PropDouble("Velocidade (gene)", gene.velocidade, 0.0, 1e5);
		mGeneTamanho = new PropDouble("Tamanho (gene)", gene.tamanho, 0.0, 1e5);
		mGeneAltruismo = new PropDouble("Altruísmo (gene)", gene.altruismo, 0.0, 1e5);

		mVelocidade = new PropDouble("Velocidade", gene.velocidade, 0.0, 1e5);
		mTamanho = new PropDouble("Tamanho", gene.tamanho, 0.0, 1e5);

		mGastoEnergetico = new PropDouble("Custo de movimentação", gastoEnergetico, 0.0, 1e5);
		mEnergia = new PropDouble("Energia", energiaInicial, 0.0, 1e5);
	}
	
	@Override
	public double getEnergia() {
		return mEnergia.get();
	}

	// IPropHolder

	@Override
	public Prop[] props() {
		Prop[] props = { mVelocidade, mTamanho, mPropAltruismo, mGeneVelocidade, mGeneTamanho, mGeneAltruismo,
				mGastoEnergetico, mEnergia };
		return props;
	}

	// IObjeto

	@Override
	public void exibir(IDisplay display) {
		display.desenharCirculo(getPosicao(), 0.5, getDisputa().getCor());
		display.desenharTexto(getPosicao(),
				Integer.toString((int) mEnergia.get()) + "/" + Integer.toString((int) mTamanho.get()));
	}

	@Override
	public void passo(IAmbiente ambiente) {
		if (getEnergia() == 0 || getEnergia() >= mTamanho.get())
			return;
		
		IAlimento alvo = (IAlimento) ambiente.maisProximo(getPosicao(), Alimento.class);
		if (alvo == null)
			return;

		if (alvo.getPosicao().equals(getPosicao())) {
			if (alvo.getAlimentando().length > 0) {
				getDisputa().conflitar(ambiente, alvo, this, alvo.getAlimentando()[0]);
			} else {
				IComensal[] cs = { this };
				alvo.setAlimentando(cs);
			}
		}

		double distX = (double) (alvo.getPosicao().x - getPosicao().x);
		double distY = (double) (alvo.getPosicao().y - getPosicao().y);
		double dist = Math.sqrt(distX * distX + distY * distY);

		double abs = Math.min(mVelocidade.get(), dist);
		double dirX = distX / dist;
		double dirY = distY / dist;

		Posicao pos = new Posicao(getPosicao().x + (int) (dirX * abs), getPosicao().y + (int) (dirY * abs));

		if (perderEnergia(ambiente, mGastoEnergetico.get() * abs))
			ambiente.mover(this, pos);
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
		mEnergia.set(getEnergia() + e);
	}

	@Override
	public IDisputa getDisputa() {
		return mDisputa;
	}

	@Override
	public boolean perderEnergia(IAmbiente ambiente, double d) {
		mEnergia.set(getEnergia() - d);
		if (getEnergia() <= 0) {
			ambiente.remover(this);
			return false;
		}
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
