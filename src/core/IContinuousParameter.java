package core;

/**
 * Les paramètres continus peuvent prendre toute valeur entre le Min et le Max fournis au constructeur.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IContinuousParameter extends IParameter, SyntheShowException{	
	public double getRate();	
	public void setRate(double p);
	/**
	 * @uml.property  name="max"
	 * @uml.associationEnd  
	 */
	public ParameterValue getMax();
	/**
	 * @uml.property  name="min"
	 * @uml.associationEnd  
	 */
	public ParameterValue getMin();
	public void setDoubleValue(double value);
}
