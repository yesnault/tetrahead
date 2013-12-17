package gui.controle;

import core.IDiscreteParameter;
import gui.presentation.IPDiscreteParameter;

/**
 * Décrit les services rendus par le contrôle d'un paramètre discret (<tt>core.IDiscreteParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICDiscreteParameter extends IDiscreteParameter {
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  
	 */
	public IPDiscreteParameter getPresentation();
}
