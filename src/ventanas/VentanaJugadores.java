package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import clases.Club;
import clases.Usuario;
import database.DBManager;
import database.DBManagerException;
import interfaces.IListaJugadores;
import utils.JLabelGraficoAjustado;

/**
 * Ventana en la cual se visualiza los jugadores
 *
 * @author sergiolopez
 *
 */
public class VentanaJugadores extends JFrame {

	private static final long serialVersionUID = 1L;

	// Para el listado de equipos
	private String nombreEquipo;
	private JList<String> bookPanel = new JList<String>();

	private static VentanaJugadores frame;

	private ArrayList<String> arrayResultado = new ArrayList<String>();

	public VentanaJugadores(List<String> arrayJugadores, Club club, Usuario u) throws DBManagerException {
		Usuario usuario = u;
		nombreEquipo = club.getNombre();
		arrayJugadores = DBManager.getJugadoresPorEquipo(nombreEquipo);
		this.setTitle("Plantilla del " + nombreEquipo);
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

		JLabel labelWikiFutbol = new JLabel("Plantilla del " + nombreEquipo);
		labelWikiFutbol.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		labelWikiFutbol.setBounds(80, 20, 385, 29);
		navBarPanel.add(labelWikiFutbol);

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

		for (String e : arrayJugadores) {
			arrayResultado.add(e);
		}

		IListaJugadores.cargarLista(bookPanel, arrayResultado);

		// Scroll para la lista de los jugadores
		JScrollPane scroll = new JScrollPane(bookPanel);
		scroll.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		scroll.setBounds(197, 85, 800, 550);
		add(scroll);

		JLabel verJugador = new JLabel("Ver jugador:");
		verJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
		verJugador.setBounds(1015, 300, 180, 30);
		verJugador.setForeground(new Color(0, 0, 0));
		verJugador.setFocusable(false);
		add(verJugador);

		JButton botonVerJugador = new JButton("Entrar");
		botonVerJugador.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonVerJugador.setBounds(1005, 340, 170, 30);
		botonVerJugador.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		botonVerJugador.setContentAreaFilled(false);
		botonVerJugador.setForeground(new Color(0, 0, 0));
		botonVerJugador.setFocusable(false);
		add(botonVerJugador);

		botonVerJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaJugador ve = new VentanaJugador(arrayResultado.get(bookPanel.getSelectedIndex()), club,
							usuario);
					ve.setVisible(true);
					dispose();
				} catch (Exception e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					JOptionPane.showMessageDialog(frame, "Seleccione un jugador");
				}
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