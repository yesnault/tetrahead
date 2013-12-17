package gui.toolbar.actionButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <tt>ActionListener</tt> qui exécute une <tt>ActionButtonCmd</tt>
 * dans sa méthode <tt>ActionPerformed</tt> 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ActionButtonListener implements ActionListener {

	ActionButtonCmd cmd;
	
	public ActionButtonListener(ActionButtonCmd cmd) {
		this.cmd = cmd;
	}

	public void actionPerformed(ActionEvent e)  {
		cmd.execute();
	}

}
