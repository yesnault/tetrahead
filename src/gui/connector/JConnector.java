package gui.connector;

import gui.controle.ICSynthetizer;
import gui.presentation.IPPortIn;
import gui.presentation.IPPortOut;
import gui.presentation.IPSynthetizer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Un JConnector est la représentation d'un connecteur entre un port
 * d'entrée et un port de sortie.
 * 
 * La courbe utilisée pour ce fil est une courbe de Bézier
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
class JConnector extends JPanel {
	
	static final long serialVersionUID = 545242L;
	public static final int RISING_DIAGONAL = 1;
	public static final int LOWERING_DIAGONAL = 2;
	
	private int diagonale;
	private boolean selected;
	private Point2D.Double start, end, one, two;
	private ControlPoint cpOne, cpTwo;
	private List<Point2D.Double> points;
	private IPPortOut portOut;
	private IPPortIn portIn;
	private MyPropertyChangeListener myPropertyChangeListenerOut;
	private MyPropertyChangeListener myPropertyChangeListenerIn;
	
	/**
	 * Construit un connecteur en le reliant à un port de sortie
	 * @param portOut
	 */
	public JConnector(IPPortOut portOut){
		super();
		this.portOut = portOut;
		diagonale = LOWERING_DIAGONAL;
		setBackground(Color.white);
		setOpaque(false);
		start = new Point2D.Double();
		one = new Point2D.Double();
		two = new Point2D.Double();
		end = new Point2D.Double();
		cpOne = new ControlPoint(one);
		cpTwo = new ControlPoint(two);
		add(cpOne, 0);
		add(cpTwo, 0);
		setSelected(false);
		setToolTipText("Cliquez avec le bouton droit pour me supprimer !");
		
		myPropertyChangeListenerOut = new MyPropertyChangeListener(this);
		((JComponent)portOut).getParent().getParent().addPropertyChangeListener(myPropertyChangeListenerOut);
		addMouseListener(new MyMouseListener(this));
	}
	
	/**
	 * Définit les points de contrôle pour la courbe de Bézier
	 * @param start Première extrémité de la courbe
	 * @param one Premier point de contrôle
	 * @param two Second point de contrôle
	 * @param end Seconde extrémité de la courbe
	 */
	public void setCurve(Point2D.Double start, Point2D.Double one, Point2D.Double two, Point2D.Double end) {
		this.start = start;
		this.one = one;
		this.two = two;
		this.end = end;
	}
	
	private final BasicStroke stroke = new BasicStroke(2.0f);
	private final BasicStroke wideStroke = new BasicStroke(5.0f);
	private final Color light = new Color(234, 233, 176);
	private final Color dark = new Color(200, 199, 147);
	
	@Override
	public void paint(Graphics g) {
		
		points = bezier(start, one, two, end, 5);
		
		Graphics2D g2 = (Graphics2D)g;
		
		Iterator<Point2D.Double> it = points.iterator();
		Point2D.Double pt1 = it.next();
		Point2D.Double pt2 = null;
		// Dessine dans la 
		while(it.hasNext()) {
			pt2 = it.next();
			g2.setStroke(wideStroke);
			g.setColor(light);
			g.drawLine((int)pt1.getX(), (int)pt1.getY(), (int)pt2.getX(), (int)pt2.getY());
			g2.setStroke(stroke);
			g.setColor(dark);
			g.drawLine((int)pt1.getX(), (int)pt1.getY()-3, (int)pt2.getX(), (int)pt2.getY()-3);
			g.drawLine((int)pt1.getX(), (int)pt1.getY()+3, (int)pt2.getX(), (int)pt2.getY()+3);
			pt1 = pt2;
		}
		super.paint(g);
	}
	
	private List<Point2D.Double> bezier(Point2D.Double p0, Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, int level) {
		List<Point2D.Double> result = new LinkedList<Point2D.Double>();
		if(level == 0) {
			result.add(p0);
			result.add(p1);
			result.add(p2);
			result.add(p3);
			return result;
		}
		Point2D.Double s0 = p0;
		Point2D.Double s1 = new Point2D.Double(
				(p0.getX() + p1.getX()) / 2,
				(p0.getY() + p1.getY()) / 2);
		Point2D.Double s2 = new Point2D.Double(((p0.getX() + 2*p1.getX() + p2.getX()) / 4),
				((p0.getY() + 2*p1.getY() + p2.getY()) / 4.0));
		Point2D.Double s3 = new Point2D.Double(((p0.getX() + 3*p1.getX() + 3*p2.getX() + p3.getX()) / 8.0),
				((p0.getY() + 3*p1.getY() + 3*p2.getY() + p3.getY()) / 8));
		
		Point2D.Double t0 = s3;
		Point2D.Double t1 = new Point2D.Double(((p1.getX() + 2*p2.getX() + p3.getX()) / 4),
				((p1.getY() + 2*p2.getY() + p3.getY()) / 4));
		Point2D.Double t2 = new Point2D.Double(((p2.getX() + p3.getX()) / 2),
				((p2.getY() + p3.getY()) / 2));
		Point2D.Double t3 = p3;
		
		List<Point2D.Double> list = bezier(s0, s1, s2, s3, level - 1); 
		Iterator<Point2D.Double> it =  list.iterator();
		Point2D.Double pTmp;
		while(it.hasNext()) {
			pTmp = it.next();
			result.add(pTmp);
		}
		
		list = bezier(t0, t1, t2, t3, level - 1);
		it =  list.iterator();
		it.next();
		while(it.hasNext()) {
			pTmp = it.next();
			result.add(pTmp);
		}
		return result;
	}
	
