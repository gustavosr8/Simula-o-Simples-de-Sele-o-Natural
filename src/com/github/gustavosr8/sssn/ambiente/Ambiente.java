package com.github.gustavosr8.sssn.ambiente;

import java.util.ArrayList;
import java.util.Random;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.alimento.Alimento;
import com.github.gustavosr8.sssn.individuo.DisputaAgressivo;
import com.github.gustavosr8.sssn.individuo.DisputaAltruista;
import com.github.gustavosr8.sssn.individuo.Gene;
import com.github.gustavosr8.sssn.individuo.IDisputa;
import com.github.gustavosr8.sssn.individuo.IIndividuo;
import com.github.gustavosr8.sssn.individuo.Individuo;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.PropDouble;
import com.github.gustavosr8.sssn.ui.props.PropInt;

public class Ambiente implements IAmbiente {
	private ArrayList<IObjeto>[][] mCasas;

	private PropInt mAltura = new PropTamanho("Altura");
	private PropInt mLargura = new PropTamanho("Largura");
	private PropInt mPassosPorRodada = new PropInt("Passos por rodada", 25, 1, 10000);
	private PropInt mRepopularCom = new PropInt("População limite para repopular", 10, 0, 255);

	private PropInt mAlimentoPorRodada = new PropInt("Quantidade inicial de alimento", 15, 0, 200);
	private PropDouble mEnergiaPorAlimento = new PropDouble("Energia por alimento", 5.0, 0.0, 1e5);
	private PropInt mDelayDeAlimento = new PropInt("Tempo de alimentação", 3, 1, 100);

	private PropDouble mCustoMov = new PropDouble("Custo de movimentação", 0.5, 0.0, 1e5);
	private PropDouble mEnergiaInicialIndividuo = new PropDouble("Energia inicial de indivíduo", 5.0, 0.0, 1e5);
	private PropDouble mEnergiaRepro = new PropDouble("Energia para reprodução", 10.0, 0.0, 1e5);

	private PropDouble mMinimoAltruismoParaAltruista = new PropDouble("Altruísmo mínimo para ser altruísta", 5.0, 0.0,
			1e5);
	private PropDouble mMinimoTamanho = new PropDouble("Valor mínimo do gene tamanho", 10.0, -1e5, 1e5);
	private PropDouble mMaximoTamanho = new PropDouble("Valor máximo do gene tamanho", 50.0, -1e5, 1e5);
	private PropDouble mMinimoVelocidade = new PropDouble("Valor mínimo do gene velocidade", 1.0, -1e5, 1e5);
	private PropDouble mMaximoVelocidade = new PropDouble("Valor máximo do gene velocidade", 5.0, -1e5, 1e5);
	private PropDouble mMinimoAltruismo = new PropDouble("Valor mínimo do gene altruísmo", 0.0, -1e5, 1e5);
	private PropDouble mMaximoAltruismo = new PropDouble("Valor máximo do gene altruísmo", 10.0, -1e5, 1e5);

	private PropInt mPasso = new PropInt("Passo", 0, 0, 10000);

	private ArrayList<IIndividuo> mSobreviventes = new ArrayList<IIndividuo>();
	private Random mRandom = new Random();

	private class PropTamanho extends PropInt {
		public PropTamanho(String nome) {
			super(nome, 25, 1, 255);
		}

		@Override
		public void setValue(String x) throws ErroProp {
			super.setValue(x);
			ArrayList<IObjeto>[][] novo = new ArrayList[mLargura.get()][mAltura.get()];
			for (int i = 0; i < mLargura.get(); i++)
				for (int j = 0; j < mAltura.get(); j++)
					if (i < mCasas.length && j < mCasas[0].length && mPasso.get() != 0)
						novo[i][j] = mCasas[i][j];
					else
						novo[i][j] = new ArrayList<IObjeto>();
			mCasas = novo;
			if (mPasso.get() == 0) {
				limpar();
				comecarRodada();
			}
		}
	}

