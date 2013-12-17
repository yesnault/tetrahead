package core;

import java.util.Collection;

import core.modules.*;

/**
 * Fabrique fournissant des instances des différents composants métier du synthétiseur.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class AFactory implements IFactory {

	public static AFactory uniqueInstance = new AFactory(); 
	
	private AFactory(){
	}
	
	public static AFactory getInstance(){
		return uniqueInstance;
	}
	
	public ISynthetizer newSynthetizer() {
		return new Synthetizer();
	}

	public IModule newADHSR() {
		return new ADHSR();
	}

	public IModule newADSRTrigger() {
		return new ADSRTrigger();
	}

	public IModule newVCF() {
		return new VCF();
	}
	
	public IModule newVCFPar() {
		return new VCFPar();
	}
	
	public IModule newLFO() {
		return new LFO();
	}

	public IModule newVCO() {
		return new VCO();
	}
	
	public IModule newOUTDirect() {
		return new OUTDirect();
	}
	
	public IModule newOUTFile() {
		return new OUTFile();
	}

	public IModule newVCA() {
		return new VCA();
	}

	public IModule newMixer() {
		return new Mixer();
	}

	public IPortIn newPortIn(String name) {
		return new PortIn(name);
	}

	public IPortOut newPortOut(String name) {
		return new PortOut(name);
	}

	public IContinuousParameter newContinuousParameter(String name, String unit, ParameterValue min, ParameterValue max) {
		return new ContinuousParameter(name, unit, min, max);
	}

	public IDiscreteParameter newDiscreteParameter(String name, String unit, Collection<ParameterValue> values) {
		return new DiscreteParameter(name, unit, values);
	}

	public IModule newOscilloscope() {
		return new Oscilloscope();
	}
}
