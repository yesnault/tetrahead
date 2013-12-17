package gui.toolbar.moduleCreator;

import gui.controle.ICModule;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Un <tt>CModuleTransferable</tt> est un objet transféré pendant 
 * un drag'n'drop depuis un bouton de création de module vers
 * l'espace de travail.
 * 
 * Cet objet contient une référence vers un <tt>ICModule</tt> créé 
 * au moment du drag.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CModuleTransferable implements Transferable {
	static final long serialVersionUID = 6155453L;
	
	/**
	 * @uml.property  name="module"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ICModule module;
	
	/**
	 * Constructeur
	 * @param module Module qui vient d'être créé.
	 */
	public CModuleTransferable(ICModule module) {
		this.module = module;
	}
	
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor data[] = new DataFlavor[1];
		try {
			data[0] = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		}
		return data;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
	}

	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType) ?
			this : null;
	}

	/**
	 * @return  Returns the module.
	 * @uml.property  name="module"
	 */
	public ICModule getModule() {
		return module;
	}
}
