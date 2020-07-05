package com.github.gustavosr8.sssn.alimento;

import java.awt.Color;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.individuo.IComensal;
import com.github.gustavosr8.sssn.ui.IDisplay;
import com.github.gustavosr8.sssn.ui.props.Prop;
import com.github.gustavosr8.sssn.ui.props.PropDouble;
import com.github.gustavosr8.sssn.ui.props.PropInt;

public class Alimento implements IAlimento {
	private PropDouble mEnergia;
	private PropInt mDelayAlimentar; // tempo que um indivíduo fica se alimentando
	private int mTempoAlimentando = 0;

	private Posicao mPosicao;

	private IComensal[] mAlimentando = {};

	public Alimento(Posicao pos, double energia, int delayAlimentar) {
		mPosicao = pos;
		mEnergia = new PropDouble("Energia", energia, -1000.0, 1000.0);
		mDelayAlimentar = new PropInt("Tempo de alimentação", delayAlimentar, 1, 100);
	}

	@Override
	public IComensal[] getAlimentando() {
		return mAlimentando;
	}

	@Override
	public void setAlimentando(IComensal[] c) {
		mAlimentando = c;
	}

	@Override
	public void terminarDeComerImediatamente(IAmbiente ambiente) {
		mTempoAlimentando = 0;
		ambiente.remover(this);
		for (int i = 0; i < mAlimentando.length; i++)
			mAlimentando[i].aoTerminarDeComer(mEnergia.get() / mAlimentando.length);
		IComensal[] vazio = {};
		mAlimentando = vazio;
	}

	// IObjeto
	@Override
	public void exibir(IDisplay display) {
		display.desenharLosango(getPosicao(), 0.25, Color.GREEN);
	}

	@Override
	public void passo(IAmbiente ambiente) {
		if (mAlimentando.length > 0) {
			mTempoAlimentando++;
			if (mTempoAlimentando >= mDelayAlimentar.get())
				terminarDeComerImediatamente(ambiente);
		}
	}

	@Override
	public Posicao getPosicao() {
		return mPosicao;
	}

	@Override
	public void updatePosicao(Posicao f) {
		mPosicao = f;
	}
	
	@Override
	public String getNome() {
		return "Alimento";
	}

	// IPropHolder
	@Override
	public Prop[] props() {
		Prop[] props = { mEnergia, mDelayAlimentar };
		return props;
	}
}