	/**
	 * @return Le port de sortie
	 */
	public IPPortOut getPPortOut() {
		return portOut;
	}
	
	/**
	 * @return Le port d'entrée
	 */
	public IPPortIn getPPortIn() {
		return portIn;
	}
	
	/**
	 * Définit le port d'entrée du connecteur
	 * @param portIn
	 */
	public void setPPortIn(IPPortIn portIn) {
		this.portIn = portIn;
		myPropertyChangeListenerIn = new MyPropertyChangeListener(this);
		((JComponent)portIn).getParent().getParent().addPropertyChangeListener(myPropertyChangeListenerIn);
	}
	
	/**
	 * Définit le type de diagonale
	 * @param diagonale
	 */
	public void setDiagonaleType(int diagonale) {
		this.diagonale = diagonale;
		if(this.diagonale == LOWERING_DIAGONAL) {
			start.setLocation(0, 0);
			one.setLocation(getWidth() / 2, getHeight() / 6);
			two.setLocation(getWidth() / 2, 4 * getHeight() / 6);
			end.setLocation(getWidth() - 1, getHeight() - 1);
		}
		else {
			start.setLocation(0, getHeight() - 1);
			one.setLocation(getWidth() / 2, 4 * getHeight() / 6);
			two.setLocation(getWidth() / 2, getHeight() / 6);
			end.setLocation(getWidth() - 1, 0);
		}
	}
	
	private class MyPropertyChangeListener implements PropertyChangeListener {
		private JConnector connector;
		public MyPropertyChangeListener(JConnector connector) {
			this.connector = connector;
		}
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getPropertyName().equals("location") && portIn != null && portOut != null) {
				Point p1 = new Point(portIn.getCenter());
				Point p2 = new Point(portOut.getCenter());
				
				SwingUtilities.convertPointToScreen(p1, ((JComponent)portIn));
				SwingUtilities.convertPointFromScreen(p1, connector.getParent());
				
				SwingUtilities.convertPointToScreen(p2, ((JComponent)portOut));
				SwingUtilities.convertPointFromScreen(p2, connector.getParent());
				
				int x1 = (int)p1.getX();
				int y1 = (int)p1.getY();
				int x2 = (int)p2.getX();
				int y2 = (int)p2.getY();
				setDiagonaleType(Math.signum(x1 - x2) == Math.signum(y1 - y2) ?
						LOWERING_DIAGONAL :
							RISING_DIAGONAL);
				setLocation(Math.min(x1, x2), Math.min(y1, y2));
				setSize(Math.abs(x1 - x2), Math.abs(y1 - y2));
				repaint();
			}
			else if(evt.getPropertyName().equals("componentZOrder")) {
				if(connector.getParent() != null) {
					connector.getParent().setComponentZOrder(connector, 0);
					connector.repaint();
				}
			}
		}
	}
	
	/**
	 * Définit les coordonnées de la première extrémité de la courbe
	 * @param p
	 */
	public void setStart(Point2D.Double p) {
		start = p;
	}
	
	/**
	 * Définit les coordonnées de la seconde extrémité de la courbe
	 * @param p
	 */
	public void setEnd(Point2D.Double p) {
		end = p;
	}
	
	/**
	 * @return <tt>true</tt> si la courbe est sélectionnée
	 */
	public boolean isSelected() {
		return selected;
	}
	
	@Override
	public boolean contains(int x, int y) {
		if(isSelected() && (cpOne.contains(x, y) || cpTwo.contains(x, y))) {
			return true;
		}
		if(points != null) {
			double diffX, diffY;
			for (Point2D.Double p : points) {
				// Théorème de Pythagore
				diffX = x - p.getX();
				diffY = y - p.getY();
				if(Math.sqrt(diffX * diffX + diffY * diffY) < 3.0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
		cpOne.setVisible(this.selected);
		cpTwo.setVisible(this.selected);
	}
	
	/**
	 * Détruit le connecteur
	 */
	public void destroy() {
		Container c = getParent();
		try {
			portOut.getControle().removePortIn(portIn.getControle());
			portIn.getControle().disconnect();
			c.remove(this);
			((JComponent)portOut).getParent().getParent().removePropertyChangeListener(myPropertyChangeListenerOut);
			((JComponent)portIn).getParent().getParent().removePropertyChangeListener(myPropertyChangeListenerIn);
			portOut = null;
			portIn = null;
		}
		catch(NullPointerException npe){
		}
		c.repaint();
	}
	
	public class MyMouseListener extends MouseAdapter {
		JConnector connector;
		public MyMouseListener(JConnector connector) {
			this.connector = connector;
		}
		
		public void mouseClicked(MouseEvent event) {
			if(event.getButton() == MouseEvent.BUTTON3) {
				try {
					// Vérifie que l'on puisse supprimer le fil
					ICSynthetizer synthetizer = (ICSynthetizer)((IPSynthetizer)connector.getParent()).getControle();
					if(synthetizer.isPlaying()) {
						throw new Exception("Arrêtez la lecture avant de supprimer ce connecteur");
					}
					connector.destroy();
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(connector.getParent(), e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
