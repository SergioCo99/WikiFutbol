package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import database.DBManagerException;

public class VentanaConfigurarOtraCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnAceptar;
	JCheckBox cbHacerAdmin, cbQuitarAdmin, cbBorrarCuenta;
	ButtonGroup bg1;
	JLabel lblCorreo;
	JComboBox<String> jcbCorreos;

	public VentanaConfigurarOtraCuenta() {

		this.setTitle("VentanaConfigurarOtraCuenta");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(240, 250, 120, 30);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce correo:");
		lblCorreo.setBounds(100, 150, 250, 40);

		try {
			String[] array = new String[database.DBManager.todosLosCorreos().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.todosLosCorreos().get(i);
			}
			jcbCorreos = new JComboBox<String>(array);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbCorreos);
		jcbCorreos.setBounds(250, 150, 250, 40);

		add(btnAceptar);
		add(cbHacerAdmin);
		add(cbQuitarAdmin);
		add(cbBorrarCuenta);
		add(lblCorreo);
		add(jcbCorreos);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!jcbCorreos.getSelectedItem().toString().equals("a")) { // PARA QUE "a" NO SE PUEDA BORRAR !!!
						if ((cbHacerAdmin.isSelected() == true)
								&& ((cbQuitarAdmin.isSelected() == false) & (cbBorrarCuenta.isSelected() == false))) {
							database.DBManager.cambiarAdmin(jcbCorreos.getSelectedItem().toString(), 1);
							JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Configurar otra cuenta",
									JOptionPane.INFORMATION_MESSAGE);
						} else if ((cbHacerAdmin.isSelected() == false)
								&& ((cbQuitarAdmin.isSelected() == true) & (cbBorrarCuenta.isSelected() == false))) {
							database.DBManager.cambiarAdmin(jcbCorreos.getSelectedItem().toString(), 0);
							JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Configurar otra cuenta",
									JOptionPane.INFORMATION_MESSAGE);
						} else if ((cbHacerAdmin.isSelected() == false)
								&& ((cbQuitarAdmin.isSelected() == false) & (cbBorrarCuenta.isSelected() == true))) {
							database.DBManager.eliminarUsuario(jcbCorreos.getSelectedItem().toString());
							JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Configurar otra cuenta",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaConfigurarOtraCuenta VCOT = new VentanaConfigurarOtraCuenta();
		VCOT.setVisible(true);
	}
}