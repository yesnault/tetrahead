package gui.controle;

import java.util.Collection;

import core.*;
import core.modules.*;

/**
 * Fabrique fournissant des instances des différents composants de contrôle du synthétiseur.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CFactory implements IFactory {

	public static CFactory uniqueInstance = new CFactory();

	private CFactory() {
	}

	public static CFactory getInstance() {
		return uniqueInstance;
	}

	public ISynthetizer newSynthetizer() {
		return new CSynthetizer();
	}

	public IModule newADHSR() {
		return new CADHSR();
	}

	public IModule newADSRTrigger() {
		return new CADSRTrigger();
	}

	public IModule newVCF() {
		return new CVCF();
	}

	public IModule newVCFPar() {
		return new CVCFPar();
	}

	public IModule newLFO() {
		return new CLFO();
	}
	
	public IModule newVCO() {
		return new CVCO();
	}
	
	public IModule newOUTDirect() {
		return new COUTDirect();
	}

	public IModule newOUTFile() {
		return new COUTFile();
	}
	
	public IModule newVCA() {
		return new CVCA();
	}

	public IModule newMixer() {
		return new CMixer();
	}

	public IPortIn newPortIn(String name) {
		return new CPortIn(name);
	}

	public IPortOut newPortOut(String name) {
		return new CPortOut(name);
	}

	public IContinuousParameter newContinuousParameter(String name, String unit,
			ParameterValue min, ParameterValue max) {
		return new CContinuousParameter(name,unit,min,max);
	}

	public IDiscreteParameter newDiscreteParameter(String name, String unit,
			Collection<ParameterValue> values) {
		return new CDiscreteParameter(name,unit,values);
	}

	public IModule newOscilloscope() {
		return new COscilloscope();
	}

}
