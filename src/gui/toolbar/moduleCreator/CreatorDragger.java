package gui.toolbar.moduleCreator;

import gui.MainFrame;
import gui.controle.ICModule;

import java.awt.*;
import java.awt.dnd.*;

import javax.swing.JOptionPane;

/**
 * Gestionnaire de drag pendant la création d'un module depuis un bouton
 * de la barre d'outils vers l'espace de travail.
 * 
 * Ce gestionnaire s'applique à un des boutons de la barre inférieure
 * de la fenêtre principale.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CreatorDragger {
	private ModuleCreatorButton button;
	private CModuleTransferable panel;
	private DragSource dragSource;
	private MyDragSourceListener myDragSourceListener;
	
	/**
	 * 
	 * @param button Bouton auquel est attaché ce gestionnaire
	 */
	public CreatorDragger(ModuleCreatorButton button) {
		this.button = button;
	}
	
	private class MyDragSourceListener implements DragSourceListener {
		public void dragDropEnd(DragSourceDropEvent dsde) {
		}
		
		public void dragEnter(DragSourceDragEvent dsde) {
			dsde.getDragSourceContext().setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}
		
		public void dragExit(DragSourceEvent dse) {
		}
		
		public void dragOver(DragSourceDragEvent dsde) {
		}
		
		public void dropActionChanged(DragSourceDragEvent dsde) {
		}
	}
	
	private class MyDragGestureListener implements DragGestureListener {
		public void dragGestureRecognized(DragGestureEvent event) {
			try {
				if(MainFrame.getInstance().getPSynthetizer().getControle().isPlaying()) {
					throw new Exception("Vous ne pouvez pas ajouter de modules pendant une lecture");
				}
				ICModule module = button.newModuleController();
				panel = new CModuleTransferable(module);
				MainFrame.clearGlassPane();
				dragSource.startDrag(event, new Cursor(Cursor.HAND_CURSOR), panel, myDragSourceListener);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MyDragSourceMotionListener implements DragSourceMotionListener {
		public void dragMouseMoved(DragSourceDragEvent event) {
		}
	}
	
	public void activerDragListener() {
		myDragSourceListener = new MyDragSourceListener();
		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(button,
				DnDConstants.ACTION_MOVE, new MyDragGestureListener());
		dragSource.addDragSourceMotionListener(new MyDragSourceMotionListener());
	}
}

