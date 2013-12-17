package gui.controle;

import core.IContinuousParameter;
import gui.presentation.IPContinuousParameter;

/**
 * Décrit les services rendus par le contrôle d'un paramètre continu (<tt>core.IContinuousParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICContinuousParameter extends IContinuousParameter {
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  
	 */
	public IPContinuousParameter getPresentation();

	public void setDoubleValue(double value);
}
