package gui.toolbar.moduleCreator;

import core.IFactory;
import core.modules.IModule;
import gui.ConcreteFactory;
import gui.controle.ICModule;

/**
 * Créateur de VCF paramétrable par l'utilisateur
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CVCFParCreator implements ModuleCreatorCmd {

	public ICModule newModuleController() {
		IFactory factory = ConcreteFactory.getCFactory();
		IModule module = factory.newVCFPar();
		return (ICModule)module;
	}

}
