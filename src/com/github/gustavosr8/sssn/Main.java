package com.github.gustavosr8.sssn;

import com.github.gustavosr8.sssn.ambiente.Ambiente;
import com.github.gustavosr8.sssn.ui.Janela;

public class Main {
	public static void main(String[] args) {
		Ambiente ambiente = new Ambiente();
		new Janela(ambiente);
	}
}
