package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import database.AdvancedDb2CsvExporter;
import database.DBManagerException;

public class VentanaDescargar extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JList<Object> b;
	DefaultListModel<?> listModel;

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

		// create list
		b = new JList<Object>();
		b.setBounds(10, 10, 200, 200);
		String[] tablasProhibidas = { "usuario" }; // no dejamos descargar estas tablas por seguridad
		DefaultListModel<Object> listModel = new DefaultListModel<Object>();
		try {
			for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
				for (int j = 0; j < tablasProhibidas.length; j++) {
					try {
						String tabla = database.DBManager.verTablas().get(i);
						if (!tabla.equals(tablasProhibidas[j])) {
							listModel.add(i, tabla);
						}
					} catch (DBManagerException e1) {
						e1.printStackTrace();
					}
				}
			}
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		b.setModel(listModel);
		b.setBounds(10, 10, 200, 200);
		// fin de list

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