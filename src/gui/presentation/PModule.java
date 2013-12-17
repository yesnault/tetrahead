package gui.presentation;

import gui.JPanelImageBg;
import gui.controle.ICModule;

import javax.sound.sampled.LineUnavailableException;

import java.awt.*;
import java.util.*;

import javax.swing.*;

/**
 * Présentation des modules (<tt>core.modules.IModule</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PModule extends JPanelImageBg implements IPModule {
	private static final long serialVersionUID = 1L;

	/*Nombre de paramètres par ligne*/
	public static int NB_PARAM_LINE = 4;
	/*Largeur du titre*/
	public static int TITLE_WIDTH = 200;
	/*Hauteur du titre*/
	public static int TITLE_HEIGHT = 20;
	/*Bordure*/
	public static int BORDER=5;

	/*JPanel contenant les présentations des ports d'entrée.*/
	private JPanel jpanPortsIn;
	/*JPanel contenant les présentations des ports de sortie.*/
	private JPanel jpanPortsOut;
	/*JPanel contenant les présentations des paramètres.*/
	private JPanel jpanParameters;
	/*JLabel affichant le titre.*/
	protected JLabel title;
	/*Hauteur du module.*/
	private int moduleHeight;
	/*Contrôle du module.*/
	private ICModule controle;

	/**
	 * Contructeur principal
	 * @param controle
	 */
	public PModule(ICModule controle) {
		super("img/Module_texture.jpg");
		this.controle = controle;
		setLayout(null);
		
		/*Mise en forme du titre.*/
		title = new JLabel(controle.getName(),JLabel.CENTER);
		title.setForeground(new Color(255,254,215));
		title.setSize(TITLE_WIDTH,TITLE_HEIGHT);
		add(title);
		
		/*Ajout d'un listener gérant les déplacement de la présentation.*/
		//MyMouseListener myMouseListener = new MyMouseListener(this);
		PModuleMouseListener myMouseListener = new PModuleMouseListener(this);
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
	}
	
	/**
	 * Constructeur d'un module avec port de sortie.
	 * @param params : présentations des paramètres d'un module.
	 * @param pIn : présentations des ports d'entrées d'un modules.
	 * @param pOut : présentation du port de sortie d'un module.
	 * @param controle : contrôle de la présentation.
	 */
	public PModule(Collection<IPParameter> params, Collection<IPPort> pIn,
			IPPort pOut, ICModule controle) {
		this(params, pIn, controle);

		/*Ajout de la présentation dans un JPanel et mise en forme.*/
		jpanPortsOut.add((JPanel) pOut);
		jpanPortsOut.setSize(((JPanel) pOut).getWidth(), ((JPanel) pOut)
				.getHeight());
		jpanPortsOut.setPreferredSize(jpanPortsOut.getSize());
		jpanPortsOut.setLocation(jpanPortsIn.getWidth()
				+ jpanParameters.getWidth(), getHeight()-jpanPortsOut.getHeight()-BORDER);
		jpanPortsOut.setOpaque(false);
		add(jpanPortsOut);
		
		/*Mise à jour de moduleHeight si nécessaire*/
		if (jpanPortsOut.getHeight() > moduleHeight) {
			moduleHeight = jpanPortsOut.getHeight();
		}

		setSizeAndShow();
	}
	
	/**
	 * Constructeur d'un module avec port de sortie.
	 * @param params : présentations des paramètres d'un module.
	 * @param pIn : présentations des ports d'entrées d'un modules.
	 * @param controle : contrôle de la présentation.
	 */
	public PModule(Collection<IPParameter> params, Collection<IPPort> pIn,
			ICModule controle) {
		this(controle);
		
		jpanPortsIn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		jpanPortsOut = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

		ArrayList<IPParameter> parameters = new ArrayList<IPParameter>();
		ArrayList<IPPort> portsIn = new ArrayList<IPPort>();

		for (IPParameter pParametre : params) {
			parameters.add(pParametre);
		}
		for (IPPort pPort : pIn) {
			portsIn.add(pPort);
		}		
		
		moduleHeight = title.getHeight();;

		/* Panel des ports d'entree. */
		for (IPPort port : portsIn) {
			jpanPortsIn.add((JPanel) port);
		}
		/*Si la liste des ports n'est pas vide alors on recalcule la taille de jpanPortsIn*/
		if (!portsIn.isEmpty()) {
			int l = ((JPanel) portsIn.get(0)).getWidth();
			int h = ((JPanel) portsIn.get(0)).getHeight() * portsIn.size();
			jpanPortsIn.setSize(l, h);
			jpanPortsIn.setPreferredSize(jpanPortsIn.getSize());
		}
		jpanPortsIn.setLocation(0, title.getHeight());
		jpanPortsIn.setOpaque(false);
		add(jpanPortsIn);

		moduleHeight = jpanPortsIn.getHeight();

		/* Panel des parametres. */
		jpanParameters = new JPanel(new FlowLayout(FlowLayout.CENTER, BORDER, BORDER));
		for (IPParameter parameter : parameters) {
			jpanParameters.add((JPanel) parameter);
		}
		/*Si la liste des paramètres n'est pas vide alors on recalcule la taille de jpanParameters*/
		if (!parameters.isEmpty()) {
			int l = 0;
			if (parameters.size() > NB_PARAM_LINE) {
				l = ( ((JPanel) parameters.get(0)).getWidth() + BORDER) * NB_PARAM_LINE;
			} else {
				l = ( ((JPanel) parameters.get(0)).getWidth() + BORDER) * parameters.size();
			}
			int h = (int) Math.ceil(parameters.size() / (double) NB_PARAM_LINE)
					* ( ((JPanel) parameters.get(0)).getHeight() + BORDER);
			jpanParameters.setSize(l+2*BORDER, h);
			jpanParameters.setPreferredSize(jpanParameters.getSize());
		}
		jpanParameters.setLocation(jpanPortsIn.getWidth(), title.getHeight());
		jpanParameters.setOpaque(false);
		add(jpanParameters);

		/*Mise à jour de moduleHeight si nécessaire*/
		if (jpanParameters.getHeight() > moduleHeight) {
			moduleHeight = jpanParameters.getHeight();
		}
		setSizeAndShow();
	}

	/**
	 * Met à jour les dimensions de la présentation.
	 *
	 */
	protected void setSizeAndShow() {
		setSize(jpanParameters.getWidth() + jpanPortsIn.getWidth()
				+ jpanPortsOut.getWidth(), moduleHeight + title.getHeight()+BORDER);
		title.setLocation((getWidth()-title.getWidth())/2,0);
		setPreferredSize(getSize());
		setVisible(true);
	}
	
	public ICModule getControle() {
		return controle;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0,0,getWidth()-1,getHeight()-1);
	}
	
	public void showException(Exception e) {
		if (e.getClass().equals(LineUnavailableException.class)) {
			JOptionPane.showMessageDialog(this, "Votre carte son est utilisée par un autre programme. \n\n Veuillez le stopper et recommencer la lecture.", "Carte son utilisée par un autre programme", JOptionPane.ERROR_MESSAGE);	
		} else {
			JOptionPane.showMessageDialog(this, "Une erreur est survenue : "+e.getMessage() + "\n\n"+  e.getStackTrace(), "Une erreur est survenue !", JOptionPane.ERROR_MESSAGE);
		}
	}
}
