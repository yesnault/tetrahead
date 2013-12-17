package gui.controle;

import gui.ConcreteFactory;
import gui.presentation.PFactory;

import java.awt.Component;
import java.util.Hashtable;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import core.AFactory;
import core.IDiscreteParameter;

/**
 * Classe implémentant le contrôle de <tt>core.modules.VCFPar</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CVCFPar extends CModule {
	public CVCFPar() {
		super(ConcreteFactory.getAFactory().newVCFPar());
	}
	

	public static void main(String...args){
		ConcreteFactory.setCFactory(CFactory.getInstance());
		ConcreteFactory.setAFactory(AFactory.getInstance());
		ConcreteFactory.setPFactory(PFactory.getInstance());
		
		ICModule cvcf = (ICModule) ConcreteFactory.getCFactory().newVCFPar();
		JDesktopPane dskp = new JDesktopPane();
		dskp.setOpaque(false);
		dskp.add((Component) cvcf.getPresentation());
		
		
		/* Affiche les modules */
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setContentPane(dskp);
		jf.setSize(400,500);
		jf.setPreferredSize(jf.getSize());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}


	@Override
	protected void addPDiscreteParameters(Hashtable<String, IDiscreteParameter> discreteParameters) {
	}
	public String getName() {
		return abstraction.getName();
	}
}
