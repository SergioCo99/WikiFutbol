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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Jugador;
import clases.Usuario;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

/**
 * Ventana para la visualizacion de la informacion de cada jugador
 * 
 * @author sergiolopez
 *
 */
public class VentanaJugador extends JFrame {

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
	String nombreJugador;
	static Component frame;

	public VentanaJugador(Jugador jugador, Usuario u) throws DBManagerException {
		init(jugador, u);
	}

	public void init(Jugador jugador, Usuario u) {
		nombreJugador = jugador.getNombre();
		this.setTitle(nombreJugador);
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

		final JPanel atrasPanel = new JPanel();
		atrasPanel.setBounds(1050, 18, 26, 30);
		atrasPanel.setOpaque(false);
		atrasPanel.setBorder(null);
		atrasPanel.setLayout(null);
		final JLabel atrasIMG = new JLabel();
		atrasPanel.add(atrasIMG);
		navBarPanel.add(atrasPanel);

		JLabel lblJugador = new JLabel(nombreJugador);
		lblJugador.setBounds(80, 20, 300, 29);
		lblJugador.setFont(new Font("Tahoma", Font.BOLD, 24));
		navBarPanel.add(lblJugador);

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
		final JLabel cabecera = new JLabel("Informaci\u00f3n sobre " + nombreJugador + ":");
		// final JLabel cabecera = new JLabel("Informaci�n sobre 'NombreEquipo':");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEquipo
		final JLabel labelNombre = new JLabel("Nombre: ");
		labelNombre.setBounds(20, 110, 150, 50);
		labelNombre.setFont(fuente2);
		labelNombre.setForeground(Color.BLACK);
		bookPanel.add(labelNombre);

		// ResultadoNombreEquipo
		final JLabel resultadoNombre = new JLabel(nombreJugador);
		resultadoNombre.setBounds(120, 110, 400, 50);
		resultadoNombre.setFont(fuente2);
		resultadoNombre.setForeground(Color.BLACK);
		bookPanel.add(resultadoNombre);

		// Fecha Nacimiento
		final JLabel labelFechaNac = new JLabel("Fecha de nacimiento: ");
		labelFechaNac.setBounds(20, 175, 290, 50);
		labelFechaNac.setFont(fuente2);
		labelFechaNac.setForeground(Color.BLACK);
		bookPanel.add(labelFechaNac);

		// Resultado Fecha Nacimiento
		String fnacimi = jugador.getFechaNac();
		final JLabel resultadoFechaNac = new JLabel(fnacimi);
		resultadoFechaNac.setBounds(260, 175, 400, 50);
		resultadoFechaNac.setFont(fuente2);
		resultadoFechaNac.setForeground(Color.BLACK);
		bookPanel.add(resultadoFechaNac);

		// Club
		final JLabel labelClub = new JLabel("Club: ");
		labelClub.setBounds(20, 240, 150, 50);
		labelClub.setFont(fuente2);
		labelClub.setForeground(Color.BLACK);
		bookPanel.add(labelClub);

		// Resultado Club
		String equipo = jugador.getClub();
		final JLabel resultadoClub = new JLabel(equipo);
		resultadoClub.setBounds(100, 240, 400, 50);
		resultadoClub.setFont(fuente2);
		resultadoClub.setForeground(Color.BLACK);
		bookPanel.add(resultadoClub);

		// Ciudad
		final JLabel labelCiudad = new JLabel("Ciudad: ");
		labelCiudad.setBounds(20, 305, 150, 50);
		labelCiudad.setFont(fuente2);
		labelCiudad.setForeground(Color.BLACK);
		bookPanel.add(labelCiudad);

		// Resultado Ciudad
		String ciudad = jugador.getCiudad();
		final JLabel resultadoCiudad = new JLabel(ciudad);
		resultadoCiudad.setBounds(115, 305, 400, 50);
		resultadoCiudad.setFont(fuente2);
		resultadoCiudad.setForeground(Color.BLACK);
		bookPanel.add(resultadoCiudad);

		// Posicion
		final JLabel labelPosicion = new JLabel("Posicion: ");
		labelPosicion.setBounds(20, 370, 150, 50);
		labelPosicion.setFont(fuente2);
		labelPosicion.setForeground(Color.BLACK);
		bookPanel.add(labelPosicion);

		// Resultado Posicion
		String posicion = jugador.getPosicion().toString();
		final JLabel resultadoPosicion = new JLabel(posicion);
		resultadoPosicion.setBounds(127, 370, 400, 50);
		resultadoPosicion.setFont(fuente2);
		resultadoPosicion.setForeground(Color.BLACK);
		bookPanel.add(resultadoPosicion);

		// Dorsal
		final JLabel labelDorsal = new JLabel("Dorsal: ");
		labelDorsal.setBounds(20, 435, 150, 50);
		labelDorsal.setFont(fuente2);
		labelDorsal.setForeground(Color.BLACK);
		bookPanel.add(labelDorsal);

		// Resultado Dorsal
		String dorsal = Integer.toString(jugador.getDorsal());
		final JLabel resultadoDorsal = new JLabel(dorsal);
		resultadoDorsal.setBounds(120, 435, 400, 50);
		resultadoDorsal.setFont(fuente2);
		resultadoDorsal.setForeground(Color.BLACK);
		bookPanel.add(resultadoDorsal);

		// Goles
		final JLabel labelGoles = new JLabel("Goles: ");
		labelGoles.setBounds(20, 500, 150, 50);
		labelGoles.setFont(fuente2);
		labelGoles.setForeground(Color.BLACK);
		bookPanel.add(labelGoles);

		// Resultado Goles
		String goles = Integer.toString(jugador.getGoles());
		final JLabel resultadoGoles = new JLabel(goles);
		resultadoGoles.setBounds(120, 500, 400, 50);
		resultadoGoles.setFont(fuente2);
		resultadoGoles.setForeground(Color.BLACK);
		bookPanel.add(resultadoGoles);

		// Altura
		final JLabel labelAltura = new JLabel("Altura: ");
		labelAltura.setBounds(400, 240, 150, 50);
		labelAltura.setFont(fuente2);
		labelAltura.setForeground(Color.BLACK);
		bookPanel.add(labelAltura);

		// Resultado Altura
		String altura = Integer.toString(jugador.getAltura());
		final JLabel resultadoAltura = new JLabel(altura);
		resultadoAltura.setBounds(500, 240, 400, 50);
		resultadoAltura.setFont(fuente2);
		resultadoAltura.setForeground(Color.BLACK);
		bookPanel.add(resultadoAltura);

		// Peso
		final JLabel labelPeso = new JLabel("Peso: ");
		labelPeso.setBounds(400, 305, 150, 50);
		labelPeso.setFont(fuente2);
		labelPeso.setForeground(Color.BLACK);
		bookPanel.add(labelPeso);

		// Resultado Peso
		String peso = Integer.toString(jugador.getPeso());
		final JLabel resultadoPeso = new JLabel(peso);
		resultadoPeso.setBounds(500, 305, 400, 50);
		resultadoPeso.setFont(fuente2);
		resultadoPeso.setForeground(Color.BLACK);
		bookPanel.add(resultadoPeso);

		// Pie Fav
		final JLabel labelPieFav = new JLabel("Pie Fav: ");
		labelPieFav.setBounds(400, 370, 150, 50);
		labelPieFav.setFont(fuente2);
		labelPieFav.setForeground(Color.BLACK);
		bookPanel.add(labelPieFav);

		// Resultado Pie Fav
		String piefav = jugador.getPiefav().toString();
		final JLabel resultadoPieFav = new JLabel(piefav);
		resultadoPieFav.setBounds(500, 370, 400, 50);
		resultadoPieFav.setFont(fuente2);
		resultadoPieFav.setForeground(Color.BLACK);
		bookPanel.add(resultadoPieFav);

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

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase

		Usuario u = new Usuario(1, "", "", "", 1, "");
		Club e = new Club(0, "", "", "", 3, "", "");
		VentanaEquipo v = new VentanaEquipo(e, u);
		v.setVisible(true);

	}
}