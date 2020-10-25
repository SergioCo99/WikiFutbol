package pruebasYEjemplos;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.DBManagerException;

public class TableExample {

	public static void main(String[] a) {

		JFrame f = new JFrame("Table Example");

		Object data[][] = null;

		Object[] objects = null;

		// tabla
		String s = "ciudad";
		try {
			for (int i = 1; i < database.db.verColumnas(s).size() + 1; i++) {
				objects = database.DBManager.verColumnas(s).toArray();
			}
			data = database.DBManager.data(s);
		} catch (DBManagerException e1) {
			e1.printStackTrace();
		}

		final JTable jt = new JTable(data, objects);

		jt.setCellSelectionEnabled(true);
		ListSelectionModel select = jt.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		select.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String Data = null;
				int[] row = jt.getSelectedRows();
				int[] columns = jt.getSelectedColumns();
				for (int i = 0; i < row.length; i++) {
					for (int j = 0; j < columns.length; j++) {
						Data = (String) jt.getValueAt(row[i], columns[j]);
					}
				}
				System.out.println("Table element selected is: " + Data);
			}
		});

		JScrollPane sp = new JScrollPane(jt);
		f.add(sp);
		f.setSize(300, 200);
		f.setVisible(true);
	}

}