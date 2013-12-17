package gui.presentation.parameterAction;

import gui.controle.ICContinuousParameter;

/**
 * Commande ex�cut�e par la pr�sentation d'un param�tre continu.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ContinuousCommand implements ParameterCommand {

	/**
	 * @uml.property  name="controle"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ICContinuousParameter controle;
	
	public ContinuousCommand(ICContinuousParameter controle){
		this.controle = controle;
	}
	
	public void execute(boolean b) {
	}

	public void execute(int x,int y) {
		double rate = controle.getPresentation().calculRate(x,y);
		controle.setRate(rate);
	}

	public void execute(String currentText) {
		try{
			double value;
			value = Double.parseDouble(currentText);
			controle.setDoubleValue(value);
		} catch(Exception e){
			controle.showException(new GetParameterValueException(currentText));
			controle.getPresentation().setViewValue(controle.getCurrentValue().toString());
		}
	}

}
