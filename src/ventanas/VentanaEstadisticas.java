package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import feedback.EstadisticaFeedback;
import feedback.RWException;

public class VentanaEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JTextField txt;
	JTable table;

	public VentanaEstadisticas() {

		this.setTitle("VentanaEstadisticas");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		txt = new JTextField();
		txt.setBounds(300, 50, 200, 20);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		add(txt);
		add(btn);

		// tabla
		String[] columns = { "Code", "Info" };

		Object[][] data = null;
		try {
			data = new Object[feedback.EstadisticaFeedback.ReadAndLoad().size()][2];
			for (int i = 0; i < feedback.EstadisticaFeedback.ReadAndLoad().size(); i++) {
				// primeras 2 filas (0 y 1)
				if (i <= 1) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i)) + " %";
					// fila nº 3 (2)
				} else if (i == 2) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i)) + "/5";
				}
				// Las filas, a mano (?)
				data[0][0] = "1/5:";
				data[1][0] = "Recomendarias? Si:";
				data[2][0] = "Recomendarias? No:";
			}
		} catch (RWException e1) {
			e1.printStackTrace();
		}

		table = new JTable(data, columns); // tabla usando las columnas y el data
		table.setBounds(100, 100, 400, 100);
		table.setDefaultEditor(Object.class, null);
		add(table);
		// hasta aqui tabla

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					EstadisticaFeedback.ReadAndLoad();
				} catch (RWException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaEstadisticas VE = new VentanaEstadisticas();
		VE.setVisible(true);
	}
}