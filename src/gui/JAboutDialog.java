package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Boîte de dialogue "A propos de..."
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class JAboutDialog extends JDialog {
	static final long serialVersionUID = 36186L;
	
	public JAboutDialog() {
		super(MainFrame.getInstance(), "A propos de TetraHead...");
		Container cp = getContentPane();
	    cp.setLayout(new FlowLayout());
	    // 200x153
	    setSize(50*2+200, 300);
	    
	    JPanelImageBg logo = new JPanelImageBg("img/logo_TetraHead_small.png");
	    logo.setLocation(50, 70);
	    add(logo);
	    
	    JLabel yvonnick = new JLabel("Yvonnick Esnault");
	    JLabel gaetan = new JLabel("Gaëtan Le Brun");
	    JLabel thibaut = new JLabel("Thibaut Lelièvre");
	    JLabel vincent = new JLabel("Vincent Mahé");
	    JLabel mail = new JLabel("mail");
	    
	    JPanel tabNoms = new JPanel();
	    tabNoms.setLayout(new GridLayout(5, 1));
	    tabNoms.add(yvonnick);
	    tabNoms.add(gaetan);
	    tabNoms.add(thibaut);
	    tabNoms.add(vincent);
	    tabNoms.add(mail);
	    
	    cp.add(tabNoms);
	    tabNoms.setLocation(50, 200);
	    
	    setLocationRelativeTo(null);
	}
}
