package gui.presentation;

import gui.controle.ICParameter;
import gui.presentation.parameterAction.TickListener;

import java.awt.*;
import javax.swing.*;

/**
 * Présentation d'un paramètre discret (<tt>core.IDiscreteParameter</tt>)
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PDiscreteParameter extends PParameter implements
		IPDiscreteParameter {

	private static final long serialVersionUID = 1L;
	
	/*La présentation affiche des images si true, des chaînes sinon.*/
	private boolean displayImages;
	/*Valeur initiale affichée.*/
	private String initValue;
	/*JLabel permettant d'afficher la valeur courante.*/
	protected JLabel currentJLabel;	

	/**
	 * Constructeur
	 * @param controle : contrôle de la présentation.
	 * @param initValue : valeur initiale affichée.
	 */
	public PDiscreteParameter(ICParameter controle, String initValue) {
		super(controle, "img/Button_50x50.png", "img/Cursor_30x30.png");
		displayImages = false;
		this.initValue = initValue;
		refreshView(initValue);
	}

	public void displayImages() {
		this.displayImages = true;
		refreshView(initValue);
	}

	/**
	 * Charge une image dans la vue du parametre.
	 * 
	 * @param path :
	 *            Le nom de l'image sans l'extension. Par dï¿½faut les images sont
	 *            chargees depuis le repertoire img avec comme extension .png.
	 * @return : Un JLabel contenant une image.
	 */
	private JLabel buildImageJLabel(String path) {
		Image image = Toolkit.getDefaultToolkit().getImage(
				"img/" + path + ".png");
		/* Creation d'un JLabel contenant l'image. */
		JLabel jlab = new JLabel(new ImageIcon(image), JLabel.CENTER);
		return jlab;
	}
	
	/**
	 * Charge une chaine de caracteres dans la vue du parametre.
	 * @param str : La chaine a visualiser.
	 * @return : Un JLabel contenant une chaine de caracteres.
	 */
	protected JLabel buildStringJLabel(String str) {
		String strTmp = str;
		strTmp = formatDoubleString(strTmp);
		JLabel jlab = new JLabel(strTmp, JLabel.CENTER);
		return jlab;
		
	}

	public void refreshView(String str) {
		if (currentJLabel != null) {
			view.remove(currentJLabel);
		}
		if (displayImages) {/*Affichage d'une image.*/
			// System.out.println("PDiscreteParameter::refreshView::display
			// Image");
			currentJLabel = buildImageJLabel(str);
			view.addJComponent((JComponent)currentJLabel);

		} else {/*Affichage d'un châine de caractères.*/
			// System.out.println("PDiscreteParameter::refreshView::display
			// String");
			currentJLabel = buildStringJLabel(str);
			view.addJComponent((JComponent)currentJLabel);
		}
		view.repaint();
	}

	public void initRotatePanel(double min, double max) {
		double tmp = ((max + 1) * 20);
		this.rotatePanel.init(min, max, 180 - tmp, 180 + tmp);
		/*Ajout d'un listener sur les click souris sur les boutons gauche et droit*/
		TickListener tickListener = new TickListener(pCmd);
		this.rotatePanel.addMouseListener(tickListener);
	}

	public void reset() {
		rotatePanel.reset();
	}
	
	public void rotate(boolean b){
		rotatePanel.rotateDiscrete(b);
	}
}
