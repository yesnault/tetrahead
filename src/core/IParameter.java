package core;

/**
 * Tous les param�tres, qu'ils soient discrets ou continus, ont un ensemble
 * de comportements en commun.<P>
 * Pour des raisons de d�couplage et simplification de la GUI, nous avons
 * choisi de factoriser un maximum de fonctionnalit�s au niveau de la classe
 * Parameter, ce qui aboutit � des types ContinuousParameter et DiscreteParameter
 * n'ayant que tr�s peu de fonctionnalit�s d�di�es.<BR/>
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
