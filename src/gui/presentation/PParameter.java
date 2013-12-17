package gui.presentation;

import gui.controle.ICParameter;
import gui.presentation.parameterAction.ParameterCommand;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe abstraite : definit les composants graphiques communs entre les
 * presentations des <tt>core.IDiscreteParameter</tt> et <tt>core.IContinuousParameter</tt>.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public abstract class PParameter extends JPanel implements IPParameter {
	/**
	 * @uml.property  name="rotatePanel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/*Panel rotatif affichant le curseur du bouton.*/
	protected RotatePanel rotatePanel;
	/**
	 * @uml.property  name="background"
	 */
	/*Image de fond de la présentation.*/
	protected Image background;
	/**
	 * @uml.property  name="view"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	/*Vue affichant les valeurs des paramètres*/
	protected PView view;
	/**
	 * @uml.property  name="currentJLabel"
	 * @uml.associationEnd  
	 */
	private static int NAME_WIDTH = 60;
	private static int NAME_HEIGHT = 9;
	/*Commande exécutée lorsqu'une action de l'utilisateur est détectée.*/
	protected ParameterCommand pCmd;
	
	private ICParameter controle;
	
	/**
	 * Constructeur
	 * @param controle : contrôle d'un paramètre.
	 * @param imgBackground : chemin de l'image de fond.
	 * @param imgCursor : chemin de l'image du curseur.
	 */
	public PParameter(ICParameter controle, String imgBackground, String imgCursor) {
		super();
		setLayout(null);
		this.controle = controle;
		
		/*
		 * JPanel constituant la vue du parametre. Cette vue permet d'afficher
		 * la valeur du parametre.
		 */
		view = new PView();
		add(view);
		view.setLocation(0, 0);
		
		Image background = Toolkit.getDefaultToolkit().getImage(imgBackground);
		ImageIcon icone = new ImageIcon(background);
		JLabel face = new JLabel(icone);
		face.setSize(background.getWidth(this), background.getHeight(this));
		face.setPreferredSize(getSize());
		face.setLocation((view.getWidth() - face.getWidth()) / 2, view
				.getHeight());
		add(face);
		
		/* Image du curseur se deplacant sur le bouton. */
		Image cursorImage = Toolkit.getDefaultToolkit().getImage(imgCursor);
		this.rotatePanel = new RotatePanel(cursorImage);
		add(rotatePanel, 0);
		this.rotatePanel.setLocation(
				(view.getWidth() - rotatePanel.getWidth()) / 2, view
				.getHeight()
				+ (face.getHeight() - rotatePanel.getHeight()) / 2);
		
		/* Affichage du nom du parametre. */
		Font ft = new Font("parametre", Font.BOLD, NAME_HEIGHT);
		JLabel jlab = new JLabel(controle.getName(), JLabel.CENTER);
		jlab.setForeground(new Color(255, 254, 215));
		jlab.setFont(ft);
		jlab.setSize(NAME_WIDTH, NAME_HEIGHT);
		jlab.setPreferredSize(jlab.getSize());
		jlab.setLocation((view.getWidth() - jlab.getWidth()) / 2, view
				.getHeight()
				+ face.getHeight());
		add(jlab);
		
		setSize(view.getWidth(), face.getHeight() + view.getHeight()
				+ jlab.getHeight());
		setPreferredSize(getSize());
		setOpaque(false);
	}
	
	/**
	 * Met en forme une chaîne de caractère représentant un double.
	 * @param str : chaîne représentant un double.
	 * @return : une chaîne de caractère représentant un double avec 2 chiffres après la virgule.
	 */
	protected String formatDoubleString(String str){
		Pattern p = Pattern.compile("([0-9]*\\.[0-9]{0,2}).*");
		Matcher m = p.matcher(str);
		if(m.matches()) {
			str = m.group(1);
		} 
		return str;
	}
	
	protected abstract void refreshView(String str);
	
	public abstract void initRotatePanel(double min, double max);
	
	public int compareTo(Object o) {
		IPParameter parameter = (IPParameter)o;
		return controle.getName().compareTo(parameter.getControle().getName());
	}
	
	public ICParameter getControle() {
		return controle;
	}

	public void setParameterCommand(ParameterCommand pc) {
		this.pCmd = pc;
	}
}
