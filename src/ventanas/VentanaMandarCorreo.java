package ventanas;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import database.DBManagerException;
import utils.JLabelGraficoAjustado;

public class VentanaMandarCorreo extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnEnviar, btnBorrarArchivo;
	JComboBox<String> jcb;
	JCheckBox todos;
	JLabel lblDestinagario, lblAsunto, lblArchivo;
	JTextField txtAsunto;
	JTextArea texto;
	JScrollPane scroll;

	String path = "", nombreDeArchivo = "";

	JLabelGraficoAjustado adjuntar;

	public VentanaMandarCorreo() {

		this.setTitle("VentanaMandarCorreo");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		lblDestinagario = new JLabel("Destinatario: ");
		lblDestinagario.setBounds(10, 30, 120, 30);

		try {
			String[] array = new String[database.DBManager.todosLosCorreos().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.todosLosCorreos().get(i);
			}
			jcb = new JComboBox<String>(array);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcb);
		jcb.setBounds(300, 30, 200, 30);

		todos = new JCheckBox();
		todos.setText("Todos");
		todos.setBounds(150, 30, 120, 30);

		btnEnviar = new JButton();
		btnEnviar.setText("Enviar");
		btnEnviar.setBounds(450, 325, 120, 30);

		adjuntar = new JLabelGraficoAjustado("resources/clip.png", 30, 30);
		adjuntar.setLocation(60, 325);

		btnBorrarArchivo = new JButton();
		btnBorrarArchivo.setText("X");
		btnBorrarArchivo.setForeground(Color.red);
		btnBorrarArchivo.setBackground(Color.white);
		btnBorrarArchivo.setBounds(10, 325, 45, 30);

		txtAsunto = new JTextField();
		txtAsunto.setBounds(10, 100, 560, 30);

		lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 75, 100, 30);

		lblArchivo = new JLabel("Archivo: Ninguno.");
		lblArchivo.setBounds(100, 325, 340, 30);

		texto = new JTextArea();
		texto.setFocusable(true);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setMargin(new Insets(10, 10, 10, 10));
		texto.setCaretPosition(0);
		scroll = new JScrollPane(texto);
		scroll.setBounds(10, 150, 560, 150);

		getContentPane().add(lblDestinagario);
		getContentPane().add(jcb);
		getContentPane().add(btnEnviar);
		getContentPane().add(txtAsunto);
		getContentPane().add(lblAsunto);
		getContentPane().add(lblArchivo);
		getContentPane().add(todos);
		getContentPane().add(scroll);
		getContentPane().add(adjuntar);
		getContentPane().add(btnBorrarArchivo);

		adjuntar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				path = utils.FileChooser.Choose();

				if (path.equals("")) {
					lblArchivo.setText("Archivo: Ninguno.");
				} else if (!path.equals("")) {
					nombreDeArchivo = path.substring(path.lastIndexOf("\\"), path.length());
					lblArchivo.setText("Archivo: " + nombreDeArchivo);

				}
			}
		});

		btnBorrarArchivo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lblArchivo.setText("Archivo: Ninguno.");
				path = "";
			}
		});

		todos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (todos.isSelected() == true) {
					jcb.setSelectedIndex(0);
				}
			}
		});

		// action listener para mandar correo SIN archivo
		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String destinatario = null;

				if (path.equals("")) {
					if (todos.isSelected() == true) {
						destinatario = "Todos";
					} else if (todos.isSelected() == false) {
						destinatario = jcb.getSelectedItem().toString();
					}

					if (!txtAsunto.getText().equals("") && !texto.getText().equals("")) {
						int result = JOptionPane.showConfirmDialog(null,
								"Quieres mandar este correo a: " + destinatario + " ?", "Confirmar envio",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							try {
								if (todos.isSelected() == true) {
									for (int i = 0; i < database.DBManager.todosLosCorreos().size(); i++) {
										utils.MailSinFichero.SendMail(database.DBManager.todosLosCorreos().get(i),
												txtAsunto.getText(), texto.getText());
									}
								} else if (todos.isSelected() == false) {
									utils.MailSinFichero.SendMail(jcb.getSelectedItem().toString(), txtAsunto.getText(),
											texto.getText());
								}
							} catch (DBManagerException e1) {
								mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Rellena todo correctamente.", "Mandar correo",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		// action listener para mandar correo CON archivo
		btnEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String destinatario = null;

				if (!path.equals("")) {
					if (todos.isSelected() == true) {
						destinatario = "Todos";
					} else if (todos.isSelected() == false) {
						destinatario = jcb.getSelectedItem().toString();
					}

					if (!txtAsunto.getText().equals("") && !texto.getText().equals("")) {
						int result = JOptionPane.showConfirmDialog(null,
								"Quieres mandar este correo a: " + destinatario + " ?", "Confirmar envio",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (result == JOptionPane.YES_OPTION) {
							try {
								if (todos.isSelected() == true) {
									for (int i = 0; i < database.DBManager.todosLosCorreos().size(); i++) {
										utils.MailConFichero.m2(database.DBManager.todosLosCorreos().get(i),
												txtAsunto.getText(), texto.getText(), path);
									}
								} else if (todos.isSelected() == false) {
									utils.MailConFichero.m2(jcb.getSelectedItem().toString(), txtAsunto.getText(),
											texto.getText(), path);
								}
							} catch (DBManagerException e1) {
								mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Rellena todo correctamente.", "Mandar correo",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaMandarCorreo VMC = new VentanaMandarCorreo();
		VMC.setVisible(true);
	}
}