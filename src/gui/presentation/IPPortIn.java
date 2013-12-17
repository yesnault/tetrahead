package gui.presentation;

import gui.controle.ICPortIn;

/**
 * Décrit les services rendus par la présentation d'un port d'entrée (<tt>core.IPortIn</tt>). 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPortIn extends IPPort {
	/**
	 * @return : le contrôle du port d'entrée.
	 * @uml.property  name="controle"
	 * @uml.associationEnd  
	 */
	public abstract ICPortIn getControle();
}
