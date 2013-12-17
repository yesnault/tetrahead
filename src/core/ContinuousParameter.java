package core;

/**
 * Les paramètres continus peuvent toute valeur entre le Min et le Max fournis au constructeur
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ContinuousParameter extends Parameter implements
		IContinuousParameter {
	/**
	 * @uml.property name="min"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private ParameterValue min;

	/**
	 * @uml.property name="max"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	private ParameterValue max;

	/**
	 * @uml.property name="range"
	 */
	private double range;

	/**
	 * @uml.property name="rate"
	 */
	private double rate;

	/**
	 * @uml.property name="bMin"
	 */
	private double bMin;

	/**
	 * @uml.property name="bMax"
	 */
	private double bMax;

	public ContinuousParameter(String name, String unit, ParameterValue min,
			ParameterValue max) {
		super(name,unit);
		this.min = min;
		this.max = max;
		this.bMin = min.toDouble();
		this.bMax = max.toDouble();

		this.range = bMax - bMin;
		reset();
	}

	/**
	 * @return Returns the rate.
	 * @uml.property name="rate"
	 */
	public double getRate() {
		return rate;
	}

	private double checkRate(double r) {
		if (r < 0.0) {
			return 0.0;
		} else if (r > 1.0) {
			return 1.0;
		}
		return r;
	}

	private double checkValue(double r) {
		if (r < bMin) {
			return bMin;
		} else if (r > bMax) {
			return bMax;
		}
		return r;
	}

	/**
	 * @param rate
	 *            The rate to set.
	 * @uml.property name="rate"
	 */
	public void setRate(double p) {
		rate = checkRate(p);
		currentValue.setDoubleValue(bMin + rate * range);
	}

	/**
	 * @return Returns the max.
	 * @uml.property name="max"
	 */
	public ParameterValue getMax() {
		return this.max;
	}

	/**
	 * @return Returns the min.
	 * @uml.property name="min"
	 */
	public ParameterValue getMin() {
		return this.min;
	}

	public void reset() {
		this.currentValue = new ParameterValue(min.toDouble());
		rate = 0.0;
	}

	public void setDoubleValue(double value) {
		// System.out.println("Continuous::value="+value);
		this.currentValue.setDoubleValue(checkValue(value));
		// System.out.println("Continuous::checkValue="+checkValue(value));
		this.rate = checkRate(value / range);
		// System.out.println("Continuous::rate="+rate);
	}

	public void showException(Exception e) {
		e.printStackTrace();
	}
}
