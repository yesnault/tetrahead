package tests;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import javax.swing.*;
import core.*;

public class TestsCompute {

	static TestsCompute test ;
	static ModuleOscillo mo ;
	static JMenuBar menuBar ;
	
	/**
	 * @param args
	 */
	private static Timer t = null; 
	
	public static void main(String[] args) {
		test = new TestsCompute() ;

		JFrame f = new JFrame("Tests méthodes compute()") ;
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { 
				System.exit(0);  
			}
		});
		// menus
		menuBar = new JMenuBar();
		f.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		TestsCompute tc = new TestsCompute();
		
		t = new Timer(10,tc.new CmdTimer());
		
		// connexion a l'oscilloscope
		mo = new ModuleOscillo(500, 250) ;
		mo.addPortsIn(ModuleOscillo.PORT_IN, new PortIn(ModuleOscillo.PORT_IN)) ;

		///////////////////////////////////////////////////
		// ajout des modules testables par oscilloscopie //
		plugTestedModule(new VCOTest());
		plugTestedModule(new LFOTest());
		plugTestedModule(new VCOPitchInTest());
		plugTestedModule(new VCFTest());
		plugTestedModule(new VCFParTest());
		plugTestedModule(new ADHSRTest());
		plugTestedModule(new ADSRTriggerTest());
		plugTestedModule(new VCATest());
		plugTestedModule(new MixerTest());
		///////////////////////////////////////////////////
		
		// affichage de l'oscilloscope
		f.getContentPane().add(mo.o, BorderLayout.SOUTH);
		// l'oscilloscope bloque des que redimensionne
		f.setResizable(false);

		f.pack();
		f.setVisible(true);
	}
	
	private static void plugTestedModule(ITestModule tm) {
		tm.setModuleOscillo(mo) ;
		tm.init() ;
		tm.getTestedModule().getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
		
		// ajouter le module au menu
		JMenu menuModule = new JMenu(tm.getTestedModule().getClass().getSimpleName());
		menuBar.add(menuModule);
		
		for(Method me : tm.getTestedMethods()) {
			JMenuItem menuCompute = new JMenuItem(me.getName());
			menuCompute.addActionListener(
					test.new ActionCommandeListener(tm, me)) ;
			menuModule.add(menuCompute) ;
		}
	}

	private class ActionCommandeListener implements ActionListener {
		ITestModule itm;
		Method me;
		public ActionCommandeListener(ITestModule testModule, Method method) {
			this.itm = testModule;
			this.me = method ;
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				
				t.setDelay(10);
				t.removeActionListener(t.getActionListeners()[0]);
				t.addActionListener(new CmdTimer(itm));
				
				t.start();
				me.invoke(itm);
				
			}
			catch(Exception exc){
				System.out.println(exc.getMessage());
				exc.printStackTrace() ;
			}
		}
	}
	
	private class CmdTimer implements ActionListener {
		ITestModule itm;
		
		public CmdTimer() {
		
		}
		
		public CmdTimer(ITestModule testModule) {
			this.itm = testModule;
		}
		
		public void actionPerformed(ActionEvent evt) {
			itm.execute();
		}
	}
	
}
