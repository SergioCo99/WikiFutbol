package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import clases.Jugador;
import clases.Usuario;
import database.DBManager;
import database.DBManagerException;
import interfaces.IListaJugadores;
import utils.JLabelGraficoAjustado;

public class VentanaJugadores extends JFrame {

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

	// Para el listado de equipos
	private DBManager database2 = new DBManager();
	private IListaJugadores interfazLista;
	private ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
	private JList bookPanel = new JList();
	private static Usuario usuario;

	// Filtrado de equipos
	private JTextField txtField;
	private static VentanaJugadores frame;

	private String jugadorBuscado;
	private ButtonGroup filtro;
	private JRadioButton rdbtnNombreJugador;
	private JRadioButton rdbtnDorsalJugador;
	private JRadioButton rdbtnPosicionJugador;
	private ArrayList<Jugador> arrayResultado = new ArrayList<Jugador>();

	// Boton acceder a equipo
	private JLabel verEquipo;
	private JButton botonVerEquipo;

	public VentanaJugadores(Usuario u) throws DBManagerException {
		// arrayEquipos = database1.getEquipos();
		arrayJugadores = database2.getJugadores();
		usuario = u;
		init();
	}

	public void init() {

		this.setTitle("Plantilla del 'Nombre Equipo'");
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

		JLabelGraficoAjustado iconoWikiFutbol = new JLabelGraficoAjustado("resources/logo1.png", 60, 50);
		iconoWikiFutbol.setLocation(10, 13);
		navBarPanel.add(iconoWikiFutbol);

		JLabel labelWikiFutbol = new JLabel("Plantilla del 'Nombre Equipo'");
		labelWikiFutbol.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		labelWikiFutbol.setBounds(80, 20, 385, 29);
		navBarPanel.add(labelWikiFutbol);

		final JLabelGraficoAjustado lupa = new JLabelGraficoAjustado("resources/lupa.png", 20, 20);
		lupa.setLocation(1005, 25);
		lupa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				jugadorBuscado = txtField.getText().toLowerCase();
				arrayResultado.clear();

				if (jugadorBuscado.isEmpty()) {
					for (Jugador a : arrayJugadores) {
						arrayResultado.add(a);
					}
					IListaJugadores.cargarLista(bookPanel, arrayResultado);
				} else {
					if (rdbtnNombreJugador.isSelected() == true) {
						for (int i = 0; i < arrayJugadores.size(); i++) {
							if (jugadorBuscado.toLowerCase().equals(arrayJugadores.get(i).getNombre().toLowerCase())) {
								arrayResultado.add(arrayJugadores.get(i));
							}
						}
						IListaJugadores.cargarLista(bookPanel, arrayResultado);
					} else if (rdbtnDorsalJugador.isSelected() == true) {
						for (int i = 0; i < arrayJugadores.size(); i++) {
							if (jugadorBuscado.toLowerCase().equals(arrayJugadores.get(i).getDorsal())) {
								arrayResultado.add(arrayJugadores.get(i));
							}
						}
						IListaJugadores.cargarLista(bookPanel, arrayResultado);
					} else if (rdbtnPosicionJugador.isSelected() == true) {
						for (int i = 0; i < arrayJugadores.size(); i++) {
							if (jugadorBuscado.toLowerCase().equals(arrayJugadores.get(i).getPosicion())) {
								arrayResultado.add(arrayJugadores.get(i));
							}
						}
						IListaJugadores.cargarLista(bookPanel, arrayResultado);
					}

					if (arrayResultado.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "No se han encontrado jugadores.");
					}
				}
			}
		});

		navBarPanel.add(lupa);

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
				// Sin funcionalidad a�n
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

		txtField = new JTextField();
		txtField.setBounds(480, 20, 500, 30);
		navBarPanel.add(txtField);
		txtField.setColumns(10);

		for (Jugador e : arrayJugadores) {
			arrayResultado.add(e);
		}
		IListaJugadores.cargarLista(bookPanel, arrayResultado);

		// Scroll para la lista de los jugadores
		JScrollPane scroll = new JScrollPane(bookPanel);
		scroll.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scroll.setBounds(197, 85, 800, 550);
		add(scroll);

		// Filtros

		JLabel lblFiltro = new JLabel("Busqueda por:");
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFiltro.setBounds(1015, 81, 169, 24);
		add(lblFiltro);

		filtro = new ButtonGroup();

		rdbtnNombreJugador = new JRadioButton("Nombre");
		rdbtnNombreJugador.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnNombreJugador.setBounds(1045, 117, 109, 23);
		rdbtnNombreJugador.setContentAreaFilled(false);
		rdbtnNombreJugador.setSelected(true);
		add(rdbtnNombreJugador);

		rdbtnDorsalJugador = new JRadioButton("Dorsal");
		rdbtnDorsalJugador.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnDorsalJugador.setBounds(1045, 141, 109, 23);
		rdbtnDorsalJugador.setContentAreaFilled(false);
		add(rdbtnDorsalJugador);

		rdbtnPosicionJugador = new JRadioButton("Posicion");
		rdbtnPosicionJugador.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnPosicionJugador.setBounds(1045, 165, 109, 23);
		rdbtnPosicionJugador.setContentAreaFilled(false);
		add(rdbtnPosicionJugador);

		filtro.add(rdbtnNombreJugador);
		filtro.add(rdbtnDorsalJugador);
		filtro.add(rdbtnPosicionJugador);

		JLabel verJugador = new JLabel("Ver jugador:");
		verJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
		verJugador.setBounds(1015, 400, 180, 30);
		verJugador.setForeground(new Color(0, 0, 0));
		verJugador.setFocusable(false);
		add(verJugador);

		JButton botonVerJugador = new JButton("Entrar");
		botonVerJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonVerJugador.setBounds(1005, 440, 170, 30);
		botonVerJugador.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		botonVerJugador.setContentAreaFilled(false);
		botonVerJugador.setForeground(new Color(0, 0, 0));
		botonVerJugador.setFocusable(false);
		add(botonVerJugador);

		botonVerJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// A�n sin funcionalidad
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
		utils.PropertiesMetodos.setProp("a", "a");

		VentanaJugadores VP = new VentanaJugadores(usuario);
		VP.setVisible(true);
	}
}