package gui.presentation;

import java.awt.Point;

/**
 * D�crit les service rendus par les pr�sentations des ports (<tt>core.IPort</tt>).
 * Etend comparable de mani�re � pouvoir les tri�s le cas �ch�ant.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPPort extends Comparable{
	/**
	 * r�initialise la pr�sentation.
	 */
	public abstract void reset();
	/**
	 * Retourne le centre de la pr�sentation.
	 * @return : un point correspondant au centre de la pr�sentation
	 */
	public Point getCenter();
}
