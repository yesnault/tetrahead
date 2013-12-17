package gui.connector;

import gui.controle.ICPortOut;
import gui.presentation.IPPortIn;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 * Gestionnaire du drag pendant la cr�ation d'un connecteur entre un port
 * de sortie et port d'entr�e.
 * 
 * Comme la cr�ation d'un connecteur se fait d'un port de sortie vers
 * un port d'entr�e, ce gestionnaire s'applique � un port d'entr�e.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ConnectorDropper {
	
	/**
	 * @uml.property  name="portIn"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private IPPortIn portIn;
	/**
	 * @uml.property  name="portOut"
	 * @uml.associationEnd  
	 */
	private ICPortOut portOut;
	
	/**
	 * Lie this avec un port d'entr�e
	 * @param portIn
	 */
	public ConnectorDropper(IPPortIn portIn) {
		this.portIn = portIn;
	}
	
	private class MyDropTargetListener implements DropTargetListener {
		
		public MyDropTargetListener() {
		}
		
		public void dragEnter(DropTargetDragEvent evt) {
			try {
				if (evt.getTransferable().getTransferData(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType)) instanceof ConnectorTransferable) {
					drawBorder(portIn.getControle().isConnected() ? 
							Color.RED :
							Color.GREEN);	
				}	
			}
			catch(Exception e) {}
		}
		
		private void drawBorder(Color color) {
			JComponent c = (JComponent)portIn;
			Border b  = BorderFactory.createLineBorder(color, 3);
			c.setBorder(b);
		}
		
		public void dragOver(DropTargetDragEvent event) {
		}
		
		public void dropActionChanged(DropTargetDragEvent event) {
		}
		
		public void dragExit(DropTargetEvent event) {
			((JComponent)portIn).setBorder(BorderFactory.createEmptyBorder());
		}
		
		public void drop(DropTargetDropEvent evt) {
			DropTargetDropEvent event = new DropTargetDropEvent(
					evt.getDropTargetContext(),
					evt.getLocation(),
					evt.getDropAction(),
					evt.getDropAction()
			);
			try {
				((JComponent)portIn).setBorder(BorderFactory.createEmptyBorder());
				if(portIn.getControle().isConnected()) {
					throw new Exception("Ce port est d�j� connect�");
				}
				Transferable t = event.getTransferable();
				if(t.isDataFlavorSupported(
						new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType)
				)) {
					ConnectorTransferable connectorTransferable = (ConnectorTransferable)t.getTransferData(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType));
					JConnector connector = connectorTransferable.getConnector();
					portOut = connector.getPPortOut().getControle();
					portOut.addPortIn(portIn.getControle());
					
					connector.setPPortIn((IPPortIn)portIn);
					portOut = null;	
					event.dropComplete(true);
				}
				else {
					event.rejectDrop();
				}
			}
			catch(ClassCastException cce) {
				event.rejectDrop();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				event.rejectDrop();
			}
		}
	}
	
	/**
	 * Active l'�coute des messages drop.
	 */
	public void activerDropListener() {
		new DropTarget((JComponent)portIn, new MyDropTargetListener());
	}
}
