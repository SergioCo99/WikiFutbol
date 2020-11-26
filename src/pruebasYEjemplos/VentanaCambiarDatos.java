package pruebasYEjemplos;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Level;

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

/**
 * Ventana la cual permite al administrador cambiar datos de las tablas
 *
 * @author sergiolopez
 *
 */
public class VentanaCambiarDatos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnCambiarDato, buscarTabla, btnQuery;
	private JTextArea textArea1;

	private JComboBox<String> jcbTablas;

	private JTable jt;
	private Object data[][] = null;
	private Object[] objects = null;
	private JScrollPane sp;

	private JLabel lblInfo, lblValor;

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

		btnQuery = new JButton();
		btnQuery.setText("Q");
		btnQuery.setBounds(370, 300, 70, 30);

		textArea1 = new JTextArea();
		textArea1.setBounds(10, 210, 560, 80);

		lblValor = new JLabel();
		lblValor.setText("Valor: ");
		lblValor.setBounds(10, 155, 560, 30);

		lblInfo = new JLabel();
		lblInfo.setText("Selecciona la celda que quieres cambiar, introduce su nuevo valor aqui y clicka "
				+ btnCambiarDato.getText() + ": ");
		lblInfo.setBounds(10, 185, 560, 30);

		try {
			String[] array = new String[database.DBManager.verTablas().size()];
			for (int i = 0; i < array.length; i++) {
				array[i] = database.DBManager.verTablas().get(i);
			}
			jcbTablas = new JComboBox<String>(array);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbTablas);
		jcbTablas.setBounds(10, 300, 140, 30);
		jcbTablas.setSelectedIndex(0);

		getContentPane().add(btnCambiarDato);
		getContentPane().add(buscarTabla);
		getContentPane().add(btnQuery);
		getContentPane().add(lblInfo);
		getContentPane().add(lblValor);
		getContentPane().add(textArea1);
		getContentPane().add(jcbTablas);

		// tabla
		String tabla = jcbTablas.getSelectedItem().toString(); // !!!
		try {
			for (int i = 1; i < (database.DBManager.verColumnas(tabla).size() + 1); i++) {
				objects = database.DBManager.verColumnas(tabla).toArray();
			}
			data = database.DBManager.data(tabla);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		jt = new JTable(data, objects) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		jt.setCellSelectionEnabled(true);

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
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}
				jt.setModel(new DefaultTableModel(data, objects));
			}
		});

		btnCambiarDato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textArea1.getText().equals(null) && !textArea1.getText().equals("")
						&& (jt.getSelectionModel().isSelectionEmpty() == false)) {
					try {
						String tabla = jcbTablas.getSelectedItem().toString();
						String columna = jt.getColumnName(jt.getSelectedColumn());
						/*
						 * Object valor = jt.getValueAt(jt.getSelectedRow(),
						 * jt.getSelectedColumn()).toString();
						 */
						Object valor = textArea1.getText();
						int id = Integer.parseInt((String) jt.getValueAt(jt.getSelectedRow(), 0));
						database.DBManager.cambiarDatosDesdeJTable(tabla, columna, valor, id);

						JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (DBManagerException e1) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
						// Can not issue empty query.

						// You have an error in your SQL syntax; check the manual that corresponds to
						// your MySQL server version for the right syntax to use

						// Can not issue SELECT via Update
						JOptionPane.showMessageDialog(null, "No se acepta el valor introducido.", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se acepta el valor introducido.", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// meter un query y tal cual y que se ejecute
					database.DBManager.cambiarDatos(textArea1.getText());

					JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Informacion",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					// Can not issue empty query.

					// You have an error in your SQL syntax; check the manual that corresponds to
					// your MySQL server version for the right syntax to use

					// Can not issue SELECT via Update
					JOptionPane.showMessageDialog(null, "No se acepta el valor introducido.", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) { // Doble click
					int fila = jt.rowAtPoint(e.getPoint());
					int columna = jt.columnAtPoint(e.getPoint());
					Object o = jt.getValueAt(fila, columna).toString();
					JOptionPane.showMessageDialog(null, o);
				}
			}
		});

		jt.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int fila = jt.rowAtPoint(e.getPoint());
				int columna = jt.columnAtPoint(e.getPoint());
				if (jt.getValueAt(fila, columna).toString().isEmpty() == false) {
					lblValor.setText("Valor: " + jt.getModel().getValueAt(fila, columna));
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