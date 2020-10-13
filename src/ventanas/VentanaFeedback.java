package ventanas;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	JRadioButton estrella1, estrella2, estrella3, estrella4, estrella5;
	JButton botonSiguiente, botonAtras;
	ButtonGroup radioButtonsEstrellas;
	JTextArea texto;
	JLabel comentario, valoracion, error;
	JScrollPane scroll;
	int charCount = 0;
	JProgressBar progressBar;
	JLabel lblChars;
	Thread update;

	public VentanaFeedback() {
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

		radioButtonsEstrellas = new ButtonGroup();
		radioButtonsEstrellas.add(estrella1);
		radioButtonsEstrellas.add(estrella2);
		radioButtonsEstrellas.add(estrella3);
		radioButtonsEstrellas.add(estrella4);
		radioButtonsEstrellas.add(estrella5);

		botonAtras = new JButton();
		botonAtras.setText("Atrás");
		botonAtras.setBounds(10, 340, 200, 30);

		botonSiguiente = new JButton();
		botonSiguiente.setText("Enviar Opinion");
		botonSiguiente.setBounds(500, 340, 200, 30);

		valoracion = new JLabel();
		valoracion.setText("Valoración:");
		valoracion.setBounds(10, 10, 100, 20);

		comentario = new JLabel();
		comentario.setText("Comentario:");
		comentario.setBounds(10, 130, 100, 20);

		texto = new JTextArea();
		texto.setFocusable(true);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setMargin(new Insets(10, 10, 10, 10));
		texto.setCaretPosition(0);
		scroll = new JScrollPane(texto);
		scroll.setBounds(10, 150, 690, 150);
		add(scroll);

		error = new JLabel();
		error.setText("Falta valoracion o comentario.");
		error.setForeground(Color.red);
		error.setVisible(false);
		error.setBounds(500, 320, 200, 20);

		add(estrella1);
		add(estrella2);
		add(estrella3);
		add(estrella4);
		add(estrella5);
		add(botonSiguiente);
		add(botonAtras);
		add(comentario);
		add(valoracion);
		add(scroll);
		add(error);

		progressBar = new JProgressBar();
		progressBar.setBounds(20, 311, 66, 14);
		add(progressBar);
		progressBar.setMaximum(500);
		progressBar.setMinimum(0);
		progressBar.setValue(0);

		lblChars = new JLabel("500 chars");
		lblChars.setBounds(98, 311, 66, 14);
		add(lblChars);
		botonSiguiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!radioButtonsEstrellas.isSelected(null) && !texto.getText().equals("")
						&& texto.getText().length() <= 500) {
					error.setVisible(false);
					System.out.println("100% OK" + texto.getText().length());
					String s = radioButtonsEstrellas.getSelection().getActionCommand();
					// mainPackage.MainWikiFutbol.log.print();
				} else if (radioButtonsEstrellas.isSelected(null) || texto.getText().equals("")) {
					error.setVisible(true);
				}
			}
		});

		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaFeedback VO = new VentanaFeedback();
		VO.setVisible(true);
	}
}