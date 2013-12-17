package gui.toolbar.moduleCreator;

import gui.toolbar.ScalableButton;
import gui.toolbar.ScalableButtonFactory;

import java.util.*;

import javax.swing.ImageIcon;
/**
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ModuleCreatorButtonFactory implements ScalableButtonFactory {

	public Collection<ScalableButton> createButtons() {
		List<ScalableButton> list = new LinkedList<ScalableButton>();

		ModuleCreatorButton buttonLFO = new ModuleCreatorButton(new ImageIcon("img/LFO_75x50.png"), new CLFOCreator());
		buttonLFO.setToolTipText("LFO");
		ModuleCreatorButton buttonVCO = new ModuleCreatorButton(new ImageIcon("img/VCO_75x50.png"), new CVCOCreator());
		buttonVCO.setToolTipText("VCO");
		ModuleCreatorButton buttonVCF = new ModuleCreatorButton(new ImageIcon("img/VCF_75x50.png"), new CVCFCreator());
		buttonVCF.setToolTipText("VCF");
		ModuleCreatorButton buttonVCFPar = new ModuleCreatorButton(new ImageIcon("img/VCFPar_75x50.png"), new CVCFParCreator());
		buttonVCFPar.setToolTipText("VCFPar");
		ModuleCreatorButton buttonADHSR = new ModuleCreatorButton(new ImageIcon("img/ADHSR_75x50.png"), new CADHSRCreator());
		buttonADHSR.setToolTipText("ADHSR");
		ModuleCreatorButton buttonADSRTrigger = new ModuleCreatorButton(new ImageIcon("img/ADSRTrigger_75x50.png"), new CADSRTriggerCreator());
		buttonADSRTrigger.setToolTipText("ADSRTrigger");
		ModuleCreatorButton buttonVCA = new ModuleCreatorButton(new ImageIcon("img/VCA_75x50.png"), new CVCACreator());
		buttonVCA.setToolTipText("VCA");
		ModuleCreatorButton buttonMixer = new ModuleCreatorButton(new ImageIcon("img/Mixer_75x50.png"), new CMixerCreator());
		buttonMixer.setToolTipText("Mixer");
		ModuleCreatorButton buttonOutDirect = new ModuleCreatorButton(new ImageIcon("img/OUT_75x50.png"), new COutDirectCreator());
		buttonOutDirect.setToolTipText("OutDirect");
		ModuleCreatorButton buttonOutFile = new ModuleCreatorButton(new ImageIcon("img/OUT_Fichier_75x50.png"), new COutFileCreator());
		buttonOutFile.setToolTipText("OutFile");
		ModuleCreatorButton buttonOscilloscope = new ModuleCreatorButton(new ImageIcon("img/Oscillo_75x50.png"), new COscilloscopeCreator());
		buttonOscilloscope.setToolTipText("Oscilloscope");
		list.add(buttonVCO);
		list.add(buttonLFO);
		list.add(buttonVCF);
		list.add(buttonVCFPar);
		list.add(buttonADHSR);
		list.add(buttonADSRTrigger);
		list.add(buttonVCA);
		list.add(buttonMixer);
		list.add(buttonOutDirect);
		list.add(buttonOutFile);
		list.add(buttonOscilloscope);
		return list;
	}

}