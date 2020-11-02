package ventanas;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import clases.Usuario;
import database.DBManagerException;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
	JLabel lblUser, lblPass, lblPassRep, lblDate, lblCorreo;
	JButton btnRegistrar;
	JTextField txtUsuario, txtPassword, txtPasswordRep, txtCorreo;
	JCalendar calendar;

	public VentanaRegistro() {

		this.setTitle("VentanaRegistro");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		lblUser = new JLabel();
		lblUser.setText("Introduce el nombre de usuario:");
		lblUser.setBounds(100, 50, 300, 20);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce tu correo:");
		lblCorreo.setBounds(100, 70, 300, 20);

		lblPass = new JLabel();
		lblPass.setText("Introduce la contraseņa:");
		lblPass.setBounds(100, 90, 300, 20);

		lblPassRep = new JLabel("Repetir contrase\u00F1a:");
		lblPassRep.setBounds(100, 110, 300, 20);

		lblDate = new JLabel();
		lblDate.setText("Introduce tu fecha de nacimiento:");
		lblDate.setBounds(100, 130, 300, 20);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(300, 50, 200, 20);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(300, 70, 200, 20);

		txtPassword = new JTextField();
		txtPassword.setBounds(300, 90, 200, 20);

		txtPasswordRep = new JTextField();
		txtPasswordRep.setBounds(300, 110, 200, 20);

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

		add(txtUsuario);
		add(txtPassword);
		add(txtPasswordRep);
		add(txtCorreo);
		add(lblUser);
		add(lblPass);
		add(lblPassRep);
		add(lblDate);
		add(lblCorreo);
		add(btnRegistrar);
		add(calendar);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (txtUsuario.getText().equals("") || txtPassword.getText().equals("")
							|| txtPasswordRep.getText().equals("") || txtCorreo.getText().equals("")
							|| txtUsuario.getText().equals(null) || txtPassword.getText().equals(null)
							|| txtPasswordRep.getText().equals(null) || txtCorreo.getText().equals(null)
							|| !txtUsuario.getText()
									.matches("^(?=.{1,45}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
							|| !txtCorreo.getText().matches(
									"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
							|| (database.DBManager.existeCorreo(txtCorreo.getText()) == true)) {
						JOptionPane.showMessageDialog(null, "Rellena todos los campos adecuadamente.");
					} else {

						if (txtPassword.getText().equals(txtPasswordRep.getText())) {
							try {
								database.DBManager.registrarUsuario(txtUsuario.getText(), txtCorreo.getText(),
										txtPassword.getText(), formatter.format(calendar.getDate()));
							} catch (DBManagerException e1) {
								mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
								e1.printStackTrace();
							}
							utils.PropertiesMetodos.setProp(txtCorreo.getText(), txtPassword.getText());

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

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaRegistro VR = new VentanaRegistro();
		VR.setVisible(true);
	}
}
