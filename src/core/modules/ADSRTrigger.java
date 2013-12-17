package core.modules;

import gui.ConcreteFactory;
import core.ParameterValue;
import core.SyntheCoreException;

/**
 * <H2>Enveloppe ADSR pilot�e par entr�es Gate et Trigger</H2>
 * Le d�but d'une onde carr�e (+5V) dans la Gate d�marre l'attaque, et la fin de cette
 * onde carr�e (passage  0 ou -5V) d�marre la retombee.<BR/>
 * Une valeur de +5V sur le Trigger d�clenche une nouvelle attaque a partir du volume
 * courant,  l'int�rieur de l'enveloppe. Il ne recule pas le moment de la retomb�e.<BR/>
 * Les phases A, D et R peuvent durer de 1 ms  20 s.<BR/>
 * Comme l'ADHSR, la pr�sente enveloppe a �t� con�ue algorithmiquement par
 * nos soins, n'ayant dispos� que d'un profil du r�sultat � produire.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class ADSRTrigger extends Module {

	private final String NAME = "ADSRTrigger";
	
	public static final String PARAM_ATTAK = "A attaque";
	public static final String PARAM_DECAY = "D descente";
	public static final String PARAM_SUSTAIN = "S volume";
	public static final String PARAM_RELEASE = "R retombe";
	
	public static final String PARAM_ATTAK_UNIT = "ms";
	public static final String PARAM_DECAY_UNIT = "ms";
	public static final String PARAM_SUSTAIN_UNIT = "/ 1";
	public static final String PARAM_RELEASE_UNIT = "ms";	
	
	public static final String PORT_SIGNAL_OUT = "Out";
	public static final String PORT_SIGNAL_IN = "In";
	public static final String PORT_GATE_IN = "Gate";
	public static final String PORT_TRIGGER_IN = "Trigger";
	
	// les variables qui suivent ont ete mises en DOUBLE
	//  pour les calculs : quotients faux avec des INT
	/**
	 * variable stockant le nombre d'chantillons restants  filtrer au titre de l'attaque
	 */
	double iAttack = 0;
		
	/**
	 * variable stockant le nombre d'echantillons restants a filtrer au titre de la descente
	 */
	double iDecay = 0;
		
	/**
	 * variable stockant le nombre d'echantillons restants a filtrer au titre de la retombee
	 */
	double iRelease = 0;
	
	/**
	 * coefficient de volume applicable pendant la suspension (entre 0 et 1)
	 */
	double cSustain = 0;
	
	/**
	 * variable indiquant une "gate" en cours a +5V
	 */
	boolean gateUp = false;
		
	/**
	 * variable indiquant un "trigger" ayant ete declenche a +5V
	 *  (sera desactive quand il redescend a moins de +5V)
	 */
	boolean triggerUp = false;
	
	/**
	 * variable indiquant si on doit faire un Sustain (mode Gate)
	 * ou faire directement un Release (mode Trigger seul)
	 */
	boolean shouldSustain = false;
	
	/**
	 * Stockage des valeurs d'entree de sortie
	 */
	double sigOut, sigIn;
		
	public ADSRTrigger() {
		usine = ConcreteFactory.getFactory();
		
		/* 5 Parameters */
		this.addContinuousParameter(PARAM_ATTAK, usine.newContinuousParameter(
				PARAM_ATTAK,PARAM_ATTAK_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		this.addContinuousParameter(PARAM_DECAY, usine.newContinuousParameter(
				PARAM_DECAY,PARAM_DECAY_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		this.addContinuousParameter(PARAM_SUSTAIN, usine.newContinuousParameter(
				PARAM_SUSTAIN,PARAM_SUSTAIN_UNIT, new ParameterValue("0.0"), new ParameterValue("1.0")));
		this.addContinuousParameter(PARAM_RELEASE, usine.newContinuousParameter(
				PARAM_RELEASE, PARAM_RELEASE_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		
		/* Port de sortie */
		this.setPortOut(usine.newPortOut(PORT_SIGNAL_OUT));
		
		/* Ports d'entree */
		this.addPortsIn(PORT_SIGNAL_IN,usine.newPortIn(PORT_SIGNAL_IN));
		this.addPortsIn(PORT_GATE_IN,usine.newPortIn(PORT_GATE_IN));
		this.addPortsIn(PORT_TRIGGER_IN,usine.newPortIn(PORT_TRIGGER_IN));
	}

	public void compute()  throws SyntheCoreException {
		double gateValue = getPortIn(PORT_GATE_IN).read();
		double triggerValue = getPortIn(PORT_TRIGGER_IN).read();
		
		// traitement du port Trigger
		if(triggerValue == 5.0) {
			if(! triggerUp) {
			// on a un nouveau declenchement de trigger
			initTimers();
			triggerUp = true;
			}
		} else	// redescente du triggerIn => on deverrouille le trigger
			triggerUp = false;
		
		// traitement du port Gate
		if(gateUp) {
			//gerer la sortie de phase Gate
			if(gateValue != 5.0) {
				gateUp = false;
				shouldSustain = false;
			}
		} else {
			if(gateValue == 5.0) {
				gateUp = true;
				// debut d'une phase GateUP => init + 1ere execution d'attaque
				initTimers();
				shouldSustain = true;
			}
		}
		
		// on utilise l'algorithme de la classe parente ADHSR (enveloppe g�n�rique).
		playNote();
		
		getPortOut().write(sigOut);
	}
	
	private void initTimers() throws SyntheCoreException {
		iAttack = Math.floor(getContinuousParameter(PARAM_ATTAK).getCurrentValue().toDouble()) * freqEch / 1000;
		
		if(gateUp) {
			iDecay = Math.floor(getContinuousParameter(PARAM_DECAY).getCurrentValue().toDouble()) * freqEch / 1000;
			
			cSustain = getContinuousParameter(PARAM_SUSTAIN).getCurrentValue().toDouble();
			if(cSustain > 1)
				throw new SyntheCoreException("ADSRTrigger : coefficient de volume du SUSTAIN superieur a 1 !");
		} else {
			// trigger seul => seulement attaque et retombee
			iDecay = 0;
			cSustain = 1.0;
		}
		
		iRelease = Math.floor(getContinuousParameter(PARAM_RELEASE).getCurrentValue().toDouble()) * freqEch / 1000;
		
	}

	/**
	 * Joue la note attendue (attaque puis descente et suspension)
	 * @throws SyntheCoreException
	 */
	private void playNote() throws SyntheCoreException {
		sigIn = getPortIn(PORT_SIGNAL_IN).read();
		
		if(iAttack > 0) {
			// iAttack va de n = "param" jusqu'a 0.
			// le coefficient a appliquer doit passer de 0 a 1.
			// coef = (n - iAttack)/n = 1 - (iAttack / n)
			int n = Math.round((long)getContinuousParameter(PARAM_ATTAK).getCurrentValue().toDouble()) * freqEch / 1000;
			if(iAttack > n)
				iAttack = n;
			
			sigOut = sigIn * (1 - (iAttack/n));
			
			iAttack--;
			
		} else if(iDecay > 0) {
			// iDelay va de n = "param" jusqu'a 0.
			// le coefficient a appliquer doit passer de 1.0 a cSustain.
			// coef = cSustain + (iDelay/n) * (1 - cSustain)
			int n = Math.round((long)getContinuousParameter(PARAM_DECAY).getCurrentValue().toDouble()) * freqEch / 1000;
			if(iDecay > n)
				iDecay = n;
			
			sigOut = sigIn * (cSustain + (iDecay / n) * (1 - cSustain));
			
			iDecay--;
			
		} else if(shouldSustain) {
			// on se contente de maintenir un volume conforme a cSustain
			sigOut = sigIn * cSustain;
			
		} else if(iRelease > 0) {
			// iRealease va de n = "Param" jusqu'a 0.
			// le coefficient a appliquer doit passer de cSustain a 0.
			// coef = cSustain * (iRelease / n)
			int n = Math.round((long)getContinuousParameter(PARAM_RELEASE).getCurrentValue().toDouble()) * freqEch / 1000;
			if(iRelease > n)
				iRelease = n;
			
			sigOut = sigIn * (cSustain * (iRelease / n));
			
			iRelease--;
			
		} else	// pas de signal
			sigOut = 0;
	}
	
	public String getName() {
		return this.NAME;
	}

	@Override
	/**
	 * Cette mthode n'effectue rien de spcial sur ce module
	 */
	public void stop() {
		// Rien  faire		
	}
}
