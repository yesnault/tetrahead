package gui.presentation;

import core.SyntheShowException;

/**
 * Cette interface d�crit les service rendus par la pr�sentation d'un param�tre continu (<tt>core.IContinuousParameter</tt>). 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPContinuousParameter extends IPParameter,SyntheShowException {
	/**
	 * Calcule un pourcentage sur une portion de cercle par rapport � un point.. 
	 * @param x : abscisse du point.
	 * @param y : ordonn�e du point.
	 * @return un double compris entre 0.0 et 1.0.
	 */
	public double calculRate(int x,int y);
	
	/**
	 * Effectue une rotation par rapport � un pourcentage fournit en param�tre.
	 * @param rate : double compris entre 0.0 et 1.0.
	 */
	public void rotateContinuous(double rate);
	
	/**
	 * Permet de passer � la pr�sentation une valeur � afficher.
	 * @param string : valeur � afficher.
	 */
	public void setViewValue(String string);
}
