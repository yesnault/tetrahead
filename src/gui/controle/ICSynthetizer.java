package gui.controle;

import core.ISynthetizer;
import gui.presentation.IPSynthetizer;

/**
 * Décrit les services rendus par le contrôle d'un synthétiseur (<tt>core.ISynthetizer</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ICSynthetizer extends ISynthetizer{
	public IPSynthetizer getPresentation();
	
}
