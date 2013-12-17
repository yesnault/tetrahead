package gui.controle;

import core.IPortIn;
import gui.presentation.IPPort;

/**
 * D�crit les services rendus par le contr�le d'un port d'entr�e (<tt>core.IPortIn</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
