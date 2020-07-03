
public interface IAmbiente extends IPropriedades {
	
	public ICasa[][] casas();
    public void mover(IObjeto i, Posicao alvo);
    public void passo();
    public void proximaRodada();
    public void reiniciar();
    
}
