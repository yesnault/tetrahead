package gui.presentation;

import core.SyntheShowException;

/**
 * Cette interface décrit les service rendus par la présentation d'un paramètre continu (<tt>core.IContinuousParameter</tt>). 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPContinuousParameter extends IPParameter,SyntheShowException {
	/**
	 * Calcule un pourcentage sur une portion de cercle par rapport à un point.. 
	 * @param x : abscisse du point.
	 * @param y : ordonnée du point.
	 * @return un double compris entre 0.0 et 1.0.
	 */
	public double calculRate(int x,int y);
	
	/**
	 * Effectue une rotation par rapport à un pourcentage fournit en paramètre.
	 * @param rate : double compris entre 0.0 et 1.0.
	 */
	public void rotateContinuous(double rate);
	
	/**
	 * Permet de passer à la présentation une valeur à afficher.
	 * @param string : valeur à afficher.
	 */
	public void setViewValue(String string);
}
