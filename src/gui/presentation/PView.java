package gui.presentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Composant permettant l'affichage formatée d'une valeur via des <tt>JTextComponent</tt>.
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PView extends JPanel {
	private static final long serialVersionUID = 1L;
	private static int VIEW_WIDTH = 60;
	private static int VIEW_HEIGHT = 20;
	private static Color colorBackground = new Color(255,254,215);
	/**
	 * @uml.property  name="ft"
	 */
	private Font ft ;
	
	public PView(){
		super();
		ft = new Font("view",Font.BOLD,9);
		setBackground(colorBackground);
		setLayout(null);
		setSize(VIEW_WIDTH,VIEW_HEIGHT);
		setPreferredSize(getSize());
	}
	
	public void addJComponent(JComponent jComp){
		jComp.setFont(ft);
		jComp.setBackground(colorBackground);
		jComp.setSize(getWidth(),getHeight());
		jComp.setPreferredSize(jComp.getSize());
		jComp.setVisible(true);
		add(jComp);
	}
	
	public void addJTextField(JTextField jTxtFld){
		jTxtFld.setHorizontalAlignment(JTextField.CENTER);
		addJComponent(jTxtFld);
	}
	
	@Override
	/**
	 * Redefinition de la methode paint. On dessine en plus un rectangle tout autour du composant
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2.0f));
		g2.drawRect(0,0,getWidth(),getHeight());
//		g2.setColor(Color.GRAY);
//		g2.drawRect(2,2,getWidth(),getHeight());
	}
	
}
