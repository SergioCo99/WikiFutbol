package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Usuario;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

public class VentanaEquipo extends JFrame {

	public static boolean privilegiosAdmin() {
		try {
			if (database.DBManager.esAdmin(utils.PropertiesMetodos.getProp1()) == true) {
				return true;
			} else {
				return false;
			}
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		return false;
	}

	private static final long serialVersionUID = 1L;
	private static Usuario usuario;
	String nombreEquipo;
	static Component frame;

	public VentanaEquipo(Club club, Usuario u) throws DBManagerException {
		init(club, u);
	}

	public void init(Club club, Usuario u) {
		usuario = u;
		nombreEquipo = club.getNombre();
		this.setTitle(nombreEquipo);
		this.setSize(1200, 700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

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
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
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

		JLabel lblEquipo = new JLabel(nombreEquipo);
		// JLabel lblEquipo = new JLabel("'NombreEquipo'");
		lblEquipo.setBounds(80, 20, 300, 29);
		lblEquipo.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblEquipo);

		JLabelGraficoAjustado iconoWikiFutbol = new JLabelGraficoAjustado("resources/logo1.png", 60, 50);
		iconoWikiFutbol.setLocation(10, 13);
		navBarPanel.add(iconoWikiFutbol);

		final JPanel bookPanel = new JPanel();
		bookPanel.setLayout(null);
		bookPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		bookPanel.setBounds(197, 62, 800, 580);
		bookPanel.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.75f));
		add(bookPanel);

		nombreEquipo = club.getNombre();

		// Cabecera
		final JLabel cabecera = new JLabel("Información sobre " + nombreEquipo + ":");
		// final JLabel cabecera = new JLabel("Información sobre 'NombreEquipo':");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEquipo
		final JLabel label1 = new JLabel("Equipo: ");
		label1.setBounds(20, 110, 150, 50);
		label1.setFont(fuente2);
		label1.setForeground(Color.BLACK);
		bookPanel.add(label1);

		// ResultadoNombreEquipo
		final JLabel label11 = new JLabel(nombreEquipo);
		// final JLabel label11 = new JLabel("Nombre Prueba");
		label11.setBounds(120, 110, 400, 50);
		label11.setFont(fuente2);
		label11.setForeground(Color.BLACK);
		bookPanel.add(label11);

		// CiudadEquipo
		final JLabel label2 = new JLabel("Ciudad: ");
		label2.setBounds(20, 175, 150, 50);
		label2.setFont(fuente2);
		label2.setForeground(Color.BLACK);
		bookPanel.add(label2);

		// ResultadoCiudadEquipo
		String ciudadEquipo = club.getCiudad();
		// String ciudadEquipo = "Ciudad Prueba";
		final JLabel label15 = new JLabel(ciudadEquipo);
		label15.setBounds(120, 175, 400, 50);
		label15.setFont(fuente2);
		label15.setForeground(Color.BLACK);
		bookPanel.add(label15);

		// EstadioEquipo
		final JLabel label22 = new JLabel("Estadio: ");
		label22.setBounds(20, 240, 150, 50);
		label22.setFont(fuente2);
		label22.setForeground(Color.BLACK);
		bookPanel.add(label22);

		// ResultadoEstadioEquipo

		String estadioEquipo = club.getEstadio();
		final JButton botonEquipo = new JButton(estadioEquipo);
		botonEquipo.setBounds(200, 240, 400, 50);
		botonEquipo.setFont(fuente2);
		botonEquipo.setForeground(Color.BLACK);
		botonEquipo.setContentAreaFilled(false);
		// botonPabellon.setBorder(new LineBorder(new Color (0,0,0),3));
		botonEquipo.setFocusable(true);
		bookPanel.add(botonEquipo);

		botonEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(estadioEquipo);
					VentanaEstadio ve = new VentanaEstadio(estadioEquipo, club, u);
					ve.setVisible(true);
					dispose();
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					JOptionPane.showMessageDialog(frame, "Este estadio no existe");
				}

			}
		});

		// AnyoCreacion
		final JLabel label3 = new JLabel("A\u00f1o de creaci\u00f3n: ");
		label3.setBounds(20, 305, 275, 50);
		label3.setFont(fuente2);
		label3.setForeground(Color.BLACK);
		bookPanel.add(label3);

		// ResultadoAnyoCreacion
		String anyoCreacion = Integer.toString(club.getAnyoCreacion());
		final JLabel label33 = new JLabel(anyoCreacion);
		// String anyoCreacion = "2020";
		label33.setBounds(200, 240, 400, 50);

		// String anyoCreacion = "2020";
		label33.setBounds(200, 305, 400, 50);
		label33.setFont(fuente2);
		label33.setForeground(Color.BLACK);
		bookPanel.add(label33);

		// Palmares
		final JLabel label4 = new JLabel("Palmares: ");
		label4.setBounds(20, 370, 275, 50);
		label4.setFont(fuente2);
		label4.setForeground(Color.BLACK);
		bookPanel.add(label4);

		// Resultado Palmares
		String palmares = club.getPalmares();
		// String palmares = "Palmares Prueba";
		final JLabel label44 = new JLabel(palmares);
		label44.setBounds(200, 370, 400, 50);
		label44.setFont(fuente2);
		label44.setForeground(Color.BLACK);
		bookPanel.add(label44);

		// NombreEntrenador
		final JLabel labelEntrenador = new JLabel("Entrenador: ");
		labelEntrenador.setBounds(20, 435, 150, 50);
		labelEntrenador.setFont(fuente2);
		labelEntrenador.setForeground(Color.BLACK);
		bookPanel.add(labelEntrenador);

		// Resultado Entrenador

		String nombreEntrenador = club.getEntrenador();
		final JButton botonEntrenador = new JButton(nombreEntrenador);
		botonEntrenador.setBounds(200, 435, 300, 50);
		botonEntrenador.setFont(fuente2);
		botonEntrenador.setForeground(Color.BLACK);
		botonEntrenador.setContentAreaFilled(false);
		// botonPabellon.setBorder(new LineBorder(new Color (0,0,0),3));
		botonEntrenador.setFocusable(true);
		bookPanel.add(botonEntrenador);

		botonEntrenador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(nombreEntrenador);
					VentanaEntrenador ve = new VentanaEntrenador(nombreEntrenador, club, u);
					ve.setVisible(true);
					dispose();
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					JOptionPane.showMessageDialog(frame, "Este entrenador no existe");
				}

			}
		});

		JLabelGraficoAjustado fotoEquipo = new JLabelGraficoAjustado("resources/logo1.png", 170, 175);
		fotoEquipo.setLocation(600, 50);
		bookPanel.add(fotoEquipo);

		final JButton btnPlantilla = new JButton("Ver Plantilla");
		btnPlantilla.setBounds(330, 515, 150, 50);
		btnPlantilla.setForeground(Color.WHITE);
		btnPlantilla.setFont(new Font("Rockwell", Font.BOLD, 14));
		btnPlantilla.setFocusPainted(false);
		btnPlantilla.setOpaque(false);
		btnPlantilla.setContentAreaFilled(false);
		btnPlantilla.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		bookPanel.add(btnPlantilla);
		btnPlantilla.setFocusable(false);

		btnPlantilla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaJugadores v1 = null;
				try {
					v1 = new VentanaJugadores(u);
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}
				v1.setVisible(true);
				dispose();
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
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase

		Usuario u = new Usuario(1, "", "", "", 1, "");
		Club e = new Club(0, "", "", "", 3, "", "");
		VentanaEquipo v = new VentanaEquipo(e, u);
		v.setVisible(true);

	}
}