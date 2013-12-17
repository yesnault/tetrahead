package core;

import java.util.Collection;

/**
 * Un paramètre discret ne peut prendre que quelques valeurs prédéfinies.
 * On passe d'une valeur à une autre par balayage de l'ensemble de ces valeurs.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class DiscreteParameter extends Parameter implements IDiscreteParameter {
	/**
	 * @uml.property  name="values"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private ParameterValue[] values;
	/**
	 * @uml.property  name="currentIdx"
	 */
	public int currentIdx;
	
	public int getNumberOfValue() {
		return values.length;
	}
	
	/**
	 * Passe a l'element suivant et retourne sa valeur.
	 * Si on depasse le premier le dernier element alors on ne fait rien
	 * et on retourne le dernier element.
	 */
	public ParameterValue next() {
		if(currentIdx < values.length-1){
			currentIdx++;
			currentValue = values[currentIdx];
		}
		//System.out.println("DiscreteParameter::next::currentValue="+currentValue.toString());
		return currentValue;
	}
	
	/**
	 * Passe a l'element precedent et retourne sa valeur.
	 * Si on depasse le premier element alors on ne fait rien et on retourne 
	 * le premier element.
	 */
	public ParameterValue previous() {
		if(currentIdx > 0){
			currentIdx--;
			currentValue = values[currentIdx];
		}
		//System.out.println("DiscreteParameter::previous::currentValue="+currentValue.toString());
		return currentValue;
	}
	
	public DiscreteParameter(String name, String unit, Collection <ParameterValue> c) {
		super(name,unit);
		values = new ParameterValue[c.size()];
		int i=0;
		for (ParameterValue value : c) {
			values[i] = value;
			i++;
		}
		reset();
	}

	public void reset() {
		currentValue = values[0];
		currentIdx = 0;		
	}
}
