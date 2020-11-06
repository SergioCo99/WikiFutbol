package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public interface IListaJugadores {

	/**
	 * Sirve para mostrar la lista de jugadores en la VentanaJugadores
	 *
	 * @param bookPanel Ubicación de la lista de Jugadores
	 * @param a         ArrayList de los jugadores
	 */
	public static void cargarLista(JList<String> bookPanel, ArrayList<String> a) {

		DefaultListModel<String> modelo = new DefaultListModel<String>();
		for (String j : a) {
			modelo.addElement(j);
		}
		bookPanel.setModel(modelo);
		bookPanel.updateUI();
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) bookPanel.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		bookPanel.setFixedCellHeight(40);
		bookPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookPanel.setVisibleRowCount(5);
		bookPanel.setFont(new Font("Rockwell", Font.BOLD, 20));
		bookPanel.setForeground(new Color(0, 0, 0));
		bookPanel.setBackground(Color.getHSBColor(1.42f, 0.68f, 0.75f));
	}
}
