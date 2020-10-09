package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import database.DBManagerException;

public class VentanaCambiarDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JTextArea jta;

	public VentanaCambiarDatos() {

		this.setTitle("VentanaAdmin1");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		jta = new JTextArea();
		jta.setBounds(10, 10, 300, 400);

		add(btn);
		add(jta);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn");

				try {
					database.DBManager.cambiarDatos(jta.getText());
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	public static void main(String[] args) {
		VentanaCambiarDatos VCD = new VentanaCambiarDatos();
		VCD.setVisible(true);
	}
}