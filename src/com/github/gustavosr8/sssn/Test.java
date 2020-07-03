package com.github.gustavosr8.sssn;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		JPanel panel ;
		GridBagConstraints gbc;
		
		public Test(){
			visual();
		}
		
		public void visual() {
			
			panel = new JPanel(new GridBagLayout());
			gbc = new GridBagConstraints();
			
			gbc.gridx = 0;
			gbc.gridy = 0;
			panel.add(new JButton("A"), gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			panel.add(new JButton("B"), gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 0;
			panel.add(new JButton("C"), gbc);
			
			gbc.gridx = 0;
			gbc.gridy = 1;
			panel.add(new JButton("D"), gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			panel.add(new JButton("E"), gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 1;
			panel.add(new JButton("F"), gbc);
		
			
			Test test = new Test();
			test.add(panel, BorderLayout.SOUTH);
			test.pack();
			test.setVisible(true);
		}
	
}
