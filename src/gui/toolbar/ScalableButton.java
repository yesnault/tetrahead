package gui.toolbar;

import gui.JPanelImageBg;
import gui.MainFrame;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Bouton dont l'icône s'agrandit quand la souris passe dessus.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ScalableButton extends JLabel {
	
	static final long serialVersionUID = 6574554L;
	
	/**
	 * Construit le bouton avec une image
	 * @param icone Icône du bouton
	 */
	public ScalableButton(ImageIcon icone) {
		super(icone);
		setLayout(null);
		setSize(icone.getIconWidth(), icone.getIconHeight());
		setPreferredSize(getSize());
		addMouseListener(new ScalableButtonMouseListener());
	}
	
	/**
	 * Crée une image à partir de l'icône du bouton et l'agrandit
	 * @return
	 */
	public JPanelImageBg createBigButton() {
		// Crée l'image
		BufferedImage bufImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		paint(bufImg.getGraphics());
		// L'agrandit
		AffineTransform tx = new AffineTransform();
		double scaleValue = 1.5;
		tx.scale(scaleValue, scaleValue);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		BufferedImage biNew = new BufferedImage( (int) (bufImg.getWidth() * scaleValue),
				(int) (bufImg.getHeight() * scaleValue),
				bufImg.getType());
		BufferedImage bufImgScaled = op.filter(bufImg, biNew);
		// Crée le panel avec la nouvelle image
		JPanelImageBg jpBg = new JPanelImageBg(bufImgScaled);
		jpBg.setOpaque(false);
		return jpBg;
	}
	
	private class ScalableButtonMouseListener extends MouseAdapter {
		public void mouseEntered(MouseEvent evt) {
			if(isEnabled()) {
				Component c = evt.getComponent();
				JPanel pan = createBigButton();
				// Ajoute ce panel dans le glassPane
				JComponent glassPane = (JComponent)MainFrame.getInstance().getGlassPane();
				glassPane.setLayout(null);
				glassPane.add(pan);
				
				// Convertit les coordonnées de la souris pour les mettre dans
				// le repère de la fenêtre
				Point p = (Point)getLocation().clone();
				p.x += c.getParent().getX() + (c.getWidth() - pan.getWidth()) / 2;
				p.y += c.getParent().getY() + (c.getHeight() - pan.getHeight()) / 2;
				pan.setLocation(p);
				glassPane.setVisible(true);
			}
		}
		
		public void mouseExited(MouseEvent evt) {
			MainFrame.clearGlassPane();
		}
	}
}
