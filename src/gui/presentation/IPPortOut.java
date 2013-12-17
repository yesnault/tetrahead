package gui.presentation;

import gui.controle.ICPortOut;

/**
 * Décrit les services rendus par la présentation d'un port de sortie (<tt>core.IPortOut</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPortOut extends IPPort{
	/**
	 * @return : le contrôle d'un port de sortie.
	 * @uml.property  name="controle"
	 * @uml.associationEnd  
	 */
	public ICPortOut getControle();
}
