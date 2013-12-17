package core;

/**
 * Classe permettant de typer les valeurs des paramètres, qu'ils soient discrets
 * ou continus, et de factoriser les traitements et accesseurs associés.<P>
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ParameterValue {

	/**
	 * @uml.property  name="value"
	 */
	private String value;

	public ParameterValue(String value) {
		this.value = value;
	}

	public ParameterValue(int value) {
		this.value = String.valueOf(value);
	}
	public ParameterValue(double value) {
		this.value = String.valueOf(value);
	}
	
	public String toString() {
		return value.toString();
	}

	public int toInt() {
		return Integer.parseInt(value);
	}

	public double toDouble() {
		return Double.parseDouble(value);
	}

	public void setDoubleValue(double newValue) {
		this.value = String.valueOf(newValue);
	}
	
	public void setIntValue(int newValue) {
		this.value = String.valueOf(newValue);
	}

	public void setStringValue(String str) {
		this.value = str;
	}
}
