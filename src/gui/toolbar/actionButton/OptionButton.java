package gui.toolbar.actionButton;

import javax.swing.JOptionPane;

import gui.MainFrame;
import gui.controle.ICSynthetizer;

/**
 * Commande exécutée quand on clique sur le bouton "options"
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class OptionButton implements ActionButtonCmd {

	private ICSynthetizer ctrl;
	
	public OptionButton(ICSynthetizer ctrl) {
		this.ctrl = ctrl;
	}
	
	public void execute() {
		MainFrame.clearGlassPane();
		String str = JOptionPane.showInputDialog(MainFrame.getInstance(),
				"Fréquence d'échantillonage ?",
				ctrl.getFreqEch()
				);
		try {
			Integer i = Integer.parseInt(str);
			ctrl.setFreqEch(i);
		}
		catch(Exception e) {}
	}
}