	public Ambiente() {
		mCasas = new ArrayList[mLargura.get()][mAltura.get()];
		reiniciar();
	}

	@Override
	public void reiniciar() {
		for (int i = 0; i < mLargura.get(); i++)
			for (int j = 0; j < mAltura.get(); j++)
				mCasas[i][j] = new ArrayList<IObjeto>();
		comecarRodada();
	}

	@Override
	public int getAltura() {
		return mAltura.get();
	}

	@Override
	public int getLargura() {
		return mLargura.get();
	}

	@Override
	public void adicionarIndividuoEm(Posicao p) {
		Gene gene = new Gene(
				mRandom.nextDouble() * (mMaximoVelocidade.get() - mMinimoVelocidade.get()) + mMinimoVelocidade.get(),
				mRandom.nextDouble() * (mMaximoTamanho.get() - mMinimoTamanho.get()) + mMinimoTamanho.get(),
				mRandom.nextDouble() * (mMaximoAltruismo.get() - mMinimoAltruismo.get()) + mMinimoAltruismo.get());
		IDisputa disputa = gene.altruismo >= mMinimoAltruismoParaAltruista.get() ? new DisputaAltruista()
				: new DisputaAgressivo();
		adicionar(new Individuo(p, disputa, gene, mCustoMov.get(), mEnergiaInicialIndividuo.get()));
	}

	@Override
	public void adicionarAlimentoEm(Posicao p) {
		adicionar(new Alimento(p, mEnergiaPorAlimento.get(), mDelayDeAlimento.get()));
	}

	@Override
	public void mover(IObjeto i, Posicao alvo) {
		remover(i);
		i.updatePosicao(alvo);
		adicionar(i);
	}

	@Override
	public void remover(IObjeto i) {
		Posicao pos = i.getPosicao();
		mCasas[pos.x][pos.y].remove(i);
	}

	@Override
	public IObjeto maisProximo(Posicao p, Class<?> cls) {
		IObjeto candidato = null;
		double disCandidato = Double.POSITIVE_INFINITY;

		for (int i = 0; i < mCasas.length; i++)
			for (int j = 0; j < mCasas[i].length; j++)
				for (int k = 0; k < mCasas[i][j].size(); k++)
					if (cls.isInstance(mCasas[i][j].get(k)) && new Posicao(i, j).distancia(p) < disCandidato) {
						disCandidato = new Posicao(i, j).distancia(p);
						candidato = mCasas[i][j].get(k);
					}

		return candidato;
	}

	@Override
	public boolean passo() {
		mPasso.set(mPasso.get() + 1);
		if (mPasso.get() >= mPassosPorRodada.get()) {
			mPasso.set(0);
			terminarRodada();
			comecarRodada();
		} else {
			ArrayList<IObjeto> obj = objetos();
			for (int i = 0; i < obj.size(); i++)
				obj.get(i).passo(this);
		}
		return mPasso.get() == mPassosPorRodada.get() - 1;
	}

	@Override
	public IObjeto[] getObj(Posicao p) {
		IObjeto[] ret = new IObjeto[mCasas[p.x][p.y].size()];
		return mCasas[p.x][p.y].toArray(ret);
	}

	private void adicionar(IObjeto obj) {
		Posicao pos = obj.getPosicao();
		if (pos.x >= 0 && pos.y >= 0 && pos.x < getLargura() && pos.y < getAltura())
			mCasas[pos.x][pos.y].add(obj);
	}

	// IPropriedades
	@Override
	public Prop[] props() {
		Prop[] props = { mAltura, mLargura, mPassosPorRodada, mPasso, mRepopularCom, mAlimentoPorRodada,
				mEnergiaPorAlimento, mDelayDeAlimento, mCustoMov, mEnergiaInicialIndividuo, mEnergiaRepro,
				mMinimoAltruismoParaAltruista, mMinimoTamanho, mMaximoTamanho, mMinimoVelocidade, mMaximoVelocidade,
				mMinimoAltruismo, mMaximoAltruismo };
		return props;
	}

