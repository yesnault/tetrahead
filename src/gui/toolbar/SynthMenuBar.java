package gui.toolbar;

import gui.MainFrame;

import java.awt.Component;

/**
 * Barre d'outils supérieure de l'application. On dérive <tt>ScalableButtonMenuBar</tt>
 * car on désactive des boutons à certains moments.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class SynthMenuBar extends ScalableButtonMenuBar {

	static final long serialVersionUID = 68716877L; 
	
	/**
	 * Construit la barre avec une fabrique de boutons
	 * @param factory Fabrique de boutons
	 */
	public SynthMenuBar(ScalableButtonFactory factory) {
		super(factory);		
	}
	
	/**
	 * Les boutons lecture, arrêt et option s'activent et se désactivent
	 * en même temps. Quand ils sont actifs, le bouton arrêt et inactif
	 * et vice-versa
	 * @param b
	 */
	public void setIsPlaying(boolean b) {
		Component[] components = getComponents();
		((SynthetizerCmdButton)components[0]).setEnabled(!b);
		((SynthetizerCmdButton)components[1]).setEnabled(b);
		((SynthetizerCmdButton)components[2]).setEnabled(!b);
		((SynthetizerCmdButton)components[3]).setEnabled(!b);
		MainFrame.clearGlassPane();
		repaint();
	}
}
