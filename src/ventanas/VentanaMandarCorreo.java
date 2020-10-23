package ventanas;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VentanaMandarCorreo extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JComboBox<String> jcb;
	JCheckBox todos;
	JLabel lblDestinagario, lblAsunto;
	JTextField txtAsunto;
	JTextArea texto;
	JScrollPane scroll;

	public VentanaMandarCorreo() {

		this.setTitle("VentanaMandarCorreo");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/logo1.png"));


		lblDestinagario = new JLabel("Destinatario: ");
		lblDestinagario.setBounds(10, 30, 120, 30);

		try {
			String[] array = new String[database.DBManager.todosLosCorreos().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.todosLosCorreos().get(i);
			}
			jcb = new JComboBox<String>(array);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcb);
		jcb.setBounds(300, 30, 200, 30);

		todos = new JCheckBox();
		todos.setText("Todos");
		todos.setBounds(150, 30, 120, 30);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 325, 120, 30);

		txtAsunto = new JTextField();
		txtAsunto.setBounds(10, 100, 560, 30);

		lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 75, 100, 30);

		texto = new JTextArea();
		texto.setFocusable(true);
		texto.setLineWrap(true);
		texto.setWrapStyleWord(true);
		texto.setMargin(new Insets(10, 10, 10, 10));
		texto.setCaretPosition(0);
		scroll = new JScrollPane(texto);
		scroll.setBounds(10, 150, 560, 150);

		add(lblDestinagario);
		add(jcb);
		add(btn);
		add(txtAsunto);
		add(lblAsunto);
		add(todos);
		add(scroll);

		todos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (todos.isSelected() == true) {
					jcb.setSelectedIndex(0);
				}
			}
		});

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String destinatario = null;

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
									utils.Mail.SendMail(database.DBManager.todosLosCorreos().get(i),
											txtAsunto.getText(), texto.getText());
								}
							} else if (todos.isSelected() == false) {
								utils.Mail.SendMail(jcb.getSelectedItem().toString(), txtAsunto.getText(),
										texto.getText());
							}
						} catch (DBManagerException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Rellena todo correctamente.", "Mandar correo",
							JOptionPane.WARNING_MESSAGE);
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