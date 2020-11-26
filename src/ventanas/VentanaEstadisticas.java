package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import feedback.RWException;

/**
 * Ventana para la visualizacion de las estadisticas de la aplicaion
 *
 * @author sergiolopez
 *
 */
public class VentanaEstadisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	JTable table;
	JScrollPane sp;
	JProgressBar valoracion;

	String[] columns = { "Code", "Info" }; // ?????
	Object[][] data = null;

	public VentanaEstadisticas() {

		this.setTitle("VentanaEstadisticas");
		this.setSize(600, 400);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.getHSBColor(0.56f, 0.2f, 0.9f));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/wf.png"));

		// JLabel superior titulo
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Estadisticas");
		lblTitulo.setFont(new Font("Sans Serif Bold", Font.BOLD, 17));
		lblTitulo.setBounds(250, 40, 100, 20);
		getContentPane().add(lblTitulo);
		
		// tabla
		try {
			data = new Object[feedback.EstadisticaFeedback.ReadAndLoad().size()][2];
			for (int i = 0; i < feedback.EstadisticaFeedback.ReadAndLoad().size(); i++) {
				// primeras 2 filas (0 y 1)
				if (i == 0) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i + 1)) + " /5";
					// fila n� 2
				} else if ((i >= 1) && (i <= 2)) {
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i + 1)) + " %";
				} else if (i == 3) {
					// fila n� 3
					data[i][1] = String.format("%.0f", feedback.EstadisticaFeedback.ReadAndLoad().get(i + 1));
				} else {
					// por ahora las demas
					data[i][1] = String.format("%.2f", feedback.EstadisticaFeedback.ReadAndLoad().get(i + 1));
				}
				// Las filas, a mano (?)
				data[0][0] = "Valoracion sobre 5:";
				data[1][0] = "Recomendarias? Si:";
				data[2][0] = "Recomendarias? No:";
				data[3][0] = "Numero de feedbacks:"; // se escribe asi en plural???
			}
			for (int z = 0; z < data.length; z++) {
				for (int y = 0; y < columns.length; y++) {
					// Imprime las estadisticas en la consola
					System.out.println(data[z][y]);
				}
			}
		} catch (RWException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}
		
		table = new JTable(data, columns) { // tabla usando las columnas y la data

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		table.setCellSelectionEnabled(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);

		sp = new JScrollPane(table);
		sp.setBounds(100, 85, 400, 100);
		getContentPane().add(sp);
		// hasta aqui tabla

		// Titulo para JProgressBar
		JLabel lblProgress = new JLabel();
		lblProgress.setText("Valoracion sobre 50: ");
		lblProgress.setFont(new Font("Sans Serif Bold", Font.BOLD, 17));
		lblProgress.setBounds(230, 200, 400, 50);
		getContentPane().add(lblProgress);
		
		// JProgressBar con las valoraciones
		try {
			int nuevaValoracion = (int) Math.round(feedback.EstadisticaFeedback.ReadAndLoad().get(1) * 10);
			valoracion = new JProgressBar(0, 50);
			valoracion.setToolTipText("");
			valoracion.setStringPainted(true);
			valoracion.setBounds(100, 250, 400, 50);
			valoracion.setValue(nuevaValoracion);
			valoracion.setForeground(Color.getHSBColor(0.56f, 0.4f, 0.85f));
			valoracion.setString(Integer.toString(nuevaValoracion));
			getContentPane().add(valoracion);

		} catch (RWException e) {
			mainPackage.MainWikiFutbol.loggerGeneral.log(Level.INFO, e.toString());
			e.printStackTrace();
		}

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaEstadisticas VE = new VentanaEstadisticas();
		VE.setVisible(true);
	}
}