package gui;

/**
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
import gui.presentation.IPFactory;
import core.*;

public class ConcreteFactory {

	protected static IFactory aFactory;

	protected static IPFactory pFactory;

	protected static IFactory cFactory;

	public static void setAFactory(IFactory f) {
		aFactory = f;
	}

	public static IFactory getAFactory() {
		return (aFactory);
	}

	public static void setCFactory(IFactory f) {
		cFactory = f;
	}

	public static IFactory getCFactory() {
		return (cFactory);
	}

	public static void setPFactory(IPFactory f) {
		pFactory = f;
	}

	public static IPFactory getPFactory() {
		return (pFactory);
	}

	public static void setFactory(IFactory f) {
		aFactory = f;
	}

	public static IFactory getFactory() {
		IFactory factory = aFactory;
		if (cFactory != null) {
			factory = cFactory;
		} else if (factory == null) {
			try {
				throw new SyntheCoreException("Factory non initialisée");
			} catch (SyntheCoreException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return (factory);
	}

}
