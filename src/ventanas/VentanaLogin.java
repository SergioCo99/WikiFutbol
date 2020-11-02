package ventanas;

import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JLabel lblCorreo, lblPass;
	JButton btnAceptar, btnRegistrar;
	JTextField txtCorreo;
	JPasswordField txtPassword;
	JCheckBox checkContrasena;
	boolean bUsuario, bPassword;
	JLabelGraficoAjustado campo;

	public VentanaLogin() {

		this.setTitle("VentanaLogin");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		lblCorreo = new JLabel();
		lblCorreo.setText("Introduce tu correo:");
		lblCorreo.setBounds(25, 100, 300, 20);

		lblPass = new JLabel();
		lblPass.setText("Introduce la contrase\u00f1a:");
		lblPass.setBounds(25, 140, 300, 20);

		txtCorreo = new JTextField();
		txtCorreo.setBounds(175, 100, 200, 20);
		txtCorreo.setText(utils.PropertiesMetodos.getProp1());

		txtPassword = new JPasswordField();
		txtPassword.setBounds(175, 140, 200, 20);
		// txtPassword.setEchoChar('ï¿½');
		txtPassword.setText(utils.PropertiesMetodos.getProp2());

		btnAceptar = new JButton();
		btnAceptar.setText("Aceptar");
		btnAceptar.setBounds(320, 250, 120, 30);

		btnRegistrar = new JButton();
		btnRegistrar.setText("Registrarse");
		btnRegistrar.setBounds(140, 250, 120, 30);

		checkContrasena = new JCheckBox();
		checkContrasena.setText("Ver contrasena");
		checkContrasena.setBounds(175, 170, 130, 20);

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

		// innecesario, por comodidad
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
				VentanaRegistro VR = new VentanaRegistro();
				VR.setVisible(true);
				dispose();
			}
		});

		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
					JOptionPane.showMessageDialog(null, "Correo o contraseï¿½a incorrectos.", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaLogin VL = new VentanaLogin();
		VL.setVisible(true);
	}
}