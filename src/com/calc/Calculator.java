package com.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Calculator extends JFrame {
	static Calculator calc;

	 public static void main(String[] args) {
		calc = new Calculator();

		calc.setTitle("Calculator");
		calc.setSize(700, 370);
		calc.setVisible(true);
		calc.setResizable(false);
		calc.setLocation(200, 200);
		Image image = Toolkit.getDefaultToolkit().getImage("calculator.jpg");
		calc.setIconImage(image);
		calc.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void moveFront() {
		calc.toFront();
	}

	JTextField display;

	public Calculator() {

		Container content = getContentPane();
		content.setLayout(null);
		Font f = new Font("Times in new roman", Font.BOLD, 12);
		display = new JTextField(40);
		display.setText("0");
		display.setEditable(false);
		display.setFont(f);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setBounds(10, 10, 400, 30);
		content.add(display);

		MenuClass menu = new MenuClass(this);
		CalculatorPanel panel = new CalculatorPanel(display);
		panel.setBounds(5, 20, 650, 400);
		content.add(panel);

		addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				if (CalculatorPanel.getStatWindow())
					StatisticOperation.d.setVisible(false);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowDeiconified(WindowEvent e) {
				if (CalculatorPanel.getStatWindow())
					StatisticOperation.d.setVisible(true);
			}
		});
	}
}





