package gui.controle;

import core.IContinuousParameter;
import core.IDiscreteParameter;
import core.IPortIn;
import core.IPortOut;
import core.SyntheCoreException;
import core.modules.IModule;
import gui.ConcreteFactory;
import gui.presentation.IPModule;
import gui.presentation.IPParameter;
import gui.presentation.IPPort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Contrôle des modules (<tt>core.modules.IModule</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public abstract class CModule implements ICModule {
	/**
	 * @uml.property  name="abstraction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	protected IModule abstraction;
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	protected IPModule presentation;
	
	public void setFreqEch(int freqEch) throws SyntheCoreException {
		abstraction.setFreqEch(freqEch);
	}

	public IPortIn getPortIn(String name) {
		return abstraction.getPortIn(name);	
	}

	public void addPortsIn(String portInName, IPortIn portIn) {
		abstraction.addPortsIn(portInName, portIn);
	}

	public void removePortsIn(IPortIn portIn) {
		abstraction.removePortsIn(portIn);
	}

	public void setPortOut(IPortOut portOut) {
		abstraction.setPortOut(portOut);
	}

	public IPortOut getPortOut() {
		return abstraction.getPortOut();
	}

	public void addContinuousParameter(String name,
			IContinuousParameter continuousParameter) {
		abstraction.addContinuousParameter(name,continuousParameter);
	}

	public void removeContinuousParameter(
			IContinuousParameter continuousParameter) {
		abstraction.removeContinuousParameter(continuousParameter);
	}

	public IContinuousParameter getContinuousParameter(String name) {
		return abstraction.getContinuousParameter(name);
	}

	public void addDiscreteParameter(String name,
			IDiscreteParameter discreteParameter) {
		abstraction.addDiscreteParameter(name,discreteParameter);
	}

	public void removeDiscreteParameter(IDiscreteParameter discreteParameter) {
		abstraction.removeDiscreteParameter(discreteParameter);
	}

	public IDiscreteParameter getDiscreteParameter(String name) {
		return getDiscreteParameter(name);
	}

	public void compute() throws Exception {
		abstraction.compute();
	}

	public void reset() {
		abstraction.reset();
		
	}
	
	public void stop() throws IOException {
		abstraction.stop();		
	}
	
	/**
	 * @return  Returns the presentation.
	 * @uml.property  name="presentation"
	 */
	public IPModule getPresentation(){
		return presentation;
	}

	public Hashtable<String, IPortIn> getPortsInTable() {
		return abstraction.getPortsInTable();
	}

	public Hashtable<String, IDiscreteParameter> getDiscreteParameterTable() {
		return abstraction.getDiscreteParameterTable();
	}

	public Hashtable<String, IContinuousParameter> getContinuousParameterTable() {
		return abstraction.getContinuousParameterTable();
	}
	
	/**
	 * Méthode abstraite permettant d'effectuer des traitements spécifiques sur les paramètres discrets.
	 * @param discreteParameters : paramètres discrets d'un module.
	 */
	protected abstract void addPDiscreteParameters(Hashtable<String, IDiscreteParameter> discreteParameters);
	
	/**
	 * Méthode permettant de définir la présentation d'un module.
	 * Elle utilise la présentation par défaut des modules mais peut-être redéfinie dans les sous-classes de manière à utiliser des présentations plus spécifiques.
	 * @param pParams : présentations des paramètres. 
	 * @param pports : présentations des ports d'entrée. 
	 * @param po : présentation du port de sortie.
	 * @param cModule : une instance de CModule.
	 */
	protected void createPresentation(ArrayList<IPParameter> pParams, ArrayList<IPPort> pports, IPortOut po, CModule cModule) {
		presentation = ConcreteFactory.getPFactory().newPModule(
				pParams,
				pports,
				((ICPortOut)po).getPresentation(),
				cModule);
	}
	/**
	 * Constructeur
	 * @param abstraction : abstraction du contrôle.
	 */
	@SuppressWarnings("unchecked")
	public CModule(IModule abstraction) {
		this.abstraction = abstraction;
		Hashtable<String, IDiscreteParameter> discreteParameters = abstraction.getDiscreteParameterTable();
		Collection<IDiscreteParameter> discreteColl = discreteParameters.values();
		Collection<IContinuousParameter> continuousColl = abstraction.getContinuousParameterTable().values();
		Collection<IPortIn> portInColl = abstraction.getPortsInTable().values();
		
		addPDiscreteParameters(discreteParameters);
			
		ArrayList<IPParameter> pParams = new ArrayList<IPParameter>();
		for (IContinuousParameter cp : continuousColl) {
			pParams.add(((ICContinuousParameter) cp).getPresentation());
		}
		for (IDiscreteParameter dc : discreteColl) {
			pParams.add(((ICDiscreteParameter) dc).getPresentation());
		}
		
		/*Tri des paramètres.
		 *Les objets de type IPParameter implémentent Comparable.*/
		Collections.sort(pParams);
	
		ArrayList<IPPort> pports = new ArrayList<IPPort>();
		for (IPortIn pIn : portInColl) {
			pports.add(((ICPortIn)pIn).getPresentation());
		}
		
		/*Tri des ports d'entrées.
		 *Les objets de type IPPort implémentent l'interface Comparable.*/
		Collections.sort(pports);
		
		IPortOut po = abstraction.getPortOut();
		
		createPresentation(pParams,pports,po,this);
	}

	public void showException(Exception e) {
		presentation.showException(e);
	}

}