	private void limpar() {
		for (int i = 0; i < mLargura.get(); i++)
			for (int j = 0; j < mAltura.get(); j++)
				mCasas[i][j].clear();
	}

	private void terminarRodada() {
		mSobreviventes.clear();
		for (int i = 0; i < mLargura.get(); i++) {
			for (int j = 0; j < mAltura.get(); j++) {
				ArrayList<IObjeto> o = mCasas[i][j];
				for (int k = 0; k < o.size(); k++)
					if (o.get(k) instanceof IIndividuo && ((IIndividuo) o.get(k)).getEnergia() >= mEnergiaRepro.get())
						mSobreviventes.add((IIndividuo) o.get(k));
			}
		}
		limpar();
	}

	private void comecarRodada() {
		int novos = 0;

		while (mSobreviventes.size() >= 2) {
			IIndividuo ind1 = mSobreviventes.remove(0);

			IIndividuo[] disponiveis = new IIndividuo[mSobreviventes.size()];
			mSobreviventes.toArray(disponiveis);

			int id = ind1.escolherParceiro(disponiveis);
			IIndividuo ind2 = mSobreviventes.remove(id);

			Gene gene = ind2.aoReproduzir(ind1);
			IDisputa disputa = gene.altruismo >= mMinimoAltruismoParaAltruista.get() ? new DisputaAltruista()
					: new DisputaAgressivo();
			adicionar(new Individuo(posicaoNaBordaAleatoriaVaga(), disputa, gene, mCustoMov.get(),
					mEnergiaInicialIndividuo.get()));

			ind1.updatePosicao(posicaoNaBordaAleatoriaVaga());
			ind1.perderEnergia(this, mEnergiaRepro.get());
			adicionar(ind1);
			ind2.updatePosicao(posicaoNaBordaAleatoriaVaga());
			ind2.perderEnergia(this, mEnergiaRepro.get());
			adicionar(ind2);

			novos += 3;
		}
		if (mSobreviventes.size() == 1) {
			IIndividuo ind1 = mSobreviventes.remove(0);
			ind1.updatePosicao(posicaoNaBordaAleatoriaVaga());
			adicionar(ind1);

			novos += 1;
		}

		if (novos < mRepopularCom.get())
			for (int i = 0; i < mRepopularCom.get() - novos; i++)
				adicionarIndividuoEm(posicaoNaBordaAleatoriaVaga());

		for (int i = 0; i < mAlimentoPorRodada.get(); i++)
			adicionarAlimentoEm(posicaoAleatoriaVaga());		
	}

	private Posicao posicaoNaBordaAleatoriaVaga() {
		Posicao p = null;
		int contagem = 0;
		do {
			int idx = mRandom.nextInt(2 * getAltura() + 2 * getLargura());
			if (idx < mAltura.get())
				p = new Posicao(0, idx);
			else if (idx < 2 * mAltura.get())
				p = new Posicao(getLargura() - 1, idx - getAltura());
			else if (idx < 2 * mAltura.get() + mLargura.get())
				p = new Posicao(idx - 2 * getAltura(), 0);
			else
				p = new Posicao(idx - 2 * getAltura() - getLargura(), getAltura() - 1);
			contagem++;
		} while (getObj(p).length > 0 && contagem < 50);
		return p;
	}

	private Posicao posicaoAleatoriaVaga() {
		Posicao p = new Posicao(-1, -1);
		int contagem = 0;
		do {
			p.x = mRandom.nextInt(getLargura());
			p.y = mRandom.nextInt(getAltura());
			contagem++;
		} while (getObj(p).length > 0 && contagem < 50);
		return p;
	}

	private ArrayList<IObjeto> objetos() {
		ArrayList<IObjeto> obj = new ArrayList<IObjeto>();
		for (int i = 0; i < getLargura(); i++)
			for (int j = 0; j < getAltura(); j++)
				obj.addAll(mCasas[i][j]);
		return obj;
	}
}
