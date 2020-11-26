package ventanas;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
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

import database.DBManager;
import database.DBManagerException;

/**
 * Ventana la cual permite al administrador cambiar datos de las tablas
 *
 * @author sergiolopez
 *
 */
public class VentanaCambiarDatos_2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnCambiarDato, buscarTabla, btnGuardar, btnInsertarFila, btnEliminarFila;
	private JTextArea textArea1;

	private JComboBox<String> jcbTablas;

	private JTable jt;
	private DefaultTableModel model;
	private Object data[][] = null;
	private Object[] objects = null;
	private JScrollPane sp, scroll;

	private JLabel lblInfo, lblInfo2, lblInfo3, lblValor;

	private String tabla;
	private int nrows;
	private int idMasBajo;

	public void refrescarJTable() {
		tabla = jcbTablas.getSelectedItem().toString(); // !!!
		try {
			// for (int i = 1; i < (database.DBManager.verColumnas(tabla).size() + 1); i++)
			// {
			objects = database.DBManager.verColumnas(tabla).toArray();
			// }
			data = database.DBManager.data(tabla);
		} catch (DBManagerException e1) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
			e1.printStackTrace();
		}
		jt.setModel(new DefaultTableModel(data, objects));
	}

	public void eliminar() {
		tabla = jcbTablas.getSelectedItem().toString();
		Object id = jt.getValueAt(jt.getSelectedRow(), 0);
		if (!tabla.equals("teamoftheyear_view") || !tabla.equals("teamoftheyear")) {
			try {
				DBManager.borrar(tabla, id);
			} catch (DBManagerException e1) {
				mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Esta tabla no se puede cambiar.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			refrescarJTable();
		} else {
			JOptionPane.showMessageDialog(null, "Esta tabla no se puede cambiar.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public VentanaCambiarDatos_2() {

		this.setTitle("VentanaCambiarDatos");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		btnCambiarDato = new JButton();
		btnCambiarDato.setText("Cambiar Datos");
		btnCambiarDato.setBounds(300, 260, 140, 30);

		buscarTabla = new JButton();
		buscarTabla.setText("Actualizar tabla");
		buscarTabla.setBounds(10, 300, 140, 30);

		btnGuardar = new JButton();
		btnGuardar.setText("Guardar en BD");
		btnGuardar.setBounds(300, 300, 140, 30);

		btnInsertarFila = new JButton();
		btnInsertarFila.setText("Insertar fila");
		btnInsertarFila.setBounds(155, 260, 140, 30);

		btnEliminarFila = new JButton();
		btnEliminarFila.setText("Borrar");
		btnEliminarFila.setBounds(155, 300, 140, 30);

		textArea1 = new JTextArea();
		textArea1.setFocusable(true);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		textArea1.setMargin(new Insets(10, 10, 10, 10));
		textArea1.setCaretPosition(0);
		textArea1.setText("");
		scroll = new JScrollPane(textArea1);
		scroll.setBounds(445, 260, 125, 70);

		lblValor = new JLabel();
		lblValor.setText("Valor: ");
		lblValor.setBounds(10, 155, 560, 30);

		lblInfo = new JLabel();
		lblInfo.setText("* Para insertar nueva fila clicka '" + btnInsertarFila.getText()
				+ "', añade valores y clicka '" + btnGuardar.getText() + "'.");
		lblInfo.setBounds(10, 185, 560, 30);

		lblInfo2 = new JLabel();
		lblInfo2.setText(
				"* Para borrar una fila clicka una celda de la fila, y luego '" + btnEliminarFila.getText() + "'.");
		lblInfo2.setBounds(10, 205, 560, 30);

		lblInfo3 = new JLabel();
		lblInfo3.setText("* Para cambiar un dato clicka en la celda y edita el valor, luego clicka '"
				+ btnCambiarDato.getText() + "'.");
		lblInfo3.setBounds(10, 225, 560, 30);

		try {
			Object[] ObjectArray = database.DBManager.verTablas().toArray();
			String[] array = Arrays.copyOf(ObjectArray, ObjectArray.length, String[].class);
			// for (int i = 0; i < array.length; i++) {
			// array[i] = database.DBManager.verTablas().get(i);
			// }
			jcbTablas = new JComboBox<String>(array);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		utils.JComboBoxAutoCompletion.enable(jcbTablas);
		jcbTablas.setBounds(10, 260, 140, 30);
		jcbTablas.setSelectedIndex(0);

		getContentPane().add(btnCambiarDato);
		getContentPane().add(buscarTabla);
		getContentPane().add(btnGuardar);
		getContentPane().add(btnInsertarFila);
		getContentPane().add(btnEliminarFila);
		getContentPane().add(lblInfo);
		getContentPane().add(lblInfo2);
		getContentPane().add(lblInfo3);
		getContentPane().add(lblValor);
		getContentPane().add(scroll);
		getContentPane().add(jcbTablas);

		// tabla
		tabla = jcbTablas.getSelectedItem().toString(); // !!!
		try {
			// for (int i = 1; i < (database.DBManager.verColumnas(tabla).size() + 1); i++)
			// {
			objects = database.DBManager.verColumnas(tabla).toArray();
			// }
			data = database.DBManager.data(tabla);
		} catch (DBManagerException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

		jt = new JTable(data, objects) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column <= 0) {
					return false;
				} else {
					return true;
				}
			}
		};

		jt.setCellSelectionEnabled(true);

		sp = new JScrollPane(jt);
		sp.setBounds(10, 10, 560, 150);
		getContentPane().add(sp);

		buscarTabla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarJTable();
			}
		});

		btnCambiarDato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (// !textArea1.getText().equals(null) &&
					// !textArea1.getText().equals("") &&
				(jt.getSelectionModel().isSelectionEmpty() == false)) {
					try {
						tabla = jcbTablas.getSelectedItem().toString();
						String columna = jt.getColumnName(jt.getSelectedColumn());

						Object valor = jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
						// Object valor = textArea1.getText();

						int id = Integer.parseInt((String) jt.getValueAt(jt.getSelectedRow(), 0));
						database.DBManager.cambiarDatosDesdeJTable(tabla, columna, valor, id);

						JOptionPane.showMessageDialog(null, "Cambio realizado con exito.", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (DBManagerException e1) {
						mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
						JOptionPane.showMessageDialog(null, "No se acepta el valor introducido.", "Alert",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se acepta el valor introducido.", "Alert",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		jt.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int fila = jt.rowAtPoint(e.getPoint());
				int columna = jt.columnAtPoint(e.getPoint());
				if (jt.getValueAt(fila, columna) != "") {
					lblValor.setText("Valor: " + jt.getModel().getValueAt(fila, columna));
				}
			}
		});

		btnInsertarFila.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refrescarJTable();
					tabla = jcbTablas.getSelectedItem().toString();
					idMasBajo = DBManager.idMasBajoSinUsar(tabla);
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}

				model = new DefaultTableModel(data, objects);
				model.addRow(new Object[] { idMasBajo });
				jt.setModel(model);
			}
		});

		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tabla = jcbTablas.getSelectedItem().toString();
					idMasBajo = DBManager.idMasBajoSinUsar(tabla);
					nrows = idMasBajo - 1;

					if (jt.getCellEditor() != null) { // No hay celdas vacias (NULL)

						if (tabla.equals("ciudad")) {
							DBManager.nuevaCiudad(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5),
									jt.getValueAt(nrows, 6));
						} else if (tabla.equals("club")) {
							DBManager.nuevoClub(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5),
									jt.getValueAt(nrows, 6));
						} else if (tabla.equals("entrenador")) {
							DBManager.nuevoEntrenador(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5),
									jt.getValueAt(nrows, 6));
						} else if (tabla.equals("estadio")) {
							DBManager.nuevoEstadio(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4));
						} else if (tabla.equals("feedback")) {
							DBManager.nuevoFeedback(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4));
						} else if (tabla.equals("jugador")) {
							DBManager.nuevoJugador(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5),
									jt.getValueAt(nrows, 6), jt.getValueAt(nrows, 7), jt.getValueAt(nrows, 8),
									jt.getValueAt(nrows, 9), jt.getValueAt(nrows, 10), jt.getValueAt(nrows, 11),
									jt.getValueAt(nrows, 12), jt.getValueAt(nrows, 13));
						} else if (tabla.equals("pais")) {
							DBManager.nuevoPais(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4));
						} else if (tabla.equals("usuario")) {
							DBManager.nuevoUsuario(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5));
						} else if (tabla.equals("usuariovotacion")) {
							DBManager.nuevoUsuarioVotacion(idMasBajo, jt.getValueAt(nrows, 1), jt.getValueAt(nrows, 2),
									jt.getValueAt(nrows, 3), jt.getValueAt(nrows, 4), jt.getValueAt(nrows, 5));
						} else {
							JOptionPane.showMessageDialog(null, "Esta tabla no se deberia cambiar.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "No puede haber ninguna celda vacia.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (DBManagerException e1) {
					mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e1.toString());
					e1.printStackTrace();
				}
				refrescarJTable();
			}
		});

		btnEliminarFila.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar();
				// jt.clearSelection();
				System.out.println(jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn()));
			}
		});

	}

}