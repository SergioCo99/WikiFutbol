package ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
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

	public static String[] prepararToft() {
		hiloToft = new Thread() {
			@Override
			public void run() {
				try {
					equipoDelAno = new String[database.DBManager.toft().size()];
					for (int i = 0; i < equipoDelAno.length; i++) {
						equipoDelAno[i] = database.DBManager.toftNombres().get(i);
					}
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloToft.start();
		return equipoDelAno;
	}

	public static List<String> prepararTodosLosCorreos() {
		hiloCorreos = new Thread() {
			@Override
			public void run() {
				try {
					listaCorreos = DBManager.todosLosCorreos();
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloCorreos.start();
		return listaCorreos;
	}

	public static List<String> prepararVentanaDescargarDatos() {
		hiloTablas = new Thread() {
			@Override
			public void run() {
				try {
					tablas = DBManager.verTablas();
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloTablas.start();
		return tablas;
	}

	public static void prepararVentanaVotar() {
		hiloDelantero = new Thread() {
			@Override
			public void run() {
				try {
					arrayDelantero = new String[database.DBManager.getJugadoresPorPosicion("Delantero").size()];
					for (int i = 0; i < arrayDelantero.length; i++) {
						arrayDelantero[i] = database.DBManager.getJugadoresPorPosicion("Delantero").get(i);
					}
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}

			}
		};
		hiloCentrocampista = new Thread() {
			@Override
			public void run() {
				try {
					arrayCentrocampista = new String[database.DBManager.getJugadoresPorPosicion("Centrocampista")
							.size()];
					for (int i = 0; i < arrayCentrocampista.length; i++) {
						arrayCentrocampista[i] = database.DBManager.getJugadoresPorPosicion("Centrocampista").get(i);
					}
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}

			}
		};
		hiloDefensa = new Thread() {
			@Override
			public void run() {
				try {
					arrayDefensa = new String[database.DBManager.getJugadoresPorPosicion("Defensa").size()];
					for (int i = 0; i < arrayDefensa.length; i++) {
						arrayDefensa[i] = database.DBManager.getJugadoresPorPosicion("Defensa").get(i);
					}
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}

			}
		};
		hiloPortero = new Thread() {
			@Override
			public void run() {
				try {
					arrayPortero = new String[database.DBManager.getJugadoresPorPosicion("Portero").size()];
					for (int i = 0; i < arrayPortero.length; i++) {
						arrayPortero[i] = database.DBManager.getJugadoresPorPosicion("Portero").get(i);
					}
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}

			}
		};
		hiloDelantero.start();
		hiloCentrocampista.start();
		hiloDefensa.start();
		hiloPortero.start();
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

	private List<Club> arrayEquipos = new ArrayList<Club>();
	private JList<String> bookPanel = new JList<String>();
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

	// Datos rapidos
	String ciudad;
	String anyoCreacion;
	String estadio;
	JLabel lblCiudad;
	JLabel lblAnyo;
	JLabel lblEstadio;

	static Thread hiloInit, hiloInit_2, hiloInit_3, hiloInit_4;

	static String[] arrayDelantero, arrayCentrocampista, arrayDefensa, arrayPortero;
	static Thread hiloDelantero, hiloCentrocampista, hiloDefensa, hiloPortero;

	static List<String> tablas;
	static Thread hiloTablas;

	static List<String> listaCorreos;
	static Thread hiloCorreos;

	static String[] equipoDelAno;
	static Thread hiloToft;

	public VentanaPrincipal(Usuario u) throws DBManagerException {
		hiloInit = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("_j");
					arrayEquipos = DBManager.getClubes();
					usuario = u;
					init();
				} catch (DBManagerException e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloInit.start();

		hiloInit_2 = new Thread() {
			@Override
			public void run() {
				try {
					hiloInit.join();
					System.out.println("j");
					prepararVentanaVotar();
				} catch (Exception e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloInit_2.start();

		hiloInit_3 = new Thread() {
			@Override
			public void run() {
				try {
					hiloInit_2.join();
					System.out.println("2j");
					prepararVentanaDescargarDatos();
				} catch (Exception e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloInit_3.start();

		hiloInit_4 = new Thread() {
			@Override
			public void run() {
				try {
					hiloInit_3.join();
					System.out.println("3j");
					prepararVentanaDescargarDatos();
				} catch (Exception e) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e.printStackTrace();
				}
			}
		};
		hiloInit_4.start();

		try {
			hiloInit_4.join();
			System.out.println("4j");
			prepararToft();
		} catch (InterruptedException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
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
				String contrasenaActual = JOptionPane.showInputDialog(null, "Introduce tu contraseña actual",
						"Cambiar contraseña", JOptionPane.WARNING_MESSAGE);
				if (/* !contrasenaActual.equals(null) */ contrasenaActual != null) {
					if (contrasenaActual.equals(utils.PropertiesMetodos.getProp2())) {
						String nuevaContrasena = JOptionPane.showInputDialog(null, "Introduce tu nueva contraseña",
								"Cambiar contraseña", JOptionPane.WARNING_MESSAGE);
						if ((nuevaContrasena != null) && !nuevaContrasena.equals("")) {
							try {
								database.DBManager.cambiarContrasena(utils.PropertiesMetodos.getProp1(),
										nuevaContrasena);
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
							JOptionPane.showMessageDialog(null, "Esa contraseña no es valida / operacion cancelada.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Esa contraseña no es valida / operacion cancelada.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Esa contraseña no es valida / operacion cancelada.", "Error",
							JOptionPane.ERROR_MESSAGE);
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
				try {
					hiloTablas.join();
					VentanaDescargar VD = new VentanaDescargar(tablas);
					VD.setVisible(true);
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e1.printStackTrace();
				}
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
				try {
					hiloCorreos.join();
					VentanaConfigurarOtraCuenta VA1 = new VentanaConfigurarOtraCuenta(listaCorreos);
					VA1.setVisible(true);
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e1.printStackTrace();
				}
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
				try {
					hiloCorreos.join();
					VentanaMandarCorreo VMC = new VentanaMandarCorreo(listaCorreos);
					VMC.setVisible(true);
				} catch (InterruptedException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
					e1.printStackTrace();
				}
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

				// prepararVentanaVotar();

				Thread hiloVentana = new Thread() {
					@Override
					public void run() {
						try {
							hiloDelantero.join();
							hiloCentrocampista.join();
							hiloDefensa.join();
							hiloPortero.join();
							setCursor(new Cursor(Cursor.WAIT_CURSOR));
							VentanaVotar VV = new VentanaVotar(arrayDelantero, arrayCentrocampista, arrayDefensa,
									arrayPortero);
							VV.setVisible(true);
							setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						} catch (InterruptedException e) {
							mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
							e.printStackTrace();
						}
					}
				};
				hiloVentana.start();

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
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}

				try {
					hiloInit_4.join();
					VentanaTeamOfTheYear VTOFT = new VentanaTeamOfTheYear(equipoDelAno);
					VTOFT.setVisible(true);
				} catch (InterruptedException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}

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

		JLabel lblDatos = new JLabel("Datos rápidos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDatos.setBounds(50, 117, 110, 20);
		add(lblDatos);

		JLabel grande = new JLabel("");
		grande.setBounds(5, 100, 190, 275);
		grande.setFocusable(false);
		grande.setOpaque(false);
		grande.setBorder(new LineBorder(new Color(1, 0, 0), 3, true));
		grande.setFocusable(false);
		add(grande);

		JLabel lblCiudad = new JLabel("Ciudad: ");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCiudad.setBounds(15, 155, 84, 13);
		add(lblCiudad);

		JLabel lblAnyo = new JLabel("Fundado en: ");
		lblAnyo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnyo.setBounds(15, 215, 120, 13);
		add(lblAnyo);

		JLabel lblEstadio = new JLabel("Estadio: ");
		lblEstadio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadio.setBounds(15, 275, 70, 13);
		add(lblEstadio);

		JLabel lblCiudadRes = new JLabel(ciudad);
		lblCiudadRes.setBounds(45, 185, 100, 13);
		lblCiudadRes.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblCiudadRes);

		JLabel lblAnyoRes = new JLabel(anyoCreacion);
		lblAnyoRes.setBounds(45, 240, 177, 18);
		lblAnyoRes.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblAnyoRes);

		JLabel lblEstadioRes = new JLabel(estadio);
		lblEstadioRes.setBounds(10, 305, 177, 18);
		lblEstadioRes.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblEstadioRes);

		MouseListener mouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					ciudad = arrayEquipos.get(bookPanel.getSelectedIndex()).getCiudad();
					lblCiudadRes.setText(ciudad);

					anyoCreacion = Integer.toString(arrayEquipos.get(bookPanel.getSelectedIndex()).getAnyoCreacion());
					lblAnyoRes.setText(anyoCreacion);

					estadio = arrayEquipos.get(bookPanel.getSelectedIndex()).getEstadio();
					lblEstadioRes.setText(estadio);

				}
			}
		};
		bookPanel.addMouseListener(mouseListener);

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) throws DBManagerException {
		// para entrar siempre modo admin desde esta clase
		utils.PropertiesMetodos.setProp("a@gmail.com", "a");

		VentanaPrincipal VP = new VentanaPrincipal(usuario);
		VP.setVisible(true);
	}
}