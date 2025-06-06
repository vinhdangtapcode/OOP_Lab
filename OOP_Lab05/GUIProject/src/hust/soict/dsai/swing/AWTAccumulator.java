package hust.soict.dsai.swing;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class AWTAccumulator extends Frame implements Serializable{
	private static final long serialVersionUID = 1L;
	private TextField tfInput;
	private TextField tfOutput;
	private int sum = 0; // Accumulated sum, init to 0

	// Constructor to setup the GUI components and event handlers
	public AWTAccumulator() {
		setLayout(new GridLayout(2, 2));

		add(new Label("Enter an Integer: "));

		tfInput = new TextField(10);
		add(tfInput);
		tfInput.addActionListener(new TFInputListener());

		add(new Label("The Accumulated Sum is: "));

		tfOutput = new TextField(10);
		tfOutput.setEditable(false);
		add(tfOutput);

		setTitle("AWT Accumulator");
		setSize(350, 120);
		setVisible(true);
		
		// Thêm WindowListener để đóng chương trình khi nhấn dấu X
	    addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            System.exit(0); // Thoát khỏi chương trình
	        }
	    });
	}
	
	public static void main(String[] args) {
		new AWTAccumulator();
	}

	private class TFInputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			int numberIn = Integer.parseInt(tfInput.getText());
			sum += numberIn;
			tfInput.setText("");
			tfOutput.setText(sum + "");
		}
	}
}



