package ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import database.DBManagerException;

public class VentanaCambiarDatos extends JFrame {

	// ojo que esta ventana no hace nada todavia, habia pensado en meter una JTable
	// o usar el textArea como si fuese una consola para que los admins lo usaran
	// para hacer cosnultas SQL a la BD

	private static final long serialVersionUID = 1L;
	JButton btnAceptar;
	JTextArea textArea1;

	JComboBox<String> tablas;

	public VentanaCambiarDatos() {

		this.setTitle("VentanaCambiarDatos");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(240, 300, 120, 30);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 175, 560, 100);

		try {
			String[] array = new String[database.DBManager.verTablas().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.verTablas().get(i);
			}
			tablas = new JComboBox<String>(array);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(tablas);
		tablas.setBounds(400, 300, 150, 30);

		add(btnAceptar);
		add(textArea1);
		add(tablas);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database.DBManager.cambiarDatos(textArea1.getText());
				} catch (DBManagerException e1) {
					// Can not issue empty query.

					// You have an error in your SQL syntax; check the manual that corresponds to
					// your MySQL server version for the right syntax to use

					// Can not issue SELECT via Update
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaCambiarDatos VCD = new VentanaCambiarDatos();
		VCD.setVisible(true);
	}
}