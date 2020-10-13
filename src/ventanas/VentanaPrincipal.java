package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import database.DBManagerException;

public class VentanaPrincipal extends JFrame {

	public static boolean privilegiosAdmin() {
		try {
			if (database.DBManager.esAdmin(mainPackage.PropertiesMetodos.getProp1()) == true) {
				return true;
			} else {
				return false;
			}
		} catch (DBManagerException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem miAjustes, miCerrarSesion, miRecordarContrasena, miDescargaDatos, miEliminarCuenta;
	JMenu menuAdmin;
	JMenuItem miConfigurarOtraCuenta, miCambiarDatos, miMandarCorreo;
	JButton btnAdmin; // habria que darle otro uso, o quitarlo

	public VentanaPrincipal() {

		this.setTitle("VentanaPrincipal");
		this.setSize(1280, 800);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();

		menu = new JMenu("Menu");
		miAjustes = new JMenuItem("Ajustes");
		miCerrarSesion = new JMenuItem("Cerrar sesion");
		miRecordarContrasena = new JMenuItem("Salir y recordar contrasena");
		miDescargaDatos = new JMenuItem("Descargar datos");
		miEliminarCuenta = new JMenuItem("Eliminar cuenta");
		menu.add(miAjustes);
		menu.add(miCerrarSesion);
		menu.add(miRecordarContrasena);
		menu.add(miDescargaDatos);
		menu.add(miEliminarCuenta);

		menuAdmin = new JMenu("Opciones admin");
		miConfigurarOtraCuenta = new JMenuItem("Configurar otra cuenta");
		miCambiarDatos = new JMenuItem("Cambiar Datos");
		miMandarCorreo = new JMenuItem("Mandar correo");
		menuAdmin.add(miConfigurarOtraCuenta);
		menuAdmin.add(miCambiarDatos);
		menuAdmin.add(miMandarCorreo);

		menuBar.add(menu);
		menuBar.add(menuAdmin);
		setJMenuBar(menuBar);

		menuAdmin.setVisible(false);

		btnAdmin = new JButton();
		btnAdmin.setText("BOTON PARA PRUEBAS");
		btnAdmin.setBounds(320, 250, 120, 30);

		if (privilegiosAdmin() == true) {
			btnAdmin.setVisible(true);
			menuAdmin.setVisible(true);
		} else {
			btnAdmin.setVisible(false);
			menuAdmin.setVisible(false);
		}

		add(btnAdmin);

		btnAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		miAjustes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nuevaContrasena = JOptionPane.showInputDialog(null, "Introduce tu nueva contraseña",
						"Cambiar contraseña", JOptionPane.WARNING_MESSAGE);

				if (nuevaContrasena != null && !nuevaContrasena.equals("")) {
					try {
						database.DBManager.cambiarContrasena(mainPackage.PropertiesMetodos.getProp1(), nuevaContrasena);
					} catch (DBManagerException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Actualizacion exitosa, reiniciando. . .", "Alerta",
							JOptionPane.INFORMATION_MESSAGE);
// ojo esta idea, darle un par d vueltas :v, ¿¿¿meterle JProgressBar???
					try {
						// assuming it takes 3 secs to complete the task
						dispose();
						Thread.sleep(3000);
						mainPackage.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
						mainPackage.MainWikiFutbol.main(null);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // fin de la idea
				} else {
					JOptionPane.showMessageDialog(null, "Esa contraseña no es valida / operacion cancelada.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		miCerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPackage.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
				dispose();
				VentanaLogin VL = new VentanaLogin();
				VL.setVisible(true);
			}
		});

		miRecordarContrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		miDescargaDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaDescargar VD = new VentanaDescargar();
				VD.setVisible(true);
			}
		});

		miEliminarCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Estas segur@? Es irreversible.", "Eliminar cuenta",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					System.out.println("YES");
					try {
						database.DBManager.eliminarUsuario(mainPackage.PropertiesMetodos.getProp1());
					} catch (DBManagerException e) {
						e.printStackTrace();
					}
					dispose();
				} else if (result == JOptionPane.NO_OPTION) {
					System.out.println("NO");
				} else {
					System.out.println("NONE");
				}
			}

		});

		miConfigurarOtraCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin1 VA1 = new VentanaAdmin1();
				VA1.setVisible(true);
			}
		});

		miCambiarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambiarDatos VCD = new VentanaCambiarDatos();
				VCD.setVisible(true);
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
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase
		mainPackage.PropertiesMetodos.setProp("a", "a");

		VentanaPrincipal VP = new VentanaPrincipal();
		VP.setVisible(true);
	}
}