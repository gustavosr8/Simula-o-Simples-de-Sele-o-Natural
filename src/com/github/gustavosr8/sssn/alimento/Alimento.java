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
	private int mDelayAlimentar; // tempo que um indiv�duo fica se alimentando
	
	private Posicao mPosicao;
	
	private IComensal mAlimentando;
	private int mPassosAlimentando; // tempo que alimentando est� se alimentando
	
	// IAlimento
	@Override
	public IComensal getAlimentando() {
		return mAlimentando;
	}

	@Override
	public void setAlimentando(IComensal c) {
		mAlimentando = c;
	}

	// IObjeto
	@Override
	public void exibir(IDisplay display) {
		// A cor come�a verde e fica mais branca conforme o alimentado se alimenta
		int rb = mDelayAlimentar > 0 ? 255 * mPassosAlimentando / mDelayAlimentar : 0;
		display.desenharLosango(mPosicao, 0.5, new Color(rb, 255, rb));
	}

	@Override
	public void passo(IAmbiente ambiente) {
		if (mAlimentando != null) {
			mPassosAlimentando++;
			if (mPassosAlimentando == mDelayAlimentar) {
				ambiente.remover(this);
				mAlimentando.aoTerminarDeComer(mEnergia);
			}
		}
	}
	
	@Override
	public Posicao getPosicao() {
		return mPosicao;
	}

	@Override
	public void aoMover(Posicao f) {
		mPosicao = f;
	}

	// IPropriedades
	@Override
	public String[] getPropriedades() {
		String[] props = {"Energia", "Tempo de alimenta��o"};
		return props;
	}

	@Override
	public String getPropriedade(String nome) throws ErroPropriedadeInexistente {
		if (nome.equals("Energia")) {
			return Float.toString(mEnergia);
		} else if (nome.equals("Tempo de alimenta��o")) {
			return Integer.toString(mDelayAlimentar);
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " n�o existe em Alimento.");
		}
		
	}

	@Override
	public void setPropriedade(String nome, String valor) throws ErroPropriedade {
		if (nome.equals("Energia")) {
			try {
				mEnergia = Float.parseFloat(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um n�mero real.");
			}
		} else if (nome.equals("Tempo de alimenta��o")) {
			try {
				mDelayAlimentar = Integer.parseInt(valor);
			} catch (NumberFormatException e) {
				throw new ErroPropriedadeTipoInvalido("A propriedade deve ser um n�mero inteiro.");
			}
		} else {
			throw new ErroPropriedadeInexistente("A propriedade " + nome + " n�o existe em Alimento.");
		}
	}
}
