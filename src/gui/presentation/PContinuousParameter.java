package gui.presentation;

import gui.controle.ICParameter;
import gui.presentation.parameterAction.GetParameterValueException;
import gui.presentation.parameterAction.Invoker;
import gui.presentation.parameterAction.MyKeyListener;
import gui.presentation.parameterAction.RotateListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *         Presentation d'un paramètre continu (<tt>core.IContinuousParameter</tt>).
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
 */
public class PContinuousParameter extends PParameter implements
		IPContinuousParameter, Invoker {

	/* Zone d'affichage et de saisie de la valeur du paramètre.*/
	private JTextField txtFld;
	/*Unité des valeurs affichées (Hz, ms, etc...)*/
	private String unit;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param controle : contrôle de la présentation.
	 * @param initValue : valeur initiale.
	 */
	public PContinuousParameter(ICParameter controle, String initValue) {
		super(controle, "img/Button_Continuous_50x50.png", "img/Cursor_30x30.png");
		txtFld = new JTextField();
		txtFld.setSize(view.getWidth(), view.getHeight());
		txtFld.setPreferredSize(txtFld.getSize());
		view.addJTextField(txtFld);
		unit = " "+controle.getUnit();
		refreshView(initValue);
	}

	/**
	 * Initialise les valeur utilisees par rotatePanel. Ajoute un RotateListener
	 * sur le bouton.
	 * 
	 * @param min :
	 *            position minimum.
	 * @param max :
	 *            position maximum.
	 * @param sensitive :
	 *            sensibilite de la souris.
	 */
	public void initRotatePanel(double min, double max) {
		this.rotatePanel.init(min, max, 20, 340);
		RotateListener rotateListener = new RotateListener(pCmd);
		this.rotatePanel.addMouseMotionListener(rotateListener);
		this.rotatePanel.addMouseListener(rotateListener);
		txtFld.addKeyListener(new MyKeyListener(this, pCmd));
	}

	public double calculRate(int x, int y) {
		return rotatePanel.calculRate(x, y);
	}

	public void rotateContinuous(double rate) {
		rotatePanel.rotateContinuous(rate);
	}

	@Override
	protected void refreshView(String str) {
		txtFld.setText(formatDoubleString(str)+unit);
		view.repaint();
	}

	public void setViewValue(String value) {
		refreshView(value);
	}

	public String getCurrentText() {
		return txtFld.getText();
	}

	public void showException(Exception e) {
		if (e.getClass() == GetParameterValueException.class)
			JOptionPane.showMessageDialog(this,
					e.getMessage(),
					((GetParameterValueException) e).getTitle(), JOptionPane.ERROR_MESSAGE);
	}
}
