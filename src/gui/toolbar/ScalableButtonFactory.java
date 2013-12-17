package gui.toolbar;


import java.util.Collection;

/**
 * Fabrique de boutons
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ScalableButtonFactory {
	/**
	 * Cr�e une liste de boutons pr�te � �tre ajout�e dans une barre
	 * de boutons.
	 * 
	 * @return une liste de boutons
	 */
	public abstract Collection<ScalableButton> createButtons();
}
