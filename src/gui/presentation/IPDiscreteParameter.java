package gui.presentation;

/**
 * D�crit les services rendus par la pr�sentation d'un param�tre discret (<tt>core.IDiscreteParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPDiscreteParameter extends IPParameter{
	/**
	 * Rafra�chie la valeur affich�e.
	 * @param str : valeur � afficher.
	 */
	public void refreshView(String str);
	
	/**
	 * Effectue une rotation de 1 cran vers la gauche ou la droite en fonction de 
	 * la valeur bool�enne pass�e en param�tre. <br>
	 * True : rotation vers la droite.
	 * False : rotation vers la gauche.
	 * @param b : indique le sens de rotation.
	 */
	public void rotate(boolean b);
	
	/**
	 * Indique � la pr�sentation qu'elle affiche des images.
	 */
	public void displayImages();
	
	/**
	 * R�initialise la pr�sentation.
	 */
	public void reset();

}
