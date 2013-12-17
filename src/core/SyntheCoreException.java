package core;

/**
 * Classe d'erreurs spécifiques à l'application (dépassements
 * de valeurs de signal essentiellement).<P>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
@SuppressWarnings("serial")
public class SyntheCoreException extends Exception {
	public SyntheCoreException(String message) {
		super("TetraHead :\n" +
				"Anormal value in Core "+ message) ;
	}
}
