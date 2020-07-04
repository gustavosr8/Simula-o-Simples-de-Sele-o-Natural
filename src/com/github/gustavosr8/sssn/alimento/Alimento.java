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
	
	private Posicao mPosicao;
	
	private IComensal[] mAlimentando = null;
	private int mPassosAlimentando = 0; // Tempo que alimentando está se alimentando
	
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
		mPassosAlimentando = 0;
		ambiente.remover(this);
		for (int i = 0; i < mAlimentando.length; i++)
			mAlimentando[i].aoTerminarDeComer(mEnergia.get() / mAlimentando.length);
		mAlimentando = null;
	}
	
	// IObjeto
	@Override
	public void exibir(IDisplay display) {
		// A cor começa verde e fica mais branca conforme o alimentado se alimenta
		int rb = mDelayAlimentar.get() > 0 ? 255 * mPassosAlimentando / mDelayAlimentar.get() : 0;
		display.desenharLosango(mPosicao, 0.5, new Color(rb, 255, rb));
	}

	@Override
	public void passo(IAmbiente ambiente) {
		if (mAlimentando != null) {
			mPassosAlimentando++;
			if (mPassosAlimentando == mDelayAlimentar.get())
				terminarDeComerImediatamente(ambiente);
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

	// IPropHolder
	@Override
	public Prop[] props() {
		Prop[] props = { mEnergia, mDelayAlimentar };
		return props;
	}
}
