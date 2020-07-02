
public class Individuo implements IReproducao, IComensal, IObjeto {

	@Override
	public String[] getPropriedades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPropriedade(String nome) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPropriedade(String nome, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exibir(IDisplay display) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passo(IAmbiente ambiente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aoTerminarDeComer(float energiaAlimento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDisputa getDisputa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gene getGene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int escolherParceiro(IReproducao[] x) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Gene aoReproduzir(IReproducao x) {
		
		Gene p1 = this.getGene();
		Gene p2 = x.getGene();
		
		Gene filho = new Gene((p1.velocidade+p2.velocidade)/2, (p1.tamanho+p2.tamanho)/2, (p1.altruismo+p2.altruismo)/2);
		
		return filho;
	}

}
