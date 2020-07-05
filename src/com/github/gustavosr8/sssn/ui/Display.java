package com.github.gustavosr8.sssn.ui;

import java.awt.Color;

import javax.swing.JPanel;

import com.github.gustavosr8.sssn.ambiente.Posicao;

public class Display extends JPanel implements IDisplay {
	private OnClickListener mOnClickListener;
	
	public Display(OnClickListener onClickListener) {
		mOnClickListener = onClickListener;
	}
	
	@Override
	public void desenharCirculo(Posicao pos, double raio, Color cor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desenharLosango(Posicao pos, double raio, Color cor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desenharTexto(Posicao pos, String texto) {
		// TODO Auto-generated method stub
		
	}
}
