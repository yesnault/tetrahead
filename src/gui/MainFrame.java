package gui;

import gui.controle.CFactory;
import gui.controle.ICSynthetizer;
import gui.presentation.IPSynthetizer;
import gui.presentation.PFactory;
import gui.toolbar.ScalableButtonMenuBar;
import gui.toolbar.moduleCreator.ModuleCreatorButtonFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import core.AFactory;
import core.ISynthetizer;

/**
 * Fenêtre principale de l'application.
 * Le patron Singleton fait qu'il n'existe qu'une seule instance de cette classe.
 *  
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class MainFrame extends JFrame {
	static final long serialVersionUID = 5671676461L;

	private static MainFrame mainFrame;

	private ISynthetizer synthetizer;
	
	private Color menuBarSynthe = new Color(52,65,93); 
	private Color menuBarMainFrame = new Color(52,65,93); 
	private Color mainFrameBackground = Color.WHITE;
	//private Color mainFrameBackground = new Color(219,219,220); 

	/**
	 * Permet de récupérer l'instance de la Mainframe
	 */
	public static MainFrame getInstance() {
		if (mainFrame == null) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}

	/**
	 * Permet de récupérer le composant présentation du synthétiseur
	 * @return Présentation du synthétiseur
	 */
	public IPSynthetizer getPSynthetizer() {
		return (IPSynthetizer) ((ICSynthetizer) synthetizer).getPresentation();
	}

	/**
	 * Rend le GlassPane invisible et le vide
	 */
	public static void clearGlassPane() {
		JPanel glassPane = (JPanel) getInstance().getGlassPane();
		glassPane.removeAll();
		glassPane.setVisible(false);
	}

	private MainFrame() {
		super("TetraHead");
				
		synthetizer = ConcreteFactory.getCFactory().newSynthetizer();
		
		add(((ICSynthetizer)synthetizer).getPresentation().getSynthMenuBar(), BorderLayout.NORTH, 0);
		((ICSynthetizer)synthetizer).getPresentation().getSynthMenuBar().setBackground(menuBarSynthe);
		
		JComponent pSynthetizer = (JComponent)((ICSynthetizer)synthetizer).getPresentation();
		pSynthetizer.setBackground(mainFrameBackground);
		
		JScrollPane scrollableSynthetizer = new JScrollPane(pSynthetizer);
		scrollableSynthetizer.setBackground(mainFrameBackground);
		add(scrollableSynthetizer, BorderLayout.CENTER, 1);
		
		ScalableButtonMenuBar bm = new ScalableButtonMenuBar(new ModuleCreatorButtonFactory());
		bm.setBackground(menuBarMainFrame);
		bm.setToolTipText("Glissez un bouton pour créer un nouveau module");
		add(bm, BorderLayout.SOUTH, 2);
		
		this.setBackground(mainFrameBackground);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int) dim.getWidth() - 100, (int) dim.getHeight() - 100);
		setVisible(true);
		// ouvrir la fenêtre prinicpale en plein écran
		setExtendedState(MAXIMIZED_BOTH);
	}

	public static void main(String[] args) {
		// splashscreen
		Image image = Toolkit.getDefaultToolkit().getImage(
				"img/logo_TetraHead.png");
		image = new ImageIcon(image).getImage();

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
		g.drawImage(image, 0, 0, null);
		g.dispose();

		ShadowedWindow window = new ShadowedWindow(bufferedImage);

		window.setVisible(true);

		ConcreteFactory.setAFactory(AFactory.getInstance());
		ConcreteFactory.setCFactory(CFactory.getInstance());
		ConcreteFactory.setPFactory(PFactory.getInstance());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainFrame.getInstance();
	}
}
