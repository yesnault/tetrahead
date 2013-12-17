package gui.presentation.parameterAction;

/**
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class GetParameterValueException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public GetParameterValueException(String message) {
		super("La valeur saisie est incorrecte : "+message);
		title = "Erreur de saisie";
	}

	public String getTitle() {
		return title;
	}
}
