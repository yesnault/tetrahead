package gui.controle;

import core.IPortIn;
import gui.presentation.IPPort;

/**
 * Décrit les services rendus par le contrôle d'un port d'entrée (<tt>core.IPortIn</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICPortIn extends IPortIn {
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  
	 */
	public IPPort getPresentation(); 
}
