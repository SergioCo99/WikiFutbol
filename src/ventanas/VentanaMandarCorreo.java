package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import database.DBManagerException;

public class VentanaMandarCorreo extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JComboBox<String> jcb;
	JCheckBox todos;

	public VentanaMandarCorreo() {

		this.setTitle("VentanaMandarCorreo");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			String[] array = new String[database.DBManager.todosLosCorreos().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.todosLosCorreos().get(i);
				jcb = new JComboBox<String>(array);
			}
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		jcb.setBounds(300, 50, 200, 30);

		todos = new JCheckBox();
		todos.setText("Todos");
		todos.setBounds(240, 150, 120, 30);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		add(jcb);
		add(btn);
		add(todos);

		todos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (todos.isSelected() == true) {
					jcb.setSelectedItem(null);
				}
			}
		});

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (todos.isSelected() == true) {
						for (int i = 0; i < database.DBManager.todosLosCorreos().size(); i++) {
							utils.Mail.SendMail(database.DBManager.todosLosCorreos().get(i), null, null);
						}
					} else if (todos.isSelected() == false) {
						utils.Mail.SendMail(jcb.getSelectedItem().toString(), null, null);
					}
				} catch (DBManagerException e1) {
					e1.printStackTrace();
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