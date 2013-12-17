package gui.controle;

import core.IDiscreteParameter;
import core.ParameterValue;
import gui.ConcreteFactory;
import gui.presentation.IPDiscreteParameter;
import gui.presentation.parameterAction.DiscreteCommand;

import java.util.Collection;

/**
 * Classe implémentant le contrôle de <tt>core.IDiscreteParameter</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */

public class CDiscreteParameter implements ICDiscreteParameter,ICParameter {

	/**
	 * @uml.property  name="abstraction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IDiscreteParameter abstraction; 
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPDiscreteParameter presentation;
	
	public CDiscreteParameter(String name, String unit, Collection<ParameterValue> values) {
		abstraction = ConcreteFactory.getAFactory().newDiscreteParameter(name,unit,values);
		presentation = ConcreteFactory.getPFactory().newPDiscreteParameter(this,abstraction.getCurrentValue().toString());
		presentation.setParameterCommand(new DiscreteCommand(this));
		presentation.initRotatePanel(1,abstraction.getNumberOfValue());
	}

	public int getNumberOfValue() {
		return abstraction.getNumberOfValue();
	}

	public ParameterValue next() {
		ParameterValue tmp = abstraction.next();
		presentation.refreshView(tmp.toString());
		presentation.rotate(true);
		return tmp;
	}

	public ParameterValue getCurrentValue() {
		return abstraction.getCurrentValue();
	}

	public void setCurrentValue(ParameterValue p) {
		abstraction.setCurrentValue(p);
	}

	public String getName() {
		return abstraction.getName();
	}

	/**
	 * @return  Returns the presentation.
	 * @uml.property  name="presentation"
	 */
	public IPDiscreteParameter getPresentation() {
		return presentation;
	}

	public ParameterValue previous() {
		ParameterValue tmp = abstraction.previous();
		presentation.refreshView(tmp.toString());
		presentation.rotate(false);
		return tmp;
	}

	public void reset() {
		abstraction.reset();
		presentation.refreshView(abstraction.getCurrentValue().toString());
		presentation.reset();
	}

	public String getUnit() {
		return abstraction.getUnit();
	}

	public void setUnit(String u) {
		abstraction.setUnit(u);
	}

}
