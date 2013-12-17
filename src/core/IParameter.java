package core;

/**
 * Tous les paramètres, qu'ils soient discrets ou continus, ont un ensemble
 * de comportements en commun.<P>
 * Pour des raisons de découplage et simplification de la GUI, nous avons
 * choisi de factoriser un maximum de fonctionnalités au niveau de la classe
 * Parameter, ce qui aboutit à des types ContinuousParameter et DiscreteParameter
 * n'ayant que très peu de fonctionnalités dédiées.<BR/>
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IParameter {
	/**
	 * @uml.property  name="currentValue"
	 * @uml.associationEnd  
	 */
	public ParameterValue getCurrentValue();
	
	/**
	 * @param currentValue  The currentValue to set.
	 * @uml.property  name="currentValue"
	 */
	public void setCurrentValue(ParameterValue p);
	
	/**
	 * @uml.property  name="name"
	 */
	public String getName();
	
	public void reset();
	
	public String getUnit();
	
	public void setUnit(String u);
	
}
