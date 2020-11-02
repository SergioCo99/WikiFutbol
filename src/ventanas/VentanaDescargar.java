package ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import database.AdvancedDb2CsvExporter;

/**
 * Ventana lo cual permite descargar los datos deseados
 * 
 * @author sergiolopez
 *
 */
public class VentanaDescargar extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btnDescargar;
	JList<Object> listaTablas;
	DefaultListModel<Object> listModel;
	JComboBox<String> tablas;

	JCheckBox cbBaseDeDatos, cbClases;
	ButtonGroup bg1;

	JScrollPane scroll;

	JLabel lblOpciones;

	public VentanaDescargar() {

		this.setTitle("VentanaDescargar");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		btnDescargar = new JButton();
		btnDescargar.setText("Descargar");
		btnDescargar.setBounds(240, 250, 120, 30);

		lblOpciones = new JLabel();
		lblOpciones.setText("Opcion para descargar: ");
		lblOpciones.setBounds(250, 10, 200, 20);

		bg1 = new ButtonGroup();
		cbBaseDeDatos = new JCheckBox("Base de Datos (con claves externas).");
		cbBaseDeDatos.setBounds(260, 40, 250, 20);
		cbClases = new JCheckBox("Clases (mas legible).");
		cbClases.setBounds(260, 70, 250, 20);
		bg1.add(cbBaseDeDatos);
		bg1.add(cbClases);

		// create list para usuarios
		listaTablas = new JList<Object>();
		scroll = new JScrollPane(listaTablas);
		scroll.setBounds(10, 10, 200, 200);

		try {
			Properties prop = utils.PropertiesMetodos.loadPropertiesFile();

			String[] tablasProhibidas = null; // no dejamos descargar estas tablas por seguridad
			// tablasProhibidas = { "usuario" }; // se escirben en el jdbc.properties
			tablasProhibidas = prop.getProperty("DB.TABLASEXCLUIDAS").split(",");

			listModel = new DefaultListModel<Object>();
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
		} catch (Exception e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		// fin de list

		getContentPane().add(btnDescargar);
		getContentPane().add(lblOpciones);
		getContentPane().add(scroll);
		getContentPane().add(cbBaseDeDatos);
		getContentPane().add(cbClases);

		btnDescargar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((listaTablas.isSelectionEmpty() == false) && (bg1.getSelection() != null)) {
					try {
						if (cbBaseDeDatos.isSelected()) {
							AdvancedDb2CsvExporter.export(listaTablas.getSelectedValue().toString());
						} else if (cbClases.isSelected()) {
							AdvancedDb2CsvExporter.classesExport(listaTablas.getSelectedValue().toString());
						}
						JOptionPane.showMessageDialog(null, "Se ha descargado correctamente.");
					} catch (Exception e1) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
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