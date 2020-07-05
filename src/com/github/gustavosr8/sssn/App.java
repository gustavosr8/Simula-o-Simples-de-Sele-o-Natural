package com.github.gustavosr8.sssn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.github.gustavosr8.sssn.ambiente.Ambiente;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ui.Janela;

public class App {
	private Ambiente mAmbiente;
	private Timer mTimer;

	private TimerListener mTimerListener;
	private double mVelocidadePlayback;
	private ActionListener mTimerCallback;

	public static void main(String[] args) {
		App app = new App();
		new Janela(app);
	}

	private App() {
		mAmbiente = new Ambiente();
	}

	public IAmbiente getAmbiente() {
		return mAmbiente;
	}

	public void passo() {
		mAmbiente.passo();
		mTimerListener.onPasso();
	}
	
	public void setTimerListener(TimerListener timerListener) {
		mTimerListener = timerListener;
	}

	public void setVelocidadePlayback(double velocidade) {
		mVelocidadePlayback = velocidade;
		if (mTimer != null && mTimer.isRunning()) {
			mTimer.stop();
			if (mVelocidadePlayback > 0.0) {
				mTimer = new Timer((int) (1000.0 / mVelocidadePlayback), mTimerCallback);
				mTimer.start();
			}
		}
	}

	public void play(boolean pausarEmNovaRodada) {
		mTimerCallback = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mAmbiente.passo() && pausarEmNovaRodada)
					pause();
				mTimerListener.onPasso();
			}
		};
		if (mVelocidadePlayback > 0.0) {
			mTimer = new Timer((int) (1000.0 / mVelocidadePlayback), mTimerCallback);
			mTimer.start();
		}
	}

	public void pause() {
		if (mTimer != null && mTimer.isRunning())
			mTimer.stop();
		mTimerListener.onPause();
	}
}
