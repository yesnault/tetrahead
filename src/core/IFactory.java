package core;

import java.util.Collection;

import core.modules.*;

 /**
 * Décrit les services rendus par une fabrique de composants applicatifs.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IFactory {
	public ISynthetizer newSynthetizer ();
	
	public IModule newADHSR();
	
	public IModule newADSRTrigger();
	
	public IModule newVCF();
	
	public IModule newVCFPar();
	
	public IModule newLFO();
	
	public IModule newVCO();
	
	public IModule newOUTFile();
	
	public IModule newOUTDirect();
	
	public IModule newVCA();
	
	public IModule newMixer();
	
	public IPortIn newPortIn(String name);
	
	public IPortOut newPortOut(String name);
	
	public IContinuousParameter newContinuousParameter(String name, String unit, ParameterValue min, ParameterValue max);
	
	public IDiscreteParameter newDiscreteParameter(String name, String unit, Collection<ParameterValue> values);

	public IModule newOscilloscope();
	

}
