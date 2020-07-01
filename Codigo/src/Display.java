import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display implements IDisplay {
	
	public static String DIRETORIO = Display.class.getResource(".").getPath();
	JFrame opening;
	JFrame main;
	JPanel p_openig;
	JPanel background;
	JButton iniciar;
	JButton info;
	JPanel tabuleiro;
	GridBagConstraints gbc;
	
	int size;
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	ImageIcon circuloAzul = new ImageIcon(DIRETORIO + "circulinholinho.jpg");
	
	
	
	
	
	Display(){
		visual();
	}
	
	private void visual() {
		
		System.out.println(DIRETORIO);
		
		opening = new JFrame("Simulação Simples de Seleção natural ");
		opening.setVisible(true);
		opening.setSize(400,100);
		opening.setLocation(dim.width/2-opening.getSize().width/2, dim.height/2-opening.getSize().height/2);
		opening.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p_openig = new JPanel();
		
		info = new JButton("tutorial");
		info.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
						+"\n"
						+ "Etiam eget ligula eu lectus lobortis condimentum. Aliquam nonummy auctor massa. "
						+"\n"
						+ "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. "
						+"\n"
						+ "Nulla at risus. Quisque purus magna, auctor et, sagittis ac, posuere eu, lectus. "
						+"\n"
						+ "Nam mattis, felis ut adipiscing.");	
			}	
		});
		
		iniciar = new JButton("iniciar");
		iniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent a) {
				main = new JFrame();
				main.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				main.setUndecorated(true);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setVisible(true);
				
				background = new JPanel(new BorderLayout());
				
								
				tabuleiro = new JPanel(new GridBagLayout());
				gbc = new GridBagConstraints();
				
				size = 10; // aqui vai algum getter pra pegar o tamanho do tabuleiro
				
				for(int i=0; i<size; i++) {
					for(int j=0; j<size; j++) {
						
						gbc.gridx = i;
						gbc.gridy = j;
						
						tabuleiro.add(new JButton(circuloAzul), gbc);
					}
				}
				
				gbc.gridx = 1;
				gbc.gridy = 1;
				
				tabuleiro.add(new JButton(circuloAzul), gbc);
				
				background.add(tabuleiro, BorderLayout.CENTER);
				main.add(background);
			}
		});
		
		p_openig.add(iniciar);
		p_openig.add(info);
		
		opening.add(p_openig);
		
	}

	@Override
	public void desenharCirculo(Posicao pos, int raio, float r, float g, float b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desenharLosango(Posicao pos, int raio, float r, float g, float b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desenharTexto(Posicao pos, String texto) {
		// TODO Auto-generated method stub
		
	}
	

}
