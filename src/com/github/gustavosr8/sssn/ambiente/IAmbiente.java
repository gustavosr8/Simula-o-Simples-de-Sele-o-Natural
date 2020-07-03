package com.github.gustavosr8.sssn.ambiente;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;

public interface IAmbiente extends IPropHolder {
    public void mover(IObjeto i, Posicao alvo);
    public void remover(IObjeto i);
    
    // Retorna o objeto de tipo cls mais pr�ximo de p.
    public IObjeto maisProximo(Posicao p, Class<?> cls);
    // true se a rodada terminou com este passo.
    public boolean passo();
    // Retorna os objetos em dada posi��o
    public IObjeto[] getObj(Posicao p);
    
    public int getAltura();
    public int getLargura();
}
