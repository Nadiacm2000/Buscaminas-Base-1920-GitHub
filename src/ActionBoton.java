import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{
	
	private VentanaPrincipal ventanaPrinc;
	private int i;
	private int j;
	
	public ActionBoton(VentanaPrincipal ventanaPrinc, int i, int j) {
		
		this.ventanaPrinc = ventanaPrinc;
		this.i = i;
		this.j = j;
	
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		if(String.valueOf(((JButton)e.getSource()).getText()).equals("-1")) {
			ventanaPrinc.mostrarFinJuego(true);
		}else {
			ventanaPrinc.mostrarNumMinasAlrededor(i, j);
		}
	}

}
