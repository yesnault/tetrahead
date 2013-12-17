package gui.presentation;

import gui.controle.ICModule;
import gui.controle.ICParameter;
import gui.controle.ICPortIn;
import gui.controle.ICPortOut;
import gui.controle.ICSynthetizer;

import java.util.Collection;

/** 
 * Fabrique des présentations.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PFactory implements IPFactory {

	public static PFactory uniqueInstance = new PFactory(); 
	
	private PFactory(){
	}
	
	public static PFactory getInstance(){
		return uniqueInstance;
	}

	public IPDiscreteParameter newPDiscreteParameter(ICParameter controle, String initValue) {
		return new PDiscreteParameter(controle,initValue);
	}

	public IPContinuousParameter newPContinuousParameter(ICParameter controle,String initValue) {
		return new PContinuousParameter(controle, initValue);
	}
	
	public IPPortIn newPPortIn(String name, ICPortIn controle){
		return new PPortIn(name, controle);
	}
	
	public IPPortOut newPPortOut(String name, ICPortOut controle){
		return new PPortOut(name, controle);
	}
	
	public IPModule newPModule(Collection<IPParameter> parameters, Collection<IPPort> portsIn, IPPort portOut,ICModule controle){
		return new PModule(parameters,portsIn,portOut,controle);
	}
	
	public IPSynthetizer newPSynthetizer(ICSynthetizer controle){
		return new PSynthetizer(controle);
	}

	public IPModule newPModule(Collection<IPParameter> params, Collection<IPPort> pIn,ICModule controle) {
		return new PModule(params,pIn,controle);
	}

	public IPOscilloscope newPOscilloscope(IPPort pIn,ICModule controle) {
		return new POscilloscope(pIn,controle);
	}
	
}
