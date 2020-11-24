package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import database.DBManagerException;

/**
 * Ventana que permite al usuario tanto valorar la aplicacion como comentar
 * sobre ella
 *
 * @author sergiolopez
 *
 */
public class VentanaFeedback extends JFrame {

	private static final long serialVersionUID = 1L;
	JRadioButton estrella1, estrella2, estrella3, estrella4, estrella5, bSi, bNo;
	JButton botonSiguiente, botonAtras;
	ButtonGroup bgEstrellas, bgRecomendacion;
	JTextArea texto;
	JLabel lblComentario, lblValoracion, lblError, lblRecomendacion;
	JScrollPane scroll;
	int charCount = 0;
	int maxChars = 500;
	JProgressBar progressBar;
	JLabel lblChars;
	Thread hiloLetras;

	public VentanaFeedback() {

		this.setTitle("VentanaFeedback");
		this.setSize(720, 480);
		setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		estrella1 = new JRadioButton("1*");
		estrella1.setActionCommand("1");
		estrella1.setBounds(100, 50, 90, 50);
		estrella1.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		estrella2 = new JRadioButton("2 *");
		estrella2.setActionCommand("2");
		estrella2.setBounds(227, 50, 90, 50);
		estrella2.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		estrella3 = new JRadioButton("3* ");
		estrella3.setActionCommand("3");
		estrella3.setBounds(335, 50, 90, 50);
		estrella3.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		estrella4 = new JRadioButton("4 *");
		estrella4.setActionCommand("4");
		estrella4.setBounds(452, 50, 90, 50);
		estrella4.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		estrella5 = new JRadioButton("5* ");
		estrella5.setActionCommand("5");
		estrella5.setBounds(570, 50, 90, 50);
		estrella5.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));


		bgEstrellas = new ButtonGroup();
		bgEstrellas.add(estrella1);
		bgEstrellas.add(estrella2);
		bgEstrellas.add(estrella3);
		bgEstrellas.add(estrella4);
		bgEstrellas.add(estrella5);

		bSi = new JRadioButton("Si");
		bSi.setActionCommand("si");
		bSi.setBounds(335, 100, 90, 50);
		bSi.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
		bNo = new JRadioButton("No");
		bNo.setActionCommand("no");
		bNo.setBounds(452, 100, 90, 50);
		bNo.setBackground(Color.getHSBColor(0.56f, 0.4f, 0.85f));


		lblRecomendacion = new JLabel();
		lblRecomendacion.setText("¿Lo recomendarias?: ");
		lblRecomendacion.setFont(new Font("Sans Serif Bold", Font.BOLD, 14));
		lblRecomendacion.setBounds(175, 100, 200, 50);

		bgRecomendacion = new ButtonGroup();
		bgRecomendacion.add(bSi);
		bgRecomendacion.add(bNo);

		botonAtras = new JButton();
		botonAtras.setText("Atras");
		botonAtras.setBounds(10, 340, 200, 30);

		botonSiguiente = new JButton();
		botonSiguiente.setText("Enviar Opinion");
		botonSiguiente.setBounds(500, 340, 200, 30);

		lblValoracion = new JLabel();
		lblValoracion.setText("Valoracion:");
		lblValoracion.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblValoracion.setBounds(315, 25, 100, 20);

		lblComentario = new JLabel();
		lblComentario.setText("Comentario:");
		lblComentario.setFont(new Font("Sans Serif Bold", Font.BOLD, 15));
		lblComentario.setBounds(10, 130, 100, 20);

		texto = new JTextArea();
		texto.setFocusable(true);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));
		texto.setMargin(new Insets(10, 10, 10, 10));
		texto.setCaretPosition(0);
		texto.setText("");
		scroll = new JScrollPane(texto);
		scroll.setBounds(10, 150, 690, 150);

		lblError = new JLabel();
		lblError.setText("Falta valoracion o comentario.");
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setBounds(500, 320, 200, 20);

		add(estrella1);
		add(estrella2);
		add(estrella3);
		add(estrella4);
		add(estrella5);
		add(bSi);
		add(bNo);
		add(botonSiguiente);
		add(botonAtras);
		add(lblComentario);
		add(lblValoracion);
		add(scroll);
		add(lblError);
		add(lblRecomendacion);

		progressBar = new JProgressBar();
		progressBar.setBounds(20, 311, 66, 14);
		add(progressBar);
		progressBar.setMaximum(maxChars);
		progressBar.setMinimum(0);

		lblChars = new JLabel();
		lblChars.setBounds(98, 311, 90, 14);
		add(lblChars);

		hiloLetras = new Thread() {
			@Override
			public void run() {
				while (texto.isEnabled()) {
					progressBar.setValue(texto.getText().length());
					lblChars.setText(texto.getText().length() + "/500 chars");
					if (texto.getText().length() > maxChars) {
						lblChars.setForeground(Color.red);
						progressBar.setForeground(Color.red);
					} else {
						lblChars.setForeground(Color.black);
						progressBar.setForeground(new Color(163, 184, 204));
					}
				}
			}
		};
		hiloLetras.start();

		botonSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setVisible(false);
				if (!bgEstrellas.isSelected(null) && !bgRecomendacion.isSelected(null) && !texto.getText().equals("")
						&& (texto.getText().length() <= maxChars)) {
					lblError.setVisible(false);
					mainPackage.MainWikiFutbol.crearFicheroLog();
					mainPackage.MainWikiFutbol.logFeedback
							.println("Puntuacion: " + bgEstrellas.getSelection().getActionCommand() + "\n" + "Si/No: "
									+ bgRecomendacion.getSelection().getActionCommand());
					try {
						database.DBManager.registrarFeedback(utils.PropertiesMetodos.getProp1(),
								bgEstrellas.getSelection().getActionCommand(),
								bgRecomendacion.getSelection().getActionCommand(), texto.getText());
					} catch (DBManagerException e1) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
						e1.printStackTrace();
					}
					dispose(); // si??? se cierra???
					JOptionPane.showMessageDialog(null, "Mandado correctamente.", "Dar Feedback",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (bgEstrellas.isSelected(null) || bgRecomendacion.isSelected(null)
						|| texto.getText().equals("") || (texto.getText().length() > maxChars)) {
					lblError.setVisible(true);
				}
			}
		});

		botonAtras.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				hiloLetras.stop();
				/*
				 * try { hiloLetras.join(); } catch (InterruptedException e1) {
				 * mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				 * e1.printStackTrace(); }
				 */
			}
		});

		this.addWindowListener(new WindowAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				hiloLetras.stop();
				/*
				 * try { hiloLetras.join(); } catch (InterruptedException e1) {
				 * mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				 * e1.printStackTrace(); }
				 */
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaFeedback VO = new VentanaFeedback();
		VO.setVisible(true);
	}
}