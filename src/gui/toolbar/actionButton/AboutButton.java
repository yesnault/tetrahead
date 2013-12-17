package gui.toolbar.actionButton;

import gui.JAboutDialog;

/**
 * Cr�e la bo�te de dialogue "A propos de" et l'affiche � l'�cran
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
