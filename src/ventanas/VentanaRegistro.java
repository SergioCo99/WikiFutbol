package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import clases.Usuario;
import database.DBManagerException;

/**
 * Ventana que da la posibilidad de registro
 *
 * @author sergiolopez
 *
 */
public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
	private JLabel lblUser, lblPass, lblPassRep, lblDate, lblCorreo;
	private JButton btnRegistrar;
	private JTextField txtUsuario, txtCorreo;
	private JPasswordField txtPassword, txtPasswordRep;
	private JCalendar calendar;
	private JCheckBox checkContrasena;

	public VentanaRegistro() {

		this.setTitle("VentanaRegistro");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.getHSBColor(1.42f, 0.68f, 0.9f));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		// JLabel superior titulo
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Registrate");
		lblTitulo.setFont(new Font("Sans Serif Bold", Font.BOLD, 17));
		lblTitulo.setBounds(250, 25, 100, 20);
		getContentPane().add(lblTitulo);

		// label añadir usuario
		lblUser = new JLabel();
		lblUser.setText("Introduce el nombre de usuario:");
		lblUser.setBounds(100, 50, 300, 20);

		// label añadir correo usuario
		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce tu correo:");
		lblCorreo.setBounds(100, 70, 300, 20);

		// label añadir password
		lblPass = new JLabel();
		lblPass.setText("Introduce la contrase\u00f1a:");
		lblPass.setBounds(100, 90, 300, 20);

		// label repetir password
		lblPassRep = new JLabel("Repetir contrase\u00f1a:");
		lblPassRep.setBounds(100, 110, 300, 20);

		// label añadir fech.nacimiento
		lblDate = new JLabel();
		lblDate.setText("Introduce tu fecha de nacimiento:");
		lblDate.setBounds(100, 130, 300, 20);

		// añadir usuario
		txtUsuario = new JTextField();
		txtUsuario.setBounds(300, 50, 200, 20);

		// añadir correo usuario
		txtCorreo = new JTextField();
		txtCorreo.setBounds(300, 70, 200, 20);

		// añadir password
		txtPassword = new JPasswordField();
		txtPassword.setBounds(300, 90, 200, 20);

		// repetir password
		txtPasswordRep = new JPasswordField();
		txtPasswordRep.setBounds(300, 110, 200, 20);

		// registro
		btnRegistrar = new JButton();
		btnRegistrar.setText("Registrarse");
		btnRegistrar.setBounds(240, 250, 120, 30);

		calendar = new JCalendar();
		calendar.setBounds(300, 130, 200, 100);
		try {
			calendar.setSelectableDateRange(formatter.parse("1900-01-01") /* YYYY-MM-dd */, new Date());
		} catch (ParseException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		// ver contraseña introducida anteriormente
		checkContrasena = new JCheckBox();
		checkContrasena.setText("Ver contrasena");
		checkContrasena.setBounds(100, 156, 130, 20);
		checkContrasena.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.9f));

		getContentPane().add(txtUsuario);
		getContentPane().add(txtPassword);
		getContentPane().add(txtPasswordRep);
		getContentPane().add(txtCorreo);
		getContentPane().add(lblUser);
		getContentPane().add(lblPass);
		getContentPane().add(lblPassRep);
		getContentPane().add(lblDate);
		getContentPane().add(lblCorreo);
		getContentPane().add(btnRegistrar);
		getContentPane().add(calendar);
		getContentPane().add(checkContrasena);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtUsuario.getText().equals("") || txtPassword.getPassword().toString().equals("")
							|| txtPasswordRep.getPassword().toString().equals("") || txtCorreo.getText().equals("")
							|| txtUsuario.getText().equals(null) || txtPassword.getPassword().equals(null)
							|| txtPasswordRep.getPassword().equals(null) || txtCorreo.getText().equals(null)
							|| !txtUsuario.getText()
									.matches("^(?=.{1,45}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
							|| !txtCorreo.getText().matches(
									"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
							|| (database.DBManager.existeCorreo2(txtCorreo.getText()) == true)) {
						JOptionPane.showMessageDialog(null, "Rellena todos los campos adecuadamente.");
					} else {
						if (Arrays.equals(txtPassword.getPassword(), txtPasswordRep.getPassword())) {
							try {
								database.DBManager.registrarUsuario(txtUsuario.getText(), txtCorreo.getText(),
										txtPassword.getPassword().toString(), formatter.format(calendar.getDate()));
							} catch (DBManagerException e1) {
								mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
								e1.printStackTrace();
							}
							utils.PropertiesMetodos.setProp(txtCorreo.getText(),
									String.valueOf(txtPassword.getPassword()));

							dispose();
							VentanaPrincipal VP = null;
							try {
								VP = new VentanaPrincipal(usuario);
							} catch (DBManagerException e1) {
								mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
								e1.printStackTrace();
							}
							VP.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden");
						}
					}
				} catch (HeadlessException | DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}
			}
		});

		checkContrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkContrasena.isSelected() == true) {
					txtPassword.setEchoChar((char) 0);
					txtPasswordRep.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('•');
					txtPasswordRep.setEchoChar('•');
				}
			}
		});

	}

}
