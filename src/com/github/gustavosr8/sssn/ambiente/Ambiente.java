package com.github.gustavosr8.sssn.ambiente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedade;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeInexistente;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeTipoInvalido;

public class Ambiente implements IAmbiente, ActionListener {
	private ArrayList<IObjeto>[][] mCasas;

	private int mAltura;
	private int mLargura;
	private int mPassosPorRodada;
	private int mRepopularCom;
	private boolean mRepopular;

	private int mPassos;

	// IPropriedades
	@Override
	public String[] getPropriedades() {
		String[] props = { "Altura", "Largura", "Passos por rodada", "Repopular ao início de cada rodada",
				"População alvo da repopulação" };
		return props;
	}

	@Override
	public String getPropriedade(String nome) throws ErroPropriedadeInexistente {
		if (nome.equals("Altura")) {
			return Integer.toString(mAltura);
		} else if (nome.equals("Largura")) {
			return Integer.toString(mLargura);
		} else if (nome.equals("Passos por rodada")) {
			return Integer.toString(mPassosPorRodada);
		} else if (nome.equals("Repopular ao início de cada rodada")) {
			return Boolean.toString(mRepopular);
		} else if (nome.equals("População alvo da repopulação")) {
			return Integer.toString(mRepopularCom);
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " não existe em Ambiente.");
		}
	}

	@Override
	public void setPropriedade(String nome, String valor) throws ErroPropriedade {
		if (nome.equals("Altura")) {
			try {
				mAltura = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um número inteiro.");
			}
		} else if (nome.equals("Largura")) {
			try {
				mLargura = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um número inteiro.");
			}
		} else if (nome.equals("Passos por rodada")) {
			try {
				mPassosPorRodada = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um número inteiro.");
			}
		} else if (nome.equals("Repopular ao início de cada rodada")) {
			try {
				mRepopular = Boolean.parseBoolean(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser true / false.");
			}
		} else if (nome.equals("População alvo da repopulação")) {
			try {
				mRepopularCom = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um número inteiro.");
			}
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " não existe em Ambiente.");
		}
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// IAmbiente
	@Override
	public void mover(IObjeto i, Posicao alvo) {
		mCasas[i.getPosicao().x][i.getPosicao().y].remove(i);
		mCasas[alvo.x][alvo.y].add(i);
		i.aoMover(alvo);
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

	@Override
	public boolean passo() {
		for (int i = 0; i < mCasas.length; i++)
			for (int j = 0; j < mCasas[i].length; j++)
				for (int k = 0; k < mCasas[i][j].size(); k++)
					mCasas[i][j].get(k).passo(this);
		mPassos++;
		if (mPassos >= mPassosPorRodada) {
			mPassos = 0;
			// TODO repopular
			return true;
		}
		return false;
	}
}
