package gui.presentation;

import gui.controle.CModule;
import gui.controle.ICSynthetizer;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import core.IPortIn;

/**
 * Cet objet �coute les �v�nements souris qu'un module peut recevoir.
 * On peut d�placer un module comme on peut d�placer la corbeille. Donc
 * cette classe d�rive de <tt>PTrashMouseListener</tt>. Mais elle �tend cette classe
 * afin de pouvoir supprimer le module quand on le d�pose dans la corbeille.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PModuleMouseListener extends PTrashMouseListener {
	
	public PModuleMouseListener(JPanel pModule) {
		super(pModule);
	}
	
	public void mouseEntered(MouseEvent e) {
		pModule.getParent().setComponentZOrder(pModule, 0);
		pModule.firePropertyChange("componentZOrder", 1, 0);
		pModule.getParent().repaint();
	}
	
	public void mouseReleased(MouseEvent event) {
		try {
			// V�rifie que l'on relache sur la poubelle
			if(isAboveTrash(event.getPoint())) {
				IPModule module = (IPModule)pModule;
				CModule cModule = (CModule)module.getControle();
				IPSynthetizer pSynthetizer = (IPSynthetizer)((JComponent)module).getParent();
				ICSynthetizer cSynthetizer = (ICSynthetizer)pSynthetizer.getControle();
				if(cSynthetizer.isPlaying()) {
					throw new Exception("Vous ne pouvez pas supprimer un module pendant que le synth�tiseur joue.");
				}
				// V�rifie que le module n'est pas connect�
				for(IPortIn port : cModule.getPortsInTable().values()) {
					if(port.isConnected()) {
						throw new Exception("D�connectez le port d'entr�e '" + port.getName() + "' avant de supprimer le module.");
					}
				}
				if(cModule.getPortOut() != null && cModule.getPortOut().isConnected()) {
					throw new Exception("D�connectez le port de sortie avant de supprimer le module.");
				}
				// Supprime le module du synthetiseur
				if(cSynthetizer.removeModules(cModule)) {
					// Met � jour la pr�sentation
					((JComponent)pSynthetizer).remove(pModule);
				}
			}
		}
		catch(NullPointerException npe) { }
		catch(ClassCastException cce) { }
		catch(Exception e) {
			JOptionPane.showMessageDialog(pModule.getParent(), e.getMessage(), "Une erreur est survenue", JOptionPane.ERROR_MESSAGE);
		}
		pModule.setVisible(true);
		super.mouseReleased(event);
	}
	
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		pModule.setVisible(!isAboveTrash(e.getPoint()));
	}
	
	private boolean isAboveTrash(Point point) {
		try {
			Point p = new Point(point);
			SwingUtilities.convertPointToScreen(p, pModule);
			SwingUtilities.convertPointFromScreen(p, pModule.getParent());
			JPanel trash = ((IPSynthetizer)pModule.getParent()).getTrash();
			return pModule != trash 
			&& p.getX() >= trash.getLocation().getX() 
			&& p.getX() <= trash.getLocation().getX() + trash.getWidth()
			&& p.getY() >= trash.getLocation().getY()
			&& p.getY() <= (trash.getLocation().getY() + trash.getHeight());
		}
		catch(NullPointerException npe) { }
		catch(ClassCastException cce) { }
		return false;
	}
}
