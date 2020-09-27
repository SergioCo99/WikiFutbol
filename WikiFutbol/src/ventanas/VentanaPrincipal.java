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
	JMenuItem mi1, mi2, mi3;
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
		mi1 = new JMenuItem("Ajustes");
		mi2 = new JMenuItem("Cerrar sesion");
		mi3 = new JMenuItem("Salir y recordar contrasena");
		menu1.add(mi1);
		menu1.add(mi2);
		menu1.add(mi3);
		mb.add(menu1);
		setJMenuBar(mb);

		btnAdmin = new JButton();
		btnAdmin.setText("Aceptar");
		btnAdmin.setBounds(320, 250, 120, 30);

		if (privilegiosAdmin() == true) {
			btnAdmin.setVisible(true);
		} else {
			btnAdmin.setVisible(false);
		}

		add(btnAdmin);

		mi1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi1");
			}
		});

		mi2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi2");
				mainPackage.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
				dispose();
				VentanaLogin VL = new VentanaLogin();
				VL.setVisible(true);
			}
		});

		mi3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("mi3");
				dispose();
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
		VentanaPrincipal VP = new VentanaPrincipal();
		VP.setVisible(true);
	}
}