package gui.controle;

import java.util.Collection;
import java.util.Iterator;

import gui.ConcreteFactory;
import gui.presentation.IPSynthetizer;
import core.ISynthetizer;
import core.modules.IModule;

/**
 * Classe implémentant le contrôle de <tt>core.ISynthetizer</tt>.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class CSynthetizer implements ICSynthetizer {
	private IPSynthetizer presentation;
	private ISynthetizer abstraction;
	
	public CSynthetizer(){
		abstraction = ConcreteFactory.getAFactory().newSynthetizer();
		presentation = ConcreteFactory.getPFactory().newPSynthetizer(this);
	}

	public void playSynthetizer() {
		abstraction.playSynthetizer();
		presentation.setIsPlaying(abstraction.isPlaying());
	}


	public void stopSynthetizer() {
		abstraction.stopSynthetizer();
		presentation.setIsPlaying(abstraction.isPlaying());
	}
	
	public void resetSynthetizer() {
		abstraction.resetSynthetizer();
	}

	public Collection<IModule> getModules() {
		return abstraction.getModules();
	}

	public Iterator<IModule> modulesIterator() {
		return abstraction.modulesIterator();
	}

	public boolean isModulesEmpty() {
	return abstraction.isModulesEmpty();
	}

	public boolean containsModules(IModule module) {
		return abstraction.containsModules(module);
	}

	public boolean containsAllModules(Collection<? extends IModule> modules) {
		return abstraction.containsAllModules(modules);
	}

	public int modulesSize() {
		return abstraction.modulesSize();
	}

	public IModule[] modulesToArray() {
		return abstraction.modulesToArray();
	}

	public <T extends IModule> T[] modulesToArray(T[] modules) {
		return abstraction.modulesToArray(modules);
	}

	public boolean addModules(IModule module) {
		return abstraction.addModules(module);
	}

	public void setModules(Collection<IModule> modules) {
		abstraction.setModules(modules);
	}

	public boolean removeModules(IModule module) {
		return abstraction.removeModules(module);
	}

	public void clearModules() {
		abstraction.clearModules();
	}

	public IPSynthetizer getPresentation() {
		return presentation;
	}
	
	public IPSynthetizer getAbstra() {
		return presentation;
	}

	public void run() {
		abstraction.run();
	}

	public boolean isPlaying() {
		return abstraction.isPlaying();
	}

	public int getFreqEch() {
		return abstraction.getFreqEch();
	}

	public void setFreqEch(int freqEch) {
		abstraction.setFreqEch(freqEch);
	}	
}
