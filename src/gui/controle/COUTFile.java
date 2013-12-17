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
 * Classe impl�mentant le contr�le de <tt>core.modules.OutFile</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class COUTFile extends CModule {
	public COUTFile() {
		
		super(ConcreteFactory.getAFactory().newOUTFile());
		
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
		
		ICModule cmixer = (ICModule) ConcreteFactory.getCFactory().newOUTFile();
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
