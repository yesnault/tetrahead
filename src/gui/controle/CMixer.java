package gui.controle;

import gui.ConcreteFactory;

import java.util.Hashtable;
import core.IDiscreteParameter;

/**
 * Classe implémentant le contrôle de <tt>core.modules.Mixer</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CMixer extends CModule {
	public CMixer() {
		super(ConcreteFactory.getAFactory().newMixer());
	}

	@Override
	protected void addPDiscreteParameters(Hashtable<String, IDiscreteParameter> discreteParameters) {
	}
	public String getName() {
		return abstraction.getName();
	}
}
