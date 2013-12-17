package gui.presentation.parameterAction;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Listener sur les mouvements de rotation de la souris pour les paramètres continus.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class RotateListener implements MouseListener,MouseMotionListener {
	/**
	 * @uml.property  name="cmd"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ParameterCommand cmd;
	/**
	 * @uml.property  name="isOver"
	 */
	boolean isOver;
	
	public RotateListener(ParameterCommand cmd) {
		this.cmd = cmd;
		isOver = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(isOver){
			cmd.execute(e.getX(),e.getY());	
		}
	}

	public void mouseEntered(MouseEvent e) {
		isOver = true;
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		isOver = false;
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
