package gui.toolbar;

import gui.MainFrame;

import java.awt.Component;

/**
 * Barre d'outils sup�rieure de l'application. On d�rive <tt>ScalableButtonMenuBar</tt>
 * car on d�sactive des boutons � certains moments.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
	 * Les boutons lecture, arr�t et option s'activent et se d�sactivent
	 * en m�me temps. Quand ils sont actifs, le bouton arr�t et inactif
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
