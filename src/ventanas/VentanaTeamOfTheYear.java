package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import database.DBManagerException;

public class VentanaTeamOfTheYear extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11;

	public VentanaTeamOfTheYear() {

		this.setTitle("VentanaTeamOfTheYear");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		String[] array = null;

		try {
			array = new String[database.DBManager.toft().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.toftNombres().get(i);
			}

		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}

		lbl1 = new JLabel();
		lbl1.setText(array[0]);
		lbl1.setBounds(100, 50, 200, 20);

		lbl2 = new JLabel();
		lbl2.setText(array[1]);
		lbl2.setBounds(250, 50, 200, 20);

		lbl3 = new JLabel();
		lbl3.setText(array[2]);
		lbl3.setBounds(400, 50, 200, 20);

		lbl4 = new JLabel();
		lbl4.setText(array[3]);
		lbl4.setBounds(100, 100, 200, 20);

		lbl5 = new JLabel();
		lbl5.setText(array[4]);
		lbl5.setBounds(250, 100, 200, 20);

		lbl6 = new JLabel();
		lbl6.setText(array[5]);
		lbl6.setBounds(400, 100, 200, 20);

		lbl7 = new JLabel();
		lbl7.setText(array[6]);
		lbl7.setBounds(50, 150, 200, 20);

		lbl8 = new JLabel();
		lbl8.setText(array[7]);
		lbl8.setBounds(175, 150, 200, 20);

		lbl9 = new JLabel();
		lbl9.setText(array[8]);
		lbl9.setBounds(300, 150, 200, 20);

		lbl10 = new JLabel();
		lbl10.setText(array[9]);
		lbl10.setBounds(425, 150, 200, 20);

		lbl11 = new JLabel();
		lbl11.setText(array[10]);
		lbl11.setBounds(250, 200, 200, 20);

		add(btn);
		add(lbl1);
		add(lbl2);
		add(lbl3);
		add(lbl4);
		add(lbl5);
		add(lbl6);
		add(lbl7);
		add(lbl8);
		add(lbl9);
		add(lbl10);
		add(lbl11);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn");
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaTeamOfTheYear VTOFT = new VentanaTeamOfTheYear();
		VTOFT.setVisible(true);
	}
}