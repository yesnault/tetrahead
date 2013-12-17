package core;

/**
 * Tous les ports, qu'ils soient d'entrée ou de sortie, ont un ensemble
 * de comportements en commun.<P>
 * Pour des raisons de découplage et simplification de la GUI, nous avons
 * choisi de factoriser un maximum de fonctionnalités au niveau de la classe
 * Port, ce qui aboutit à des types PortIn et PortOut
 * n'ayant que très peu de fonctionnalités dédiées.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
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
