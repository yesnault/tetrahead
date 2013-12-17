package gui.controle;

import gui.ConcreteFactory;
import gui.presentation.IPOscilloscope;
import gui.presentation.IPParameter;
import gui.presentation.IPPort;

import java.util.ArrayList;
import java.util.Hashtable;


import core.IDiscreteParameter;
import core.IPortOut;
import core.modules.Oscilloscope;
/**
 * Classe implémentant le contrôle de <tt>core.modules.Oscilloscope</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class COscilloscope extends CModule {

	public COscilloscope() {
		super(ConcreteFactory.getAFactory().newOscilloscope());
	}

	@Override
	protected void createPresentation(ArrayList<IPParameter> pParams, ArrayList<IPPort> pports, IPortOut po, CModule module) {
		presentation = ConcreteFactory.getPFactory().newPOscilloscope(pports.get(0),this);
	}
	
	@Override
	protected void addPDiscreteParameters(
			Hashtable<String, IDiscreteParameter> discreteParameters) {
	}

	public String getName() {
		return abstraction.getName();
	}
	
	@Override
	public void compute() throws Exception {
		super.compute();
		((IPOscilloscope)presentation).setValue(((Oscilloscope)abstraction).getValue());
		
	}

}
