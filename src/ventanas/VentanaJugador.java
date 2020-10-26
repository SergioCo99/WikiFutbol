package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Jugador;
import clases.Jugador.PieFav;
import clases.Jugador.Posicion;
import clases.Usuario;
import database.DBManagerException;
import utils.JLabelGraficoAjustado;

public class VentanaJugador extends JFrame {

	

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

	private static final long serialVersionUID = 1L;
	private static Usuario usuario;
	String nombreJugador;
	static Component frame;

	public VentanaJugador(Jugador jugador, Usuario u) throws DBManagerException {
		init(jugador, u);
	}

	public void init(Jugador jugador, Usuario u) {
		usuario = u;
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
		final JLabel cabecera = new JLabel("Información sobre " + nombreJugador + ":");
		// final JLabel cabecera = new JLabel("Información sobre 'NombreEquipo':");
		cabecera.setBounds(200, 11, 500, 50);
		Font fuente2 = new Font("Tahoma", 3, 20);
		cabecera.setFont(new Font("Tahoma", Font.BOLD, 20));
		cabecera.setForeground(Color.BLACK);
		bookPanel.add(cabecera);

		// NombreEquipo
		final JLabel label1 = new JLabel("Nombre: ");
		label1.setBounds(20, 110, 150, 50);
		label1.setFont(fuente2);
		label1.setForeground(Color.BLACK);
		bookPanel.add(label1);

		// ResultadoNombreEquipo
		final JLabel label11 = new JLabel(nombreJugador);
		// final JLabel label11 = new JLabel("Nombre Prueba");
		label11.setBounds(120, 110, 400, 50);
		label11.setFont(fuente2);
		label11.setForeground(Color.BLACK);
		bookPanel.add(label11);

		// Fecha Nacimiento
		final JLabel label2 = new JLabel("Fecha de nacimiento: ");
		label2.setBounds(20, 175, 290, 50);
		label2.setFont(fuente2);
		label2.setForeground(Color.BLACK);
		bookPanel.add(label2);

		// Resultado Fecha Nacimiento
		String fnacimi = jugador.getFechaNac();
		final JLabel label15 = new JLabel(fnacimi);
		label15.setBounds(260, 175, 400, 50);
		label15.setFont(fuente2);
		label15.setForeground(Color.BLACK);
		bookPanel.add(label15);

		// Club
		final JLabel label22 = new JLabel("Club: ");
		label22.setBounds(20, 240, 150, 50);
		label22.setFont(fuente2);
		label22.setForeground(Color.BLACK);
		bookPanel.add(label22);
		
		// Resultado Club
		String equipo = jugador.getClub();
		final JLabel label223 = new JLabel(equipo);
		label223.setBounds(100, 240, 400, 50);
		label223.setFont(fuente2);
		label223.setForeground(Color.BLACK);
		bookPanel.add(label223);
		
		// Ciudad
		final JLabel label3 = new JLabel("Ciudad: ");
		label3.setBounds(20, 305, 150, 50);
		label3.setFont(fuente2);
		label3.setForeground(Color.BLACK);
		bookPanel.add(label3);
		
		// Resultado Ciudad
		String ciudad = jugador.getCiudad();
		final JLabel label33 = new JLabel(ciudad);
		label33.setBounds(115, 305, 400, 50);
		label33.setFont(fuente2);
		label33.setForeground(Color.BLACK);
		bookPanel.add(label33);
				
		// Posicion
		final JLabel label4 = new JLabel("Posicion: ");
		label4.setBounds(20, 370, 150, 50);
		label4.setFont(fuente2);
		label4.setForeground(Color.BLACK);
		bookPanel.add(label4);
		
		// Resultado Posicion
		//String a = jugador.getPosicion();
		final JLabel label44 = new JLabel("Prueba");
		label44.setBounds(127, 370, 400, 50);
		label44.setFont(fuente2);
		label44.setForeground(Color.BLACK);
		bookPanel.add(label44);
		
		// Dorsal
		final JLabel label5 = new JLabel("Dorsal: ");
		label5.setBounds(20, 435, 150, 50);
		label5.setFont(fuente2);
		label5.setForeground(Color.BLACK);
		bookPanel.add(label5);
		
		// Resultado Dorsal
		String dorsal = Integer.toString(jugador.getDorsal());
		final JLabel label55 = new JLabel(dorsal);
		label55.setBounds(120, 435, 400, 50);
		label55.setFont(fuente2);
		label55.setForeground(Color.BLACK);
		bookPanel.add(label55);
				
		// Goles
		final JLabel label6 = new JLabel("Goles: ");
		label6.setBounds(20, 500, 150, 50);
		label6.setFont(fuente2);
		label6.setForeground(Color.BLACK);
		bookPanel.add(label6);
		
		// Resultado Goles
		String goles = Integer.toString(jugador.getGoles());
		final JLabel label66 = new JLabel(goles);
		label66.setBounds(120, 500, 400, 50);
		label66.setFont(fuente2);
		label66.setForeground(Color.BLACK);
		bookPanel.add(label66);
		
		// Altura
		final JLabel label7 = new JLabel("Altura: ");
		label7.setBounds(400, 240, 150, 50);
		label7.setFont(fuente2);
		label7.setForeground(Color.BLACK);
		bookPanel.add(label7);
		
		// Resultado Altura
		String altura = Integer.toString(jugador.getAltura());
		final JLabel label77 = new JLabel(altura);
		label77.setBounds(500, 240, 400, 50);
		label77.setFont(fuente2);
		label77.setForeground(Color.BLACK);
		bookPanel.add(label77);
				
		// Peso
		final JLabel label8 = new JLabel("Peso: ");
		label8.setBounds(400, 305, 150, 50);
		label8.setFont(fuente2);
		label8.setForeground(Color.BLACK);
		bookPanel.add(label8);
		
		// Resultado Peso
		String peso = Integer.toString(jugador.getPeso());
		final JLabel label88 = new JLabel(peso);
		label88.setBounds(500, 305, 400, 50);
		label88.setFont(fuente2);
		label88.setForeground(Color.BLACK);
		bookPanel.add(label88);
				
		// Pie Fav
		final JLabel label9 = new JLabel("Pie Fav: ");
		label9.setBounds(400, 370, 150, 50);
		label9.setFont(fuente2);
		label9.setForeground(Color.BLACK);
		bookPanel.add(label9);
		
		// Resultado Pie Fav
		//PieFav piefav1 = jugador.getPiefav();
		final JLabel label17 = new JLabel("Prueba");
		label17.setBounds(500, 370, 400, 50);
		label17.setFont(fuente2);
		label17.setForeground(Color.BLACK);
		bookPanel.add(label17);
		
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