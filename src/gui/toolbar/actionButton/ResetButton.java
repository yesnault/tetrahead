package gui.toolbar.actionButton;

import gui.controle.ICSynthetizer;

/**
 * Commande qui est executée quand l'utilisateur clique sur le bouton "reset"
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ResetButton implements ActionButtonCmd {
	private ICSynthetizer synthetizer;
	
	public ResetButton(ICSynthetizer synthetizer) {
		this.synthetizer = synthetizer;
	}

	public void execute() {
		synthetizer.resetSynthetizer();
	}

}
