package com.github.gustavosr8.sssn.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import com.github.gustavosr8.sssn.ambiente.Posicao;
import com.github.gustavosr8.sssn.IObjeto;
import com.github.gustavosr8.sssn.ambiente.IAmbiente;

public class Display extends JPanel implements IDisplay {
	private OnClickPosicao mOnClick;
	private Graphics mGraphics = null;

	private IAmbiente mAmbiente;

	private int mDeltaX = 10;
	private int mDeltaY = 10;
	private int mTamanhoCelula = 16;

	private boolean mDragged = false;
	private int mMouseX = 0;
	private int mMouseY = 0;

	public Display(IAmbiente ambiente, OnClickPosicao onClick) {
		mAmbiente = ambiente;
		mOnClick = onClick;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mMouseX = e.getX();
				mMouseY = e.getY();
				mDragged = false;
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				mDragged = true;
				int x = e.getX();
				int y = e.getY();
				mDeltaX += x - mMouseX;
				mDeltaY += y - mMouseY;
				mMouseX = x;
				mMouseY = y;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				mMouseX = e.getX();
				mMouseY = e.getY();
				repaint();
			}
		});
	}

	private Posicao posicaoSelecionada() {
		int selX = (mMouseX - mDeltaX) / mTamanhoCelula;
		int selY = (mMouseY - mDeltaY) / mTamanhoCelula;
		if (selX >= 0 && selY >= 0 && selX < mAmbiente.getLargura() && selY < mAmbiente.getAltura())
			return new Posicao(selX, selY);
		else
			return null;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.LIGHT_GRAY);
		for (int x = 0; x <= mAmbiente.getLargura(); x++)
			g.drawLine(x * mTamanhoCelula + mDeltaX, mDeltaY, x * mTamanhoCelula + mDeltaX,
					mAmbiente.getAltura() * mTamanhoCelula + mDeltaY);

		for (int y = 0; y <= mAmbiente.getLargura(); y++)
			g.drawLine(mDeltaX, y * mTamanhoCelula + mDeltaY, mAmbiente.getLargura() * mTamanhoCelula + mDeltaX,
					y * mTamanhoCelula + mDeltaY);

		Posicao selPos = posicaoSelecionada();
		if (selPos != null) {
			g.setColor(Color.BLUE);
			g.drawRect(selPos.x * mTamanhoCelula + mDeltaX, selPos.y * mTamanhoCelula + mDeltaY, mTamanhoCelula,
					mTamanhoCelula);
		}

		mGraphics = g;
		for (int x = 0; x < mAmbiente.getLargura(); x++)
			for (int y = 0; y < mAmbiente.getAltura(); y++) {
				IObjeto[] o = mAmbiente.getObj(new Posicao(x, y));
				for (int i = 0; i < o.length; i++)
					o[i].exibir(this);
			}
		mGraphics = null;
	}

	@Override
	public void desenharCirculo(Posicao pos, double raio, Color cor) {
		int r = (int) (raio * (double) mTamanhoCelula);

		int x = pos.x * mTamanhoCelula - mDeltaX - r;
		int y = pos.y * mTamanhoCelula - mDeltaY - r;

		if (x + r > 0 && y + r > 0 && x - r < getWidth() && y - r < getHeight()) {
			mGraphics.setColor(cor);
			mGraphics.fillOval(x, y, 2 * r, 2 * r);
		}
	}

	@Override
	public void desenharLosango(Posicao pos, double raio, Color cor) {
		int r = (int) (raio * (double) mTamanhoCelula);

		int x = pos.x * mTamanhoCelula - mDeltaX - r;
		int y = pos.y * mTamanhoCelula - mDeltaY - r;

		if (x + r > 0 && y + r > 0 && x - r < getWidth() && y - r < getHeight()) {
			int[] xPoints = { x + r, x, x - r, x };
			int[] yPoints = { y, y + r, y, y - r };

			mGraphics.setColor(cor);
			mGraphics.drawPolyline(xPoints, yPoints, 4);
		}
	}

	@Override
	public void desenharTexto(Posicao pos, String texto) {
		int x = pos.x * mTamanhoCelula - mDeltaX;
		int y = pos.y * mTamanhoCelula - mDeltaY;

		if (x + mTamanhoCelula / 2 > 0 && y + mTamanhoCelula / 2 > 0 && x - mTamanhoCelula / 2 < getWidth()
				&& y - mTamanhoCelula / 2 < getHeight()) {
			mGraphics.setColor(Color.BLACK);
			mGraphics.drawString(texto, x, y);
		}
	}
}
