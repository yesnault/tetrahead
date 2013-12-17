package core;

import java.util.Collection;
import java.util.Iterator;

import core.modules.IModule;

/**
 * Interface du synthétiseur du point de vue Métier. <P>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ISynthetizer extends Runnable{
	/**
	 * Lance le séquenceur du SyntheLab.<P>
	 * 
	 * Pour chaque module sur l'espace de travail, exécute sa méthode 
	 * <I>compute()</I> une fois par cycle (un échantillon unitaire).
	 *
	 */
	public void playSynthetizer();

	/**
	 * Arrête le séquenceur.
	 *
	 */
	public void stopSynthetizer();
	
	/**
	 * Remet à leur valeur par défaut tous les <I>Parameter</I> de tous
	 * les modules présents sur l'espace de travail.
	 *
	 */
	public void resetSynthetizer();

	/**
	 * Ensemble des modules présents sur l'espace de travail.
	 */
	public Collection<IModule> getModules();

	public Iterator<IModule> modulesIterator();

	public boolean isModulesEmpty();

	public boolean containsModules(IModule module);

	public boolean containsAllModules(Collection<? extends IModule> modules);

	public int modulesSize();

	public IModule[] modulesToArray();

	public <T extends IModule> T[] modulesToArray(T[] modules);

	public boolean addModules(IModule module);

	public void setModules(Collection<IModule> modules);

	public boolean removeModules(IModule module);

	public void clearModules();

	public boolean isPlaying();
	
	/**
	 * La féquence d'échantillonnage est commune à tous les modules et
	 * gérée au sein du Synthétiseur.
	 */
	public int getFreqEch();

	/**
	 * Modifie la fréquence courante et la répercute à chacun des modules 
	 * présents sur l'espace de travail.
	 */
	public void setFreqEch(int freqEch);
}
