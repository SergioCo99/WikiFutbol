package ventanas;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clases.Usuario;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

/**
 * Venta para el inicio de sesion
 *
 * @author sergiolopez
 *
 */
public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private JLabel lblCorreo, lblPass;
	private JButton btnAceptar, btnRegistrar;
	private JTextField txtCorreo;
	private JPasswordField txtPassword;
	private JCheckBox checkContrasena;
	private boolean bUsuario, bPassword;
	private JLabelGraficoAjustado campo;

	public VentanaLogin() {

		this.setTitle("VentanaLogin");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.getHSBColor(1.42f, 0.68f, 0.9f));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		// Label introducir correo
		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce tu correo:");
		lblCorreo.setBounds(25, 100, 300, 20);

		// Label introducir contraseña
		lblPass = new JLabel();
		lblPass.setText("Introduce la contraseña:");
		lblPass.setBounds(25, 140, 300, 20);

		// Introducir correo
		txtCorreo = new JTextField();
		txtCorreo.setBounds(175, 100, 200, 20);
		txtCorreo.setText(utils.PropertiesMetodos.getProp1());

		// Introducir contraseña
		txtPassword = new JPasswordField();
		txtPassword.setBounds(175, 140, 200, 20);
		// txtPassword.setEchoChar('•');
		txtPassword.setText(utils.PropertiesMetodos.getProp2());

		// Aceptar para entrar a la aplicacion
		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(320, 250, 120, 30);

		// Ir a la ventana de registro
		btnRegistrar = new JButton();
		btnRegistrar.setText("Registrarse");
		btnRegistrar.setBounds(140, 250, 120, 30);

		// Opcion de ver la contraseña del JPasswordField
		checkContrasena = new JCheckBox();
		checkContrasena.setText("Ver contrasena");
		checkContrasena.setBounds(175, 170, 130, 20);
		checkContrasena.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.9f));

		add(txtCorreo);
		add(txtPassword);
		add(lblCorreo);
		add(lblPass);
		add(btnAceptar);
		add(btnRegistrar);
		add(checkContrasena);

		// logo
		campo = new JLabelGraficoAjustado("resources/wf.png", 231, 140);
		campo.setLocation(350, 75);
		add(campo);

		txtCorreo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (bUsuario == false) {
					txtCorreo.setText("");
					bUsuario = true;
				}
			}
		});

		// ""innecesario"", por comodidad
		txtCorreo.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
		txtCorreo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					/* PUT YOUR STUFF HERE OR CALL A FUNCTION */
					if (bPassword == false) {
						txtPassword.setText("");
						bPassword = true;
					}
					/* If you want to change the focus to the next component */
					txtPassword.grabFocus();
				}
			}
		});
		// =====

		txtPassword.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (bPassword == false) {
					txtPassword.setText("");
					bPassword = true;
				}
			}
		});

		// innecesario, por comodidad
		txtPassword.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
		txtPassword.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					/* PUT YOUR STUFF HERE OR CALL A FUNCTION */
					if (bUsuario == false) {
						txtCorreo.setText("");
						bUsuario = true;
					}
					/* If you want to change the focus to the next component */
					txtCorreo.grabFocus();
				}
			}
		});
		// =====

		checkContrasena.addActionListener(e -> {
			if (checkContrasena.isSelected() == true) {
				txtPassword.setEchoChar((char) 0);
			} else {
				txtPassword.setEchoChar('•');
			}
		});

		btnRegistrar.addActionListener(e -> {
			VentanaRegistro VR = new VentanaRegistro();
			VR.setVisible(true);
			dispose();
		});

		btnAceptar.addActionListener(e -> {
			String passw = new String(txtPassword.getPassword());
			try {
				if (database.DBManager.login(txtCorreo.getText(), passw) == true) {
					utils.PropertiesMetodos.setProp(txtCorreo.getText(), passw);
					VentanaPrincipal VP = new VentanaPrincipal(usuario);
					VP.setVisible(true);
					dispose();
				}
			} catch (DBManagerException e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Alert",
						JOptionPane.WARNING_MESSAGE);
			}

		});

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
			}
		});

	}

}