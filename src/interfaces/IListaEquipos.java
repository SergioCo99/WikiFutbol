package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import clases.Club;

/**
 * Interfaz IListaEquipos
 *
 * @author sergi
 *
 */
public interface IListaEquipos {

	/**
	 * Sirve para mostrar la lista de equipos en la VentanaPrincipal
	 *
	 * @param bookPanel Ubicación de la lista de Equipos
	 * @param arrayClub ArrayList de los clubes
	 */
	public static void cargarLista(JList<String> bookPanel, List<Club> arrayClub) {

		List<String> list = new ArrayList<String>();
		for (Club e : arrayClub) {
			list.add(e.getNombre());
		}
		utils.MetodosRecursivos.mergeSortList(list);

		DefaultListModel<String> modelo = new DefaultListModel<String>();
		for (String s : list) {
			modelo.addElement(s);
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
