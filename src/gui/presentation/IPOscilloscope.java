package gui.presentation;

/**
* Etend IModule et d�ccrit les services rendus par le module oscilloscope (<tt>core.Oscilloscope</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IPOscilloscope extends IPModule {
	public void setValue(double value);
}
