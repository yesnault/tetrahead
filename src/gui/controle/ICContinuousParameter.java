package gui.controle;

import core.IContinuousParameter;
import gui.presentation.IPContinuousParameter;

/**
 * D�crit les services rendus par le contr�le d'un param�tre continu (<tt>core.IContinuousParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
