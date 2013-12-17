package gui.controle;

import core.IPortIn;
import core.IPortOut;
import core.SyntheCoreException;
import gui.ConcreteFactory;
import gui.presentation.IPPortIn;
/**
 * Classe implémentant le contrôle de <tt>core.IPortIn</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CPortIn implements ICPortIn {
	
	/**
	 * @uml.property  name="abstraction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPortIn abstraction; 
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPPortIn presentation;
	
	public CPortIn(String name) {
		abstraction = ConcreteFactory.getAFactory().newPortIn(name);
		presentation = ConcreteFactory.getPFactory().newPPortIn(name, this);
	}

	public void connect(IPortOut p) {
		abstraction.connect(p);
	}

	public void disconnect() {
		abstraction.disconnect();
	}

	public void write(double signal) {
		try {
			abstraction.write(signal);
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
	}

	public double read() {
		return abstraction.read();
	}

	public boolean isConnected() {
		return abstraction.isConnected();
	}

	public String getName() {
		return abstraction.getName();
	}

	/**
	 * @return  Returns the presentation.
	 * @uml.property  name="presentation"
	 */
	public IPPortIn getPresentation() {
		return presentation;
	}

	public void reset() {
		abstraction.reset();
	}

}
