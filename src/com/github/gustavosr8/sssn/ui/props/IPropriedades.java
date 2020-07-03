package com.github.gustavosr8.sssn.ui.props;

public interface IPropriedades {
	public String[] getPropriedades();
    public String getPropriedade(String nome) throws ErroPropriedadeInexistente;
    public void setPropriedade(String nome, String valor) throws ErroPropriedade;
}
