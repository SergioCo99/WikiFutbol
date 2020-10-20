package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import feedback.RWException;

public class VentanaEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JTable table;

	public VentanaEstadisticas() {

		this.setTitle("VentanaEstadisticas");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		add(btn);

		// tabla
		String[] columns = { "Code", "Info" };

		Object[][] data = null;
		try {
			data = new Object[feedback.EstadisticaFeedback.ReadAndLoad().size()][2];
			for (int i = 0; i < feedback.EstadisticaFeedback.ReadAndLoad().size(); i++) {
				// primeras 2 filas (0 y 1)
				if (i == 0) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i)) + " /5";
					// fila nº 2
				} else if (i >= 1 && i <= 2) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i)) + " %";
				} else if (i == 3) {
					// fila nº 3
					data[i][1] = String.format("%.0f", feedback.EstadisticaFeedback.ReadAndLoad().get(i));
				} else {
					// por ahora las demas
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i));
				}
				// Las filas, a mano (?)
				data[0][0] = "Valoracion sobre 5:";
				data[1][0] = "Recomendarias? Si:";
				data[2][0] = "Recomendarias? No:";
				data[3][0] = "Numero de feedbacks:"; // se escribe asi en plural???
			}
			for (int z = 0; z < data.length; z++) {
				for (int y = 0; y < columns.length; y++) {
					System.out.println(data[z][y]);
				}
			}
		} catch (RWException e1) {
			e1.printStackTrace();
		}

		table = new JTable(data, columns); // tabla usando las columnas y el data
		table.setBounds(100, 100, 400, 100);
		table.setDefaultEditor(Object.class, null);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		add(table);
		// hasta aqui tabla

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Haremos algo con esto??
				System.out.println("No hace na, Haremos algo con esto??");
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaEstadisticas VE = new VentanaEstadisticas();
		VE.setVisible(true);
	}
}