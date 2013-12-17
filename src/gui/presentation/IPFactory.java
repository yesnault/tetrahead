package gui.presentation;

import gui.controle.ICModule;
import gui.controle.ICParameter;
import gui.controle.ICPortIn;
import gui.controle.ICPortOut;
import gui.controle.ICSynthetizer;

import java.util.Collection;

/**
 * D�crit les services rendus par une fabrique de pr�sentations.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPFactory {
	/**
	 * 
	 * @param controle : controle d'un param�tre discret.
	 * @param initValue : valeur initiale � afficher.
	 * @return : pr�sentation d'un param�tre discret.
	 */
	public IPDiscreteParameter newPDiscreteParameter(ICParameter controle,String initValue);
	/**
	 * 
	 * @param controle : controle d'un param�tre continu.
	 * @param initValue : valeur initiale � afficher.
	 * @return : pr�sentation d'un param�tre continu.
	 */
	public IPContinuousParameter newPContinuousParameter(ICParameter controle,String initValue);
	/**
	 * 
	 * @param name : nom du port d'entr�e.
	 * @param controle : contr�le d'un port d'entr�e.
	 * @return : pr�sentation d'un param�tre d'entr�e.
	 */
	public IPPortIn newPPortIn(String name, ICPortIn controle);
	/**
	 * 
	 * @param name : nom du port de sortie.
	 * @param controle : contr�le d'un port de sortie.
	 * @return : pr�sentation d'un port de sortie.
	 */
	public IPPortOut newPPortOut(String name, ICPortOut controle);
	/**
	 * 
	 * @param params : pr�sentations des param�tres d'un module.
	 * @param pIn : pr�sentations des ports d'entr�e d'un module.
	 * @param pOut : pr�sentation du port de sortie d'un module.
	 * @param controle : contr�le d'un module.
	 * @return : la pr�sentation d'un module.
	 */
	public IPModule newPModule(Collection<IPParameter> params,Collection<IPPort> pIn,IPPort pOut, ICModule controle);
	/**
	 * 
	 * @param params : pr�sentations des param�tres d'un module.
	 * @param pIn : pr�sentations des ports d'entr�e d'un module.
	 * @param controle : contr�le d'un module.
	 * @return : la pr�sentation d'un module.
	 */
	public IPModule newPModule(Collection<IPParameter> params,Collection<IPPort> pIn, ICModule controle);
	/**
	 * 
	 * @param controle : contr�le d'un synth�tiseur.
	 * @return : la pr�sentation d'un synth�tiseur.
	 */
	public IPSynthetizer newPSynthetizer(ICSynthetizer controle);
	/**
	 * @param port : pr�sentation du port d'entr�e d'un oscilloscope.
	 * @param controle : contr�le d'un oscilloscope.
	 * @return : la pr�sentation d'un oscilloscope.
	 */
	public IPOscilloscope newPOscilloscope(IPPort port, ICModule controle);
}
