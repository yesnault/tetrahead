package gui.toolbar;

import gui.toolbar.actionButton.ActionButtonCmd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

/**
 * <tt>ScalableButton</tt> qui exécute une commande quand on clique dessus.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class SynthetizerCmdButton extends ScalableButton {
	
	static final long serialVersionUID = 587541L;
	
	private ActionButtonCmd cmd;
	
	/**
	 * Crée le bouton
	 * @param icon Icône du bouton
	 * @param cmd Commande à exécuter 
	 */
	public SynthetizerCmdButton(ImageIcon icon, ActionButtonCmd cmd) {
		super(icon);
		this.cmd = cmd;
		addMouseListener(new MyMouseListener(this));
	}
	
	/**
	 * Accesseur de la commande
	 * @return Commande du bouton
	 */
	public ActionButtonCmd getCommand() {
		return cmd;
	}
	
	private class MyMouseListener extends MouseAdapter {
		private SynthetizerCmdButton button;
		
		public MyMouseListener(SynthetizerCmdButton button) {
			this.button = button;
		}
		
		public void mouseClicked(MouseEvent event) {
			if(button.isEnabled()) {
				cmd.execute();
			}
		}
	}
}
