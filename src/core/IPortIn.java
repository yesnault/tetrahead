package core;

/**
 * Port d'entr�e. <P>
 * Les signaux passent de module en module par un "PUSH" du signal jusqu'au
 * PortIn du module r�cepteur, sa m�thode "compute()" faisant ensuite un "PULL"
 * du PortIn. <BR/>
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPortIn extends IPort {
	public void connect(IPortOut p);
	public void disconnect();
}
