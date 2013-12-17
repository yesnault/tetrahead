package gui.connector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * Cette classe représente un point de contrôle. On le voit à
 * l'écran par un petit carré rouge. Quand on déplace ce carré,
 * la courbe de Bézier est modifiée.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * @see connector.BezierCurve
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ControlPoint extends JPanel {

	static final long serialVersionUID = 8745245L;
	private Point2D.Double p;

	private int xDebut, yDebut;
	
	private static int TAILLE = 3;
	
	/**
	 * Construit un point de contrôle à partir d'un Point2D.Double
	 * @param p Coordonnées du centre du point de contrôle
	 */
	public ControlPoint(Point2D.Double p) {
		super();
		this.p = p;
		setCenter(p.getX(), p.getY());
		setSize(TAILLE*2, TAILLE*2);
		setBackground(Color.red);
		addMouseListener(new ControlPointMouseListener());
		addMouseMotionListener(new ControlPointMouseMotionListener());
	}
	
	/**
	 * @return Coordonnées du point de contrôle
	 */
	public Point2D.Double getPoint() {
		return p;
	}
	
	/**
	 * @return l'abscisse du point de contrôle
	 */
	public double getCenterX() {
		return p.getX();
	}
	
	/**
	 * @return l'ordonnée du point de contrôle
	 */
	public double getCenterY() {
		return p.getY();
	}
	
	public void paint(Graphics g) {
		setLocation((int)p.getX() - TAILLE, (int)p.getY() - TAILLE);
		g.setColor(Color.RED);
		g.fillOval(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Change le centre du point de contrôle
	 * @param x
	 * @param y
	 */
	public void setCenter(double x, double y) {
		p.setLocation(x, y);
		//setLocation((int)x - TAILLE, (int)y - TAILLE);
		if(getParent() != null) {
			getParent().repaint();
		}
		repaint();
	}
	
	public boolean contains(int x, int y) {
		//return Math.sqrt((x - p.getX())*(x - p.getX()) + (y - p.getY())*(y - p.getY())) <= TAILLE;
		return true;
	}
	
	private class ControlPointMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if(isVisible()) {
				setCenter(getCenterX() + e.getX() - xDebut,
					getCenterY() + e.getY() - yDebut);
			}
		}
	}
	
	private class ControlPointMouseListener extends MouseAdapter {		
		public void mousePressed(MouseEvent e){
			xDebut = e.getX();
			yDebut = e.getY();
		} 
	}
}
