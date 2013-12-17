package gui.controle;

import core.IPortIn;
import core.IPortOut;
import core.SyntheCoreException;
import gui.ConcreteFactory;
import gui.presentation.IPPortOut;
/**
 * Classe implémentant le contrôle de <tt>core.IPortOut</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CPortOut implements ICPortOut {

	/**
	 * @uml.property  name="abstraction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPortOut abstraction; 
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPPortOut presentation;
	
	public CPortOut(String name) {
		abstraction = ConcreteFactory.getAFactory().newPortOut(name);
		presentation = ConcreteFactory.getPFactory().newPPortOut(name, this);
	}

	public void addPortIn(IPortIn p) {
		abstraction.addPortIn(p);
	}

	public void removePortIn(IPortIn p) {
		abstraction.removePortIn(p);
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
	
	public IPPortOut getPresentation() {
		return presentation;
	}

	public void reset() {
		abstraction.reset();
	}

}
