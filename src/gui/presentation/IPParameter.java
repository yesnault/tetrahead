package gui.presentation;

import gui.controle.ICParameter;
import gui.presentation.parameterAction.ParameterCommand;

/**
* Décrit l'interface des services rendus par les présentations des paramètres (<tt>core.IParameter</tt>) des modules.
 * Etend comparable de manière à ce qu'on puisse les trier le cas échéant.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPParameter extends Comparable{
	/**
	 * Initialise la commande à exécuter lorsque l'utilisateur interagit avec la présentation.
	 * @param pc : une commande.
	 */
	public void setParameterCommand(ParameterCommand pc);
	
	/**
	 * Initialise le panel rotatif de la présentation.
	 * Ce panel peut effectuer des rotations entre 2 angles minimum et maximum.
	 * @param min : angle minimum.
	 * @param max : angle maximum.
	 */
	public void initRotatePanel(double min, double max);
	
	/**
	 * Retourne le contrôle de la présentation.
	 */
	public ICParameter getControle();
}
