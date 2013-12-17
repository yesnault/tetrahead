package gui.presentation;

import gui.controle.ICParameter;
import gui.presentation.parameterAction.ParameterCommand;

/**
* D�crit l'interface des services rendus par les pr�sentations des param�tres (<tt>core.IParameter</tt>) des modules.
 * Etend comparable de mani�re � ce qu'on puisse les trier le cas �ch�ant.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPParameter extends Comparable{
	/**
	 * Initialise la commande � ex�cuter lorsque l'utilisateur interagit avec la pr�sentation.
	 * @param pc : une commande.
	 */
	public void setParameterCommand(ParameterCommand pc);
	
	/**
	 * Initialise le panel rotatif de la pr�sentation.
	 * Ce panel peut effectuer des rotations entre 2 angles minimum et maximum.
	 * @param min : angle minimum.
	 * @param max : angle maximum.
	 */
	public void initRotatePanel(double min, double max);
	
	/**
	 * Retourne le contr�le de la pr�sentation.
	 */
	public ICParameter getControle();
}
