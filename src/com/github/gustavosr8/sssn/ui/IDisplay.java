package com.github.gustavosr8.sssn.ui;

import java.awt.Color;

import com.github.gustavosr8.sssn.ambiente.Posicao;

public interface IDisplay {
	public void desenharCirculo(Posicao pos, double raio, Color cor);
	public void desenharLosango(Posicao pos, double raio, Color cor);
	public void desenharTexto(Posicao pos, String texto);
}
