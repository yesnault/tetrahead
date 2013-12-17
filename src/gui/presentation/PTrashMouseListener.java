package gui.presentation;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Un objet <tt>PTrashMouseListener</tt> écoute les événements souris que la corbeille
 * peut recevoir. Cela permet de la déplacer dans la fenêtre avec la souris.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PTrashMouseListener implements MouseMotionListener, MouseListener {
	
	private int diffX, diffY;
	protected JPanel pModule;
	
	public PTrashMouseListener(JPanel trash) {
		this.pModule = trash;
	}
	
	public void mousePressed(MouseEvent e) {
		Point p = new Point(e.getPoint());
		SwingUtilities.convertPointToScreen(p, pModule);
		SwingUtilities.convertPointFromScreen(p, pModule.getParent());
		diffX = (int)p.getX() - pModule.getX();
		diffY = (int)p.getY() - pModule.getY();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent event) {
		Container parent = pModule.getParent();
		if(parent instanceof IPSynthetizer) {
			((IPSynthetizer)parent).refreshSize();
		}
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseDragged(MouseEvent e) {
		Point p = new Point(e.getPoint());
		SwingUtilities.convertPointToScreen(p, pModule);
		SwingUtilities.convertPointFromScreen(p, pModule.getParent());
		pModule.setLocation(Math.max(0, (int)p.getX() - diffX), 
				Math.max(0, (int)p.getY() - diffY));
		pModule.firePropertyChange("location", true, false);
	}
	
	public void mouseMoved(MouseEvent e) {
		
	}
}
