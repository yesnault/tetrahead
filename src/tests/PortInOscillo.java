package tests;

import core.PortIn;

public class PortInOscillo extends PortIn {

	/**
	 * @uml.property  name="signal"
	 */
	double signal = .0 ;
	
	public PortInOscillo(String name) {
		super(name);
	}

	public void write(double signal) {
		this.signal = signal;
	}
	public double read() {
		return signal;
	}
}
