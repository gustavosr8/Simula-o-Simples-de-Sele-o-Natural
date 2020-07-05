package com.github.gustavosr8.sssn.ambiente;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;

public interface IAmbiente extends IPropHolder {
    public void mover(IObjeto i, Posicao alvo);
    public void remover(IObjeto i);
    
    // Retorna o objeto de tipo cls mais próximo de p.
    public IObjeto maisProximo(Posicao p, Class<?> cls);
    // true se a rodada terminou com este passo.
    public boolean passo();
    // Retorna os objetos em dada posição
    public IObjeto[] getObj(Posicao p);
    
    public int getAltura();
    public int getLargura();
    
    public void reiniciar();
    
    public void adicionarIndividuoEm(Posicao p);
    public void adicionarAlimentoEm(Posicao p);
}
