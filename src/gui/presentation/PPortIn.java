package gui.presentation;

import gui.connector.ConnectorDropper;
import gui.controle.ICPortIn;

/**
 * Présentation d'un port d'entrée (<tt>core.IPortIn</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PPortIn extends PPort implements IPPortIn{

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="controle"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ICPortIn controle;
	/**
	 * @uml.property  name="dropper"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/*Gestionnaire de drop.*/
	private ConnectorDropper dropper;
	
	public PPortIn(String name, ICPortIn controle) {
		super(name);
		this.controle = controle;
		dropper = new ConnectorDropper(this);
		dropper.activerDropListener();
		setToolTipText("Glissez un port de sortie vers moi pour nous connecter");
	}
	
	/**
	 * @return  Returns the controle.
	 * @uml.property  name="controle"
	 */
	public ICPortIn getControle() {
		return controle;
	}

	public void reset() {
		// Rien à faire ici
	}
	
	public int compareTo(Object o) {
		IPPortIn portIn = (IPPortIn)o;
		return getControle().getName().compareTo(portIn.getControle().getName());
	}

}
