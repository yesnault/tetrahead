package core.modules;

import core.*;

/**
 * Voltage Controlled Filter.<p>
 * Le pr�sent algorithme manipule les valeurs d'entr�e et de sortie
 * aux temps t, t-1 et t-2, repr�sent�s par les valeurs T0, T_1, T_2
 * <BR/>
 * Ce module permet de realiser des filtres LowPass OU HighPass, 
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

public class VCFPar extends VCF {
	
	private final String NAME = "VCF Param";
	
	// parametres
	public static String PARAM_CUT_OFF = "Cut off" ;
	
	public static String PARAM_CUT_OFF_UNIT = "Hz" ;
	
	double cutOffPar;
	
	/**
	 * Traitement stricto sensu de filtrage du signal.
	 * Les coefficients sont pris tels que recalcules au dernier
	 * changement de parametre.
	 * @throws SyntheCoreException 
	 *
	 */
	@Override
	public void compute() throws SyntheCoreException {
		// faire la moyenne entre le cuteOff <Bouton> et le cuteOff <Port>
		cutOffIn = this.getPortIn(PORT_CUT_OFF_IN).read();
		cutOffPar = this.getContinuousParameter(PARAM_CUT_OFF).getCurrentValue().toDouble();
		if(getPortIn(PORT_SIGNAL_IN).isConnected())
			cutOff = (cutOffIn + cutOffPar) / 2;
		else
			cutOff = cutOffPar;
		
		resonance = this.getContinuousParameter(PARAM_RESONANCE).getCurrentValue().toDouble();
		
		super.filter();
		
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
	
	public String getName() {
		return this.NAME;
	}
	
	public VCFPar() {
		super();
		
		/* Cut OFF */
		// INFO fr�quence audible de 20 Hz � 20000 hz 
		this.addContinuousParameter(PARAM_CUT_OFF,
				usine.newContinuousParameter(PARAM_CUT_OFF, PARAM_CUT_OFF_UNIT,
						new ParameterValue(20), new ParameterValue(20000)));
	}
	
	
	@Override
	/**
	 * Cette m�thode n'effectue rien de sp�cial sur ce module
	 */
	public void stop() {
		// Rien � faire		
	}
}