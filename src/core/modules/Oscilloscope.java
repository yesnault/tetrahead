package core.modules;

import gui.ConcreteFactory;
import core.SyntheCoreException;

/**
 * 
 * Module Oscilloscope. La m�thode compute() de ce module ne fait rien
 * de particulier, hormis de renvoyer la valeur qu'il a re�ue sur son port
 * d'entr�e.<P>
 * 
 * Il est possible d'am�liorer ce module en le faisant travailler avec un buffer.
 * Pour cela, le module d'affichage doit �tre revu �galement pour ne pas qu'il se mette � 
 * jour � tous les tours d'horloge.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class Oscilloscope extends Module {

	public static final String PORT_IN = "In";
	private final String NAME = "Oscilloscope";
	private double value;

	public Oscilloscope() {
		usine = ConcreteFactory.getFactory();
		this.addPortsIn(PORT_IN, usine.newPortIn(PORT_IN));
		value = 0.0;
	}
	@Override
	public void compute() throws SyntheCoreException {
		value = getPortIn(PORT_IN).read();
	}

	@Override
	public void reset() {
		value = 0.0;
	}

	@Override
	public void stop() {
		
	}

	public String getName() {
		return this.NAME;
	}
	
	public double getValue() {
		return value;
	}

}
