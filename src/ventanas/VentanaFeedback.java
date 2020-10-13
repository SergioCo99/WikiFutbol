package ventanas;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	Thread update;

	public VentanaFeedback() {

		this.setTitle("VentanaFeedback");
		this.setSize(720, 480);
		setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		estrella1 = new JRadioButton("1*");
		estrella1.setActionCommand("1");
		estrella1.setBounds(100, 50, 90, 50);
		estrella2 = new JRadioButton("2 *");
		estrella2.setActionCommand("2");
		estrella2.setBounds(227, 50, 90, 50);
		estrella3 = new JRadioButton("3* ");
		estrella3.setActionCommand("3");
		estrella3.setBounds(335, 50, 90, 50);
		estrella4 = new JRadioButton("4 *");
		estrella4.setActionCommand("4");
		estrella4.setBounds(452, 50, 90, 50);
		estrella5 = new JRadioButton("5* ");
		estrella5.setActionCommand("5");
		estrella5.setBounds(570, 50, 90, 50);

		bgEstrellas = new ButtonGroup();
		bgEstrellas.add(estrella1);
		bgEstrellas.add(estrella2);
		bgEstrellas.add(estrella3);
		bgEstrellas.add(estrella4);
		bgEstrellas.add(estrella5);

		bSi = new JRadioButton("Si");
		bSi.setActionCommand("si");
		bSi.setBounds(335, 100, 90, 50);
		bNo = new JRadioButton("No");
		bNo.setActionCommand("no");
		bNo.setBounds(452, 100, 90, 50);

		lblRecomendacion = new JLabel();
		lblRecomendacion.setText("�Lo recomendarias?: ");
		lblRecomendacion.setBounds(175, 100, 200, 50);

		bgRecomendacion = new ButtonGroup();
		bgRecomendacion.add(bSi);
		bgRecomendacion.add(bNo);

		botonAtras = new JButton();
		botonAtras.setText("Atr�s");
		botonAtras.setBounds(10, 340, 200, 30);

		botonSiguiente = new JButton();
		botonSiguiente.setText("Enviar Opinion");
		botonSiguiente.setBounds(500, 340, 200, 30);

		lblValoracion = new JLabel();
		lblValoracion.setText("Valoraci�n:");
		lblValoracion.setBounds(10, 10, 100, 20);

		lblComentario = new JLabel();
		lblComentario.setText("Comentario:");
		lblComentario.setBounds(10, 130, 100, 20);

		texto = new JTextArea();
		texto.setFocusable(true);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setMargin(new Insets(10, 10, 10, 10));
		texto.setCaretPosition(0);
		scroll = new JScrollPane(texto);
		scroll.setBounds(10, 150, 690, 150);
		add(scroll);

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

		update = new Thread() {
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
		update.start();

		botonSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setVisible(false);
				if (!bgEstrellas.isSelected(null) && !bgRecomendacion.isSelected(null) && !texto.getText().equals("")
						&& texto.getText().length() <= maxChars) {
					lblError.setVisible(false);
					mainPackage.MainWikiFutbol.crearFicheroLog();
					mainPackage.MainWikiFutbol.log
							.println("Puntuacion: " + bgEstrellas.getSelection().getActionCommand() + "\n" + "Si/No: "
									+ bgRecomendacion.getSelection().getActionCommand());
					dispose(); // si??? se cierra???
				} else if (bgEstrellas.isSelected(null) || bgRecomendacion.isSelected(null)
						|| texto.getText().equals("") || texto.getText().length() > maxChars) {
					lblError.setVisible(true);
				}
			}
		});

		botonAtras.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				update.stop();
			}
		});

		this.addWindowListener(new WindowAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				update.stop();
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaFeedback VO = new VentanaFeedback();
		VO.setVisible(true);
	}
}