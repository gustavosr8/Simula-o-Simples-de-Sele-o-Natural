package com.github.gustavosr8.sssn.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public class Janela {
	private JFrame mFrame;
	private Display mDisplay;

	public Janela(IAmbiente ambiente) {
		mFrame = new JFrame("Simulação simples de seleção natural");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mDisplay = new Display(ambiente, null);

		JSlider sliderVelocidade = new JSlider(0, 12, 4);
		sliderVelocidade.setMajorTickSpacing(1);
		sliderVelocidade.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelsSlider = new Hashtable<Integer, JLabel>();
		for (int i = 0; i <= 20; i += 4)
			labelsSlider.put(i, new JLabel(Integer.toString(i / 4)));
		sliderVelocidade.setLabelTable(labelsSlider);
		sliderVelocidade.setPaintLabels(true);


		JButton buttonPlayPause = new JButton("Reproduzir");
		JButton buttonPular = new JButton("Próximo passo");
		
		JCheckBox checkboxPausarTermino = new JCheckBox("Pausar ao terminar rodada");
		
		JPanel controles1 = new JPanel();
		controles1.setLayout(new BoxLayout(controles1, BoxLayout.Y_AXIS));
		controles1.add(new JLabel("Velocidade (passos/segundo)"));
		controles1.add(sliderVelocidade);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(buttonPlayPause);
		buttons.add(buttonPular);
		
		JPanel controles2 = new JPanel();
		controles2.setLayout(new GridLayout(0, 1));
		controles2.add(checkboxPausarTermino);
		controles2.add(buttons);
		
		JPanel controles = new JPanel();
		controles.setLayout(new BoxLayout(controles, BoxLayout.X_AXIS));
		controles.add(controles1);
		controles.add(controles2);
		controles.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		String[] colunas = { "Propriedade", "Valor" };
		Object[][] dados = { { "Teste", "1" }, { "Teste", "2" } };
		JTable tabela = new JTable(dados, colunas);
		tabela.setSize(tabela.getPreferredSize());

		JButton buttonPropsAmbiente = new JButton("Propriedades do ambiente");

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(controles, BorderLayout.PAGE_START);
		panel.add(tabela, BorderLayout.CENTER);
		panel.add(buttonPropsAmbiente, BorderLayout.PAGE_END);

		JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, mDisplay, panel);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(512);
		split.setResizeWeight(0.5);
		mFrame.setContentPane(split);

		mFrame.setLocationRelativeTo(null);
		mFrame.pack();
		mFrame.setVisible(true);
		mFrame.setSize(1024, 700);
	}
}
