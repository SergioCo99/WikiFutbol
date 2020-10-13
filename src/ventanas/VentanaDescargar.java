package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import database.AdvancedDb2CsvExporter;
import database.DBManagerException;

public class VentanaDescargar extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnDescargar;
	JList<Object> listaTablas;
	DefaultListModel<?> listModel;

	public VentanaDescargar() {

		this.setTitle("VentanaDescargar");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		btnDescargar = new JButton();
		btnDescargar.setText("Descargar");
		btnDescargar.setBounds(240, 250, 120, 30);

		// create list
		listaTablas = new JList<Object>();
		listaTablas.setBounds(10, 10, 200, 200);
		String[] tablasProhibidas = { "usuario" }; // no dejamos descargar estas tablas por seguridad
		DefaultListModel<Object> listModel = new DefaultListModel<Object>();

		try {
			for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
				String tabla = database.DBManager.verTablas().get(i);
				listModel.add(i, tabla);
			}
			for (int j = 0; j < tablasProhibidas.length; j++) {
				for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
					String tabla = database.DBManager.verTablas().get(i);
					if (tablasProhibidas[j].equals(tabla)) {
						listModel.remove(i);
					}
				}
			}
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}

		listaTablas.setModel(listModel);
		listaTablas.setBounds(10, 10, 200, 200);
		// fin de list

		add(btnDescargar);
		add(listaTablas);

		btnDescargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (listaTablas.isSelectionEmpty() == false) {
					try {
						AdvancedDb2CsvExporter.export(listaTablas.getSelectedValue().toString());
						JOptionPane.showMessageDialog(null, "Se ha descargado correctamente.");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se ha descargado nada.", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaDescargar VD = new VentanaDescargar();
		VD.setVisible(true);
	}
}