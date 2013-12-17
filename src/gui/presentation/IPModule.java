package gui.presentation;

import core.SyntheShowException;
import gui.controle.ICModule;

/**
 * Décrit les services rendus par les présentations des modules (<tt>core.modules.IModule</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPModule extends SyntheShowException{
	/**
	 * Retourne le contrôle d'un module.
	 */
	public ICModule getControle();
	/**
	 * Permet d'afficher dans la présentation une exception.
	 */
	public void showException(Exception e);
}
