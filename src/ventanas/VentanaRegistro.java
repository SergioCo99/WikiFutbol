package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import clases.Usuario;
import database.DBManagerException;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
	JLabel lblUser, lblPass, lblDate, lblCorreo;
	JButton btnRegistrar;
	JTextField txtUsuario, txtPassword, txtCorreo;
	JCalendar calendar;

	public VentanaRegistro() {

		this.setTitle("VentanaRegistro");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblUser = new JLabel();
		lblUser.setText("Introduce el nombre de usuario:");
		lblUser.setBounds(100, 50, 300, 20);

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce tu correo:");
		lblCorreo.setBounds(100, 70, 300, 20);

		lblPass = new JLabel();
		lblPass.setText("Introduce la contraseña:");
		lblPass.setBounds(100, 90, 300, 20);

		lblDate = new JLabel();
		lblDate.setText("Introduce tu fecha de nacimiento:");
		lblDate.setBounds(100, 130, 300, 20);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(300, 50, 200, 20);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(300, 70, 200, 20);

		txtPassword = new JTextField();
		txtPassword.setBounds(300, 90, 200, 20);

		btnRegistrar = new JButton();
		btnRegistrar.setText("Registrarse");
		btnRegistrar.setBounds(240, 250, 120, 30);

		calendar = new JCalendar();
		calendar.setBounds(300, 130, 200, 100);
		try {
			calendar.setSelectableDateRange(formatter.parse("1900-01-01") /* YYYY-MM-dd */, new Date());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		add(txtUsuario);
		add(txtPassword);
		add(txtCorreo);
		add(lblUser);
		add(lblPass);
		add(lblDate);
		add(lblCorreo);
		add(btnRegistrar);
		add(calendar);

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database.DBManager.registrarUsuario(txtUsuario.getText(), txtCorreo.getText(),
							txtPassword.getText(), formatter.format(calendar.getDate()));
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
				utils.PropertiesMetodos.setProp(txtCorreo.getText(), txtPassword.getText());

				dispose();
				VentanaPrincipal VP = null;
				try {
					VP = new VentanaPrincipal(usuario);
				} catch (DBManagerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				VP.setVisible(true);
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaRegistro VR = new VentanaRegistro();
		VR.setVisible(true);
	}
}