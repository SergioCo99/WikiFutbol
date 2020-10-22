package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import database.DBManagerException;
import utils.JLabelGraficoAjustado;

public class VentanaTeamOfTheYear extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lblCampo;

	public VentanaTeamOfTheYear() {

		this.setTitle("VentanaTeamOfTheYear");
		this.setSize(500, 765);
		this.setLayout(null);
		this.setResizable(false);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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
		lbl1.setForeground(Color.white);
		lbl1.setBounds(100, 50, 200, 20);

		lbl2 = new JLabel();
		lbl2.setText(array[1]);
		lbl2.setForeground(Color.white);
		lbl2.setBounds(250, 50, 200, 20);

		lbl3 = new JLabel();
		lbl3.setText(array[2]);
		lbl3.setForeground(Color.white);
		lbl3.setBounds(400, 50, 200, 20);

		lbl4 = new JLabel();
		lbl4.setText(array[3]);
		lbl4.setForeground(Color.white);
		lbl4.setBounds(100, 100, 200, 20);

		lbl5 = new JLabel();
		lbl5.setText(array[4]);
		lbl5.setForeground(Color.white);
		lbl5.setBounds(250, 100, 200, 20);

		lbl6 = new JLabel();
		lbl6.setText(array[5]);
		lbl6.setForeground(Color.white);
		lbl6.setBounds(400, 100, 200, 20);

		lbl7 = new JLabel();
		lbl7.setText(array[6]);
		lbl7.setForeground(Color.white);
		lbl7.setBounds(50, 150, 200, 20);

		lbl8 = new JLabel();
		lbl8.setText(array[7]);
		lbl8.setForeground(Color.white);
		lbl8.setBounds(175, 150, 200, 20);

		lbl9 = new JLabel();
		lbl9.setText(array[8]);
		lbl9.setForeground(Color.white);
		lbl9.setBounds(300, 150, 200, 20);

		lbl10 = new JLabel();
		lbl10.setText(array[9]);
		lbl10.setForeground(Color.white);
		lbl10.setBounds(425, 150, 200, 20);

		lbl11 = new JLabel();
		lbl11.setText(array[10]);
		lbl11.setForeground(Color.white);
		lbl11.setBounds(225, 680, 200, 20);

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

		// -----
		JLabelGraficoAjustado campo = new JLabelGraficoAjustado("resources/campo22.jpg", 487, 728);
		campo.setLocation(0, 0);
		add(campo);
		// -----
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaTeamOfTheYear VTOFT = new VentanaTeamOfTheYear();
		VTOFT.setVisible(true);
	}
}