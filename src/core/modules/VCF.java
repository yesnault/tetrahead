package core.modules;

import gui.ConcreteFactory;

import java.util.ArrayList;
import java.util.Collection;
import core.*;

/**
 * Voltage Controlled Filter.<p>
 * Le pr�sent algorithme manipule les valeurs d'entr�e et de sortie
 * aux temps t, t-1 et t-2, repr�sent�s par les valeurs T0, T_1, T_2
 * <BR/>
 * Ce module permet de r�aliser des filtres LowPass OU HighPass, 
 * avec les param�tres cuteOff et r�sonance pilotes chacun soit par 
 * un bouton curseur soit par un port d'entr�e. C'est le codage 
 * (la composition) de l'instance du module qui d�termine la provenance 
 * des valeurs pour ces deux param�tres. 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class VCF extends Module {
	
	private final String NAME = "VCF";
	
	// port de sortie 
	public static String PORT_SIGNAL_OUT = "Out" ;
	
	// ports d'entree 
	public static String PORT_CUT_OFF_IN = "Cut off" ;
	public static String PORT_SIGNAL_IN = "In" ;
	
	// parametres
	public static String PARAM_RESONANCE = "Resonance" ;
	public static String PARAM_BY_PASS = "By pass" ;
	
	public static String PARAM_RESONANCE_UNIT = "%" ;
	public static String PARAM_BY_PASS_UNIT = "" ;
	
	// valeurs du parametre discret LowPass
	public static String PVALUE_BYPASS_LOWPASS = "Low Pass" ;
	public static String PVALUE_BYPASS_HIGHPASS = "High Pass" ;
	
	double sigOut = 0;
	double cutOff, cutOffIn, cutOffPrec;
	double resonance;
	
	/* LP and HP filter - Patrice Tarrabia */
	double c;
	double a1 = .0;
	double a2 = .0;
	double a3 = .0;
	double b1 = .0;
	double b2 = .0;
	double in0 = .0;
	double in_1 = .0;
	double in_2 = .0;
	double out0 = .0;
	double out_1 = .0;
	double out_2 = .0;
	
	/**
	 * Traitement stricto sensu de filtrage du signal.
	 * Les coefficients sont pris tels que recalcules au dernier
	 * changement de parametre.
	 * @throws SyntheCoreException 
	 *
	 */
	@Override
	public void compute() throws SyntheCoreException {
		// le port va de -5V � +5V => aller de 20 � 20000
		cutOff = (Math.pow(2, this.getPortIn(PORT_CUT_OFF_IN).read()) + 0.) * 640;
//		// lissage du param�tre d'entr�e pour �viter de perturber les valeurs n-1 et n-2
//		cutOff = (2 * cutOffPrec +cutOff) / 3;
//		cutOffPrec = cutOff;
//		est inop�rant sur les bavures en mode HighPass.
		
		resonance = this.getContinuousParameter(PARAM_RESONANCE).getCurrentValue().toDouble();
		
		filter();
		
		if(sigOut > 5.) {
//			throw new SyntheCoreException("VCF : valeur de sortie sup�rieure � +5V !");
			sigOut = 5.;
		}
		if(sigOut < -5.) {
//			throw new SyntheCoreException("VCF : valeur de sortie inf�rieure � -5V !");
			sigOut = -5.;
		}
		
		/* Ecriture sur le port de sortie */
		getPortOut().write(sigOut) ;
	}
	
	/** LP and HP filter - Patrice Tarrabia
	 Type : biquad, tweaked butterworthReferences : Posted by Patrice TarrabiaCode :
	 r  = rez amount, from sqrt(2) to ~ 0.1
	 f  = cutoff frequency
	 (from ~0 Hz to SampleRate/2 - though many
	 synths seem to filter only  up to SampleRate/4)
	 
	 The filter algo:
	 out(n) = a1 * in + a2 * in(n-1) + a3 * in(n-2) - b1*out(n-1) - b2*out(n-2)
	 */
	void filter() {
		
		if(resonance < .1)
			resonance = .1;
		if(resonance > Math.sqrt(2))
			resonance = Math.sqrt(2);
		
		/* si cuteOff = 0, division par 0 dans le calcul de C */
		if(cutOff == 0)
			cutOff = 1;
		/* (Math.PI * cutOff / freqEch) doit rester < � PI/2
		 * car tangente non calculable � PI/2 et n�gative ensuite */
		if(cutOff > freqEch / 2)
			cutOff = freqEch / 2 - 1;
		
		
		if(getDiscreteParameter(PARAM_BY_PASS).getCurrentValue().toString().equals(PVALUE_BYPASS_LOWPASS)) {
			// Lowpass:
			c = 1.0 / Math.tan(Math.PI * cutOff / freqEch);
			
			a1 = 1.0 / ( 1.0 + resonance * c + c * c);
			a2 = 2 * a1;
			a3 = a1;
			b1 = 2.0 * ( 1.0 - c*c) * a1;
			b2 = ( 1.0 - resonance * c + c * c) * a1;
		} else {
			// Highpass:
			c = Math.tan(Math.PI * cutOff / freqEch);
			
			a1 = 1.0 / ( 1.0 + resonance * c + c * c);
			a2 = -2 * a1;
			a3 = a1;
			b1 = 2.0 * ( c*c - 1.0) * a1;
			b2 = ( 1.0 - resonance * c + c * c) * a1;
		}
		
		// NB : algorithme pr�vu pour des valeurs "unitaires" => ramener signalIn � [-1, +1]
		in0 = getPortIn(PORT_SIGNAL_IN).read() / 5;
		out0 = a1 * in0 + a2 * in_1 + a3 * in_2 - b1*out_1 - b2*out_2;
		in_2 = in_1;
		in_1 = in0;
		out_2 = out_1;
		out_1 = out0;
		
		sigOut = out0;
	}
	
	public String getName() {
		return this.NAME;
	}
	
	public VCF() {
		usine = ConcreteFactory.getFactory();
		
		/* Port d'entr�e */
		this.addPortsIn(PORT_SIGNAL_IN,usine.newPortIn(VCF.PORT_SIGNAL_IN));
		
		/* Port d'entr�e : Cut Off In */
		this.addPortsIn(PORT_CUT_OFF_IN,usine.newPortIn(VCF.PORT_CUT_OFF_IN));
		
		Collection <ParameterValue> dp = new ArrayList <ParameterValue>();
		
		
		dp.add(new ParameterValue(PVALUE_BYPASS_LOWPASS));
		dp.add(new ParameterValue(PVALUE_BYPASS_HIGHPASS));
		
		this.addDiscreteParameter(PARAM_BY_PASS,
				usine.newDiscreteParameter(PARAM_BY_PASS,PARAM_BY_PASS_UNIT,dp));
		
		this.addContinuousParameter(PARAM_RESONANCE,
				usine.newContinuousParameter(PARAM_RESONANCE, PARAM_RESONANCE_UNIT,
						new ParameterValue(0), new ParameterValue(100)));
		
		/* Port de sortie */
		this.setPortOut(usine.newPortOut(PORT_SIGNAL_OUT));
	}
	
	
	@Override
	/**
	 * Cette m�thode n'effectue rien de sp�cial sur ce module
	 */
	public void stop() {
		// Rien � faire		
	}
}