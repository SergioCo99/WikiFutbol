package ventanas;

import java.awt.Color;
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
public class VentanaEntrenador extends JFrame {

	private static final long serialVersionUID = 1L;
	static VentanaEquipo frame;

	public VentanaEntrenador(String entrenador, Club club, Usuario u) throws DBManagerException {
		String nombreEntrenador = null;
		try {
			nombreEntrenador = DBManager.nombreEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		this.setTitle(nombreEntrenador);
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

		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaEquipo v1 = null;
				try {
					v1 = new VentanaEquipo(club, u);
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

		JLabel lblEntrenador = new JLabel(nombreEntrenador);
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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombreEntrenador + ":");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEntrenador
		final JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(20, 110, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombreEntrenador
		final JLabel resultadoNombre = new JLabel(nombreEntrenador);
		resultadoNombre.setBounds(120, 110, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);

		// fechaNac
		final JLabel labelFechaNac = new JLabel("Fecha Nacimiento: ");
		labelFechaNac.setBounds(20, 175, 300, 50);
		labelFechaNac.setFont(fuente2);
		labelFechaNac.setForeground(Color.BLACK);
		bookPanel.add(labelFechaNac);

		// ResultadofechaNac
		String fechaNacimiento = null;
		try {
			fechaNacimiento = DBManager.fechaNacimiento(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoFechaNac = new JLabel(fechaNacimiento);
		resultadoFechaNac.setBounds(230, 175, 400, 50);
		resultadoFechaNac.setFont(fuente2);
		resultadoFechaNac.setForeground(Color.BLACK);
		bookPanel.add(resultadoFechaNac);

		// Club
		final JLabel labelClub = new JLabel("Club: ");
		labelClub.setBounds(20, 240, 150, 50);
		labelClub.setFont(fuente2);
		labelClub.setForeground(Color.BLACK);
		bookPanel.add(labelClub);

		// ResultadoClub
		String clubEntrenador = null;
		try {
			clubEntrenador = DBManager.clubEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoClub = new JLabel(clubEntrenador);
		resultadoClub.setBounds(200, 240, 400, 50);
		resultadoClub.setFont(fuente2);
		resultadoClub.setForeground(Color.BLACK);
		bookPanel.add(resultadoClub);

		// Ciudad
		final JLabel labelCiudad = new JLabel("Ciudad: ");
		labelCiudad.setBounds(20, 305, 275, 50);
		labelCiudad.setFont(fuente2);
		labelCiudad.setForeground(Color.BLACK);
		bookPanel.add(labelCiudad);

		// ResultadoCiudad
		String ciudadEntrenador = null;
		try {
			ciudadEntrenador = DBManager.ciudadEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoCiudad = new JLabel(ciudadEntrenador);
		resultadoCiudad.setBounds(200, 240, 400, 50);
		resultadoCiudad.setBounds(200, 305, 400, 50);
		resultadoCiudad.setFont(fuente2);
		resultadoCiudad.setForeground(Color.BLACK);
		bookPanel.add(resultadoCiudad);

		// Formacion
		final JLabel labelFormacion = new JLabel("Formacion: ");
		labelFormacion.setBounds(20, 370, 275, 50);
		labelFormacion.setFont(fuente2);
		labelFormacion.setForeground(Color.BLACK);
		bookPanel.add(labelFormacion);

		// Resultado Formacion
		String formacionEntrenador = null;
		try {
			formacionEntrenador = DBManager.formacionEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoFormacion = new JLabel(formacionEntrenador);
		resultadoFormacion.setBounds(200, 370, 400, 50);
		resultadoFormacion.setFont(fuente2);
		resultadoFormacion.setForeground(Color.BLACK);
		bookPanel.add(resultadoFormacion);

		// Mentalidad
		final JLabel labelMentalidad = new JLabel("Mentalidad: ");
		labelMentalidad.setBounds(20, 435, 150, 50);
		labelMentalidad.setFont(fuente2);
		labelMentalidad.setForeground(Color.BLACK);
		bookPanel.add(labelMentalidad);

		// ResultadoMentalidad
		String mentalidadEntrenador = null;
		try {
			mentalidadEntrenador = DBManager.mentalidadEntrenador(entrenador, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoMentalidad = new JLabel(mentalidadEntrenador);
		resultadoMentalidad.setBounds(200, 435, 400, 50);
		resultadoMentalidad.setFont(fuente2);
		resultadoMentalidad.setForeground(Color.BLACK);
		bookPanel.add(resultadoMentalidad);

		//Imagen logo
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
