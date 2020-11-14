package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utils.JLabelGraficoAjustado;

/**
 * Ventana para la visualiozacion del equipo del anyo
 *
 * @author sergiolopez
 *
 */
public class VentanaTeamOfTheYear extends JFrame {

	private static final long serialVersionUID = 1L;
	JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11;
	JLabelGraficoAjustado campo;

	public VentanaTeamOfTheYear(String[] array) {

		this.setTitle("VentanaTeamOfTheYear");
		this.setSize(500, 765);
		getContentPane().setLayout(null);
		this.setResizable(false);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width / 2) - (this.getSize().width / 2), (dim.height / 2) - (this.getSize().height / 2));

		/*
		 * String[] array = null; try { array = new
		 * String[database.DBManager.toft().size()]; for (int i = 0; i < array.length;
		 * i++) { array[i] = database.DBManager.toftNombres().get(i); } } catch
		 * (DBManagerException e) {
		 * mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
		 * e.printStackTrace(); }
		 */

		// EI
		lbl1 = new JLabel();
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		lbl1.setVerticalAlignment(JLabel.CENTER);
		lbl1.setText(array[0].toUpperCase());
		lbl1.setForeground(Color.white);
		lbl1.setBounds(50, 190, 200, 20);
		lbl1.setFont(new Font("Arial", Font.PLAIN, 20));

		// DC
		lbl2 = new JLabel();
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		lbl2.setVerticalAlignment(JLabel.CENTER);
		lbl2.setText(array[1].toUpperCase());
		lbl2.setForeground(Color.white);
		lbl2.setBounds(150, 109, 200, 20);
		lbl2.setFont(new Font("Arial", Font.PLAIN, 20));

		// ED
		lbl3 = new JLabel();
		lbl3.setHorizontalAlignment(JLabel.CENTER);
		lbl3.setVerticalAlignment(JLabel.CENTER);
		lbl3.setText(array[2].toUpperCase());
		lbl3.setForeground(Color.white);
		lbl3.setBounds(260, 190, 200, 20);
		lbl3.setFont(new Font("Arial", Font.PLAIN, 20));

		// MI
		lbl4 = new JLabel();
		lbl4.setHorizontalAlignment(JLabel.CENTER);
		lbl4.setVerticalAlignment(JLabel.CENTER);
		lbl4.setText(array[3].toUpperCase());
		lbl4.setForeground(Color.white);
		lbl4.setBounds(50, 428, 200, 20);
		lbl4.setFont(new Font("Arial", Font.PLAIN, 20));

		// MC
		lbl5 = new JLabel();
		lbl5.setHorizontalAlignment(JLabel.CENTER);
		lbl5.setVerticalAlignment(JLabel.CENTER);
		lbl5.setText(array[4].toUpperCase());
		lbl5.setForeground(Color.white);
		lbl5.setBounds(150, 291, 200, 20);
		lbl5.setFont(new Font("Arial", Font.PLAIN, 20));

		// MD
		lbl6 = new JLabel();
		lbl6.setHorizontalAlignment(JLabel.CENTER);
		lbl6.setVerticalAlignment(JLabel.CENTER);
		lbl6.setText(array[5].toUpperCase());
		lbl6.setForeground(Color.white);
		lbl6.setBounds(260, 428, 200, 20);
		lbl6.setFont(new Font("Arial", Font.PLAIN, 20));

		// LI
		lbl7 = new JLabel();
		lbl7.setHorizontalAlignment(JLabel.CENTER);
		lbl7.setVerticalAlignment(JLabel.CENTER);
		lbl7.setText(array[6].toUpperCase());
		lbl7.setForeground(Color.white);
		lbl7.setBounds(10, 500, 200, 20);
		lbl7.setFont(new Font("Arial", Font.PLAIN, 20));

		// CT I
		lbl8 = new JLabel();
		lbl8.setHorizontalAlignment(JLabel.CENTER);
		lbl8.setVerticalAlignment(JLabel.CENTER);
		lbl8.setText(array[7].toUpperCase());
		lbl8.setForeground(Color.white);
		lbl8.setBounds(50, 563, 200, 20);
		lbl8.setFont(new Font("Arial", Font.PLAIN, 20));

		// CT D
		lbl9 = new JLabel();
		lbl9.setHorizontalAlignment(JLabel.CENTER);
		lbl9.setVerticalAlignment(JLabel.CENTER);
		lbl9.setText(array[8].toUpperCase());
		lbl9.setForeground(Color.white);
		lbl9.setBounds(260, 563, 200, 20);
		lbl9.setFont(new Font("Arial", Font.PLAIN, 20));

		// LD
		lbl10 = new JLabel();
		lbl10.setHorizontalAlignment(JLabel.CENTER);
		lbl10.setVerticalAlignment(JLabel.CENTER);
		lbl10.setText(array[9].toUpperCase());
		lbl10.setForeground(Color.white);
		lbl10.setBounds(286, 500, 200, 20);
		lbl10.setFont(new Font("Arial", Font.PLAIN, 20));

		// P
		lbl11 = new JLabel();
		lbl11.setHorizontalAlignment(JLabel.CENTER);
		lbl11.setVerticalAlignment(JLabel.CENTER);
		lbl11.setText(array[10].toUpperCase());
		lbl11.setForeground(Color.white);
		lbl11.setBounds(150, 651, 200, 20);
		lbl11.setFont(new Font("Arial", Font.PLAIN, 20));

		getContentPane().add(lbl1);
		getContentPane().add(lbl2);
		getContentPane().add(lbl3);
		getContentPane().add(lbl4);
		getContentPane().add(lbl5);
		getContentPane().add(lbl6);
		getContentPane().add(lbl7);
		getContentPane().add(lbl8);
		getContentPane().add(lbl9);
		getContentPane().add(lbl10);
		getContentPane().add(lbl11);

		// -----
		campo = new JLabelGraficoAjustado("resources/campo22.jpg", 487, 728);
		campo.setSize(486, 737);
		campo.setLocation(0, 0);
		getContentPane().add(campo);
		// -----
	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		// VentanaTeamOfTheYear VTOFT = new VentanaTeamOfTheYear();
		// VTOFT.setVisible(true);
	}
}