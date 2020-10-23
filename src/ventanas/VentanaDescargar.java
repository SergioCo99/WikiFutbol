package ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import database.AdvancedDb2CsvExporter;

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
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/logo1.png"));


		btnDescargar = new JButton();
		btnDescargar.setText("Descargar");
		btnDescargar.setBounds(240, 250, 120, 30);

		// create list para usuarios
		listaTablas = new JList<Object>();
		listaTablas.setBounds(10, 10, 200, 200);

		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();

			String[] tablasProhibidas = null; // no dejamos descargar estas tablas por seguridad
			// tablasProhibidas = { "usuario" }; // se escirben en el jdbc.properties
			tablasProhibidas = prop.getProperty("DB.TABLASEXCLUIDAS").split(",");

			DefaultListModel<Object> listModel = new DefaultListModel<Object>();
			for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
				String tabla = database.DBManager.verTablas().get(i);
				listModel.add(i, tabla);
			}
			for (int j = 0; j < tablasProhibidas.length; j++) {
				for (int i = 0; i < database.DBManager.verTablas().size(); i++) {
					String tabla = database.DBManager.verTablas().get(i);
					if (tablasProhibidas[j].equals(tabla)) {
						// listModel.remove(i);
						listModel.removeElement(tabla);
					}
				}
			}
			listaTablas.setModel(listModel);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
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