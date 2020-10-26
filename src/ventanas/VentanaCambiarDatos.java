package ventanas;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import database.DBManagerException;

public class VentanaCambiarDatos extends JFrame {

	// ojo que esta ventana no hace nada todavia, habia pensado en meter una JTable
	// o usar el textArea como si fuese una consola para que los admins lo usaran
	// para hacer cosnultas SQL a la BD

	private static final long serialVersionUID = 1L;
	JButton btnCambiarDato, buscarTabla;
	JTextArea textArea1;

	JComboBox<String> jcbTablas;

	JTable jt;
	DefaultTableModel model; // no se si lo usamos, me gustaria usarlo
	Object data[][] = null;
	Object[] objects = null;
	JScrollPane sp;

	JLabel info;

	public VentanaCambiarDatos() {

		this.setTitle("VentanaCambiarDatos");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		btnCambiarDato = new JButton();
		btnCambiarDato.setText("Cambiar Datos");
		btnCambiarDato.setBounds(450, 300, 120, 30);

		buscarTabla = new JButton();
		buscarTabla.setText("Buscar tabla");
		buscarTabla.setBounds(240, 300, 120, 30);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 190, 560, 100);

		info = new JLabel();
		info.setText("Selecciona la celda que quieres cambiar, introduce su nuevo valor aqui y clicka Cambiar Datos:");
		info.setBounds(10, 165, 560, 30);

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
		jcbTablas.setBounds(10, 300, 120, 30); // jcbTablas.setBounds(400, 300, 150, 30);
		jcbTablas.setSelectedIndex(0);

		getContentPane().add(btnCambiarDato);
		getContentPane().add(buscarTabla);
		getContentPane().add(info);
		getContentPane().add(textArea1);
		getContentPane().add(jcbTablas);

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

		jt = new JTable(data, objects) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false; // TODO
			}
		};

		jt.setCellSelectionEnabled(true);

		// ListSelectionModel select = jt.getSelectionModel(); // Que hace???
		// select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Que hace???

		sp = new JScrollPane(jt);
		sp.setBounds(10, 10, 560, 150);
		getContentPane().add(sp);

		buscarTabla.addActionListener(new ActionListener() {

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

		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) { // Doble click
					int fila = jt.rowAtPoint(e.getPoint());
					int columna = jt.columnAtPoint(e.getPoint());
					if ((columna >= 0) && (columna < 7)) {
						Object o = jt.getValueAt(fila, columna).toString();
						JOptionPane.showMessageDialog(null, o);
					}
				}
			}
		});

		btnCambiarDato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// meter un query y tal cual y que se ejecute
					// database.DBManager.cambiarDatos(textArea1.getText());

					String tabla = jcbTablas.getSelectedItem().toString();
					String columna = jt.getColumnName(jt.getSelectedColumn());
					// Object valor = jt.getValueAt(jt.getSelectedRow(),
					// jt.getSelectedColumn()).toString();
					Object valor = textArea1.getText();
					int id = Integer.parseInt((String) jt.getValueAt(jt.getSelectedRow(), 0));
					database.DBManager.CambiarDatosDesdeJTable(tabla, columna, valor, id);
				} catch (DBManagerException e1) {
					// Can not issue empty query.

					// You have an error in your SQL syntax; check the manual that corresponds to
					// your MySQL server version for the right syntax to use

					// Can not issue SELECT via Update
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaCambiarDatos VCD = new VentanaCambiarDatos();
		VCD.setVisible(true);
	}
}