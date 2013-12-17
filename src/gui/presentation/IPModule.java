package gui.presentation;

import core.SyntheShowException;
import gui.controle.ICModule;

/**
 * D�crit les services rendus par les pr�sentations des modules (<tt>core.modules.IModule</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPModule extends SyntheShowException{
	/**
	 * Retourne le contr�le d'un module.
	 */
	public ICModule getControle();
	/**
	 * Permet d'afficher dans la pr�sentation une exception.
	 */
	public void showException(Exception e);
}
