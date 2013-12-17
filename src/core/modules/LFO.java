package core.modules;

import gui.ConcreteFactory;

import java.util.ArrayList;
import java.util.Collection;


import core.ParameterValue;
import core.SyntheCoreException;

/**
 * 
 * Low Freuency [Voltage] Oscillator.<P>
 * 
 * Similaire au VCO avec des param�tres de Pitch (min, max)
 * tr�s inf�rieurs et sans Pitch-in.<BR/>
 * 
 * Compte-tenu qu'elle n�cessitait les m�mes algorithmes que le VCO,
 *  alors qu'elle avait un composant/fonctionnalit� de moins, les deux
 *  modules VCO et LFO ont �t� r�usin�s (refactoring) pour factoriser les
 *  algorithmes et composants dans le LFO, le VCO en h�ritant et y ajoutant 
 *  son PitchIn.<BR/>
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class LFO extends Module {
	
	protected String NAME = "LFO";
	
	// port de sortie
	public static String PORT_SIGNAL_OUT = "Out";

	// parametres
	public static String PARAM_PITCH = "Pitch";
	
	public static String PARAM_SHAPE = "Shapes";
	
	// valeurs du parametre discret Shape
	public static String PVALUE_SHAPE_SQUARE = "Square";
	
	public static String PVALUE_SHAPE_SAWTOOTH = "Saw_tooth";
	
	public static String PVALUE_SHAPE_FACTORYROOF = "Factory_roof";
	
	public static String PVALUE_SHAPE_TRIANGLE = "Triangle";
	
	public static String PVALUE_SHAPE_SINE = "Sine";
	
	private static double PITCH_MIN = 0;
	
	private static double PITCH_MAX = 20;
	
	private static String PITCH_UNIT = "Hz";
	
	// nombre de valeurs a calculer pour UNE periode d'onde.
	double n;
	
	// numero de la valeur a traiter : 0 <= i < n
	int i = 0;
	
	@Override
	public void compute() throws SyntheCoreException {
		
		double pitch = getContinuousParameter(PARAM_PITCH).getCurrentValue().toDouble();
		
		// on ne fait rien si le pitch est nul !!!
		if(pitch > 0) {
			/**
			 * si le pitch change, n peut avoir diminue => recadrer i
			 */
			n = freqEch / pitch;
			
			// i dans [0, n]  => i/n dans [0, 1]
			if (i > n)
				i = 0;
			
			if (n < 2)
				throw new SyntheCoreException(
				"VCO : moins de 1 valeur par periode");
			
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
		} else {
			getPortOut().write(0);
		}
	}
	
	/**
	 * G�n�re une onde signal de forme carr�e.<BR/>
	 * Algorithme con�u par nous m�mes.
	 */
	void computeSquare() {
		try {
			if (i < (n / 2))
				
				getPortOut().write(5.0);
			
			else
				getPortOut().write(-5.0);
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * G�n�re une onde signal de forme dents de scie.<BR/>
	 * Algorithme con�u par nous m�mes.
	 */
	void computeSawTooth() {
		// on commence a +5V pour i = 0 jusqu'a -5V pour i = n-1, soit un
		// intervalle de 10V
		try {
			getPortOut().write(5.0 - (i / n) * 10.);
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * G�n�re une onde signal en forme de toit d'usine.<BR/>
	 * Algorithme con�u par nous m�mes.
	 */
	void computeFactoryRoof() {
		// on commence a -5V pour i = 0 jusqu'a +5V pour i = n-1, soit un
		// intervalle de 10V
		try {
			getPortOut().write(((i / n) * 10.) - 5.);
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * G�n�re une onde signal de forme traingulaire.<BR/>
	 * Algorithme con�u par nous m�mes.
	 */
	void computeTriangle() {
		// on commence a -5V pour i = 0 jusqu'a +5V pour i = n/2
		// => on croit deux fois plus vite
		try {
			if (i <= (n / 2))
				
				getPortOut().write((2. * i * 10. / n) - 5.);
			
			else
				getPortOut().write(5. + (10. * (n - 2. * i) / n));
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * G�n�re une onde signal de forme sinuso�dale.<BR/>
	 * Algorithme con�u par nous m�mes.
	 */
	void computeSine() {
		double j = i * 1.;
		// revient  calculer la valeur de Y a partir de la hauteur de l'angle
		// sur un cercle de taille 1
		try {
			getPortOut().write(Math.sin(2. * Math.PI * (j / n)) * 5.);
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * Construction des parametres de base 
	 * type de signal, pitch, mais pas le pich-in.
	 * Cette methode est egalement utilise dans le VCO
	 */
	public LFO() {
		usine = ConcreteFactory.getFactory();
		
		this.addContinuousParameter(LFO.PARAM_PITCH, usine
				.newContinuousParameter(LFO.PARAM_PITCH,PITCH_UNIT,
						new ParameterValue(get_pitch_min()), new ParameterValue(get_pitch_max())));
		Collection<ParameterValue> pv = new ArrayList<ParameterValue>();
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SQUARE));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SAWTOOTH));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_FACTORYROOF));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_TRIANGLE));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		this.addDiscreteParameter(LFO.PARAM_SHAPE, usine.newDiscreteParameter(
				LFO.PARAM_SHAPE, "", pv));
		/* Ajout du port de sortie */
		this.setPortOut(usine.newPortOut(LFO.PORT_SIGNAL_OUT));
	}
	
	@Override
	/**
	 * Cette m�thode n'effectue rien de sp�cial sur ce module
	 */
	public void stop() {
		i = 0;
		n = 0;
	}
	
	public void reset() {
		super.reset();
		stop();
	}
}