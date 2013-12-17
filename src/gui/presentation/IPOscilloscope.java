package gui.presentation;

/**
* Etend IModule et déccrit les services rendus par le module oscilloscope (<tt>core.Oscilloscope</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPOscilloscope extends IPModule {
	public void setValue(double value);
}
