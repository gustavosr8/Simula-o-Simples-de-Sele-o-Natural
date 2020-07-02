import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public float getPropriedade(String nome) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPropriedade(String nome, float valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		passo();
		
	}

}
