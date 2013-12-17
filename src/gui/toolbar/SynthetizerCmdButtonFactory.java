package gui.toolbar;

import gui.controle.ICSynthetizer;
import gui.toolbar.actionButton.AboutButton;
import gui.toolbar.actionButton.OptionButton;
import gui.toolbar.actionButton.PlayButton;
import gui.toolbar.actionButton.ResetButton;
import gui.toolbar.actionButton.StopButton;

import java.util.Collection;
import java.util.LinkedList;

import javax.swing.ImageIcon;

/**
 * Fabrique concrète pour la barre d'outils supérieure de l'application.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class SynthetizerCmdButtonFactory implements ScalableButtonFactory {

	private ICSynthetizer controle;
	
	/**
	 * Construit la fabrique
	 * @param controle Composant controle du synthétiseur
	 */
	public SynthetizerCmdButtonFactory(ICSynthetizer controle) {
		this.controle = controle;
	}
	
	/**
	 * Construit les boutons et les renvoie dans une collection 
	 */
	public Collection<ScalableButton> createButtons() {
		Collection<ScalableButton> list = new LinkedList<ScalableButton>();
		ScalableButton buttonPlay = new SynthetizerCmdButton(new ImageIcon("img/start.png"), new PlayButton(controle));
		buttonPlay.setToolTipText("Lecture");
		ScalableButton buttonStop = new SynthetizerCmdButton(new ImageIcon("img/stop.png"), new StopButton(controle));
		buttonStop.setToolTipText("Arrêt");
		ScalableButton buttonReset = new SynthetizerCmdButton(new ImageIcon("img/reset.png"), new ResetButton(controle));
		buttonReset.setToolTipText("Reset");
		ScalableButton buttonOption = new SynthetizerCmdButton(new ImageIcon("img/options.png"), new OptionButton(controle));
		buttonOption.setToolTipText("Options");
		ScalableButton buttonAbout = new SynthetizerCmdButton(new ImageIcon("img/about.png"), new AboutButton());
		buttonAbout.setToolTipText("A propos de");
		buttonPlay.setEnabled(!controle.isPlaying()); 
		buttonStop.setEnabled(controle.isPlaying());
		buttonReset.setEnabled(!controle.isPlaying());
		buttonOption.setEnabled(!controle.isPlaying());
		list.add(buttonPlay);
		list.add(buttonStop);
		list.add(buttonReset);
		list.add(buttonOption);
		list.add(buttonAbout);
		return list;
	}

}
