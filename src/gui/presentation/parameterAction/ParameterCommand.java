package gui.presentation.parameterAction;

/**
 * Interface des commandes ex�cut�es par un <tt>gui.presentation.IPParameter</tt> 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */

public interface ParameterCommand {

	void execute(boolean b);
	void execute(int x,int y);
	void execute(String currentText);
}
