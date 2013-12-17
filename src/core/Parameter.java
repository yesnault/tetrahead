package core;

/**
 * Voir IParameter
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public abstract class Parameter implements IParameter {
	/**
	 * @uml.property  name="currentValue"
	 * @uml.associationEnd  
	 */
	protected ParameterValue currentValue;
	/**
	 * @uml.property  name="name"
	 */
	protected String name;
	private String unit = "";
	
	/**
	 * @return  Returns the currentValue.
	 * @uml.property  name="currentValue"
	 */
	public ParameterValue getCurrentValue() {
		return currentValue;
	}
	
	/**
	 * @param currentValue  The currentValue to set.
	 * @uml.property  name="currentValue"
	 */
	public void setCurrentValue(ParameterValue p) {
		currentValue = p;
	}
	
	/**
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	public Parameter(String name, String unit) {
		this.name = name;
		this.unit = unit;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String u) {
		this.unit = u;
	}
}
