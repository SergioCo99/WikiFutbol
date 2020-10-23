package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Usuario;
import database.DBManager;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

public class VentanaEntrenador extends JFrame {
	private Usuario usuario;
	static VentanaEquipo frame;

	public static boolean privilegiosAdmin() {
		try {
			if (database.DBManager.esAdmin(utils.PropertiesMetodos.getProp1()) == true) {
				return true;
			} else {
				return false;
			}
		} catch (DBManagerException e) {
			e.printStackTrace();
		}
		return false;
	}

	public VentanaEntrenador(String entrenador, Club club, Usuario u) throws DBManagerException {
		usuario = u;
		String nombreEntrenador = null;
		try {
			nombreEntrenador = DBManager.nombreEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		this.setTitle(nombreEntrenador);
		this.setSize(1200, 700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/logo1.png"));

		// Navbar Panel
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		navBarPanel.setBounds(-5, -5, 1203, 70);
		navBarPanel.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.75f));
		add(navBarPanel);
		navBarPanel.setLayout(null);

		final JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(1040, 20, 140, 30);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Rockwell", Font.BOLD, 14));
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		navBarPanel.add(btnAtras);
		btnAtras.setFocusable(false);

		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal v1 = null;
				try {
					v1 = new VentanaPrincipal(u);
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
				v1.setVisible(true);
				dispose();
			}
		});

		final JPanel atrasPanel = new JPanel();
		atrasPanel.setBounds(1050, 18, 26, 30);
		atrasPanel.setOpaque(false);
		atrasPanel.setBorder(null);
		atrasPanel.setLayout(null);
		final JLabel atrasIMG = new JLabel();
		atrasPanel.add(atrasIMG);
		navBarPanel.add(atrasPanel);

		JLabel lblEntrenador = new JLabel(nombreEntrenador);
		// JLabel lblEquipo = new JLabel("'NombreEquipo'");
		lblEntrenador.setBounds(80, 20, 300, 29);
		lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblEntrenador);

		JLabelGraficoAjustado iconoWikiFutbol = new JLabelGraficoAjustado("resources/logo1.png", 60, 50);
		iconoWikiFutbol.setLocation(10, 13);
		navBarPanel.add(iconoWikiFutbol);

		final JPanel bookPanel = new JPanel();
		bookPanel.setLayout(null);
		bookPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		bookPanel.setBounds(197, 62, 800, 580);
		bookPanel.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.75f));
		add(bookPanel);

		// Cabecera
		final JLabel cabecera = new JLabel("Informaci�n sobre " + nombreEntrenador + ":");
		// final JLabel cabecera = new JLabel("Informaci�n sobre 'NombreEntrenador':");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEntrenador
		final JLabel label1 = new JLabel("Nombre: ");
		label1.setBounds(20, 110, 150, 50);
		label1.setFont(fuente2);
		label1.setForeground(Color.BLACK);
		bookPanel.add(label1);

		// ResultadoNombreEntrenador
		final JLabel label11 = new JLabel(nombreEntrenador);
		// final JLabel label11 = new JLabel("Nombre Prueba");
		label11.setBounds(120, 110, 400, 50);
		label11.setFont(fuente2);
		label11.setForeground(Color.BLACK);
		bookPanel.add(label11);

		// fechaNac
		final JLabel label2 = new JLabel("Fecha Nacimiento: ");
		label2.setBounds(20, 175, 300, 50);
		label2.setFont(fuente2);
		label2.setForeground(Color.BLACK);
		bookPanel.add(label2);

		// ResultadofechaNac
		String ciudadEquipo = "01/01/1999";
		final JLabel label15 = new JLabel(ciudadEquipo);
		label15.setBounds(230, 175, 400, 50);
		label15.setFont(fuente2);
		label15.setForeground(Color.BLACK);
		bookPanel.add(label15);

		// Club
		final JLabel label22 = new JLabel("Club: ");
		label22.setBounds(20, 240, 150, 50);
		label22.setFont(fuente2);
		label22.setForeground(Color.BLACK);
		bookPanel.add(label22);

		// ResultadoClub
		String estadioEquipo = "Club prueba";
		final JLabel label155 = new JLabel(estadioEquipo);
		label155.setBounds(200, 240, 400, 50);
		label155.setFont(fuente2);
		label155.setForeground(Color.BLACK);
		bookPanel.add(label155);

		// Ciudad
		final JLabel label3 = new JLabel("Ciudad: ");
		label3.setBounds(20, 305, 275, 50);
		label3.setFont(fuente2);
		label3.setForeground(Color.BLACK);
		bookPanel.add(label3);

		// ResultadoCiudad
		String ciudad = "Bilbao";
		final JLabel label33 = new JLabel(ciudad);
		label33.setBounds(200, 240, 400, 50);
		label33.setBounds(200, 305, 400, 50);
		label33.setFont(fuente2);
		label33.setForeground(Color.BLACK);
		bookPanel.add(label33);

		// Formacion
		final JLabel label4 = new JLabel("Formacion: ");
		label4.setBounds(20, 370, 275, 50);
		label4.setFont(fuente2);
		label4.setForeground(Color.BLACK);
		bookPanel.add(label4);

		// Resultado Formacion
		String formacion = "Formacion Prueba";
		final JLabel label44 = new JLabel(formacion);
		label44.setBounds(200, 370, 400, 50);
		label44.setFont(fuente2);
		label44.setForeground(Color.BLACK);
		bookPanel.add(label44);

		// Mentalidad
		final JLabel labelMentalidad = new JLabel("Mentalidad: ");
		labelMentalidad.setBounds(20, 435, 150, 50);
		labelMentalidad.setFont(fuente2);
		labelMentalidad.setForeground(Color.BLACK);
		bookPanel.add(labelMentalidad);

		// ResultadoMentalidad

		String mentalidad = "Prueba mentalidad";
		final JLabel label442 = new JLabel(formacion);
		label442.setBounds(200, 435, 400, 50);
		label442.setFont(fuente2);
		label442.setForeground(Color.BLACK);
		bookPanel.add(label442);

		JLabelGraficoAjustado fotoEquipo = new JLabelGraficoAjustado("resources/logo1.png", 170, 175);
		fotoEquipo.setLocation(600, 50);
		bookPanel.add(fotoEquipo);
	}

}
