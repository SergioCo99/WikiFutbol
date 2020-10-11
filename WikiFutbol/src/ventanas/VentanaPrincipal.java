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
	JMenuBar mb;
	JMenu menu1;
	JMenuItem mi11, mi12, mi13, mi14;
	JMenu menu2;
	JMenuItem mi21, mi22, mi23;
	JButton btnAdmin;

	public VentanaPrincipal() {

		this.setTitle("WikiFutbol");
		this.setSize(1280, 800);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mb = new JMenuBar();

		menu1 = new JMenu("Menu");
		mi11 = new JMenuItem("Ajustes");
		mi12 = new JMenuItem("Cerrar sesion");
		mi13 = new JMenuItem("Salir y recordar contrasena");
		mi14 = new JMenuItem("Descargar datos");
		menu1.add(mi11);
		menu1.add(mi12);
		menu1.add(mi13);
		menu1.add(mi14);

		menu2 = new JMenu("Opciones admin");
		mi21 = new JMenuItem("Configurar otra cuenta");
		mi22 = new JMenuItem("Añadir datos o ??? (CambiarDatos)");
		mi23 = new JMenuItem("Mandar correo/notificacion");
		menu2.add(mi21);
		menu2.add(mi22);
		menu2.add(mi23);

		mb.add(menu1);
		mb.add(menu2);
		setJMenuBar(mb);

		menu2.setVisible(false);

		btnAdmin = new JButton();
		btnAdmin.setText("BOTON PARA PRUEBAS");
		btnAdmin.setBounds(320, 250, 120, 30);

		if (privilegiosAdmin() == true) {
			btnAdmin.setVisible(true);
			menu2.setVisible(true);
		} else {
			btnAdmin.setVisible(false);
			menu2.setVisible(false);
		}

		add(btnAdmin);

		btnAdmin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnAdmin");
			}
		});

		mi11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi1");

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

		mi12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi2");
				mainPackage.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
				dispose();
				VentanaLogin VL = new VentanaLogin();
				VL.setVisible(true);
			}
		});

		mi13.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi3");
				dispose();
			}
		});

		mi14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mi14");
				VentanaDescargar VD = new VentanaDescargar();
				VD.setVisible(true);
			}
		});

		mi21.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin1 VA1 = new VentanaAdmin1();
				VA1.setVisible(true);
			}
		});

		mi22.addActionListener(new ActionListener() {

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