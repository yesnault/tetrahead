package gui.presentation.parameterAction;

/**
 * Cette interface est utilis�e dans une impl�mentation du design pattern command concernant
 * la saisie de la valeur d'un param�tre.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
