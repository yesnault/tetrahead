package gui.presentation;

import java.awt.Point;

/**
 * Décrit les service rendus par les présentations des ports (<tt>core.IPort</tt>).
 * Etend comparable de manière à pouvoir les triés le cas échéant.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPort extends Comparable{
	/**
	 * réinitialise la présentation.
	 */
	public abstract void reset();
	/**
	 * Retourne le centre de la présentation.
	 * @return : un point correspondant au centre de la présentation
	 */
	public Point getCenter();
}
