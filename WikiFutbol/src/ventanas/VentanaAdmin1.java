package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import database.DBManagerException;

public class VentanaAdmin1 extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JTextField txt;
	JCheckBox cb1, cb2, cb3;
	ButtonGroup bg1;

	public VentanaAdmin1() {

		this.setTitle("VentanaAdmin1");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		bg1 = new ButtonGroup();
		cb1 = new JCheckBox("Hacer admin");
		cb1.setBounds(10, 30, 100, 20);
		cb2 = new JCheckBox("Quitar admin");
		cb2.setBounds(170, 30, 100, 20);
		cb3 = new JCheckBox("Borrar cuenta DEFINITIVAMENTE");
		cb3.setBounds(330, 30, 250, 20);
		bg1.add(cb1);
		bg1.add(cb2);
		bg1.add(cb3);

		txt = new JTextField();
		txt.setBounds(250, 150, 250, 40);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		add(txt);
		add(btn);
		add(cb1);
		add(cb2);
		add(cb3);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn");

				String usuario = txt.getText();

				if (!usuario.equals("a")) {
					try {
						if (cb1.isSelected() == true && cb2.isSelected() == false & cb3.isSelected() == false) {
							database.DBManager.cambiarAdmin(usuario, 1);
						} else if (cb1.isSelected() == false && cb2.isSelected() == true & cb3.isSelected() == false) {
							database.DBManager.cambiarAdmin(usuario, 0);
						} else if (cb1.isSelected() == false && cb2.isSelected() == false & cb3.isSelected() == true) {
							database.DBManager.eliminarUsuario(usuario);
						}
					} catch (DBManagerException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("usuario protegido"); // esto hay k borrarlo
				}
			}
		});

	}

	public static void main(String[] args) {
		VentanaAdmin1 VA1 = new VentanaAdmin1();
		VA1.setVisible(true);
	}
}