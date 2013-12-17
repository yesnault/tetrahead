package gui.toolbar.actionButton;

import gui.JAboutDialog;

/**
 * Crée la boîte de dialogue "A propos de" et l'affiche à l'écran
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class AboutButton implements ActionButtonCmd {

	public void execute() {
		JAboutDialog aboutDialog = new JAboutDialog();
		aboutDialog.setVisible(true);
	}
}
