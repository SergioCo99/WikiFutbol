package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
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
 * Ventana que muestra informacion sobre los equipos
 *
 * @author sergi
 *
 */
public class VentanaEquipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private String nombreEquipo;
	private static Component frame;

	public VentanaEquipo(Club club, Usuario u) throws DBManagerException {
		init(club, u);
	}

	public void init(Club club, Usuario u) {
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
			VentanaPrincipal v1 = null;
			try {
				v1 = new VentanaPrincipal(u);
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

		JLabel lblEquipo = new JLabel(nombreEquipo);
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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombreEquipo + ":");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEquipo
		final JLabel labelNombre = new JLabel("Equipo: ");
		labelNombre.setBounds(20, 110, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombreEquipo
		final JLabel resultadoNombre = new JLabel(nombreEquipo);
		resultadoNombre.setBounds(120, 110, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);

		// CiudadEquipo
		final JLabel labelCiudad = new JLabel("Ciudad: ");
		labelCiudad.setBounds(20, 175, 150, 50);
		labelCiudad.setFont(fuente2);
		labelCiudad.setForeground(Color.BLACK);
		bookPanel.add(labelCiudad);

		// ResultadoCiudadEquipo
		String nombreCiudad = club.getCiudad();
		final JButton botonCiudad = new JButton(nombreCiudad);
		botonCiudad.setBounds(120, 175, 200, 50);
		botonCiudad.setFont(fuente2);
		botonCiudad.setForeground(Color.BLACK);
		botonCiudad.setContentAreaFilled(false);
		botonCiudad.setFocusable(false);
		bookPanel.add(botonCiudad);

		botonCiudad.addActionListener(e -> {
			try {
				System.out.println(nombreCiudad);
				VentanaCiudad ve = new VentanaCiudad(nombreCiudad, club, u);
				ve.setVisible(true);
				dispose();
			} catch (Exception e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				JOptionPane.showMessageDialog(frame, "Esta ciudad no existe");
			}

		});
		// EstadioEquipo
		final JLabel labelEstadio = new JLabel("Estadio: ");
		labelEstadio.setBounds(20, 240, 150, 50);
		labelEstadio.setFont(fuente2);
		labelEstadio.setForeground(Color.BLACK);
		bookPanel.add(labelEstadio);

		// ResultadoEstadioEquipo
		String estadioEquipo = club.getEstadio();
		final JButton botonEquipo = new JButton(estadioEquipo);
		botonEquipo.setBounds(200, 240, 400, 50);
		botonEquipo.setFont(fuente2);
		botonEquipo.setForeground(Color.BLACK);
		botonEquipo.setContentAreaFilled(false);
		botonEquipo.setFocusable(false);
		bookPanel.add(botonEquipo);

		botonEquipo.addActionListener(e -> {
			try {
				System.out.println(estadioEquipo);
				VentanaEstadio ve = new VentanaEstadio(estadioEquipo, club, u);
				ve.setVisible(true);
				dispose();
			} catch (Exception e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				JOptionPane.showMessageDialog(frame, "Este estadio no existe");
			}

		});

		// AnyoCreacion
		final JLabel labelAnyo = new JLabel("A\u00f1o de creaci\u00f3n: ");
		labelAnyo.setBounds(20, 305, 275, 50);
		labelAnyo.setFont(fuente2);
		labelAnyo.setForeground(Color.BLACK);
		bookPanel.add(labelAnyo);

		// ResultadoAnyoCreacion
		String anyoCreacion = Integer.toString(club.getAnyoCreacion());
		final JLabel resultadoAnyo = new JLabel(anyoCreacion);
		resultadoAnyo.setBounds(200, 240, 400, 50);
		resultadoAnyo.setBounds(200, 305, 400, 50);
		resultadoAnyo.setFont(fuente2);
		resultadoAnyo.setForeground(Color.BLACK);
		bookPanel.add(resultadoAnyo);

		// Palmares
		final JLabel labelPalmares = new JLabel("Palmares: ");
		labelPalmares.setBounds(20, 370, 275, 50);
		labelPalmares.setFont(fuente2);
		labelPalmares.setForeground(Color.BLACK);
		bookPanel.add(labelPalmares);

		// Resultado Palmares
		String palmares = club.getPalmares();
		final JLabel resultadoPalmares = new JLabel(palmares);
		resultadoPalmares.setBounds(200, 370, 400, 50);
		resultadoPalmares.setFont(fuente2);
		resultadoPalmares.setForeground(Color.BLACK);
		bookPanel.add(resultadoPalmares);

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
		botonEntrenador.setFocusable(false);
		bookPanel.add(botonEntrenador);

		botonEntrenador.addActionListener(e -> {
			try {
				System.out.println(nombreEntrenador);
				VentanaEntrenador ve = new VentanaEntrenador(nombreEntrenador, club, u);
				ve.setVisible(true);
				dispose();
			} catch (Exception e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				JOptionPane.showMessageDialog(frame, "Este entrenador no existe");
			}

		});

		JLabelGraficoAjustado fotoEquipo = new JLabelGraficoAjustado("resources/logo1.png", 170, 175);
		fotoEquipo.setLocation(600, 50);
		bookPanel.add(fotoEquipo);

		// Btn ver plantilla del equipo seleccionado
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

		btnPlantilla.addActionListener(e -> {
			try {
				List<String> arrayJugadores = new ArrayList<String>();
				arrayJugadores = DBManager.getJugadoresPorEquipo(nombreEquipo);
				VentanaJugadores ve = new VentanaJugadores(arrayJugadores, club, u);
				ve.setVisible(true);
				dispose();
			} catch (Exception e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				JOptionPane.showMessageDialog(frame, "Este entrenador no existe");
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