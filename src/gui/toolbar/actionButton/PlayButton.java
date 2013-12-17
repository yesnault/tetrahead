package gui.toolbar.actionButton;

import gui.controle.ICSynthetizer;

/**
 * Commande qui est execut�e quand l'utilisateur clique sur le bouton "lecture"
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PlayButton implements ActionButtonCmd {
	private ICSynthetizer synthetizer;
	
	public PlayButton(ICSynthetizer synthetizer) {
		this.synthetizer = synthetizer;
	}

	public void execute() {
		synthetizer.playSynthetizer();
		new Thread(synthetizer).start();
	}

}
