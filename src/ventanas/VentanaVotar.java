package ventanas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import database.DBManagerException;

/**
 * Ventana que da la posibilidad de votar a los usuarios de sus jugadores
 * favoritos para cada posicion
 *
 * @author sergiolopez
 *
 */
public class VentanaVotar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnVotar;
	private JComboBox<String> jcbDelantero, jcbCentrocampista, jcbDefensa, jcbPortero;
	private JLabel lblDelantero, lblCentrocampista, lblDefensa, lblPortero;

	private Thread votacion, update;

	public VentanaVotar(List<String> arrayDelantero, List<String> arrayCentrocampista, List<String> arrayDefensa,
			List<String> arrayPortero) {

		this.setTitle("VentanaVotar");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		JLabel lblVotaciones = new JLabel("Votaciones");
		lblVotaciones.setFont(new Font("Sans Serif Bold", Font.BOLD, 17));
		lblVotaciones.setBounds(240, 20, 100, 30);

		lblDelantero = new JLabel();
		lblDelantero.setText("Mejor delantero: ");
		lblDelantero.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblDelantero.setBounds(90, 100, 200, 30);

		lblCentrocampista = new JLabel();
		lblCentrocampista.setText("Mejor centrocampista: ");
		lblCentrocampista.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblCentrocampista.setBounds(90, 150, 200, 30);

		lblDefensa = new JLabel();
		lblDefensa.setText("Mejor defensa: ");
		lblDefensa.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblDefensa.setBounds(90, 200, 200, 30);

		lblPortero = new JLabel();
		lblPortero.setText("Mejor portero: ");
		lblPortero.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblPortero.setBounds(90, 250, 200, 30);

		// Delanteros
		jcbDelantero = new JComboBox<String>(arrayDelantero.toArray(new String[arrayDelantero.size()]));
		utils.JComboBoxAutoCompletion.enable(jcbDelantero);
		jcbDelantero.setBounds(300, 100, 200, 30);
		jcbDelantero.setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));

		// Centrocampistas
		jcbCentrocampista = new JComboBox<String>(arrayCentrocampista.toArray(new String[arrayCentrocampista.size()]));
		utils.JComboBoxAutoCompletion.enable(jcbCentrocampista);
		jcbCentrocampista.setBounds(300, 150, 200, 30);
		jcbCentrocampista.setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));

		// Defensas
		jcbDefensa = new JComboBox<String>(arrayDefensa.toArray(new String[arrayDefensa.size()]));
		utils.JComboBoxAutoCompletion.enable(jcbDefensa);
		jcbDefensa.setBounds(300, 200, 200, 30);
		jcbDefensa.setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));

		// Porteros
		jcbPortero = new JComboBox<String>(arrayPortero.toArray(new String[arrayPortero.size()]));
		utils.JComboBoxAutoCompletion.enable(jcbPortero);
		jcbPortero.setBounds(300, 250, 200, 30);
		jcbPortero.setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));

		btnVotar = new JButton();
		btnVotar.setText("Votar");
		btnVotar.setBounds(240, 325, 120, 30);

		add(lblVotaciones);
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
				votacion = new Thread() {
					@Override
					public void run() {
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
						} catch (DBManagerException e) {
							mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
							e.printStackTrace();
						}
					}
				};
				votacion.start();

				update = new Thread() {
					@Override
					public void run() {
						try {
							votacion.join();
							// actualiza el numero de votos de cada jugador
							database.DBManager.actualizarVotos();
							// actualiza el teamoftheyear
							database.DBManager.toft();
						} catch (DBManagerException | InterruptedException e) {
							mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
							e.printStackTrace();
						}
					}
				};
				update.start();

				dispose();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

	}

}