package core;

/**
 * Un param�tre discret ne peut prendre que quelques valeurs pr�d�finies.
 * On passe d'une valeur � une autre par balayage de l'ensemble de ces valeurs.<BR/>
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public interface IDiscreteParameter extends IParameter{
	public int getNumberOfValue();
	public ParameterValue next();
	public ParameterValue previous();
}
