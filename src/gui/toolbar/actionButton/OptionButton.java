package gui.toolbar.actionButton;

import javax.swing.JOptionPane;

import gui.MainFrame;
import gui.controle.ICSynthetizer;

/**
 * Commande ex�cut�e quand on clique sur le bouton "options"
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
				"Fr�quence d'�chantillonage ?",
				ctrl.getFreqEch()
				);
		try {
			Integer i = Integer.parseInt(str);
			ctrl.setFreqEch(i);
		}
		catch(Exception e) {}
	}
}
