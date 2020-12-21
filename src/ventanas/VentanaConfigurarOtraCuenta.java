package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
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
	private JButton btnEjecutar;
	private JRadioButton cbHacerAdmin, cbQuitarAdmin, cbBorrarCuenta;
	private ButtonGroup bg1;
	private JLabel lblCorreo, lblTitulo;
	private JComboBox<String> jcbCorreos;
	private JLabel lblOpciones;

	public VentanaConfigurarOtraCuenta(List<String> listaCorreos) {

		this.setTitle("VentanaConfigurarOtraCuenta");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		lblTitulo = new JLabel();
		lblTitulo.setText("Configurar cuentas");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTitulo.setBounds(210, 40, 250, 50);

		bg1 = new ButtonGroup();
		cbHacerAdmin = new JRadioButton("Hacer admin");
		cbHacerAdmin.setBounds(116, 180, 150, 20);
		cbQuitarAdmin = new JRadioButton("Quitar admin");
		cbQuitarAdmin.setBounds(116, 202, 150, 20);
		cbBorrarCuenta = new JRadioButton("Borrar cuenta DEFINITIVAMENTE");
		cbBorrarCuenta.setBounds(116, 224, 250, 20);
		bg1.add(cbHacerAdmin);
		bg1.add(cbQuitarAdmin);
		bg1.add(cbBorrarCuenta);

		btnEjecutar = new JButton();
		btnEjecutar.setText("Ejecutar");
		btnEjecutar.setBounds(240, 250, 120, 30);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce correo:");
		lblCorreo.setBounds(70, 100, 250, 40);

		String[] array = new String[listaCorreos.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = listaCorreos.get(i);
		}
		jcbCorreos = new JComboBox<String>(array);
		utils.JComboBoxAutoCompletion.enable(jcbCorreos);
		jcbCorreos.setBounds(201, 100, 250, 40);

		getContentPane().add(btnEjecutar);
		getContentPane().add(cbHacerAdmin);
		getContentPane().add(cbQuitarAdmin);
		getContentPane().add(cbBorrarCuenta);
		getContentPane().add(lblCorreo);
		getContentPane().add(lblTitulo);
		getContentPane().add(jcbCorreos);

		lblOpciones = new JLabel("Opciones: ");
		lblOpciones.setBounds(70, 160, 120, 13);
		getContentPane().add(lblOpciones);

		btnEjecutar.addActionListener(e -> {
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
		});

	}

}