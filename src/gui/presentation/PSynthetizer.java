package gui.presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import gui.controle.ICSynthetizer;
import gui.toolbar.SynthMenuBar;
import gui.toolbar.SynthetizerCmdButtonFactory;
import gui.toolbar.moduleCreator.CreatorDropper;

import javax.swing.*;

/**
 * Présentation d'un synthétiseur (<tt>core.ISynthetizer</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PSynthetizer extends JDesktopPane implements IPSynthetizer{
	static final long serialVersionUID = 6574534L;
	
	private ICSynthetizer controle;
	private CreatorDropper dropper;
	private SynthMenuBar menuBar;
	private JPanel trash;
	public PSynthetizer(ICSynthetizer controle) {
		super();
		setOpaque(false);
		this.controle = controle;
		this.dropper = new CreatorDropper(this);
		dropper.activerDropListener();
		
		menuBar = new SynthMenuBar(new SynthetizerCmdButtonFactory(getControle()));
		
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/Trash.png"));
		JLabel jlab = new JLabel(icon);
		jlab.setSize(icon.getIconWidth(),icon.getIconHeight());
		
		trash = new JPanel(); 
		trash.add(jlab);
		trash.setSize(icon.getIconWidth(),icon.getIconHeight());
		add(trash);
		
		trash.setOpaque(false);
		trash.setLocation(10, 50);
		PTrashMouseListener trashListener = new PTrashMouseListener(trash); 
		trash.addMouseListener(trashListener);
		trash.addMouseMotionListener(trashListener);
		trash.setToolTipText("Je suis la corbeille. Déplacez un module au-dessus de moi pour le supprimer");
	}
	
	public SynthMenuBar getSynthMenuBar() {
		return menuBar;
	}
	
	public JPanel getTrash() {
		return trash;
	}
	
	public CreatorDropper getDropper() {
		return dropper;
	}

	public ICSynthetizer getControle() {
		return controle;
	}

	public void setIsPlaying(boolean b) {
		menuBar.setIsPlaying(b);
	}
	
	public void refreshSize() {
		Dimension dim = new Dimension(0, 0);
		Component[] tab = getComponents();
		for(int i = 0; i < tab.length; i++) {
			dim.setSize(
					Math.max(dim.getWidth(), tab[i].getX() + tab[i].getWidth()),
					Math.max(dim.getHeight(), tab[i].getY() + tab[i].getHeight())
					);
		}
		setSize((int)dim.getWidth() + 5, (int)dim.getHeight() + 5);
		setPreferredSize(getSize());
	}
}
