package gui.toolbar.actionButton;

import gui.controle.ICSynthetizer;

/**
 * Commande qui est execut�e quand l'utilisateur clique sur le bouton "arr�t"
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class StopButton implements ActionButtonCmd {
	private ICSynthetizer synthetizer;
	
	public StopButton(ICSynthetizer synthetizer) {
		this.synthetizer = synthetizer;
	}

	public void execute() {
		synthetizer.stopSynthetizer();
	}

}
