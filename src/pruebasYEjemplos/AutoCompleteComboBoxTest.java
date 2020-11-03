package pruebasYEjemplos;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class AutoCompleteComboBoxTest extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox comboBox;

	public AutoCompleteComboBoxTest() {
		setTitle("AutoCompleteComboBox");
		String[] countries = new String[] { "india", "australia", "newzealand", "england", "germany", "france",
				"ireland", "southafrica", "bangladesh", "holland", "america" };
		comboBox = new AutoCompleteComboBox(countries);
		add(comboBox, BorderLayout.NORTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AutoCompleteComboBoxTest();
	}
}

// Implementtion of AutoCompleteComboBox
class AutoCompleteComboBox extends JComboBox {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public int caretPos = 0;
	public JTextField tfield = null;

	public AutoCompleteComboBox(final Object countries[]) {
		super(countries);
		setEditor(new BasicComboBoxEditor());
		setEditable(true);
	}

	@Override
	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
		tfield.setText(getItemAt(index).toString());
		tfield.setSelectionEnd(caretPos + tfield.getText().length());
		tfield.moveCaretPosition(caretPos);
	}

	@Override
	public void setEditor(ComboBoxEditor editor) {
		super.setEditor(editor);
		if (editor.getEditorComponent() instanceof JTextField) {
			tfield = (JTextField) editor.getEditorComponent();
			tfield.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent ke) {
					char key = ke.getKeyChar();
					if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key))) {
						return;
					}
					caretPos = tfield.getCaretPosition();
					String text = "";
					try {
						text = tfield.getText(0, caretPos);
					} catch (javax.swing.text.BadLocationException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < getItemCount(); i++) {
						String element = (String) getItemAt(i);
						if (element.startsWith(text)) {
							setSelectedIndex(i);
							return;
						}
					}
				}
			});
		}
	}
}