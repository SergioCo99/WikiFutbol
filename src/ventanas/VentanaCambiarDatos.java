package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class VentanaCambiarDatos extends JFrame {

	// ojo que esta ventana no hace nada todavia, habia pensado en meter una JTable
	// o usar el textArea como si fuese una consola para que los admins lo usaran
	// para hacer cosnultas SQL a la BD

	private static final long serialVersionUID = 1L;
	JButton btnAceptar;
	JTextArea textArea1;

	public VentanaCambiarDatos() {

		this.setTitle("VentanaCambiarDatos");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(240, 250, 120, 30);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 10, 560, 200);

		add(btnAceptar);
		add(textArea1);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaCambiarDatos VCD = new VentanaCambiarDatos();
		VCD.setVisible(true);
	}
}