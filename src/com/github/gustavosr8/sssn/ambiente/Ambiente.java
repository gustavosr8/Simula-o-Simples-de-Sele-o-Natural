package com.github.gustavosr8.sssn.ambiente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedade;
import com.github.gustavosr8.sssn.ui.props.ErroPropriedadeInexistente;

public class Ambiente implements IAmbiente, ActionListener {
	@Override
	public ICasa[][] casas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mover(IObjeto i, Posicao alvo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void passo() {
		// TODO Auto-generated method stub
	}

	@Override
	public void proximaRodada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reiniciar() {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] getPropriedades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPropriedade(String nome) throws ErroPropriedadeInexistente {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPropriedade(String nome, String valor) throws ErroPropriedade {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		passo();
	}

}
