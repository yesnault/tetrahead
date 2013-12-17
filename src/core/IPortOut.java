package core;

/**
 * Port de sortie. <P>
 * Les signaux passent de module en module par un "PUSH" du signal par
 * la méthode "compute()" du module émetteur jusqu'au PortIn du module 
 * récepteur, au travers du PortOut (méthode "write()"),
 * la méthode "compute()" du récepteur faisant ensuite un "PULL"
 * de son PortIn. <BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPortOut extends IPort {
	
	/**
	 * Ajoute une connection avec un port d'entrée
	 * @param p
	 */
	public void addPortIn (IPortIn p);
	/**
	 * Supprime un port d'entrée connecté à ce port de Sortie
	 * @param p
	 */
	public void removePortIn(IPortIn p);
}
