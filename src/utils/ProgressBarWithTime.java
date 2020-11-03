package utils;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class ProgressBarWithTime extends JFrame {

	private static final long serialVersionUID = 1L;
	static JProgressBar b;

	/**
	 * @param int tiempos, cuanto menos pongas mas rapido carga la progressBar
	 */
	public static void ProgressBarWithTimeCall(int tiempoms) {

		ProgressBarWithTime VP = new ProgressBarWithTime();
		VP.setVisible(true);

		int i = 0;
		try {
			while (i <= 100) {
				// set text accoring to the level to which the bar is filled
				if (i > 30 && i < 70) {
					b.setString("wait for sometime");
				} else if (i > 70) {
					b.setString("almost finished loading");
				} else {
					b.setString("loading started");
				}
				if (i == 100) {
					VP.dispose();
				}

				// fill the menu bar
				b.setValue(i + 10);

				// delay the thread
				Thread.sleep(tiempoms);
				i += 1;
			}
		} catch (Exception e) {
		}
	}

	private ProgressBarWithTime() {

		this.setSize(300, 150);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// create a progressbar
		b = new JProgressBar();
		b.setBounds(10, 50, 260, 50);

		// set initial value
		b.setValue(0);

		b.setStringPainted(true);

		// add progressbar
		getContentPane().add(b);

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		//ProgressBarWithTimeCall(10);
		
		JFrame frame = new JFrame("MessageDialog");
	    JOptionPane pane = new JOptionPane();
	    pane.setMessage("long message...");
	    JProgressBar jProgressBar = new JProgressBar(1, 100);
	    jProgressBar.setValue(15);
	    pane.add(jProgressBar,1);
	    JDialog dialog = pane.createDialog(frame, "Information message");
	    dialog.setVisible(true);
	    dialog.dispose();
	    System.exit(0);
	}
}