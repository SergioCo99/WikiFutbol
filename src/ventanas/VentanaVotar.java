package ventanas;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import database.DBManagerException;

public class VentanaVotar extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnVotar;
	JComboBox<String> jcbDelantero, jcbCentrocampista, jcbDefensa, jcbPortero;
	JLabel lblDelantero, lblCentrocampista, lblDefensa, lblPortero;

	public VentanaVotar() {

		this.setTitle("VentanaVotar");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		lblDelantero = new JLabel();
		lblDelantero.setText("Mejor delantero: ");
		lblDelantero.setBounds(10, 50, 200, 30);

		lblCentrocampista = new JLabel();
		lblCentrocampista.setText("Mejor centrocampista: ");
		lblCentrocampista.setBounds(10, 100, 200, 30);

		lblDefensa = new JLabel();
		lblDefensa.setText("Mejor defensa: ");
		lblDefensa.setBounds(10, 150, 200, 30);

		lblPortero = new JLabel();
		lblPortero.setText("Mejor portero: ");
		lblPortero.setBounds(10, 200, 200, 30);

		// Delanteros
		try {
			String[] arrayDelantero = new String[database.DBManager.getJugadoresPorPosicion("Delantero").size()];
			for (int i = 0; i < arrayDelantero.length; i++) {
				arrayDelantero[i] = database.DBManager.getJugadoresPorPosicion("Delantero").get(i);
			}
			jcbDelantero = new JComboBox<String>(arrayDelantero);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbDelantero);
		jcbDelantero.setBounds(300, 50, 200, 30);

		// Centrocampistas
		try {
			String[] arrayCentrocampista = new String[database.DBManager.getJugadoresPorPosicion("Centrocampista")
					.size()];
			for (int i = 0; i < arrayCentrocampista.length; i++) {
				arrayCentrocampista[i] = database.DBManager.getJugadoresPorPosicion("Centrocampista").get(i);
			}
			jcbCentrocampista = new JComboBox<String>(arrayCentrocampista);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbCentrocampista);
		jcbCentrocampista.setBounds(300, 100, 200, 30);

		// Defensas
		try {
			String[] arrayDefensa = new String[database.DBManager.getJugadoresPorPosicion("Defensa").size()];
			for (int i = 0; i < arrayDefensa.length; i++) {
				arrayDefensa[i] = database.DBManager.getJugadoresPorPosicion("Defensa").get(i);
			}
			jcbDefensa = new JComboBox<String>(arrayDefensa);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbDefensa);
		jcbDefensa.setBounds(300, 150, 200, 30);

		// Porteros
		try {
			String[] arrayPortero = new String[database.DBManager.getJugadoresPorPosicion("Portero").size()];
			for (int i = 0; i < arrayPortero.length; i++) {
				arrayPortero[i] = database.DBManager.getJugadoresPorPosicion("Portero").get(i);
			}
			jcbPortero = new JComboBox<String>(arrayPortero);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbPortero);
		jcbPortero.setBounds(300, 200, 200, 30);

		btnVotar = new JButton();
		btnVotar.setText("Votar");
		btnVotar.setBounds(240, 325, 120, 30);

		add(lblDelantero);
		add(lblCentrocampista);
		add(lblDefensa);
		add(lblPortero);
		add(jcbDelantero);
		add(jcbCentrocampista);
		add(jcbDefensa);
		add(jcbPortero);
		add(btnVotar);

		btnVotar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				try {
					// id del usuario que vota,lo obtenemos mediante account.properties
					int id = database.DBManager.getIdUsuario(utils.PropertiesMetodos.getProp1());
					// id del delantero votado,lo obtenemos mediante su nombre
					String j1 = jcbDelantero.getSelectedItem().toString();
					int idj1 = database.DBManager.getIdJugador(j1);
					// id del centrocampista votado,lo obtenemos mediante su nombre
					String j2 = jcbCentrocampista.getSelectedItem().toString();
					int idj2 = database.DBManager.getIdJugador(j2);
					// id del defensa votado,lo obtenemos mediante su nombre
					String j3 = jcbDefensa.getSelectedItem().toString();
					int idj3 = database.DBManager.getIdJugador(j3);
					// id del portero votado,lo obtenemos mediante su nombre
					String j4 = jcbPortero.getSelectedItem().toString();
					int idj4 = database.DBManager.getIdJugador(j4);
					// metodo para votar
					database.DBManager.votar(id, idj1, idj2, idj3, idj4);
					// actualiza el numero de votos de cada jugador
					database.DBManager.actualizarVotos();
					// actualiza el teamoftheyear
					database.DBManager.toft();

					dispose();
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaVotar VV = new VentanaVotar();
		VV.setVisible(true);
	}
}