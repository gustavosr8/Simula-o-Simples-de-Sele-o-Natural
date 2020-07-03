package com.github.gustavosr8.sssn.ambiente;

import java.util.ArrayList;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ui.props.IPropriedades;

public interface IAmbiente extends IPropriedades {
    public void mover(IObjeto i, Posicao alvo);
    public void remover(IObjeto i);
    // Retorna o objeto de tipo cls mais próximo de p.
    public IObjeto maisProximo(Posicao p, Class<?> cls);
    // true se a rodada terminou com este passo.
    public boolean passo();
}
