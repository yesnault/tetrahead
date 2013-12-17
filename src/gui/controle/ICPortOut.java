package gui.controle;

import core.IPortOut;
import gui.presentation.IPPort;

/**
 * D�crit les services rendus par le contr�le d'un port de sortie (<tt>core.IPortOut</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICPortOut extends IPortOut {
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  
	 */
	public IPPort getPresentation(); 
}
