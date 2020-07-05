package com.github.gustavosr8.sssn.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
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
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;
import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.ui.props.ErroProp;
import com.github.gustavosr8.sssn.ui.props.IPropHolder;
import com.github.gustavosr8.sssn.ui.props.Prop;

public class Janela {
	private JFrame mFrame;
	private Display mDisplay;
	private DefaultTableModel mPropTable;
	private Prop[] mShownProps;

	public Janela(IAmbiente ambiente) {
		mFrame = new JFrame("Simulação simples de seleção natural");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mDisplay = new Display(ambiente, new OnClickPosicao() {
			@Override
			public void onClick(MouseEvent e, Posicao pos) {
				IObjeto[] obj = ambiente.getObj(pos);
				if (obj.length == 0) {
					setProps(ambiente);
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
			}
		});

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

		JButton buttonPropsAmbiente = new JButton("Propriedades do ambiente");
		buttonPropsAmbiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setProps(ambiente);
			}
		});

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

		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		dataPanel.add(controles, BorderLayout.PAGE_START);
		dataPanel.add(table, BorderLayout.CENTER);
		dataPanel.add(buttonPropsAmbiente, BorderLayout.PAGE_END);

		JSplitPane split = new JSplitPane(SwingConstants.VERTICAL, mDisplay, dataPanel);
		split.setOneTouchExpandable(true);
		split.setDividerLocation(512);
		split.setResizeWeight(0.5);
		mFrame.setContentPane(split);

		mFrame.setLocationRelativeTo(null);
		mFrame.pack();
		mFrame.setVisible(true);
		mFrame.setSize(1024, 700);

		setProps(ambiente);
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
}
