package gui.toolbar.moduleCreator;

import gui.ConcreteFactory;
import gui.controle.ICModule;

/**
 * Cr�ateur d'oscilloscope
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class COscilloscopeCreator implements ModuleCreatorCmd {

	public ICModule newModuleController() {
		return (ICModule)ConcreteFactory.getCFactory().newOscilloscope();
	}

}
