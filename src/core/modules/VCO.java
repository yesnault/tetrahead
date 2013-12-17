package core.modules;

import core.SyntheCoreException;


/**
 * Voltage Controlled Oscillator.<p>
 * 
 * Le pr�sent algorithme g�n�re des ondes de diff�rentes formes :
 * <ul>
 * <li> onde carr�e (Square)
 * <li> onde en dents de scie (SawTooth)
 * <li> onde en triangle (Triangle)
 * <li> onde sinuso�dale (Sine)
 * </ul>
 * Ce module permettra (dans une version ulterieure) d'ajouter des
 * harmoniques � la fr�quence de base, en fonction des valeurs du param�tre
 * PitchIn.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class VCO extends LFO {
	@SuppressWarnings("unused")
	private final String NAME = "VCO";
	
	// acces a cinq octaves
	public static double PITCH_MIN = 20;
	public static double PITCH_MAX = 3520;
	
	// ports d'entree
	public static String PORT_PITCH_IN = "Pitch";
	
	
	public VCO () {
		super();
		add_pitch_in();
	}
	
	/**
	 * Ajout du port Pitch-In
	 *
	 */
	private void add_pitch_in() {
		this.addPortsIn(VCO.PORT_PITCH_IN, usine.newPortIn(VCO.PORT_PITCH_IN));
	}
	
	public String getName() {
		return this.NAME;
	}
	
	protected double get_pitch_min() {
		return PITCH_MIN;
	}
	
	protected double get_pitch_max() {
		return PITCH_MAX;
	}
	public void compute() throws SyntheCoreException {
		
		double pitch = getContinuousParameter(PARAM_PITCH).getCurrentValue().toDouble();
		double pitchIn = getPortIn(PORT_PITCH_IN).read();
		
		/**
		 * si le pitch change, n peut avoir diminue => recadrer i
		 */
		double freqValue = pitch * Math.pow(2, pitchIn/3);
		// att�nuation du changement de frequence du au pitchIn
		if(freqValue > (freqEch / 4)) {
			freqValue = (freqEch / 4);
		}
		
		n = freqEch / freqValue;
		
		// i dans [0, n]  => i/n dans [0, 1]
		if (i > n)
			i = 0;
		
		if (n < 2)
			throw new SyntheCoreException(
			"VCO : moins de 2 valeurs par periode");
		
		if (getDiscreteParameter(PARAM_SHAPE).getCurrentValue().toString()
				.equals(PVALUE_SHAPE_SQUARE)) {
			computeSquare();
		} else if (getDiscreteParameter(PARAM_SHAPE).getCurrentValue()
				.toString().equals(PVALUE_SHAPE_SAWTOOTH)) {
			computeSawTooth();
		} else if (getDiscreteParameter(PARAM_SHAPE).getCurrentValue()
				.toString().equals(PVALUE_SHAPE_FACTORYROOF)) {
			computeFactoryRoof();
		} else if (getDiscreteParameter(PARAM_SHAPE).getCurrentValue()
				.toString().equals(PVALUE_SHAPE_TRIANGLE)) {
			computeTriangle();
		} else if (getDiscreteParameter(PARAM_SHAPE).getCurrentValue()
				.toString().equals(PVALUE_SHAPE_SINE)) {
			computeSine();
		} else {
			throw new SyntheCoreException("VCO : Mode invalide");
		}
		
		// on incremente le compteur de valeurs
		i++;
	}
	
}
