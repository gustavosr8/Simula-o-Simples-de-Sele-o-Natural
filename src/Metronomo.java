import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Metronomo extends ActionSubject implements ActionListener{

	private Timer counter;
	private int quantidade, corrente;
	
	public Metronomo(int intervalo, int quantidade) {
		
		this.quantidade = quantidade;
		corrente = 0;
		counter = new Timer(intervalo, this);
	}
	
	public void comecar() {
		counter.start();
	}
	
	public void parar() {
		counter.stop();
	}
	
	public void actionPerformed(ActionEvent event) {
		corrente ++;
		if(corrente >= quantidade) {
			parar();
		}
		notify(event);
		
	}

}
