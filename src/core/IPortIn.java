package core;

/**
 * Port d'entrée. <P>
 * Les signaux passent de module en module par un "PUSH" du signal jusqu'au
 * PortIn du module récepteur, sa méthode "compute()" faisant ensuite un "PULL"
 * du PortIn. <BR/>
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPortIn extends IPort {
	public void connect(IPortOut p);
	public void disconnect();
}
