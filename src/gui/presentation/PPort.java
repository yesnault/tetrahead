package gui.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Présentation des ports (<tt>core.IPort</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public abstract class PPort extends JPanel implements IPPort{
	
	private static int NAME_WIDTH = 50;
	private static int NAME_HEIGHT = 9;
	
	public PPort(String name){
		super();
		setLayout(new BorderLayout());
		Font ft = new Font("port",Font.BOLD,NAME_HEIGHT);
		JLabel jlab = new JLabel(name,JLabel.CENTER);
		jlab.setForeground(new Color(255,254,215));
		jlab.setFont(ft);
		jlab.setSize(NAME_WIDTH,NAME_HEIGHT);
		jlab.setPreferredSize(jlab.getSize());
		add(jlab,BorderLayout.SOUTH);
		
		Image background = Toolkit.getDefaultToolkit().getImage(
				"img/Port_25x25.png");
		ImageIcon icone = new ImageIcon(background);
		JLabel face = new JLabel(icone);
		face.setSize(background.getWidth(this), background.getHeight(this));
		face.setPreferredSize(getSize());
		add(face,BorderLayout.CENTER);
		
		setSize(jlab.getWidth(),jlab.getHeight()+face.getHeight());
		setPreferredSize(getSize());
		setOpaque(false);
	}
	
	public Point getCenter() {
		return new Point(getWidth() / 2, 25 / 2);
	}
}
