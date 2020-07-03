
public class Alimento implements IAlimento {

	private float energia;
	private IComensal atuante = null;
	
	
	public IComensal getAlimentando() {
		return atuante;
	}

	public void setAlimentando(IComensal c) {
		
		if (atuante == null) {
			atuante = c;
		
		}else {
			IDisputa conflito = atuante.getDisputa();
			conflito.incitar();
		}
		
	}


	public void exibir(IDisplay display) {
		// TODO Auto-generated method stub
		
	}

	public void passo(IAmbiente ambiente) {
		// TODO Auto-generated method stub
		
	}

	public String[] getPropriedades() {
		String[] propriedades = {"energia"};
		
		return propriedades;
	}

	public float getPropriedade(String nome) {
		
		if(nome == "energia") {
			return energia;
		}
		
		return 0;
		
	}

	public void setPropriedade(String nome, float valor) {
		
		if(nome == "energia") {
			energia = valor;
		}
	}	
}
