package gui.controle;

import gui.ConcreteFactory;

import java.util.Hashtable;

import core.IDiscreteParameter;

/**
 * Classe impl�mentant le contr�le de <tt>core.modules.ADSRTrigger</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CADSRTrigger extends CModule {

	public CADSRTrigger() {
		super(ConcreteFactory.getAFactory().newADSRTrigger());
	}

	@Override
	protected void addPDiscreteParameters(Hashtable<String, IDiscreteParameter> discreteParameters) {
	}
	public String getName() {
		return abstraction.getName();
	}

}
