package gui.connector;


import gui.MainFrame;
import gui.presentation.IPPortOut;
import gui.presentation.IPSynthetizer;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Gestionnaire du drag pendant la création d'un connecteur entre un port
 * de sortie et port d'entrée.
 * 
 * Comme la création d'un connecteur se fait d'un port de sortie vers
 * un port d'entrée, ce gestionnaire s'applique à un port de sortie.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ConnectorDragger {
	private DragSource dragSource;
	private Transferable transferable;
	private MyDragSourceListener myDragSourceListener;
	private IPPortOut port;
	private Point initPoint;
	private JConnector connector; 
	private IPSynthetizer presentation = MainFrame.getInstance().getPSynthetizer();
	
	/**
	 * Lie this avec un port de sortie
	 * @param port
	 */
	public ConnectorDragger(IPPortOut port) {
		this.port = port;
	}
	
	private class MyDragSourceListener implements DragSourceListener {
		public void dragDropEnd(DragSourceDropEvent dsde) {
			if(!dsde.getDropSuccess()) {
				connector.destroy();
				((JComponent)presentation).remove(connector);
			}
			((JComponent)presentation).repaint();
			connector = null;
		}
		
		public void dragEnter(DragSourceDragEvent dsde) {
			dsde.getDragSourceContext().setCursor(new Cursor(Cursor.HAND_CURSOR));
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
					throw new Exception("Vous ne pouvez pas ajouter des connecteurs pendant une lecture");
				}
				initPoint = new Point(event.getDragOrigin());
				IPPortOut panPort = (IPPortOut)port;			
				SwingUtilities.convertPointToScreen(initPoint, ((JComponent)panPort));
				SwingUtilities.convertPointFromScreen(initPoint, ((JComponent)presentation));
				
				connector = new JConnector(panPort);
				transferable = new ConnectorTransferable(connector);

				((JComponent)presentation).add(connector, 0);
				connector.setLocation(initPoint);
				connector.setVisible(true);
				
				dragSource.startDrag(event, new Cursor(Cursor.HAND_CURSOR), transferable, myDragSourceListener);
				((JComponent)presentation).repaint();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class MyDragSourceMotionListener implements DragSourceMotionListener {
				
		public void dragMouseMoved(DragSourceDragEvent event) {
			Point p = new Point(event.getLocation());
			SwingUtilities.convertPointFromScreen(p, ((JComponent)presentation));
			int x1 = (int)p.getX();
			int y1 = (int)p.getY();
			int x2 = (int)initPoint.getX();
			int y2 = (int)initPoint.getY();
			connector.setDiagonaleType(Math.signum(x1 - x2) == Math.signum(y1 - y2) ?
					JConnector.LOWERING_DIAGONAL :
					JConnector.RISING_DIAGONAL);
			connector.setLocation(Math.min(x1, x2), Math.min(y1, y2));
			connector.setSize(Math.abs(x1 - x2), Math.abs(y1 - y2));
			connector.repaint();
			//connector.setEnd(new Point2D.Double(p.getX(), p.getY()));
		}
	}
	
	/**
	 * Active l'écoute des messages drag
	 */
	public void activerDragListener() {
		myDragSourceListener = new MyDragSourceListener();
		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer((JPanel)port,
				DnDConstants.ACTION_MOVE, new MyDragGestureListener());
		dragSource.addDragSourceMotionListener(new MyDragSourceMotionListener());
	}
	
}
