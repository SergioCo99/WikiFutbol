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
 * Ventana para la visualizacion de las caracteristicas de cada estadio
 * 
 * @author sergiolopez
 *
 */
public class VentanaEstadio extends JFrame {

	private static final long serialVersionUID = 1L;
	static VentanaEquipo frame;

	// se usa en esta clase??? si no hay que borrarlo
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

	public VentanaEstadio(String estadio, Club club, Usuario u) throws DBManagerException {
		String nombreEstadio = null;
		try {
			nombreEstadio = DBManager.nombreEstadio(estadio, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		this.setTitle(nombreEstadio);
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

		JLabel lblEstadio = new JLabel(nombreEstadio);
		lblEstadio.setBounds(80, 20, 300, 29);
		lblEstadio.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblEstadio);

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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombreEstadio + ":");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEstadio
		final JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(20, 110, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombreEstadio
		final JLabel resultadoNombre = new JLabel(nombreEstadio);
		resultadoNombre.setBounds(120, 110, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);

		// Aforo
		final JLabel labelAforo = new JLabel("Aforo: ");
		labelAforo.setBounds(20, 175, 300, 50);
		labelAforo.setFont(fuente2);
		labelAforo.setForeground(Color.BLACK);
		bookPanel.add(labelAforo);

		// ResultadoAforo
		String aforo = null;
		try {
			aforo = Integer.toString(DBManager.aforoEstadio(estadio, "wikifutbolschema"));
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoAforo = new JLabel(aforo);
		resultadoAforo.setBounds(230, 175, 400, 50);
		resultadoAforo.setFont(fuente2);
		resultadoAforo.setForeground(Color.BLACK);
		bookPanel.add(resultadoAforo);

		// AnyoCreacion
		final JLabel labelAnyo = new JLabel("A\u00f1o de creaci\u00f3n: ");
		labelAnyo.setBounds(20, 240, 280, 50);
		labelAnyo.setFont(fuente2);
		labelAnyo.setForeground(Color.BLACK);
		bookPanel.add(labelAnyo);

		// ResultadoAnyoClub
		String anyoDeEstadio = null;
		try {
			anyoDeEstadio = Integer.toString(DBManager.anyoEstadio(estadio, "wikifutbolschema"));
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoAnyo = new JLabel(anyoDeEstadio);
		resultadoAnyo.setBounds(200, 240, 400, 50);
		resultadoAnyo.setFont(fuente2);
		resultadoAnyo.setForeground(Color.BLACK);
		bookPanel.add(resultadoAnyo);

		// Ciudad
		final JLabel labelCiudad = new JLabel("Ciudad: ");
		labelCiudad.setBounds(20, 305, 280, 50);
		labelCiudad.setFont(fuente2);
		labelCiudad.setForeground(Color.BLACK);
		bookPanel.add(labelCiudad);

		// ResultadoCiudad
		String ciudadEstadio = null;
		try {
			ciudadEstadio = DBManager.ciudadEstadio(estadio, "wikifutbolschema");
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		final JLabel resultadoCiudad = new JLabel(ciudadEstadio);
		resultadoCiudad.setBounds(200, 305, 400, 50);
		resultadoCiudad.setFont(fuente2);
		resultadoCiudad.setForeground(Color.BLACK);
		bookPanel.add(resultadoCiudad);

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
