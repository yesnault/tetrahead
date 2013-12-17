package gui.toolbar.moduleCreator;

import gui.controle.ICModule;
/**
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ModuleCreatorCmd {
	public abstract ICModule newModuleController();
}
