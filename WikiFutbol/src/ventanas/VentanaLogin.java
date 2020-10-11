package ventanas;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DBManagerException;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lblUser, lblPass;
	JButton btnAceptar, btnRegistrar;
	JTextField txtUsuario;
	JPasswordField txtPassword;
	JCheckBox checkContrasena;
	boolean b1, b2;

	public VentanaLogin() {

		this.setTitle("Login");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblUser = new JLabel();
		lblUser.setText("Introduce el nombre de usuario:");
		lblUser.setBounds(100, 100, 300, 20);

		lblPass = new JLabel();
		lblPass.setText("Introduce la contraseña:");
		lblPass.setBounds(100, 140, 300, 20);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(300, 100, 200, 20);
		txtUsuario.setText(mainPackage.PropertiesMetodos.getProp1());

		txtPassword = new JPasswordField();
		txtPassword.setBounds(300, 140, 200, 20);
		// txtPassword.setEchoChar('•');
		txtPassword.setText(mainPackage.PropertiesMetodos.getProp2());

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(320, 250, 120, 30);

		btnRegistrar = new JButton();
		btnRegistrar.setText("Registrarse");
		btnRegistrar.setBounds(140, 250, 120, 30);

		checkContrasena = new JCheckBox();
		checkContrasena.setText("Ver contrasena");
		checkContrasena.setBounds(300, 170, 200, 20);

		add(txtUsuario);
		add(txtPassword);
		add(lblUser);
		add(lblPass);
		add(btnAceptar);
		add(btnRegistrar);
		add(checkContrasena);

		txtUsuario.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (b1 == false) {
					txtUsuario.setText("");
					b1 = true;
				}
			}
		});

		// por comodidad
		txtUsuario.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
		txtUsuario.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					/* PUT YOUR STUFF HERE OR CALL A FUNCTION */
					if (b2 == false) {
						txtPassword.setText("");
						b2 = true;
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
				if (b2 == false) {
					txtPassword.setText("");
					b2 = true;
				}
			}
		});

		// por comodidad
		txtPassword.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
		txtPassword.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					/* PUT YOUR STUFF HERE OR CALL A FUNCTION */
					if (b1 == false) {
						txtUsuario.setText("");
						b1 = true;
					}
					/* If you want to change the focus to the next component */
					txtUsuario.grabFocus();
				}
			}
		});
		// =====

		checkContrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkContrasena.isSelected() == true) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('•');
				}
			}
		});

		btnRegistrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnRegistrar");
				VentanaRegistro VR = new VentanaRegistro();
				VR.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnAceptar");
				String passw = new String(txtPassword.getPassword());
				try {
					if (database.DBManager.login(txtUsuario.getText(), passw) == true) {
						mainPackage.PropertiesMetodos.setProp(txtUsuario.getText(), passw);
						VentanaPrincipal VP = new VentanaPrincipal();
						VP.setVisible(true);
						dispose();
					}
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}

			}
		});

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				mainPackage.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaLogin VL = new VentanaLogin();
		VL.setVisible(true);
	}
}