package core.modules;

import core.*;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Interface au coeur de la portabilit� de l'application.<P>
 * 
 * Elle permet d'assurer :
 * <ul>
 *   <li>le d�couplage d'avec la GUI (mod�le PAC-Proxy)
 *   <li>la factorisation des concepts rattach�s aux diff�rents modules. Ces
 *   derniers ne contiennent ainsi que la partie algorithmique qui leur est
 *   sp�cifique.
 * </ul>
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public interface IModule extends SyntheShowException{
	
	/* M�thodes d�finies dans la classe abstraite Module */
	public void setFreqEch(int freqEch) throws SyntheCoreException;
	
	public IPortIn getPortIn(String name);
	public void addPortsIn(String portInName, IPortIn portIn);
	public void removePortsIn(IPortIn portIn);
	
	/**
	 * @param portOut  The portOut to set.
	 * @uml.property  name="portOut"
	 */
	public void setPortOut(IPortOut portOut);
	/**
	 * @uml.property  name="portOut"
	 * @uml.associationEnd  
	 */
	public IPortOut getPortOut();
	
	public void addContinuousParameter(String name, IContinuousParameter continuousParameter);
	public void removeContinuousParameter(IContinuousParameter continuousParameter);
	public IContinuousParameter getContinuousParameter(String name);
		
	public void addDiscreteParameter(String name, IDiscreteParameter discreteParameter);
	public void removeDiscreteParameter(IDiscreteParameter discreteParameter);
	public IDiscreteParameter getDiscreteParameter(String name);
	
	public Hashtable<String, IPortIn> getPortsInTable();
	public Hashtable<String, IDiscreteParameter> getDiscreteParameterTable();
	public Hashtable<String, IContinuousParameter> getContinuousParameterTable();
	
	/* M�thodes d�finies dans chaque module */
	public abstract void compute() throws Exception;
	public abstract void reset();
	public abstract void stop() throws IOException;
	
	/**
	 * @uml.property  name="name"
	 */
	public String getName();

	public void showException(Exception e);
	
}
