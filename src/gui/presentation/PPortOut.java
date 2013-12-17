package gui.presentation;

import gui.connector.ConnectorDragger;
import gui.controle.ICPortOut;

/**
 * Présentation d'un port de sortie (<tt>core.IPortOut</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PPortOut extends PPort implements IPPortOut{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="controle"
	 * @uml.associationEnd  readOnly="true"
	 */
	private ICPortOut controle;
	/**
	 * @uml.property  name="dragger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ConnectorDragger dragger;
	
	public PPortOut(String name, ICPortOut controle) {
		super(name);
		this.controle = controle;
		dragger = new ConnectorDragger(this);
		dragger.activerDragListener();
		setToolTipText("Glissez-moi vers un port d'entrée pour nous connecter");
	}

	/**
	 * @return  Returns the controle.
	 * @uml.property  name="controle"
	 */
	public ICPortOut getControle() {
		return controle;
	}
	
	/**
	 * Le reset d'un port n'a pas d'effet sur l'interface
	 */
	public void reset() {
		// Rien à faire ici
	}
	
	public int compareTo(Object o) {
		IPPortOut portOut = (IPPortOut)o;
		return getControle().getName().compareTo(portOut.getControle().getName());
	}

}
