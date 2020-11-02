package ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Usuario;
import database.DBManager;
import database.DBManagerException;
import interfaces.IListaEquipos;
import utils.JLabelGraficoAjustado;

/**
 * Ventana principal para la visualizacion de todos los equipos disponibles
 * 
 * @author sergiolopez
 *
 */
public class VentanaPrincipal extends JFrame {

	public static boolean privilegiosAdmin(String correo) {
		try {
			if (database.DBManager.esAdmin(correo) == true) {
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
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem miAjustes, miCerrarSesion, miRecordarContrasena, miDescargaDatos, miEliminarCuenta;
	JMenu menuAdmin;
	JMenuItem miConfigurarOtraCuenta, miCambiarDatos, miMandarCorreo;
	JMenu menuOpinion;
	JMenuItem miFeedback, miEstadisticas;
	JMenu menuTeamOfTheYear;
	JMenuItem miVotar, miVerEquipo;

	// Para el listado de equipos
	private DBManager database1 = new DBManager();
	private IListaEquipos interfazLista;
	private ArrayList<Club> arrayEquipos = new ArrayList<Club>();
	private JList bookPanel = new JList();
	private static Usuario usuario;

	// Filtrado de equipos
	private JTextField txtField;
	private static VentanaPrincipal frame;

	private String equipoBuscado;
	private ButtonGroup filtro;
	private JRadioButton rdbtnNombreEquipo;
	private JRadioButton rdbtnEstadio;
	private JRadioButton rdbtnEntrenador;
	private JRadioButton rdbtnNumLigas;
	private ArrayList<Club> arrayResultado = new ArrayList<Club>();

	// Boton acceder a equipo
	private JLabel verEquipo;
	private JButton botonVerEquipo;

	public VentanaPrincipal(Usuario u) throws DBManagerException {
		// arrayEquipos = database1.getEquipos();
		arrayEquipos = database1.getClubes();
		usuario = u;
		init();
	}

	public void init() {

		this.setTitle("WikiFutbol Principal");
		this.setSize(1200, 700);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		menuBar = new JMenuBar();

		menu = new JMenu("Menu");
		miAjustes = new JMenuItem("Ajustes");
		miCerrarSesion = new JMenuItem("Cerrar sesion");
		miRecordarContrasena = new JMenuItem("Salir y recordar contrasena");
		miDescargaDatos = new JMenuItem("Descargar datos");
		miEliminarCuenta = new JMenuItem("Eliminar cuenta");
		menu.add(miAjustes);
		menu.add(miCerrarSesion);
		menu.add(miRecordarContrasena);
		menu.add(miDescargaDatos);
		menu.add(miEliminarCuenta);

		menuAdmin = new JMenu("Opciones admin");
		miConfigurarOtraCuenta = new JMenuItem("Configurar otra cuenta");
		miCambiarDatos = new JMenuItem("Cambiar Datos");
		miMandarCorreo = new JMenuItem("Mandar correo");
		menuAdmin.add(miConfigurarOtraCuenta);
		menuAdmin.add(miCambiarDatos);
		menuAdmin.add(miMandarCorreo);

		menuOpinion = new JMenu("Opinion");
		miFeedback = new JMenuItem("Dar feedback");
		miEstadisticas = new JMenuItem("Ver estadisticas de feedback");
		menuOpinion.add(miFeedback);
		menuOpinion.add(miEstadisticas);

		menuTeamOfTheYear = new JMenu("Team Of The Year");
		miVotar = new JMenuItem("Votar");
		miVerEquipo = new JMenuItem("Ver equipo");
		menuTeamOfTheYear.add(miVotar);
		menuTeamOfTheYear.add(miVerEquipo);

		menuBar.add(menu);
		menuBar.add(menuAdmin);
		menuBar.add(menuOpinion);
		menuBar.add(menuTeamOfTheYear);
		setJMenuBar(menuBar);

		menuAdmin.setVisible(false);

		if (privilegiosAdmin(utils.PropertiesMetodos.getProp1()) == true) {
			menuAdmin.setVisible(true);
		} else {
			menuAdmin.setVisible(false);
		}

		miAjustes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nuevaContrasena = JOptionPane.showInputDialog(null, "Introduce tu nueva contrase\u00f1a",
						"Cambiar contrase�a", JOptionPane.WARNING_MESSAGE);

				if ((nuevaContrasena != null) && !nuevaContrasena.equals("")) {
					try {
						database.DBManager.cambiarContrasena(utils.PropertiesMetodos.getProp1(), nuevaContrasena);
					} catch (DBManagerException e) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Actualizacion exitosa, reiniciando. . .", "Alerta",
							JOptionPane.INFORMATION_MESSAGE);
					// reinicio
					utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
					dispose();
					VentanaLogin VL = new VentanaLogin();
					VL.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Esa contrase\u00f1a no es valida / operacion cancelada.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		miCerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
				dispose();
				VentanaLogin VL = new VentanaLogin();
				VL.setVisible(true);
			}
		});

		miRecordarContrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		miDescargaDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaDescargar VD = new VentanaDescargar();
				VD.setVisible(true);
			}
		});

		miEliminarCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "Estas segur@? Es irreversible.", "Eliminar cuenta",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					try {
						database.DBManager.eliminarUsuario(utils.PropertiesMetodos.getProp1());
					} catch (DBManagerException e) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
						e.printStackTrace();
					}
					utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
					dispose();
				}
			}

		});

		miConfigurarOtraCuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaConfigurarOtraCuenta VA1 = new VentanaConfigurarOtraCuenta();
				VA1.setVisible(true);
			}
		});

		miCambiarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCambiarDatos VCD = new VentanaCambiarDatos();
				VCD.setVisible(true);
			}
		});

		miMandarCorreo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMandarCorreo VMC = new VentanaMandarCorreo();
				VMC.setVisible(true);
			}
		});

		miFeedback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaFeedback VF = new VentanaFeedback();
				VF.setVisible(true);
			}
		});

		miEstadisticas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaEstadisticas VE = new VentanaEstadisticas();
				VE.setVisible(true);
			}
		});

		miVotar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaVotar VV = new VentanaVotar();
				VV.setVisible(true);
			}
		});

		miVerEquipo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				try {
					// actualiza el numero de votos de cada jugador
					database.DBManager.actualizarVotos();
					// actualiza el teamoftheyear
					database.DBManager.toft();
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
				VentanaTeamOfTheYear VTOFT = new VentanaTeamOfTheYear();
				VTOFT.setVisible(true);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				utils.PropertiesMetodos.setProp("ejemplo@gmail.com", "12345");
			}
		});

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

		JLabel labelWikiFutbol = new JLabel("WIKIFUTBOL");
		labelWikiFutbol.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		labelWikiFutbol.setBounds(80, 20, 205, 29);
		navBarPanel.add(labelWikiFutbol);

		final JLabelGraficoAjustado lupa = new JLabelGraficoAjustado("resources/lupa.png", 20, 20);
		lupa.setLocation(870, 25);
		lupa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				equipoBuscado = txtField.getText().toLowerCase();
				arrayResultado.clear();

				if (equipoBuscado.isEmpty()) {
					for (Club a : arrayEquipos) {
						arrayResultado.add(a);
					}
					IListaEquipos.cargarLista(bookPanel, arrayResultado);
				} else {
					if (rdbtnNombreEquipo.isSelected() == true) {
						for (int i = 0; i < arrayEquipos.size(); i++) {
							if (equipoBuscado.toLowerCase().equals(arrayEquipos.get(i).getNombre().toLowerCase())) {
								arrayResultado.add(arrayEquipos.get(i));
							}
						}
						IListaEquipos.cargarLista(bookPanel, arrayResultado);
					} else if (rdbtnEstadio.isSelected() == true) {
						for (int i = 0; i < arrayEquipos.size(); i++) {
							if (equipoBuscado.toLowerCase().equals(arrayEquipos.get(i).getEstadio().toLowerCase())) {
								arrayResultado.add(arrayEquipos.get(i));
							}
						}
						IListaEquipos.cargarLista(bookPanel, arrayResultado);
					} else if (rdbtnEntrenador.isSelected() == true) {
						for (int i = 0; i < arrayEquipos.size(); i++) {
							if (equipoBuscado.toLowerCase().equals(arrayEquipos.get(i).getEntrenador().toLowerCase())) {
								arrayResultado.add(arrayEquipos.get(i));
							}
						}
						IListaEquipos.cargarLista(bookPanel, arrayResultado);
					} else if (rdbtnNumLigas.isSelected() == true) {
						for (int i = 0; i < arrayEquipos.size(); i++) {
							if (equipoBuscado.toLowerCase().equals(arrayEquipos.get(i).getPalmares().toLowerCase())) {
								arrayResultado.add(arrayEquipos.get(i));
							}
						}
						IListaEquipos.cargarLista(bookPanel, arrayResultado);
					}

					if (arrayResultado.isEmpty()) {
						JOptionPane.showMessageDialog(frame, "No se han encontrado resultados.");
					}
				}
			}
		});

		navBarPanel.add(lupa);

		txtField = new JTextField();
		txtField.setBounds(347, 20, 500, 30);
		navBarPanel.add(txtField);
		txtField.setColumns(10);

		for (Club e : arrayEquipos) {
			arrayResultado.add(e);
		}
		IListaEquipos.cargarLista(bookPanel, arrayResultado);

		// Scroll para la lista de los equipos
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

		rdbtnNombreEquipo = new JRadioButton("Nombre");
		rdbtnNombreEquipo.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnNombreEquipo.setBounds(1045, 117, 109, 23);
		rdbtnNombreEquipo.setContentAreaFilled(false);
		rdbtnNombreEquipo.setSelected(true);
		add(rdbtnNombreEquipo);

		rdbtnEstadio = new JRadioButton("Estadio");
		rdbtnEstadio.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnEstadio.setBounds(1045, 141, 109, 23);
		rdbtnEstadio.setContentAreaFilled(false);
		add(rdbtnEstadio);

		rdbtnEntrenador = new JRadioButton("Entrenador");
		rdbtnEntrenador.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnEntrenador.setBounds(1045, 165, 109, 23);
		rdbtnEntrenador.setContentAreaFilled(false);
		add(rdbtnEntrenador);

		rdbtnNumLigas = new JRadioButton("Num Ligas");
		rdbtnNumLigas.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnNumLigas.setBounds(1045, 189, 150, 23);
		rdbtnNumLigas.setContentAreaFilled(false);
		add(rdbtnNumLigas);

		filtro.add(rdbtnEntrenador);
		filtro.add(rdbtnNumLigas);
		filtro.add(rdbtnEstadio);
		filtro.add(rdbtnNombreEquipo);

		JLabel verEquipo = new JLabel("Ver equipo:");
		verEquipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		verEquipo.setBounds(1015, 400, 180, 30);
		verEquipo.setForeground(new Color(0, 0, 0));
		verEquipo.setFocusable(false);
		add(verEquipo);

		JButton botonVerEquipo = new JButton("Entrar");
		botonVerEquipo.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonVerEquipo.setBounds(1005, 440, 170, 30);
		botonVerEquipo.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		botonVerEquipo.setContentAreaFilled(false);
		botonVerEquipo.setForeground(new Color(0, 0, 0));
		botonVerEquipo.setFocusable(false);
		add(botonVerEquipo);

		botonVerEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaEquipo ve = new VentanaEquipo(arrayResultado.get(bookPanel.getSelectedIndex()), usuario);
					ve.setVisible(true);
					dispose();
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					JOptionPane.showMessageDialog(frame, "Seleccione un equipo");
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase
		utils.PropertiesMetodos.setProp("a@gmail.com", "a");

		VentanaPrincipal VP = new VentanaPrincipal(usuario);
		VP.setVisible(true);
	}
}