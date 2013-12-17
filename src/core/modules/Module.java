package core.modules;

import core.*;

import java.io.IOException;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;

/**
 * Classe factorisant l'ensemble des �l�ments et traitements communs aux
 * diff�rents modules de traitement du son, de fa�on qu'ils ne contiennent
 * que le code relatif � leur traitement du son (algorithmes).<P>
 * La volont� d'avoir une GUI traitant n'importe quel module de la m�me et unique
 * fa�on a conduit � g�n�raliser/factoriser l'acc�s aux composants. Ainsi
 * la liste des ports d'entr�e, des param�tres, le port de sortie �ventuel
 * sont tous g�r�s au niveau de la classe Module.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public abstract class Module implements IModule {

	/**
	 * IFactory : usine utilis�e dans les constructeurs des  diff�rents modules
	 * @uml.property  name="usine"
	 * @uml.associationEnd  readOnly="true"
	 */
	IFactory usine;
	
	/**
	 * variables privees utilisees par tous les modules
	 * @uml.property  name="freqEch"
	 */
	int freqEch = 44000 ;
	
	/**
	 * Frequence d'echantillonnage choisie pour/par le synthetiseur
	 * @param freqEch  Le minimum est de 2 KHz
	 * @uml.property  name="freqEch"
	 */
	public void setFreqEch(int freqEch) throws SyntheCoreException {
		if(freqEch < 2000)
			freqEch = 2000 ;
		
		this.freqEch = freqEch ;
	}
	
	/**
	 * @return  Returns the freqEch.
	 * @uml.property  name="freqEch"
	 */
	public int getFreqEch() {
		return freqEch;
	}
	
	/**
	 * @uml.property  name="portsIn"
	 * @uml.associationEnd  multiplicity="(0 -1)" aggregation="composite" inverse="module:core.PortIn"
	 */
	private Hashtable<String, IPortIn> portsIn = new Hashtable<String, IPortIn>();

	/**
	 * Ensures that this collection contains the specified element (optional operation). 
	 * @param element  whose presence in this collection is to be ensured.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="portsIn"
	 */
	public void addPortsIn(String portInName, IPortIn portIn) {
		portsIn.put(portInName, portIn);
	}
	
	public IPortIn getPortIn(String name) {
		return portsIn.get(name) ;
	}

	/**
	 * Removes a single instance of the specified element from this collection, if it is present (optional operation).
	 * @param element  to be removed from this collection, if present.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="portsIn"
	 */
	public void removePortsIn(IPortIn portIn) {
		portsIn.remove(portIn);
	}


	/**
	 * @uml.property  name="portsOut"
	 * @uml.associationEnd  multiplicity="(0 -1)" aggregation="composite" inverse="module:core.PortOut"
	 */
	private IPortOut portOut;

	/**
	 * M�thode � red�finir dans les classes filles.
	 * Doit contenir le code � ex�cuter � chaque tour d'horloge
	 * @throws LineUnavailableException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	public abstract void compute() throws Exception;
	
	/**
	 * R�initialisation des valeurs de tous les param�tres et de tous les ports. 
	 * Les connexions entre les ports sont cependant maintenues.
	 * 
	 */
	public void reset() {
		Collection<IPortIn> ports = getPortsInTable().values();
		Iterator<IPortIn> itPorts = ports.iterator();
		while(itPorts.hasNext()) {
			itPorts.next().reset();
		}
		
		Collection<IContinuousParameter> continuous = getContinuousParameterTable().values();
		Iterator<IContinuousParameter> itContinuous = continuous.iterator();
		
		while(itContinuous.hasNext()) {
			itContinuous.next().reset();
		}
		
		
		Collection<IDiscreteParameter> discretes = getDiscreteParameterTable().values();
		Iterator<IDiscreteParameter> itDiscretes = discretes.iterator();
		
		while(itDiscretes.hasNext()) {
			itDiscretes.next().reset();
		}
		
		if (getPortOut() != null) {
			getPortOut().reset();
		}
	}
	
	/**
	 * M�thode � red�finir dans les classes filles.<br>
	 * Doit contenir le code � ex�cuter lorsque l'on fait appel � 
	 * la m�thode Stop sur le synth�tiseur.<br>
	 * Par exemple, le module <tt>OUTDirect</tt> doit lib�rer la ligne son 
	 * du syst�me
	 * @throws IOException 
	 */
	public abstract void stop() throws IOException;

	/**
	 * @param portOut  The portOut to set.
	 * @uml.property  name="portOut"
	 */
	public void setPortOut(IPortOut portOut) {
		this.portOut = portOut;
	}
	
	/**
	 * @return  Returns the portOut.
	 * @uml.property  name="portOut"
	 */
	public IPortOut getPortOut() {
		return portOut ;
	}
	
	/**
	 * @uml.property  name="continuousParameter"
	 * @uml.associationEnd  multiplicity="(0 -1)" aggregation="composite"
	 */
	private Hashtable<String, IContinuousParameter> continuousParameter = new  Hashtable<String, IContinuousParameter>();

	/**
	 * Ensures that this collection contains the specified element (optional operation). 
	 * @param element  whose presence in this collection is to be ensured.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="continuousParameter"
	 */
	public void addContinuousParameter(String name, IContinuousParameter continuousParameter) {
		this.continuousParameter.put(name, continuousParameter);
	}

	/**
	 * Removes a single instance of the specified element from this collection, if it is present (optional operation).
	 * @param element  to be removed from this collection, if present.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="continuousParameter"
	 */
	public void removeContinuousParameter(IContinuousParameter continuousParameter) {
		this.continuousParameter.remove(continuousParameter);
	}
	
	public IContinuousParameter getContinuousParameter(String name) {
		return continuousParameter.get(name) ;
	}

	/**
	 * @uml.property  name="discreteParameter"
	 * @uml.associationEnd  multiplicity="(0 -1)" aggregation="composite"
	 */
	private Hashtable<String, IDiscreteParameter> discreteParameter = new  Hashtable<String, IDiscreteParameter>();

	/**
	 * Ensures that this collection contains the specified element (optional operation). 
	 * @param element  whose presence in this collection is to be ensured.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="discreteParameter"
	 */
	public void addDiscreteParameter(String name, IDiscreteParameter discreteParameter) {
		this.discreteParameter.put(name, discreteParameter);
	}

	/**
	 * Removes a single instance of the specified element from this collection, if it is present (optional operation).
	 * @param element  to be removed from this collection, if present.
	 * @see java.util.Collection#add(Object)
	 * @uml.property  name="discreteParameter"
	 */
	public void removeDiscreteParameter(IDiscreteParameter discreteParameter) {
		this.discreteParameter.remove(discreteParameter);
	}
	
	public IDiscreteParameter getDiscreteParameter(String name) {
		return discreteParameter.get(name) ;
	}

	public Hashtable<String, IPortIn> getPortsInTable() {
		return portsIn;
	}

	public Hashtable<String, IDiscreteParameter> getDiscreteParameterTable() {
		return discreteParameter;
	}
	
	public Hashtable<String, IContinuousParameter> getContinuousParameterTable() {
		return continuousParameter;
	}

	public abstract String getName();
	
	public void showException(Exception e) {
		System.out.println(e.getStackTrace());
	}
}
