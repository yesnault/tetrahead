package gui.presentation.parameterAction;

import gui.controle.ICDiscreteParameter;
/**
 * Commande ex�cut�e par la pr�sentation d'un param�tre discret.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class DiscreteCommand implements ParameterCommand {

	/**
	 * @uml.property  name="discrete"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	ICDiscreteParameter discrete;
	
	public DiscreteCommand(ICDiscreteParameter discrete){
		this.discrete = discrete;
	}
	
	public void execute(boolean b) {
		if(b){
			discrete.next();
		} else{
			discrete.previous();
		}
	}

	public void execute(int x, int y) {
	}
	
	public void execute(String currentText) {	
	}
}
