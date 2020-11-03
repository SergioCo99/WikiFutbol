package pruebasYEjemplos;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBarWithTime extends JFrame {

	private static final long serialVersionUID = 1L;
	static JProgressBar progress;

	/**
	 * @param int tiempos, cuanto menos pongas mas rapido carga la progressBar
	 */
	public static void ProgressBarWithTimeCall(int tiempoms) {

		ProgressBarWithTime v = new ProgressBarWithTime();
		v.setVisible(true);

		int i = 0;
		try {
			while (i <= 100) {
				// set text accoring to the level to which the bar is filled
				if ((i > 30) && (i < 70)) {
					progress.setString("wait for sometime");
				} else if (i > 70) {
					progress.setString("almost finished loading");
				} else {
					progress.setString("loading started");
				}
				if (i == 100) {
					v.dispose();
				}

				// fill the menu bar
				progress.setValue(i + 10);

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
		this.setUndecorated(true);

		// create a progressbar
		progress = new JProgressBar();
		progress.setBounds(10, 50, 260, 50);

		// set initial value
		progress.setValue(0);

		progress.setStringPainted(true);

		// add progressbar
		getContentPane().add(progress);

	}

	// este main es para pruebas, habria que quitarlo
	public static void main(String[] args) {
		pruebasYEjemplos.ProgressBarWithTime.ProgressBarWithTimeCall(10);
	}
}