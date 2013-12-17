package core;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Voir IPortOut
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PortOut extends Port implements IPortOut {

	Collection <IPortIn> portsIn;
	

	@Override
	/**
	 * Write : écrit le signal dans tous les ports qui sont 
	 * connectés avec le porte d'entrée 
	 */
	public void write(double signal) throws SyntheCoreException {
		if (signal <-5 || signal > 5)
			throw new SyntheCoreException(" portOut Signal hors limite : "+signal);
		
		for (IPortIn p : portsIn) {
			p.write(signal);
		}		
		
	}

	@Override
	/**
	 * Cette méthode renvoie toujours 0.0, un portOut ne peut être lu.
	 */
	public double read() {
		return 0.0;
	}
	
	/**
	 * Constructeur
	 *
	 */
	public PortOut(String name) {
		super(name);
		portsIn = new ArrayList <IPortIn> ();
	}
	
	public void addPortIn (IPortIn p) {
		portsIn.add(p);
		p.connect(this);
	}
	
	public void removePortIn(IPortIn p) {
		p.disconnect();
		portsIn.remove(p);
	}

	@Override
	/**
	 * Indique si le port est connecté ou non à des ports d'entrée
	 */
	public boolean isConnected() {
		return portsIn.size() >0;
	}

	/**
	 * Le reset sur les ports de sortie n'a pas d'effet
	 */
	public void reset() {
		// rien à faire ici
	}

}
