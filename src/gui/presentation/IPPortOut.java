package gui.presentation;

import gui.controle.ICPortOut;

/**
 * D�crit les services rendus par la pr�sentation d'un port de sortie (<tt>core.IPortOut</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPortOut extends IPPort{
	/**
	 * @return : le contr�le d'un port de sortie.
	 * @uml.property  name="controle"
	 * @uml.associationEnd  
	 */
	public ICPortOut getControle();
}
