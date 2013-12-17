package gui.presentation.parameterAction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Listener sur la saisie au clavier pour la présentation des paramètres continus.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class MyKeyListener implements KeyListener {
	private Invoker invoker;
	private ParameterCommand cmd;
	
	public MyKeyListener(Invoker invoker,ParameterCommand cmd) {
		this.invoker = invoker;
		this.cmd = cmd;
	}
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode()+" "+KeyEvent.VK_ENTER);
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			//System.out.println(invoker.getCurrentText());
			cmd.execute(invoker.getCurrentText());
		}
	}

	public void keyReleased(KeyEvent e) {
	}

}
