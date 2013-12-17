package gui.presentation;

import javax.swing.JPanel;

import gui.controle.ICSynthetizer;
import gui.toolbar.SynthMenuBar;
import gui.toolbar.moduleCreator.CreatorDropper;

/** 
 * D�crit les services rendus par la pr�sentation du synth�tiseur (<tt>core.ISynthetizer</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPSynthetizer {
	/**
	 * @return le contr�le du synthetiseur.
	 */
	public ICSynthetizer getControle();
	
	/**
	 * Initialise l'�tat courant de la pr�sentation.
	 * @param b : bool�en � true si en cours de traitement, � false sinon.
	 */
	public void setIsPlaying(boolean b);
	
	/**
	 * @return : retourne la barre de menu du synth�tiseur.
	 */
	public SynthMenuBar getSynthMenuBar();
	
	/**
	 * @return : retourne la poubelle de la pr�sentation.
	 */
	public JPanel getTrash();
	
	/**
	 * @return : retourne le gestionnaire de drop de la pr�sentation.
	 */
	public CreatorDropper getDropper();
	
	/**
	 * rafraichit la taille de la pr�sentation.
	 */
	public void refreshSize();
}
