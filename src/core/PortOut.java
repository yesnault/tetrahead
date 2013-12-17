package core;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Voir IPortOut
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PortOut extends Port implements IPortOut {

	Collection <IPortIn> portsIn;
	

	@Override
	/**
	 * Write : �crit le signal dans tous les ports qui sont 
	 * connect�s avec le porte d'entr�e 
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
	 * Cette m�thode renvoie toujours 0.0, un portOut ne peut �tre lu.
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
	 * Indique si le port est connect� ou non � des ports d'entr�e
	 */
	public boolean isConnected() {
		return portsIn.size() >0;
	}

	/**
	 * Le reset sur les ports de sortie n'a pas d'effet
	 */
	public void reset() {
		// rien � faire ici
	}

}
