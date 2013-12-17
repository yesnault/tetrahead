package core.modules;

import gui.ConcreteFactory;

import java.util.ArrayList;
import java.util.Collection;
import core.ParameterValue;
import core.SyntheCoreException;

/**
 * Classe g�n�rant l'enveloppe d'un signal.<P>
 * Elle r�alise directement la modification du signal, 
 * � la diff�rence des enveloppes classiques qui g�n�rent un signal 
 * repr�sentant l'enveloppe � destination du port "Gain" d'un VCA
 * qui r�alise alors la modification du signal � traiter.<BR/>
 * Ce choix de conception n'en est pas vraiment un, mais une cons�quence
 * de la documentation disponible. Celle-ci nous apportait le profil et
 * nous avons r�alis� l'algorithme correspondant nous m�mes.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class ADHSR extends Module {

	private final String NAME = "ADHSR";
	public static final String PARAM_ATTAK = "A attaque";
	public static final String PARAM_DECAY = "D descente";
	public static final String PARAM_HOLD = "H suspens";
	public static final String PARAM_SUSTAIN = "S volume";
	public static final String PARAM_RELEASE = "R retomb�e";
	
	public static final String PARAM_ATTAK_UNIT = "ms";
	public static final String PARAM_DECAY_UNIT = "ms";
	public static final String PARAM_HOLD_UNIT = "ms";
	public static final String PARAM_SUSTAIN_UNIT = "%";
	public static final String PARAM_RELEASE_UNIT = "ms";	
	
	public static final String PORT_SIGNAL_OUT = "Out";
	public static final String PORT_SIGNAL_IN = "In";
	
	public static final String PARAM_REPEAT = "Refaire";
	public static final String PARAM_REPEAT_UNIQUE = "Unique";
	public static final String PARAM_REPEAT_LOOP = "Boucle";
	public static final String PARAM_REPEAT_UNIT = "";

	/**
	 * La variable "topDepart" est mise a vrai pour signaler
	 * le debut d'une nouvelle enveloppe.
	 * 
	 * Pour avoir un debut d'enveloppe commande, prendre l'ADSRTrigger
	 */
	private boolean topDepart = true;
	
	// les variables qui suivent ont ete mises en DOUBLE
	//  pour les calculs : quotients faux avec des INT
	/**
	 * variable stockant le nombre d'�chantillons restants � filtrer au titre de l'attaque
	 */
	double iAttack = 0;
		
	/**
	 * variable stockant le nombre d'�chantillons restants � filtrer au titre de la descente
	 */
	double iDecay = 0;
		
	/**
	 * variable stockant le nombre d'�chantillons restants � filtrer au titre de la suspension
	 */
	double iHold = 0;
		
	/**
	 * variable stockant le nombre d'�chantillons restants � filtrer au titre de la retombee
	 */
	double iRelease = 0;
	
	/**
	 * coefficient de volume applicable pendant la suspension (entre 0 et 1)
	 */
	double cSustain = 0;
		
	public ADHSR() {
		usine = ConcreteFactory.getFactory();
		
		/* 5 Parameters */
		this.addContinuousParameter(PARAM_ATTAK, usine.newContinuousParameter(
				PARAM_ATTAK, PARAM_ATTAK_UNIT,new ParameterValue("0"), new ParameterValue(1000.)));
		this.addContinuousParameter(PARAM_DECAY, usine.newContinuousParameter(
				PARAM_DECAY,PARAM_DECAY_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		this.addContinuousParameter(PARAM_HOLD, usine.newContinuousParameter(
				PARAM_HOLD,PARAM_HOLD_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		this.addContinuousParameter(PARAM_SUSTAIN, usine.newContinuousParameter(
				PARAM_SUSTAIN,PARAM_SUSTAIN_UNIT, new ParameterValue("0.0"), new ParameterValue("1.0")));
		this.addContinuousParameter(PARAM_RELEASE, usine.newContinuousParameter(
				PARAM_RELEASE,PARAM_RELEASE_UNIT, new ParameterValue("0"), new ParameterValue(1000.)));
		
		/* Port de sortie */
		this.setPortOut(usine.newPortOut(PORT_SIGNAL_OUT));
		
		/* Port d'entr�e */
		this.addPortsIn(PORT_SIGNAL_IN,usine.newPortIn(PORT_SIGNAL_IN));
		
		/* Parametre de choix entre ex�cution unique ou boucle infinie */
		Collection <ParameterValue> dp = new ArrayList <ParameterValue>();
		dp.add(new ParameterValue(PARAM_REPEAT_LOOP));
		dp.add(new ParameterValue(PARAM_REPEAT_UNIQUE));
		this.addDiscreteParameter(PARAM_REPEAT, usine.newDiscreteParameter(PARAM_REPEAT,PARAM_REPEAT_UNIT, dp));
	}

	public void compute()  throws SyntheCoreException {
		if(topDepart) {
			iAttack = Math.floor(getContinuousParameter(PARAM_ATTAK).getCurrentValue().toDouble()) * freqEch / 1000;
			iDecay = Math.floor(getContinuousParameter(PARAM_DECAY).getCurrentValue().toDouble()) * freqEch / 1000;
			iHold = Math.floor(getContinuousParameter(PARAM_HOLD).getCurrentValue().toDouble()) * freqEch / 1000;
			iRelease = Math.floor(getContinuousParameter(PARAM_RELEASE).getCurrentValue().toDouble()) * freqEch / 1000;
			
			cSustain = getContinuousParameter(PARAM_SUSTAIN).getCurrentValue().toDouble();
			if(cSustain > 1)
				throw new SyntheCoreException("ADHSR : coefficient de volume du SUSTAIN superieur a 1 !");
			topDepart = false;
		} else {
			if(iAttack > 0) {
				// iAttack va de n = "param" jusqu'a 0.
				// le coefficient a appliquer doit passer de 0 a 1.
				// coef = (n - iAttack)/n = 1 - (iAttack / n)
				int n = Math.round((long)getContinuousParameter(PARAM_ATTAK).getCurrentValue().toDouble()) * freqEch / 1000;
				if(iAttack > n)
					iAttack = n;
				
				getPortOut().write(getPortIn(PORT_SIGNAL_IN).read() * (1 - (iAttack/n)));
				
				iAttack--;
			} else if(iDecay > 0) {
				// iDelay va de n = "param" jusqu'a 0.
				// le coefficient a appliquer doit passer de 1 a cSustain.
				// coef = cSustain + (iDelay/n) * (1 - cSustain)
				int n = Math.round((long)getContinuousParameter(PARAM_DECAY).getCurrentValue().toDouble()) * freqEch / 1000;
				if(iDecay > n)
					iDecay = n;
				
				getPortOut().write(getPortIn(PORT_SIGNAL_IN).read()
						* (cSustain + (iDecay / n) * (1 - cSustain)));
				
				iDecay--;
			} else if(iHold > 0) {
				// iHold va de n = "param" jusqu'a 0.
				// on se contente de maintenir un volume conforme a cSustain
				int n = Math.round((long)getContinuousParameter(PARAM_HOLD).getCurrentValue().toDouble()) * freqEch / 1000;
				if(iHold > n)
					iHold = n;
				
				getPortOut().write(getPortIn(PORT_SIGNAL_IN).read() * cSustain);
						
				iHold--;
			} else if(iRelease > 0) {
				// iRealease va de n = "Param" jusqu'a 0.
				// le coefficient a appliquer doit passer de cSustain a 0.
				// coef = cSustain * (iRelease / n)
				int n = Math.round((long)getContinuousParameter(PARAM_RELEASE).getCurrentValue().toDouble()) * freqEch / 1000;
				if(iRelease > n)
					iRelease = n;
				
				getPortOut().write(getPortIn(PORT_SIGNAL_IN).read()
						* (cSustain * (iRelease / n)));
				
				iRelease--;
			} else {
				// hors enveloppe => 0
				getPortOut().write(0);
				
				// si l'utilisateur veut boucler, on reenclenche le "topDepart"
				String valRelease = getDiscreteParameter(PARAM_REPEAT).getCurrentValue().toString();
				if(valRelease.equals(PARAM_REPEAT_LOOP))
					topDepart = true;
			}
		}
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
