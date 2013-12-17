package tests;

import core.SyntheCoreException;
import core.modules.Module;
import gui.presentation.ViewOscilloscope;

public class ModuleOscillo extends Module {
	
	/**
	 * @uml.property  name="nAME"
	 */
	private final String NAME = "Oscilloscope";
	ViewOscilloscope o;
	
	protected ModuleOscillo(int width, int height) {
		o = new ViewOscilloscope(width, height) ;
	}

	/**
	 * @uml.property  name="o"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */

	
	public static String PORT_IN = "PortIn" ;
	
	@Override
	public void compute() throws SyntheCoreException {
		// a chaque Tic, on recupere la valeur sur le port d'entree
		o.setValue(getPortIn("PortIn").read()) ;
	}

	@Override
	public void reset() {
		// Rien ici

	}
	
	public String getName() {
		return this.NAME;
	}

	@Override
	public void stop() {
		// rien ici
		
	}

}
