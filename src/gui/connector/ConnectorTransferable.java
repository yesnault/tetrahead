package gui.connector;

import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Un objet <tt>ConnectorTransferable</tt> est transferé pendant un drag'n'drop
 * depuis un port de sortie vers un port d'entrée.
 * 
 * Il contient un objet JConnector. 
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ConnectorTransferable implements Transferable {

	/**
	 * @uml.property  name="port"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JConnector connector;
	
	public ConnectorTransferable(JConnector connector) {
		this.connector = connector;
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
	 * @return  Returns the port.
	 * @uml.property  name="port"
	 */
	public JConnector getConnector() {
		return connector;
	}
}
