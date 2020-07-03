package com.github.gustavosr8.sssn.ambiente;

public class Posicao {
	public int x;
    public int y;
    
    public Posicao(int x, int y) {
    	this.x = x;
    	this.y = y;
    }
    
    double distancia(Posicao outro) {
    	double dx = (double)(x - outro.x);
    	double dy = (double)(y - outro.y);
    	return Math.sqrt(dx * dx + dy * dy);
    }
}
