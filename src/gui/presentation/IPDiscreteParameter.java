package gui.presentation;

/**
 * Décrit les services rendus par la présentation d'un paramètre discret (<tt>core.IDiscreteParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPDiscreteParameter extends IPParameter{
	/**
	 * Rafraîchie la valeur affichée.
	 * @param str : valeur à afficher.
	 */
	public void refreshView(String str);
	
	/**
	 * Effectue une rotation de 1 cran vers la gauche ou la droite en fonction de 
	 * la valeur booléenne passée en paramètre. <br>
	 * True : rotation vers la droite.
	 * False : rotation vers la gauche.
	 * @param b : indique le sens de rotation.
	 */
	public void rotate(boolean b);
	
	/**
	 * Indique à la présentation qu'elle affiche des images.
	 */
	public void displayImages();
	
	/**
	 * Réinitialise la présentation.
	 */
	public void reset();

}
