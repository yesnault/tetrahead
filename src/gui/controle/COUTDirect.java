package gui.controle;

import gui.ConcreteFactory;
import gui.presentation.IPParameter;
import gui.presentation.IPPort;
import gui.presentation.PFactory;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import core.AFactory;
import core.IDiscreteParameter;
import core.IPortOut;
/**
 * Classe implémentant le contrôle de <tt>core.modules.OutDirect</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class COUTDirect extends CModule {
	public COUTDirect() {
		
		super(ConcreteFactory.getAFactory().newOUTDirect());
		
	}
	

	
	@Override
	protected void addPDiscreteParameters(Hashtable<String, IDiscreteParameter> discreteParameters) {
	}
	public String getName() {
		return abstraction.getName();
	}
	
	@Override
	protected void createPresentation(ArrayList<IPParameter> pParams, ArrayList<IPPort> pports, IPortOut po, CModule module) {
		presentation = ConcreteFactory.getPFactory().newPModule(
				pParams,
				pports,
				this);
	}
	
	public static void main(String...args){
		ConcreteFactory.setCFactory(CFactory.getInstance());
		ConcreteFactory.setAFactory(AFactory.getInstance());
		ConcreteFactory.setPFactory(PFactory.getInstance());
		
		ICModule cmixer = (ICModule) ConcreteFactory.getCFactory().newOUTDirect();
		JDesktopPane dskp = new JDesktopPane();
		dskp.setOpaque(false);
		dskp.add((Component) cmixer.getPresentation());
		
		
		/* Affiche les modules */
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setContentPane(dskp);
		jf.setSize(400,500);
		jf.setPreferredSize(jf.getSize());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
