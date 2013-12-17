package gui.presentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Ce panel représente un petit oscilloscope qui sert visualiser un signal.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ViewOscilloscope extends JPanel {

	static final long serialVersionUID = 8454364L;

	/**
	 * @uml.property name="yMin"
	 */
	private double yMin;

	/**
	 * @uml.property name="yMax"
	 */
	private double yMax;

	private int lastY;
	
	private BufferedImage buff;

	private Graphics2D buffGr2D;

	/**
	 * Cree un nouvel oscilloscope
	 */
	public ViewOscilloscope() {
		this(300, 200);
	}

	/**
	 * Constructeur ajoute pour tests.TestsCompute
	 * <P>
	 * 
	 * @param width
	 * @param height
	 */
	public ViewOscilloscope(int width, int height) {
		yMin = -6.0;
		yMax = 6.0;
		lastY = 0;

		// ameliorer l'oscillo en generant l'image en arriere-plan
		setBackground(Color.BLACK);
		buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffGr2D = (Graphics2D) buff.getGraphics();

		setSize(width, height);
		setPreferredSize(getSize());
	}

	public void paint(Graphics g) {
		g.drawImage(buff, 0, 0, this);
	}

	private int realCoordToPanel(double x) {
		double a, b;
		a = getHeight() / (yMin - yMax);
		b = -a * yMax;
		// System.out.println((int)(a*value + b));
		return (int) (a * x + b);
	}

	/**
	 * Indique l'oscilloscope qu'une nouvelle valeur vient d'tre entre. Le panel
	 * est ensuite rafraichi
	 * 
	 * @param value
	 * @uml.property name="value"
	 */
	public void setValue(double value) {
		int width = getWidth();
		buffGr2D.copyArea(0, 0, width, getHeight(), -1, 0);
		// on ecrit le fond
		buffGr2D.setColor(Color.BLACK);
		buffGr2D.drawRect(width - 1, 0, 2, getHeight());
		// on ecrit la ligne
		int y = realCoordToPanel(value);
		buffGr2D.setColor(Color.GREEN);
		buffGr2D.drawLine(width - 1, lastY, width - 1, y);
		lastY = y;

		repaint();
	}

	/**
	 * Modifie le maximum de l'axe des ordonnes
	 * 
	 * @param yMax :
	 *            nouvelle valeur du maximum de l'axe des ordonnes
	 */
	public void setMax(double yMax) {
		this.yMax = yMax;
	}

	/**
	 * Modifie le minimum de l'axe des ordonnes
	 * 
	 * @param yMin :
	 *            nouvelle valeur du minimum de l'axe des ordonnes
	 */
	public void setMin(double yMin) {
		this.yMin = yMin;
	}
}
