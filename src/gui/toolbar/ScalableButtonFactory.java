package gui.toolbar;


import java.util.Collection;

/**
 * Fabrique de boutons
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ScalableButtonFactory {
	/**
	 * Crée une liste de boutons prête à être ajoutée dans une barre
	 * de boutons.
	 * 
	 * @return une liste de boutons
	 */
	public abstract Collection<ScalableButton> createButtons();
}
