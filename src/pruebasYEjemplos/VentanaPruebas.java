package pruebasYEjemplos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Es una ventana con solo un boton para probar cosas nuevas, no lo incluiremos
 * en el proyecto
 */
public class VentanaPruebas extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton btn;
	JTextField txt;

	public VentanaPruebas() {

		this.setTitle("VentanaPruebas");
		this.setSize(600, 400);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txt = new JTextField();
		txt.setBounds(300, 50, 200, 20);

		btn = new JButton();
		btn.setText("btn");
		btn.setBounds(240, 250, 120, 30);

		add(txt);
		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("btn");
			}
		});

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		VentanaPruebas VP = new VentanaPruebas();
		VP.setVisible(true);
	}
}