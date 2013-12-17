package core;

/**
 * Voir IPortIn
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PortIn extends Port implements IPortIn{

	public PortIn(String name) {
		super(name);
	}

	/**
	 * @uml.property  name="signal"
	 */
	private double signal = 0.0;
	
	@Override
	public void write(double signal) {
		this.signal = signal;
	}

	@Override
	public double read() {
		return signal;
	}

	public void connect(IPortOut p) {
		isConnected = true;
	}
	
	public void disconnect() {
		isConnected = false;
		signal = 0.0;
	}

	@Override
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * Mise � zero du signal
	 */
	public void reset() {
		signal = 0;
	}
}
