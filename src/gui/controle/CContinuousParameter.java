package gui.controle;

import core.IContinuousParameter;
import core.ParameterValue;
import gui.ConcreteFactory;
import gui.presentation.IPContinuousParameter;
import gui.presentation.parameterAction.ContinuousCommand;

/**
 * Classe implémentant le contrôle de <tt>core.IContinuousParameter</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */

public class CContinuousParameter implements ICContinuousParameter,ICParameter {

	/**
	 * @uml.property  name="abstraction"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IContinuousParameter abstraction; 
	/**
	 * @uml.property  name="presentation"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPContinuousParameter presentation;
	
	public CContinuousParameter(String name, String unit, ParameterValue min, ParameterValue max) {
		abstraction = ConcreteFactory.getAFactory().newContinuousParameter(name,unit,min,max);
		presentation = ConcreteFactory.getPFactory().newPContinuousParameter(this,abstraction.getCurrentValue().toString());
		presentation.setParameterCommand(new ContinuousCommand(this));
		presentation.initRotatePanel(0,1);
	}

	public double getRate() {
		return abstraction.getRate();
	}

	public void setRate(double p) {
		abstraction.setRate(p);
		presentation.rotateContinuous(abstraction.getRate());
		presentation.setViewValue(abstraction.getCurrentValue().toString());
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
	public IPContinuousParameter getPresentation() {
		return presentation;
	}

	public ParameterValue getMax() {
		return abstraction.getMax();
	}
	
	public ParameterValue getMin() {
		return abstraction.getMin();
	}

	public void reset() {
		abstraction.reset();
		presentation.rotateContinuous(abstraction.getRate());
		presentation.setViewValue(abstraction.getCurrentValue().toString());
	}

	public void setDoubleValue(double value) {
		abstraction.setDoubleValue(value);
		// System.out.println("CContinuous::abstracttion.getcurrentValue()="+abstraction.getCurrentValue().toString());
		// System.out.println("CContinuous::abstracttion.getRate()="+abstraction.getRate());
		presentation.rotateContinuous(abstraction.getRate());
		presentation.setViewValue(abstraction.getCurrentValue().toString());
	}

	public void showException(Exception e) {
		presentation.showException(e);
	}

	public String getUnit() {
		return abstraction.getUnit();
	}

	public void setUnit(String u) {
		abstraction.setUnit(u);		
	}
}
