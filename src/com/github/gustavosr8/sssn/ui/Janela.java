package com.github.gustavosr8.sssn.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;
import com.github.gustavosr8.sssn.App;
import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.TimerListener;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;
import com.github.gustavosr8.sssn.ui.props.Prop;

public class Janela implements TimerListener {
	private static final int EDIT_CURSOR = 0;
	private static final int EDIT_DELETAR = 1;
	private static final int EDIT_ADD_IND = 2;
	private static final int EDIT_ADD_ALIMENTO = 3;
	
	private DefaultTableModel mPropTable;
	private Prop[] mShownProps;
	private JFrame mFrame;
	private JButton mButtonPlayPause;
	private JButton mButtonPular;
	private Display mDisplay;
	
	private boolean mPlaying = false;
	private ModeButton mSelectedBtnMode;
	private int mMode = EDIT_CURSOR;
	
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	private class ModeButton extends JButton implements ActionListener {
		private int mBtnMode;
		
		ModeButton(int mode, String texto) {
			super(texto);
			mBtnMode = mode;
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			mMode = mBtnMode;
			mSelectedBtnMode.setEnabled(true);
			mSelectedBtnMode = this;
			setEnabled(false);
			getRootPane().requestFocus();
		}
	}
	
	public Janela(App app) {
		FlatDarkLaf.install();
		
		app.setTimerListener(this);

		mFrame = new JFrame("Simulação simples de seleção natural");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mDisplay = new Display(app, new DisplayClickListener() {
			@Override
			public void onClick(MouseEvent e, Posicao pos) {
				IObjeto[] obj = app.getAmbiente().getObj(pos);
				switch (mMode) {
				case EDIT_CURSOR:
					if (obj.length == 0) {
						setProps(app.getAmbiente());
					} else if (obj.length == 1) {
						setProps(obj[0]);
					} else {
						JPopupMenu menu = new JPopupMenu();
						for (int i = 0; i < obj.length; i++) {
							IObjeto objeto = obj[i];
							String nome = objeto.getNome() + " (" + Integer.toString(i + 1) + ")";
							JMenuItem item = new JMenuItem(nome);
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									setProps(objeto);
								}
							});
							menu.add(item);
						}
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
					break;
				case EDIT_DELETAR:
					if (obj.length == 1) {
						app.getAmbiente().remover(obj[0]);
					} else if (obj.length != 0) {
						JPopupMenu menu = new JPopupMenu();
						for (int i = 0; i < obj.length; i++) {
							IObjeto objeto = obj[i];
							String nome = objeto.getNome() + " (" + Integer.toString(i + 1) + ")";
							JMenuItem item = new JMenuItem(nome);
							item.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									app.getAmbiente().remover(objeto);
								}
							});
							menu.add(item);
						}
						menu.show(e.getComponent(), e.getX(), e.getY());
					}
					break;
				case EDIT_ADD_IND:
					app.getAmbiente().adicionarIndividuoEm(pos);
					break;
				case EDIT_ADD_ALIMENTO:
					app.getAmbiente().adicionarAlimentoEm(pos);
					break;
				}
				mDisplay.repaint();
			}
		});

		JSlider sliderVelocidade = new JSlider(0, 8, 2);
		sliderVelocidade.setMajorTickSpacing(1);
		sliderVelocidade.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelsSlider = new Hashtable<Integer, JLabel>();
		for (int i = 0; i <= 8; i += 1)
			labelsSlider.put(i, new JLabel(df.format(velocidadeDeSlider(i))));
		sliderVelocidade.setLabelTable(labelsSlider);
		sliderVelocidade.setPaintLabels(true);
		app.setVelocidadePlayback(velocidadeDeSlider(sliderVelocidade.getValue()));
		sliderVelocidade.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				app.setVelocidadePlayback(velocidadeDeSlider(sliderVelocidade.getValue()));
			}
		});

		JCheckBox checkboxPausarTermino = new JCheckBox("Pausar ao terminar rodada");
		app.setPausarEmNovaRodada(false);
		checkboxPausarTermino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.setPausarEmNovaRodada(checkboxPausarTermino.isSelected());
			}
		});

		mButtonPular = new JButton("Próximo passo");
		mButtonPular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.passo();
			}
		});
		
		mButtonPlayPause = new JButton("Reproduzir");
		mButtonPlayPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!mPlaying) {
					mPlaying = true;
					mButtonPlayPause.setText("Pausar");
					mButtonPular.setEnabled(false);
					app.play();
				} else {
					app.pause();
				}
			}
		});

		JPanel cabecalho1 = new JPanel();
		cabecalho1.setLayout(new BoxLayout(cabecalho1, BoxLayout.Y_AXIS));
		cabecalho1.add(new JLabel("Velocidade (passos/segundo)"));
		cabecalho1.add(sliderVelocidade);

		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(mButtonPular);
		buttons.add(mButtonPlayPause);

		JPanel cabecalho2 = new JPanel();
		cabecalho2.setLayout(new GridLayout(0, 1));
		cabecalho2.add(checkboxPausarTermino);
		cabecalho2.add(buttons);

		JPanel cabecalho = new JPanel();
		cabecalho.setLayout(new GridLayout(1, 2));
		cabecalho.add(cabecalho1);
		cabecalho.add(cabecalho2);
		cabecalho.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		JTable table = new JTable(new DefaultTableModel(0, 2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1;
			}

			@Override
			public void fireTableChanged(TableModelEvent e) {
				super.fireTableChanged(e);
				int row = e.getFirstRow();
				if (row != e.getLastRow() || e.getColumn() != 1)
					return;
				try {
					mShownProps[row].setValue(getValueAt(row, 1).toString());
				} catch (ErroProp erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro ao editar propriedade",
							JOptionPane.INFORMATION_MESSAGE);
				}
				mDisplay.repaint();
				updateProps();
			}
		});
		mPropTable = (DefaultTableModel) table.getModel();
		
		JButton reset = new JButton("Reiniciar");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.getAmbiente().reiniciar();
				mDisplay.repaint();
				setProps(app.getAmbiente());
			}
		});

		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		dataPanel.add(cabecalho, BorderLayout.PAGE_START);
		dataPanel.add(table, BorderLayout.CENTER);
		dataPanel.add(reset, BorderLayout.PAGE_END);
		
		JPanel modes = new JPanel();
		modes.setLayout(new GridLayout(1, 4));
		ModeButton mode1 = new ModeButton(EDIT_CURSOR, "Selecionar");
		mSelectedBtnMode = mode1;
		mode1.setEnabled(false);
		modes.add(mode1);
		modes.add(new ModeButton(EDIT_DELETAR, "Deletar"));
		modes.add(new ModeButton(EDIT_ADD_IND, "Adicionar indivíduo"));
		modes.add(new ModeButton(EDIT_ADD_ALIMENTO, "Adicionar alimento"));
		
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(modes, BorderLayout.PAGE_START);
		displayPanel.add(mDisplay, BorderLayout.CENTER);

		JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, displayPanel, dataPanel);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(512);
		split.setResizeWeight(0.5);
		mFrame.setContentPane(split);

		mFrame.setLocationRelativeTo(null);
		mFrame.pack();
		mFrame.setVisible(true);
		mFrame.setSize(1024, 700);
		mFrame.requestFocus();

		setProps(app.getAmbiente());
	}
	
	private double velocidadeDeSlider(int valor) {
		return Math.pow(2.0, (double) valor - 2);
	}

	private void setProps(IPropHolder props) {
		mShownProps = props.props();
		updateProps();
	}

	private void updateProps() {
		mPropTable.setRowCount(0);

		for (int i = 0; i < mShownProps.length; i++) {
			String[] row = { mShownProps[i].getKey(), mShownProps[i].getValue() };
			mPropTable.addRow(row);
		}

		mPropTable.fireTableDataChanged();
	}
	
	@Override
	public void onPause() {
		mPlaying = false;
		mButtonPular.setEnabled(true);
		mButtonPlayPause.setText("Reproduzir");
	}

	@Override
	public void onPasso() {
		mDisplay.repaint();
		updateProps();
	}
}
