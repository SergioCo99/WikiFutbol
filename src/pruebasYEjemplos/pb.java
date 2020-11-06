package pruebasYEjemplos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//from w  w  w.  j a v a  2  s.c  o  m
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class pb {

	public static void main(String[] args) {
		new CountUpProgressBar();
	}
}

class CountUpProgressBar extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
	JLabel label = new JLabel("", JLabel.CENTER);
	Timer timer = new Timer(100, new ActionListener() {
		int counter = 1;

		@Override
		public void actionPerformed(ActionEvent ae) {
			label.setText(String.valueOf(counter) + "%");
			bar.setValue(++counter);
			if (counter > 100) {
				timer.stop();
			}
		}
	});

	CountUpProgressBar() {
		super.setLayout(new GridLayout(0, 1));
		bar.setValue(0);
		timer.start();
		this.add(bar);
		this.add(label);
		JOptionPane.showMessageDialog(null, this);
	}
}