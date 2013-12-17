package core;

import core.modules.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe permettant d'appeler les m�thodes setFreqEch, compute(), reset()
 * sur tous les modules pr�sents. Lance, Stop, Reset le synth�tiseur, Ajoute, 
 * Supprime des modules.
 * Voir ISynthetizer
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class Synthetizer extends Thread implements ISynthetizer {

	/**
	 * @uml.property name="isPlaying"
	 */
	boolean isPlaying;

	/**
	 * @uml.property name="modules"
	 * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite"
	 *                     inverse="synthetizer:core.modules.Module"
	 */
	private Collection<IModule> modules;
	
	private int freqEch = 44100;

	public Synthetizer() {
		this.modules = new ArrayList<IModule>();
		isPlaying = false;
	}
	
	/**
	 * Mise en place de la fr�quence d'�chantillonnage sur tous les modules pr�sents
	 * c'est � ex�cuter avant de cr�er un thread de lecture
	 */
	public void playSynthetizer() {
		isPlaying = true;
		for (IModule m : modules) {
			try {
				m.setFreqEch(getFreqEch());
			} catch (SyntheCoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Arr�t du player : fait appel � la m�thode Stop() de tous les modules
	 * 
	 * @throws IOException
	 */
	public void stopSynthetizer() {
		synchronized (this) {
			isPlaying = false;

			for (IModule module : modules) {
				try {
					module.stop();
				} catch (Exception e) {
					module.showException(e);
					e.printStackTrace();
					// 	FIXME Indiquer � l'interface que le synth� est stopp�
				}
			}
		}
	}

	/**
	 * Reset de tous les modules, si l'on est pas en cours de playing
	 */
	public void resetSynthetizer() {
		synchronized (this) {
			if (isPlaying) {
				System.err.println(" Synthetizer :: resetSynthetizer. Pas de reset possible en cours de playing.");
				return;
			}

			for (IModule m : modules) {
				m.reset();
			}
		}
	}
	
	/**
	 * M�thode Run du Thread lorsque le synth�tiseur est en cours de jeu
	 * C'est dans cette m�thode que l'on fait appel � la m�thode compute()
	 * sur chaque module pr�sent.
	 */
	public void run() {
		int nb = 0;
		/* Tant que l'on est dans un �tat playing */
		while (isPlaying) {
			/* Pour tous les modules */
			for (IModule module : modules) {
				try {
					module.compute();
				} catch (Exception e) {
					module.showException(e);
					e.printStackTrace();
					stopSynthetizer();
					// 	FIXME Indiquer � l'interface que le synth� est stopp�
				}
				/*
				 * Section de code qui �vite d'occuper le CPU � 100%
				 * Cela n'a pas d'incidence sur le son g�n�r� (la 
				 * production va tellement vite...
				 */
				nb++;
				if (nb % 200000 == 0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					nb = 0;

				}
			}
		}


	}

	/**
	 * Getter of the property <tt>modules</tt>
	 * 
	 * @return Returns the modules.
	 * @uml.property name="modules"
	 */
	public Collection<IModule> getModules() {
		return modules;
	}

	/**
	 * Returns an iterator over the elements in this collection.
	 * 
	 * @return an <tt>Iterator</tt> over the elements in this collection
	 * @see java.util.Collection#iterator()
	 * @uml.property name="modules"
	 */
	public Iterator<IModule> modulesIterator() {
		return modules.iterator();
	}

	/**
	 * Returns <tt>true</tt> if this collection contains no elements.
	 * 
	 * @return <tt>true</tt> if this collection contains no elements
	 * @see java.util.Collection#isEmpty()
	 * @uml.property name="modules"
	 */
	public boolean isModulesEmpty() {
		return modules.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this collection contains the specified
	 * element.
	 * 
	 * @param element
	 *            whose presence in this collection is to be tested.
	 * @see java.util.Collection#contains(Object)
	 * @uml.property name="modules"
	 */
	public boolean containsModules(IModule module) {
		return modules.contains(module);
	}

	/**
	 * Returns <tt>true</tt> if this collection contains all of the elements
	 * in the specified collection.
	 * 
	 * @param elements
	 *            collection to be checked for containment in this collection.
	 * @see java.util.Collection#containsAll(Collection)
	 * @uml.property name="modules"
	 */
	public boolean containsAllModules(Collection<? extends IModule> modules) {
		return this.modules.containsAll(modules);
	}

	/**
	 * Returns the number of elements in this collection.
	 * 
	 * @return the number of elements in this collection
	 * @see java.util.Collection#size()
	 * @uml.property name="modules"
	 */
	public int modulesSize() {
		return modules.size();
	}

	/**
	 * Returns all elements of this collection in an array.
	 * 
	 * @return an array containing all of the elements in this collection
	 * @see java.util.Collection#toArray()
	 * @uml.property name="modules"
	 */
	public IModule[] modulesToArray() {
		return modules.toArray(new IModule[modules.size()]);
	}

	/**
	 * Returns an array containing all of the elements in this collection; the
	 * runtime type of the returned array is that of the specified array.
	 * 
	 * @param a
	 *            the array into which the elements of this collection are to be
	 *            stored.
	 * @return an array containing all of the elements in this collection
	 * @see java.util.Collection#toArray(Object[])
	 * @uml.property name="modules"
	 */
	public <T extends IModule> T[] modulesToArray(T[] modules) {
		return (T[]) this.modules.toArray(modules);
	}

	/**
	 * Ajoute un module dans la liste des modules � jouer
	 * 
	 * @param element
	 *            whose presence in this collection is to be ensured.
	 * @see java.util.Collection#add(Object)
	 * @uml.property name="modules"
	 */
	public boolean addModules(IModule module) {
		return modules.add(module);
	}

	/**
	 * Setter of the property <tt>modules</tt>
	 * 
	 * @param modules
	 *            the modules to set.
	 * @uml.property name="modules"
	 */
	public void setModules(Collection<IModule> modules) {
		this.modules = modules;
	}

	/**
	 * Retire un module de la liste des modules pr�sents dans le synth�tiseur
	 * 
	 * @param element
	 *            to be removed from this collection, if present.
	 * @see java.util.Collection#add(Object)
	 * @uml.property name="modules"
	 */
	public boolean removeModules(IModule module) {
		return modules.remove(module);
	}

	/**
	 * Removes all of the elements from this collection (optional operation).
	 * 
	 * @see java.util.Collection#clear()
	 * @uml.property name="modules"
	 */
	public void clearModules() {
		modules.clear();
	}

	public boolean isPlaying() {
		// System.out.println("Synthetizer::isPlaying()::isPlaying="+isPlaying);
		return isPlaying;
	}
	
	/**
	 * Retourne la fr�quence d'�chantillonnage 
	 */
	public int getFreqEch() {
		return freqEch;
	}
	
	/**
	 * Mise � jour de la fr�quence d'�chantillonnage (44100, 16000...)
	 */
	public void setFreqEch(int freqEch) {
		this.freqEch = freqEch;
	}

}
