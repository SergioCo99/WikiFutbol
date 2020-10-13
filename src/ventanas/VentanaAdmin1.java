package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.DBManagerException;

public class VentanaAdmin1 extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnAceptar;
	JTextField txtCorreo;
	JCheckBox cbHacerAdmin, cbQuitarAdmin, cbBorrarCuenta;
	ButtonGroup bg1;
	JLabel lblCorreo;

	public VentanaAdmin1() {

		this.setTitle("VentanaAdmin1");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		bg1 = new ButtonGroup();
		cbHacerAdmin = new JCheckBox("Hacer admin");
		cbHacerAdmin.setBounds(10, 30, 100, 20);
		cbQuitarAdmin = new JCheckBox("Quitar admin");
		cbQuitarAdmin.setBounds(170, 30, 100, 20);
		cbBorrarCuenta = new JCheckBox("Borrar cuenta DEFINITIVAMENTE");
		cbBorrarCuenta.setBounds(330, 30, 250, 20);
		bg1.add(cbHacerAdmin);
		bg1.add(cbQuitarAdmin);
		bg1.add(cbBorrarCuenta);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(250, 150, 250, 40);

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(240, 250, 120, 30);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce correo:");
		lblCorreo.setBounds(100, 150, 250, 40);

		add(txtCorreo);
		add(btnAceptar);
		add(cbHacerAdmin);
		add(cbQuitarAdmin);
		add(cbBorrarCuenta);
		add(lblCorreo);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = txtCorreo.getText();
				if (!usuario.equals("a")) {
					try {
						if (cbHacerAdmin.isSelected() == true
								&& cbQuitarAdmin.isSelected() == false & cbBorrarCuenta.isSelected() == false) {
							database.DBManager.cambiarAdmin(usuario, 1);
						} else if (cbHacerAdmin.isSelected() == false
								&& cbQuitarAdmin.isSelected() == true & cbBorrarCuenta.isSelected() == false) {
							database.DBManager.cambiarAdmin(usuario, 0);
						} else if (cbHacerAdmin.isSelected() == false
								&& cbQuitarAdmin.isSelected() == false & cbBorrarCuenta.isSelected() == true) {
							database.DBManager.eliminarUsuario(usuario);
						}
					} catch (DBManagerException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaAdmin1 VA1 = new VentanaAdmin1();
		VA1.setVisible(true);
	}
}