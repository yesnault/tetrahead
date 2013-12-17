package gui.presentation;

import gui.controle.ICPortIn;

/**
 * D�crit les services rendus par la pr�sentation d'un port d'entr�e (<tt>core.IPortIn</tt>). 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPortIn extends IPPort {
	/**
	 * @return : le contr�le du port d'entr�e.
	 * @uml.property  name="controle"
	 * @uml.associationEnd  
	 */
	public abstract ICPortIn getControle();
}
