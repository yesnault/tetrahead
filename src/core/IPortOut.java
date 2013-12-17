package core;

/**
 * Port de sortie. <P>
 * Les signaux passent de module en module par un "PUSH" du signal par
 * la m�thode "compute()" du module �metteur jusqu'au PortIn du module 
 * r�cepteur, au travers du PortOut (m�thode "write()"),
 * la m�thode "compute()" du r�cepteur faisant ensuite un "PULL"
 * de son PortIn. <BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPortOut extends IPort {
	
	/**
	 * Ajoute une connection avec un port d'entr�e
	 * @param p
	 */
	public void addPortIn (IPortIn p);
	/**
	 * Supprime un port d'entr�e connect� � ce port de Sortie
	 * @param p
	 */
	public void removePortIn(IPortIn p);
}
