package gui.toolbar;


import java.util.Collection;

import javax.swing.*;

/**
 * Barre de boutons contenant des <tt>ScalableButtonMenuBar</tt>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ScalableButtonMenuBar extends JPanel {
	static final long serialVersionUID = 684435434L;
	
	/**
	 * Construit une barre de boutons à partir d'une fabrique de boutons
	 * 
	 * @param factory Fabrique de boutons
	 */
	public ScalableButtonMenuBar(ScalableButtonFactory factory) {
		super();
		Collection<ScalableButton> buttons = factory.createButtons();
		for(ScalableButton b : buttons) {
			add(b);
		}
	}
}
