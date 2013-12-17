package gui.presentation.parameterAction;

/**
 * Interface des commandes exécutées par un <tt>gui.presentation.IPParameter</tt> 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */

public interface ParameterCommand {

	void execute(boolean b);
	void execute(int x,int y);
	void execute(String currentText);
}
