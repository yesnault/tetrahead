package gui.presentation;

import java.awt.*;
import java.awt.geom.*;

/**
 * Composant permettant de faire effectuer une rotation à une image.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class RotatePanel extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property name="image"
	 */
	private Image image;

	/**
	 * @uml.property name="currentAngle"
	 */
	private double currentAngle;

	/**
	 * @uml.property name="shape"
	 */
	private Shape shape;

	/**
	 * @uml.property name="angleMin"
	 */
	private double angleMin;

	/**
	 * @uml.property name="angleMax"
	 */
	private double angleMax;

	/**
	 * @uml.property name="cran"
	 */
	private double cran;

	/**
	 * @uml.property name="min"
	 */
	private double min;

	/**
	 * @uml.property name="max"
	 */
	private double max;

	private double range;

	/**
	 * @param image :
	 *            image affichee dans le composant.
	 */
	public RotatePanel(Image image) {
		this.image = image;

		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);
		try {
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(image.getWidth(this), image.getHeight(this));
		setPreferredSize(getSize());
		// setOpaque(false);
	}

	/**
	 * Initialise les valeur utilisees pour calculer les rotations.
	 * 
	 * @param min :
	 *            position minimum.
	 * @param max :
	 *            position maximum.
	 * @param sensitive :
	 *            sensibilite du deplacement.
	 * @param angleMin :
	 *            angle minimum.
	 * @param angleMax :
	 *            angle max.
	 */

	public void init(double min, double max, double angleMin, double angleMax) {
		this.min = min;
		this.max = max;

		this.angleMin = angleMin;
		this.angleMax = angleMax;
		this.range = angleMax - angleMin;
		this.cran = range / (max - min);
		this.currentAngle = angleMin;
	}

	/**
	 * Calcule un angle de rotation lorsque ce composant est utilise dans une
	 * presentation de parametre discret. Permet de tourner de position en
	 * position dans un sens comme dans l'autre.
	 * 
	 * @param sens :
	 *            booleen a true si on tourne de 0 vers 360, a false si on
	 *            tourne dans l'autre sens.
	 */
	public void rotateDiscrete(boolean sens) {

		double angleTmp = currentAngle;

		if (sens) {
			angleTmp += cran;
		} else {
			angleTmp -= cran;
		}
		currentAngle = checkAngle(angleTmp);
		// System.out.println("RotatePanel::rotate()::value=" + value + "
		// cran="+ cran);
		repaint();
	}

	/**
	 * Vérifie que l'anble passé en paramètres est valide ou non en fonction de 
	 * angleMin et angleMax.
	 * @param angleTmp
	 * @return : angleMin si angleTmp &lt angleMin, angleMax si angleTmp &gt angleMax, angleTmp sinon.
	 */
	public double checkAngle(double angleTmp) {
		if (angleTmp < angleMin) {
			return angleMin;
		} else if (angleTmp > angleMax) {
			return angleMax;
		}
		return angleTmp;
	}

	public void rotateContinuous(double f) {
		currentAngle = checkAngle(angleMin + range * f);
		// System.out.println("RotatePanel::rotateContinuous::angle="+currentAngle+"rate="+f);
		repaint();
	}

	public double calculRate(int x, int y) {
		/* Centre de la rotation. */
		double x0 = y - (this.getHeight()) / 2;
		double y0 = (this.getWidth()) / 2 - x;
		double angle, rate;
		
		/*Calcul d'un angle en fonction de x0, y0, x et y.*/
		if (x0 <= 0.0 && y0 < 0.0) {
			// System.out.println(angleMax-Math.toDegrees(Math.acos(x0/(Math.sqrt(x0*x0+y0*y0)))));
			angle = Math.ceil((360 - Math.toDegrees(Math.acos(x0
					/ (Math.sqrt(x0 * x0 + y0 * y0))))));
		} else if (y0 < 0.0 && x0 > 0.0) {
			// System.out.println(angleMax - Math.toDegrees(Math.acos(x0/(Math.sqrt(x0*x0+y0*y0)))));
			angle = Math.ceil((360 - Math.toDegrees(Math.acos(x0
					/ (Math.sqrt(x0 * x0 + y0 * y0))))));
		} else {
			// System.out.println(Math.toDegrees(Math.acos(x0/(Math.sqrt(x0*x0+y0*y0)))));
			angle = Math.ceil((Math.toDegrees(Math.acos(x0
					/ (Math.sqrt(x0 * x0 + y0 * y0))))));
		}
		/*Calcul d'un pourcentage en fonction de l'angle et de la portion de cercle
		 * défini par angleMin et range*/
		rate = (angle - angleMin) / range;
		// System.out.println("RotatePanel::calculRate::angle="+angle+"rate="+rate);
		return rate;
	}

	public void paint(Graphics g) {
		super.paint(g);
		/* Utilisation de java2D */
		Graphics2D g2d = (Graphics2D) g;
		/* Sauvegarde du transform courant. */
		AffineTransform origXform = g2d.getTransform();
		/* Creation d'un nouveau tranform */
		AffineTransform newXform = (AffineTransform) (origXform.clone());

		/* Centre de la rotation. */
		int xRot = (this.getWidth()) / 2;
		int yRot = (this.getHeight()) / 2;
		/* Rotation du composant. */
		newXform.rotate(Math.toRadians(currentAngle), xRot, yRot);
		g2d.setTransform(newXform);


		/* Coordonnes de l'image placee au centre du panel. */
		int x = (getWidth() - image.getWidth(this)) / 2;
		int y = (getHeight() - image.getHeight(this)) / 2;

		// System.out.println("RotatePanel::Location::xLocation="+ getLocation().x + " yLocation=" + getLocation().y);

		/* On dessine l'image. */
		g2d.drawImage(image, x, y, this);
		/* On remet le d'origine. */
		g2d.setTransform(origXform);
	}

	/**
	 * Redfinition de contains.
	 */
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, image.getWidth(this), image
					.getHeight(this));
		}
		return (shape.contains(x, y));
	}

	/**
	 * @return Returns the currentAngle.
	 * @uml.property name="currentAngle"
	 */
	public double getCurrentAngle() {
		return currentAngle;
	}

	/**
	 * @return Returns the min.
	 * @uml.property name="min"
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @return Returns the max.
	 * @uml.property name="max"
	 */
	public double getMax() {
		return max;
	}

	public void setCurrentAngle(double d) {
		currentAngle = d;
		repaint();
	}

	public void reset() {
		this.currentAngle = angleMin;
		repaint();
	}

}