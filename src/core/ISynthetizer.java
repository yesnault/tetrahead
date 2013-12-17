package core;

import java.util.Collection;
import java.util.Iterator;

import core.modules.IModule;

/**
 * Interface du synth�tiseur du point de vue M�tier. <P>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface ISynthetizer extends Runnable{
	/**
	 * Lance le s�quenceur du SyntheLab.<P>
	 * 
	 * Pour chaque module sur l'espace de travail, ex�cute sa m�thode 
	 * <I>compute()</I> une fois par cycle (un �chantillon unitaire).
	 *
	 */
	public void playSynthetizer();

	/**
	 * Arr�te le s�quenceur.
	 *
	 */
	public void stopSynthetizer();
	
	/**
	 * Remet � leur valeur par d�faut tous les <I>Parameter</I> de tous
	 * les modules pr�sents sur l'espace de travail.
	 *
	 */
	public void resetSynthetizer();

	/**
	 * Ensemble des modules pr�sents sur l'espace de travail.
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
	 * La f�quence d'�chantillonnage est commune � tous les modules et
	 * g�r�e au sein du Synth�tiseur.
	 */
	public int getFreqEch();

	/**
	 * Modifie la fr�quence courante et la r�percute � chacun des modules 
	 * pr�sents sur l'espace de travail.
	 */
	public void setFreqEch(int freqEch);
}
