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
public class VentanaPais extends JFrame {

	private static final long serialVersionUID = 1L;
	static VentanaCiudad frame;

	public VentanaPais(String pais, Club club, Usuario u) throws DBManagerException {
		String nombrePais = null;
		try {
			nombrePais = DBManager.nombrePais(pais, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		this.setTitle(nombrePais);
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

		JLabel lblPais = new JLabel(nombrePais);
		lblPais.setBounds(80, 20, 300, 29);
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblPais);

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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombrePais + ":");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);
	
		// NombrePais
		final JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(20, 175, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombrePais
		final JLabel resultadoNombre = new JLabel(nombrePais);
		resultadoNombre.setBounds(120, 175, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);
		
		// NombreCapital
		final JLabel labelCapital = new JLabel("Capital: ");
		labelCapital.setBounds(20, 240, 150, 50);
		labelCapital.setFont(fuente2);
		labelCapital.setForeground(Color.BLACK);
		bookPanel.add(labelCapital);

		// ResultadoCapital
		String nombreCapital = null;
		try {
			nombreCapital = DBManager.capitalPais(pais, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoCapital = new JLabel(nombreCapital);
		resultadoCapital.setBounds(135, 240, 400, 50);
		resultadoCapital.setFont(fuente2);
		resultadoCapital.setForeground(Color.BLACK);
		bookPanel.add(resultadoCapital);

		// Gentilicio
		final JLabel labelGentilicio = new JLabel("Gentilicio: ");
		labelGentilicio.setBounds(400, 305, 200, 50);
		labelGentilicio.setFont(fuente2);
		labelGentilicio.setForeground(Color.BLACK);
		bookPanel.add(labelGentilicio);

		// ResultadoGentilicio
		String gentilicio = null;
		try {
			gentilicio = DBManager.gentilicioPais(pais, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoGentilicio = new JLabel(gentilicio);
		resultadoGentilicio.setBounds(520, 305, 400, 50);
		resultadoGentilicio.setFont(fuente2);
		resultadoGentilicio.setForeground(Color.BLACK);
		bookPanel.add(resultadoGentilicio);
				
		// Idioma
		final JLabel labelIdioma = new JLabel("Idioma: ");
		labelIdioma.setBounds(20, 305, 150, 50);
		labelIdioma.setFont(fuente2);
		labelIdioma.setForeground(Color.BLACK);
		bookPanel.add(labelIdioma);

		// ResultadoIdioma
		String idioma = null;
		try {
			idioma = DBManager.idiomaPais(pais, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		final JLabel resultadoIdioma = new JLabel(idioma);
		resultadoIdioma.setBounds(135, 305, 400, 50);
		resultadoIdioma.setFont(fuente2);
		resultadoIdioma.setForeground(Color.BLACK);
		bookPanel.add(resultadoIdioma);
		
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
