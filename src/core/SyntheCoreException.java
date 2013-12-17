package core;

/**
 * Classe d'erreurs sp�cifiques � l'application (d�passements
 * de valeurs de signal essentiellement).<P>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
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
