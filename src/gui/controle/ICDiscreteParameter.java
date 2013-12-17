package gui.controle;

import core.IDiscreteParameter;
import gui.presentation.IPDiscreteParameter;

/**
 * D�crit les services rendus par le contr�le d'un param�tre discret (<tt>core.IDiscreteParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
