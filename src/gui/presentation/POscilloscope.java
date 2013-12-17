package gui.presentation;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gui.controle.ICModule;

/**
 * Présentation du module oscilloscope (<tt>core.modules.Oscilloscope</tt>)
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class POscilloscope extends PModule implements IPOscilloscope{

	private static final long serialVersionUID = 1L;
	/*Vue de l'oscilloscope.*/
	private ViewOscilloscope view;
	/*Panel contenant le port d'entrée.*/
	private JPanel pInPan;
	/*JLabel permettant de figer l'oscilloscope.*/
	private JLabel capture;
	/*Etat de l'oscilloscope.*/
	private boolean capturing;
	
	
	public POscilloscope(IPPort pIn, ICModule controle)  {
		super(controle);

		this.pInPan = new JPanel();
		pInPan.setLayout(null);
		pInPan.setOpaque(false);
		pInPan.add(((JComponent) pIn));
		pInPan.setLocation(0, title.getHeight());
		pInPan.setSize(((JComponent)pIn).getSize());
		pInPan.setPreferredSize(pInPan.getSize());
		add(pInPan);
		
		view = new ViewOscilloscope(200,100);
		view.setLocation(((JComponent)pIn).getWidth(),title.getHeight());
		add(view);
		
		Image background = Toolkit.getDefaultToolkit().getImage("img/snapshot.png");
		ImageIcon icone = new ImageIcon(background);
		capture = new JLabel(icone);
		capture.setSize(background.getWidth(this), background.getHeight(this));
		capture.setPreferredSize(getSize());
		capture.setLocation(pInPan.getWidth()+(view.getWidth()-capture.getWidth())/2,title.getHeight()+view.getHeight());
		add(capture);
		capture.addMouseListener(new CaptureListener());
		setSizeAndShow();

	}
	
	public void initView(double min, double max, double value){
		view.setMin(min);
		view.setMax(max);
		view.setValue(value);
	}
	
	@Override
	protected void setSizeAndShow() {
		setSize(pInPan.getWidth()+view.getWidth()+BORDER,title.getHeight()+view.getHeight()+capture.getHeight()+BORDER);
		title.setLocation((getWidth()-title.getWidth())/2,0);
	}

	public void setValue(double value) {
		if(!capturing){
			view.setValue(value);
		}
	}
	
	private class CaptureListener implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			capturing = !capturing;
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
		
	}
}
