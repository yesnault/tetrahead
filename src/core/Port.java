package core;

/**
 * Voir IPort
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
abstract class Port implements IPort {
	
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="isConnected"
	 */
	protected boolean isConnected = false;
	
	public abstract void write(double signal) throws SyntheCoreException;
	public abstract double read();
	public abstract boolean isConnected();	
	
	
	public Port(String name ) {
		this.name = name;
	}
	/**
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
}
