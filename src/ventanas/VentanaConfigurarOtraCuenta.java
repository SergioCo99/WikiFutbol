package ventanas;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import database.DBManagerException;

/**
 * Ventana que permite al administrador modificar los datos de los usuarios asi
 * como como borrarlos.
 *
 * @author sergiolopez
 *
 */
public class VentanaConfigurarOtraCuenta extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnEjecutar;
	JRadioButton cbHacerAdmin, cbQuitarAdmin, cbBorrarCuenta;
	ButtonGroup bg1;
	JLabel lblCorreo;
	JComboBox<String> jcbCorreos;
	JLabel lblOpciones;

	public VentanaConfigurarOtraCuenta() {

		this.setTitle("VentanaConfigurarOtraCuenta");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		bg1 = new ButtonGroup();
		cbHacerAdmin = new JRadioButton("Hacer admin");
		cbHacerAdmin.setBounds(116, 29, 100, 20);
		cbQuitarAdmin = new JRadioButton("Quitar admin");
		cbQuitarAdmin.setBounds(116, 51, 100, 20);
		cbBorrarCuenta = new JRadioButton("Borrar cuenta DEFINITIVAMENTE");
		cbBorrarCuenta.setBounds(116, 73, 250, 20);
		bg1.add(cbHacerAdmin);
		bg1.add(cbQuitarAdmin);
		bg1.add(cbBorrarCuenta);

		btnEjecutar = new JButton();
		btnEjecutar.setText("Ejecutar");
		btnEjecutar.setBounds(240, 250, 120, 30);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce correo:");
		lblCorreo.setBounds(10, 150, 250, 40);

		try {
			String[] array = new String[database.DBManager.todosLosCorreos().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.todosLosCorreos().get(i);
			}
			jcbCorreos = new JComboBox<String>(array);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbCorreos);
		jcbCorreos.setBounds(201, 150, 250, 40);

		getContentPane().add(btnEjecutar);
		getContentPane().add(cbHacerAdmin);
		getContentPane().add(cbQuitarAdmin);
		getContentPane().add(cbBorrarCuenta);
		getContentPane().add(lblCorreo);
		getContentPane().add(jcbCorreos);

		lblOpciones = new JLabel("Opciones: ");
		lblOpciones.setBounds(106, 10, 120, 13);
		getContentPane().add(lblOpciones);

		btnEjecutar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
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
					} else {
						JOptionPane.showMessageDialog(null, "Selecciona una opcion.", "Configurar otra cuenta",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error inesperado.", "Configurar otra cuenta",
							JOptionPane.ERROR_MESSAGE);
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