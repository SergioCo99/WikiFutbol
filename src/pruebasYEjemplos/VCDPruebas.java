package pruebasYEjemplos;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import database.DBManagerException;

public class VCDPruebas extends JFrame {

	// ojo que esta ventana no hace nada todavia, habia pensado en meter una JTable
	// o usar el textArea como si fuese una consola para que los admins lo usaran
	// para hacer cosnultas SQL a la BD

	private static final long serialVersionUID = 1L;
	JButton btnActualizar, buscarTabla;
	JTextArea textArea1;

	JComboBox<String> jcbTablas;

	JTable jt;
	DefaultTableModel model;
	Object data[][] = null;
	Object[] objects = null;
	JScrollPane sp;

	public VCDPruebas() {

		this.setTitle("VentanaCambiarDatos");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		btnActualizar = new JButton();
		btnActualizar.setText("Actualizar");
		btnActualizar.setBounds(240, 300, 120, 30);

		buscarTabla = new JButton();
		buscarTabla.setText("Buscar tabla");
		buscarTabla.setBounds(10, 300, 120, 30);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 175, 560, 100);

		try {
			String[] array = new String[database.DBManager.verTablas().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.verTablas().get(i);
			}
			jcbTablas = new JComboBox<String>(array);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbTablas);
		jcbTablas.setBounds(400, 300, 150, 30);
		jcbTablas.setSelectedIndex(0);

		add(btnActualizar);
		add(buscarTabla);
		add(textArea1);
		add(jcbTablas);

		btnActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					database.DBManager.cambiarDatos(textArea1.getText());
				} catch (DBManagerException e1) {
					// Can not issue empty query.

					// You have an error in your SQL syntax; check the manual that corresponds to
					// your MySQL server version for the right syntax to use

					// Can not issue SELECT via Update
				}
			}
		});

		// tabla

		String tabla = jcbTablas.getSelectedItem().toString(); // !!!
		try {
			for (int i = 1; i < (database.DBManager.verColumnas(tabla).size() + 1); i++) {
				objects = database.DBManager.verColumnas(tabla).toArray();
			}
			data = database.DBManager.data(tabla);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}

		jt = new JTable(data, objects);

		jt.setCellSelectionEnabled(true);
		// ListSelectionModel select = jt.getSelectionModel(); // Que hace???
		// select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Que hace???

		sp = new JScrollPane(jt);
		sp.setBounds(10, 10, 560, 150);
		add(sp);

		btnActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tabla = jcbTablas.getSelectedItem().toString(); // !!!
				try {
					for (int i = 1; i < (database.DBManager.verColumnas(tabla).size() + 1); i++) {
						objects = database.DBManager.verColumnas(tabla).toArray();
					}
					data = database.DBManager.data(tabla);
				} catch (DBManagerException e1) {
					e1.printStackTrace();
				}
				jt.setModel(new DefaultTableModel(data, objects));
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VCDPruebas VCD = new VCDPruebas();
		VCD.setVisible(true);
	}
}