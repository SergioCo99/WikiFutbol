package pruebasYEjemplos;

import javax.swing.JFrame;

import database.DBManagerException;

public class VentanaEquipoPrueba extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public VentanaEquipoPrueba() throws DBManagerException {
		init();
	}

	public void init() {

		this.setTitle("WikiFutbol Principal");
		this.setSize(1200, 700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase

		VentanaEquipoPrueba v = new VentanaEquipoPrueba();
		v.setVisible(true);

	}
}