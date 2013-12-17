package gui.toolbar.moduleCreator;

import gui.controle.ICModule;
import gui.presentation.IPModule;
import gui.presentation.IPSynthetizer;
import java.awt.datatransfer.*;
import java.awt.dnd.*;

import javax.swing.JComponent;

/**
 * Gestionnaire de drag pendant la cr�ation d'un module depuis un bouton
 * de la barre d'outils vers l'espace de travail.
 * 
 * Ce gestionnaire s'applique � l'espace de travail de la fen�tre principale.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CreatorDropper {
	private IPSynthetizer desktop;
	private ICModule cModule;
	
	/**
	 * Constructeur
	 * @param desktop Pr�sentation du synth�tiseur auquel est rattach� ce gestionnaire.
	 */
	public CreatorDropper(IPSynthetizer desktop) {
		this.desktop = desktop;
		cModule = null;
	}
	
	private class MyDropTargetListener implements DropTargetListener {
		
		private IPModule pModule;
		
		public MyDropTargetListener() {
		}
		
		public void dragEnter(DropTargetDragEvent evt) {
			DropTargetDropEvent event = new DropTargetDropEvent(
					evt.getDropTargetContext(),
					evt.getLocation(),
					evt.getDropAction(),
					evt.getDropAction()
			);
			if(cModule == null) {
				try {
					Transferable t = event.getTransferable();
					if(t.isDataFlavorSupported(
							new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType)
					)) {
						evt.acceptDrag(DnDConstants.ACTION_MOVE);
						CModuleTransferable cModuleTransferable = (CModuleTransferable)t.getTransferData(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType));
						cModule = cModuleTransferable.getModule();
						
						pModule = (IPModule)cModule.getPresentation(); 
						((JComponent)pModule).setLocation((int)(event.getLocation().getX() - ((JComponent)pModule).getWidth() / 2),
								(int)(event.getLocation().getY() - ((JComponent)pModule).getHeight() / 2));
						((JComponent)desktop).add(((JComponent)pModule),0);
						
						desktop.getControle().addModules(cModule);
					}
				}
				catch(ClassCastException cce) {
				}
				catch(Exception e) {
				}
			}
		}
		
		public void dragOver(DropTargetDragEvent event) {
			if(pModule != null) {
				((JComponent)pModule).setLocation(
						Math.max(0, (int)(event.getLocation().getX() - ((JComponent)pModule).getWidth() / 2)),
						Math.max(0, (int)(event.getLocation().getY() - ((JComponent)pModule).getHeight() / 2)));
			}
		}
		public void dropActionChanged(DropTargetDragEvent event) {
		}
		public void dragExit(DropTargetEvent event) {
		}
		public void drop(DropTargetDropEvent event) {
			cModule = null;
			pModule = null;
			((IPSynthetizer)desktop).refreshSize();
		}
	}
	
	public void activerDropListener() {
		new DropTarget((JComponent) desktop, new MyDropTargetListener());		
	}
}
