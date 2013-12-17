package gui.presentation;

import gui.controle.ICModule;
import gui.controle.ICParameter;
import gui.controle.ICPortIn;
import gui.controle.ICPortOut;
import gui.controle.ICSynthetizer;

import java.util.Collection;

/**
 * Décrit les services rendus par une fabrique de présentations.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPFactory {
	/**
	 * 
	 * @param controle : controle d'un paramètre discret.
	 * @param initValue : valeur initiale à afficher.
	 * @return : présentation d'un paramètre discret.
	 */
	public IPDiscreteParameter newPDiscreteParameter(ICParameter controle,String initValue);
	/**
	 * 
	 * @param controle : controle d'un paramètre continu.
	 * @param initValue : valeur initiale à afficher.
	 * @return : présentation d'un paramètre continu.
	 */
	public IPContinuousParameter newPContinuousParameter(ICParameter controle,String initValue);
	/**
	 * 
	 * @param name : nom du port d'entrée.
	 * @param controle : contrôle d'un port d'entrée.
	 * @return : présentation d'un paramètre d'entrée.
	 */
	public IPPortIn newPPortIn(String name, ICPortIn controle);
	/**
	 * 
	 * @param name : nom du port de sortie.
	 * @param controle : contrôle d'un port de sortie.
	 * @return : présentation d'un port de sortie.
	 */
	public IPPortOut newPPortOut(String name, ICPortOut controle);
	/**
	 * 
	 * @param params : présentations des paramètres d'un module.
	 * @param pIn : présentations des ports d'entrée d'un module.
	 * @param pOut : présentation du port de sortie d'un module.
	 * @param controle : contrôle d'un module.
	 * @return : la présentation d'un module.
	 */
	public IPModule newPModule(Collection<IPParameter> params,Collection<IPPort> pIn,IPPort pOut, ICModule controle);
	/**
	 * 
	 * @param params : présentations des paramètres d'un module.
	 * @param pIn : présentations des ports d'entrée d'un module.
	 * @param controle : contrôle d'un module.
	 * @return : la présentation d'un module.
	 */
	public IPModule newPModule(Collection<IPParameter> params,Collection<IPPort> pIn, ICModule controle);
	/**
	 * 
	 * @param controle : contrôle d'un synthétiseur.
	 * @return : la présentation d'un synthétiseur.
	 */
	public IPSynthetizer newPSynthetizer(ICSynthetizer controle);
	/**
	 * @param port : présentation du port d'entrée d'un oscilloscope.
	 * @param controle : contrôle d'un oscilloscope.
	 * @return : la présentation d'un oscilloscope.
	 */
	public IPOscilloscope newPOscilloscope(IPPort port, ICModule controle);
}
