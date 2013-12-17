package gui.presentation.parameterAction;

/**
 * Cette interface est utilisée dans une implémentation du design pattern command concernant
 * la saisie de la valeur d'un paramètre.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface Invoker {
	/**
	 * @return la valeur courante de l'invoker.
	 */
	public String getCurrentText();
	
}
