package gui.presentation.parameterAction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Listener sur les clics gauche et droit de la souris pour les paramètres discrets.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class TickListener implements MouseListener {
	/**
	 * @uml.property  name="cmd"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ParameterCommand cmd;

	public TickListener(ParameterCommand cmd) {
		this.cmd = cmd;
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) { /*Right click*/
			cmd.execute(true);
		} else if (e.getButton() == MouseEvent.BUTTON1) { /*Left click*/
			cmd.execute(false);
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
