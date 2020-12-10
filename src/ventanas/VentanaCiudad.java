package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
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
import database.DBManager;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

/**
 * Ventana para la visualizacion de los datos de cada entrenador
 *
 * @author sergiolopez
 *
 */
public class VentanaCiudad extends JFrame {

	private static final long serialVersionUID = 1L;
	private static VentanaCiudad frame;

	public VentanaCiudad(String ciudad, Club club, Usuario u) throws DBManagerException {
		String nombreCiudad = null;
		try {
			nombreCiudad = DBManager.nombreCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		this.setTitle(nombreCiudad);
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

		// Btn ventana anterior
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

		btnAtras.addActionListener(e -> {
			VentanaEquipo v1 = null;
			try {
				v1 = new VentanaEquipo(club, u);
			} catch (DBManagerException e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				e1.printStackTrace();
			}
			v1.setVisible(true);
			dispose();
		});

		final JPanel atrasPanel = new JPanel();
		atrasPanel.setBounds(1050, 18, 26, 30);
		atrasPanel.setOpaque(false);
		atrasPanel.setBorder(null);
		atrasPanel.setLayout(null);
		final JLabel atrasIMG = new JLabel();
		atrasPanel.add(atrasIMG);
		navBarPanel.add(atrasPanel);

		JLabel lblCiudad = new JLabel(nombreCiudad);
		lblCiudad.setBounds(80, 20, 300, 29);
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblCiudad);

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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombreCiudad + ":");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreCiudad
		final JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(20, 110, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombreCiudad
		final JLabel resultadoNombre = new JLabel(nombreCiudad);
		resultadoNombre.setBounds(120, 110, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);

		// NombreProvincia
		final JLabel labelProvincia = new JLabel("Provincia: ");
		labelProvincia.setBounds(20, 175, 150, 50);
		labelProvincia.setFont(fuente2);
		labelProvincia.setForeground(Color.BLACK);
		bookPanel.add(labelProvincia);

		// ResultadoProvincia
		String nombreProvincia = null;
		try {
			nombreProvincia = DBManager.provinciaCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoProvincia = new JLabel(nombreProvincia);
		resultadoProvincia.setBounds(135, 175, 400, 50);
		resultadoProvincia.setFont(fuente2);
		resultadoProvincia.setForeground(Color.BLACK);
		bookPanel.add(resultadoProvincia);

		// NombreComAutonoma
		final JLabel labelComAutonoma = new JLabel("Com. Autónoma: ");
		labelComAutonoma.setBounds(20, 240, 200, 50);
		labelComAutonoma.setFont(fuente2);
		labelComAutonoma.setForeground(Color.BLACK);
		bookPanel.add(labelComAutonoma);

		// ResultadoComAutonoma
		String nombreComAutonoma = null;
		try {
			nombreComAutonoma = DBManager.comAutonomaCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoComAutonoma = new JLabel(nombreComAutonoma);
		resultadoComAutonoma.setBounds(200, 240, 400, 50);
		resultadoComAutonoma.setFont(fuente2);
		resultadoComAutonoma.setForeground(Color.BLACK);
		bookPanel.add(resultadoComAutonoma);

		// NombrePais
		final JLabel labelPais = new JLabel("Pais: ");
		labelPais.setBounds(20, 305, 150, 50);
		labelPais.setFont(fuente2);
		labelPais.setForeground(Color.BLACK);
		bookPanel.add(labelPais);

		// ResultadoPais
		String nombrePais = null;
		try {
			nombrePais = DBManager.paisCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JButton botonPais = new JButton(nombrePais);
		botonPais.setBounds(85, 305, 200, 50);
		botonPais.setFont(fuente2);
		botonPais.setForeground(Color.BLACK);
		botonPais.setContentAreaFilled(false);
		botonPais.setFocusable(false);
		bookPanel.add(botonPais);
		String nomPais = nombrePais;
		botonPais.addActionListener(e -> {
			try {
				VentanaPais ve = new VentanaPais(nomPais, club, u);
				ve.setVisible(true);
				dispose();
			} catch (Exception e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				JOptionPane.showMessageDialog(frame, "Esta ciudad no existe");
			}

		});

		// Poblacion
		final JLabel labelPoblacion = new JLabel("Población: ");
		labelPoblacion.setBounds(20, 370, 150, 50);
		labelPoblacion.setFont(fuente2);
		labelPoblacion.setForeground(Color.BLACK);
		bookPanel.add(labelPoblacion);

		// ResultadoPoblacion
		String poblacion = null;
		try {
			poblacion = DBManager.poblacionCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoPoblacion = new JLabel(poblacion + " habitantes");
		resultadoPoblacion.setBounds(135, 370, 400, 50);
		resultadoPoblacion.setFont(fuente2);
		resultadoPoblacion.setForeground(Color.BLACK);
		bookPanel.add(resultadoPoblacion);

		// Gentilicio
		final JLabel labelGentilicio = new JLabel("Gentilicio: ");
		labelGentilicio.setBounds(400, 370, 150, 50);
		labelGentilicio.setFont(fuente2);
		labelGentilicio.setForeground(Color.BLACK);
		bookPanel.add(labelGentilicio);

		// ResultadoPoblacion
		String gentilicio = null;
		try {
			gentilicio = DBManager.gentilicioCiudad(ciudad, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoGentilicio = new JLabel(gentilicio);
		resultadoGentilicio.setBounds(520, 370, 400, 50);
		resultadoGentilicio.setFont(fuente2);
		resultadoGentilicio.setForeground(Color.BLACK);
		bookPanel.add(resultadoGentilicio);

		// Imagen logo
		JLabelGraficoAjustado fotoEquipo = new JLabelGraficoAjustado("resources/logo1.png", 170, 175);
		fotoEquipo.setLocation(600, 50);
		bookPanel.add(fotoEquipo);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
			}
		});
	}

}
