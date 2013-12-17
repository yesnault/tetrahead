package gui.toolbar.moduleCreator;

import gui.controle.ICModule;
import gui.toolbar.ScalableButton;

import javax.swing.*;
/**
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class ModuleCreatorButton extends ScalableButton {
	
	static final long serialVersionUID = 435413L;
	private CreatorDragger dragger;
	private ModuleCreatorCmd cmd;
	
	public ModuleCreatorButton(ImageIcon icone, ModuleCreatorCmd cmd) {
		super(icone);
		dragger = new CreatorDragger(this);
		dragger.activerDragListener();
		this.cmd = cmd;
		
		setSize(icone.getIconWidth(), icone.getIconHeight());
		setPreferredSize(getSize());
	}
	
	public ICModule newModuleController() {
		return cmd.newModuleController();
	}
	
}
