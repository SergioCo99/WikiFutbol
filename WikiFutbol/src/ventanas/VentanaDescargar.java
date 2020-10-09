package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import database.AdvancedDb2CsvExporter;

public class VentanaDescargar extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JList<Object> b;

	public VentanaDescargar() {

		this.setTitle("VentanaAdmin1");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		// String array to store weekdays
		String tablas[] = { "usuario", "jugador" };

		// create list
		b = new JList<Object>(tablas);
		b.setBounds(10, 10, 200, 200);

		add(btn);
		add(b);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn");

				database.AdvancedDb2CsvExporter exporter = new AdvancedDb2CsvExporter();
				exporter.export(b.getSelectedValue().toString());
			}
		});

	}

	public static void main(String[] args) {
		VentanaDescargar VD = new VentanaDescargar();
		VD.setVisible(true);
	}
}