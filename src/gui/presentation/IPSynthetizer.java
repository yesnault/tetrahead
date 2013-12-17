package gui.presentation;

import javax.swing.JPanel;

import gui.controle.ICSynthetizer;
import gui.toolbar.SynthMenuBar;
import gui.toolbar.moduleCreator.CreatorDropper;

/** 
 * Décrit les services rendus par la présentation du synthétiseur (<tt>core.ISynthetizer</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPSynthetizer {
	/**
	 * @return le contrôle du synthetiseur.
	 */
	public ICSynthetizer getControle();
	
	/**
	 * Initialise l'état courant de la présentation.
	 * @param b : booléen à true si en cours de traitement, à false sinon.
	 */
	public void setIsPlaying(boolean b);
	
	/**
	 * @return : retourne la barre de menu du synthétiseur.
	 */
	public SynthMenuBar getSynthMenuBar();
	
	/**
	 * @return : retourne la poubelle de la présentation.
	 */
	public JPanel getTrash();
	
	/**
	 * @return : retourne le gestionnaire de drop de la présentation.
	 */
	public CreatorDropper getDropper();
	
	/**
	 * rafraichit la taille de la présentation.
	 */
	public void refreshSize();
}
