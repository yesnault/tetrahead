package core.modules;

import gui.ConcreteFactory;
import core.ParameterValue;
import core.SyntheCoreException;

/**
 * Ce module permet d'amplifier ou d'att�nuer le signal re�u en entr�e. <P>
 * NOTA BENE : le <tt>VCA</tt> traditionel d'un synth�tiseur sert surtout � moduler
 * un signal sous la conduite d'un g�n�rateur d'enveloppe. Mais notre <tt>ADHSR</tt>
 * fait lui-m�me cette modulation (voir le module <tt>ADHSR</tt>).<BR/>
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class VCA extends Module {
	// TODO VM : afficher l'attenuation/amplification en decibels plutot qu'en % 

	/**
	 * @uml.property  name="nAME"
	 */
	private final String NAME = "VCA";

	public static final String PARAM_GAIN = "Volume";
	public static final String PARAM_GAIN_UNIT = "%";

	public static final String PORT_GAIN_IN = "gain";

	public static final String PORT_SIGNAL_OUT = "Out";
	public static final String PORT_SIGNAL_IN = "In";
	
	double sigIn, sigOut, gain, gainParam, gainPort;
			
	public VCA() {
		usine = ConcreteFactory.getFactory();

		/* Un Parametre de volume : Continuous Parameter
		 	Volume en pourcentage, de z�ro � 200 % */
		this.addContinuousParameter(PARAM_GAIN, usine
				.newContinuousParameter(PARAM_GAIN, PARAM_GAIN_UNIT,
						new ParameterValue(0), new ParameterValue(200)));
		
		/* Port de sortie */
		this.setPortOut(usine.newPortOut(PORT_SIGNAL_OUT));
				
		/* Port d'entr�e */
		this.addPortsIn(PORT_SIGNAL_IN,usine.newPortIn(PORT_SIGNAL_IN));
		this.addPortsIn(PORT_GAIN_IN,usine.newPortIn(PORT_GAIN_IN));
	}

	@Override
	public void compute() throws SyntheCoreException  {
		gainParam = this.getContinuousParameter(PARAM_GAIN).getCurrentValue().toDouble();
		if(getPortIn(PORT_GAIN_IN).isConnected()) {
			gainPort = (this.getPortIn(PORT_GAIN_IN).read() + 5) * 10;
			// l'entr�e sur le port va de -5V � +5V, pass�e en 0 � 100%
			gain = (gainPort + gainParam) / 2;
		} else
			gain = gainParam;
		
		// le signal de sortie est fonction lin�aire du % indiqu� (0 - 200)
		sigIn = getPortIn(PORT_SIGNAL_IN).read();
		sigOut = sigIn * gain / 100;
		
		// Ecr�tage des valeurs d�passant les bornes
		if(sigOut > 5)
			sigOut = 5;
		if(sigOut < -5)
			sigOut = -5;
		
		getPortOut().write(sigOut);
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