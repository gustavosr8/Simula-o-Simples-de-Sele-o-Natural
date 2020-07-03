package com.github.gustavosr8.sssn.alimento;

import java.awt.Color;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.individuo.IComensal;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedade;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeInexistente;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeTipoInvalido;

public class Alimento implements IAlimento {
	private float mEnergia;
	private IComensal mAlimentando;
	private Posicao mPosicao;
	
	@Override
	public IComensal getAlimentando() {
		return mAlimentando;
	}

	@Override
	public void setAlimentando(IComensal c) {
		mAlimentando = c;
	}

	@Override
	public void exibir(IDisplay display) {
		display.desenharLosango(mPosicao, 0.5, Color.GREEN);
	}

	@Override
	public void passo(IAmbiente ambiente) {
	}

	@Override
	public String[] getPropriedades() {
		String[] props = {"energia"};
		return props;
	}

	@Override
	public String getPropriedade(String nome) throws ErroPropriedadeInexistente {
		if (nome.equals("energia")) {
			return Float.toString(mEnergia);
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " não existe em Alimento.");
		}
		
	}

	@Override
	public void setPropriedade(String nome, String valor) throws ErroPropriedade {
		if (nome.contentEquals("energia")) {
			try {
				mEnergia = Float.parseFloat(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um número real.");
			}
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " não existe em Alimento.");
		}
	}	
}
