import java.util.ArrayList;

public class Casa implements ICasa {

	
	private ArrayList<IObjeto> objetos = new ArrayList<IObjeto>();
		
	
	
	
	public IObjeto[] getObjetos() {
		
		return (IObjeto[]) objetos.toArray();
	
	}

	
	public void adicionar(IObjeto o) {
		
		objetos.add(o);
		
	}


	public void remover(IObjeto o) {
		
		objetos.remove(o);
		
	}

}
