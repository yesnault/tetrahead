package core;

/**
 * Tous les ports, qu'ils soient d'entr�e ou de sortie, ont un ensemble
 * de comportements en commun.<P>
 * Pour des raisons de d�couplage et simplification de la GUI, nous avons
 * choisi de factoriser un maximum de fonctionnalit�s au niveau de la classe
 * Port, ce qui aboutit � des types PortIn et PortOut
 * n'ayant que tr�s peu de fonctionnalit�s d�di�es.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPort {
	public abstract void write(double signal)  throws SyntheCoreException;
	public abstract double read();
	public abstract boolean isConnected();	
	public abstract void reset();
	
	public String getName();
	
}
