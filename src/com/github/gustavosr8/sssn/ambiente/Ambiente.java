package com.github.gustavosr8.sssn.ambiente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.alimento.Alimento;
import com.github.gustavosr8.sssn.individuo.Gene;
import com.github.gustavosr8.sssn.individuo.Individuo;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.PropDouble;
import com.github.gustavosr8.sssn.ui.props.PropInt;

public class Ambiente implements IAmbiente, ActionListener {
	private ArrayList<IObjeto>[][] mCasas;

	private PropInt mAltura = new PropTamanho("Altura");
	private PropInt mLargura = new PropTamanho("Largura");
	private PropInt mPassosPorRodada = new PropInt("Passos por rodada", 10, 1, 10000);
	private PropInt mRepopularCom = new PropInt("População limite para repopular", 10, 0, 255);

	private PropDouble mEnergiaPorAlimento = new PropDouble("Energia por alimento", 2.0, 0.0, 1e5);
	private PropInt mDelayDeAlimento = new PropInt("Tempo de alimentação", 3, 1, 100);

	private PropDouble mCustoMov = new PropDouble("Custo de movimentação", 2.0, 0.0, 1e5);
	private PropDouble mEnergiaInicialIndividuo = new PropDouble("Energia inicial de indivíduo", 10.0, 0.0, 1e5);

	private PropDouble mMinimoAltruismoParaAltruista = new PropDouble("Altruísmo mínimo para ser altruísta", 1.0, 0.0,
			1e5);
	private PropDouble mMinimoTamanho = new PropDouble("Valor mínimo do gene tamanho", 0.0, -1e5, 1e5);
	private PropDouble mMaximoTamanho = new PropDouble("Valor máximo do gene tamanho", 100.0, -1e5, 1e5);
	private PropDouble mMinimoVelocidade = new PropDouble("Valor mínimo do gene velocidade", 0.0, -1e5, 1e5);
	private PropDouble mMaximoVelocidade = new PropDouble("Valor máximo do gene velocidade", 10.0, -1e5, 1e5);
	private PropDouble mMinimoAltruismo = new PropDouble("Valor mínimo do gene altruísmo", 0.0, -1e5, 1e5);
	private PropDouble mMaximoAltruismo = new PropDouble("Valor máximo do gene altruísmo", 10.0, -1e5, 1e5);

	private int mPassos = 0;
	
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
					if (i < mCasas.length && j < mCasas[0].length)
						novo[i][j] = mCasas[i][j];
					else
						novo[i][j] = new ArrayList<IObjeto>();
			mCasas = novo;
		}		
	}

	public Ambiente() {
		mCasas = new ArrayList[mLargura.get()][mAltura.get()];
		for (int i = 0; i < mLargura.get(); i++)
			for (int j = 0; j < mAltura.get(); j++)
				mCasas[i][j] = new ArrayList<IObjeto>();
	}
	
	public int getAltura() {
		return mAltura.get();
	}

	public int getLargura() {
		return mLargura.get();
	}
	
	private void adicionar(IObjeto obj) {
		Posicao pos = obj.getPosicao();
		if (pos.x >= 0 && pos.y >= 0 && pos.x < getLargura() && pos.y < getAltura())
			mCasas[obj.getPosicao().x][obj.getPosicao().y].add(obj);
	}

	@Override
	public void mover(IObjeto i, Posicao alvo) {
		mCasas[i.getPosicao().x][i.getPosicao().y].remove(i);
		mCasas[alvo.x][alvo.y].add(i);
		i.updatePosicao(alvo);
	}

	@Override
	public void remover(IObjeto i) {
		mCasas[i.getPosicao().x][i.getPosicao().y].remove(i);
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

	// IPropriedades
	@Override
	public Prop[] props() {
		Prop[] props = { mAltura, mLargura, mPassosPorRodada, mRepopularCom, mEnergiaPorAlimento,
				mDelayDeAlimento, mCustoMov, mEnergiaInicialIndividuo, mMinimoAltruismoParaAltruista, mMinimoTamanho,
				mMaximoTamanho, mMinimoVelocidade, mMaximoVelocidade, mMinimoAltruismo, mMaximoAltruismo };
		return props;
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		passo();

	}

	@Override
	public boolean passo() {
		for (int i = 0; i < mCasas.length; i++)
			for (int j = 0; j < mCasas[i].length; j++)
				for (int k = 0; k < mCasas[i][j].size(); k++)
					mCasas[i][j].get(k).passo(this);
		mPassos++;
		if (mPassos >= mPassosPorRodada.get()) {
			mPassos = 0;
			// TODO repopular
			return true;
		}
		return false;
	}

	@Override
	public IObjeto[] getObj(Posicao p) {
		IObjeto[] ret = new IObjeto[mCasas[p.x][p.y].size()];
		return mCasas[p.x][p.y].toArray(ret);
	}
}
