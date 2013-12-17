package core.modules;

import gui.ConcreteFactory;

import java.util.Collection;
import java.util.Iterator;
import core.IContinuousParameter;
import core.IPortIn;
import core.ParameterValue;
import core.SyntheCoreException;

/**
 * Module assurant un simple m�lange de plusieurs signaux fournis en entr�e.<P>
 * 
 * L'algorithme r�alise une moyenne des signaux pond�r�e par leur volume respectif.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class Mixer extends Module {

	private final String NAME = "Mixer";

	public static final String PORT_1 = "In 1";
	public static final String PORT_2 = "In 2";
	public static final String PORT_3 = "In 3";
	public static final String PORT_4 = "In 4";
	
	public static final String VOLUME_UNIT = "";
	
	public static final String VOLUME_PORT_1 = "Volume 1";
	public static final String VOLUME_PORT_2 = "Volume 2";
	public static final String VOLUME_PORT_3 = "Volume 3";
	public static final String VOLUME_PORT_4 = "Volume 4";
	
	
	public static final String PARAM_PORT = "Port";
	public static final String PORT_SIGNAL_OUT = "Out";
	public static final String PORT_SIGNAL_IN = "In";
			
	public Mixer() {
		usine = ConcreteFactory.getFactory();
		
		/* 4 Ports : Port IN */
		this.addPortsIn(PORT_1,usine.newPortIn(PORT_1));
		this.addPortsIn(PORT_2,usine.newPortIn(PORT_2));
		this.addPortsIn(PORT_3,usine.newPortIn(PORT_3));
		this.addPortsIn(PORT_4,usine.newPortIn(PORT_4));

		/* 4 Parametres de volume : Continuous Parameter - Volume */
		this.addContinuousParameter(VOLUME_PORT_1, usine
				.newContinuousParameter(VOLUME_PORT_1,VOLUME_UNIT,
						new ParameterValue(0), new ParameterValue(100)));
		this.addContinuousParameter(VOLUME_PORT_2, usine
				.newContinuousParameter(VOLUME_PORT_2,VOLUME_UNIT,
						new ParameterValue(0), new ParameterValue(100)));
		this.addContinuousParameter(VOLUME_PORT_3, usine
				.newContinuousParameter(VOLUME_PORT_3,VOLUME_UNIT,
						new ParameterValue(0), new ParameterValue(100)));
		this.addContinuousParameter(VOLUME_PORT_4, usine
				.newContinuousParameter(VOLUME_PORT_4,VOLUME_UNIT,
						new ParameterValue(0), new ParameterValue(100)));		
		
		/* Mise � z�ro de tous les modules */
		reset();
		/* Port de sortie */
		this.setPortOut(usine.newPortOut(PORT_SIGNAL_OUT));
				
	}

	@Override
	public void compute()  throws SyntheCoreException {
		double signal = 0;
		double total_volume = 0;
		
		Collection<IPortIn> ports = getPortsInTable().values();
		Collection<IContinuousParameter> volumes = getContinuousParameterTable().values();
		
		if (ports.size() != volumes.size()) {
			throw new SyntheCoreException("Mixer : nombre de ports In diff�rent du nombre de volumes");
		}
		
		// on va parcourir simultanement les 2 collections
		Iterator<IPortIn> itPorts = ports.iterator();
		Iterator<IContinuousParameter> itVolumes = volumes.iterator();
		
		while(itPorts.hasNext()) {
			double volume = itVolumes.next().getCurrentValue().toDouble();
			signal = signal + ( itPorts.next().read() * volume);
			total_volume = total_volume + volume;
		}
		
		signal = signal / total_volume;
		
		getPortOut().write(signal);
	}
	
	public String getName() {
		return this.NAME;
	}
	
	@Override
	/**
	 * Cette m�thode n'effectue rien de sp�cial sur ce module
	 */
	public void stop() {
		// Rien � faire		
	}
}
