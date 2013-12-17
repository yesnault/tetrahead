package gui.controle;

import core.modules.IModule;
import gui.presentation.IPModule;

/**
 * D�crit les services rendus par le contr�le d'un module (<tt>core.modules.IModule</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICModule extends IModule {
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  
	 */
	public IPModule getPresentation();
	public void showException(Exception e);
}
