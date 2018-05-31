import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CalculatorGUI {
	public static final String[] OPERATORS = { "+", "-", "*", "/" };
	public static final String INITIAL_RESULT = " ";
	private String enter;
	private String result;
	private JLabel labelEnter;
	private JLabel labelResult;
	private StringCalculator sc;

	public CalculatorGUI() {
		//initialization
		enter = "0";
		result = INITIAL_RESULT;
		labelEnter = new JLabel(enter);
		labelResult = new JLabel(result);
		sc = new StringCalculator();
		
		// frame work
		JFrame frame = new JFrame("Calculator");
		frame.setLayout(new BorderLayout());

		// Enter panel (enter display)
		JPanel panelEnter = new JPanel(new BorderLayout());
		panelEnter.add(labelEnter, BorderLayout.EAST);
		frame.add(panelEnter, BorderLayout.NORTH);

		// Result panel (result panel)
		JPanel panelResult = new JPanel(new BorderLayout());
		panelResult.add(labelResult, BorderLayout.EAST);
		frame.add(panelResult, BorderLayout.CENTER);

		// Keyboard panel
		JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
		// 1st line
		buttonPanel.add(makeClearButton('C')); //clear button
		buttonPanel.add(makeDeleteButton('<')); //delete button
		JButton buttonPercent = new JButton("%"); //fake button not implemented yet
		buttonPanel.add(buttonPercent);
		buttonPanel.add(makeOperatorButton('/'));
		// 2nd line
		buttonPanel.add(makeNumberButton("7"));
		buttonPanel.add(makeNumberButton("8"));
		buttonPanel.add(makeNumberButton("9"));
		buttonPanel.add(makeOperatorButton('*'));
		// 3rd line
		buttonPanel.add(makeNumberButton("4"));
		buttonPanel.add(makeNumberButton("5"));
		buttonPanel.add(makeNumberButton("6"));
		buttonPanel.add(makeOperatorButton('-'));
		// 4th line
		buttonPanel.add(makeNumberButton("1"));
		buttonPanel.add(makeNumberButton("2"));
		buttonPanel.add(makeNumberButton("3"));
		buttonPanel.add(makeOperatorButton('+'));
		// 5th line
		buttonPanel.add(makeNumberButton("0"));
		buttonPanel.add(makeNumberButton("00"));
		JButton buttonPoint = new JButton(".");  //fake button  not implemented yet
		buttonPanel.add(buttonPoint);
		buttonPanel.add(makeEqualButton('='));

		frame.add(buttonPanel, BorderLayout.SOUTH);

		// pack and visible
		frame.pack();
		frame.setVisible(true);
	}

	private class NumberListener implements ActionListener {
		private String display;

		public void actionPerformed(ActionEvent e) {
			if (enter.equals("0"))
				enter = display;
			else
				enter += display;
			labelEnter.setText(enter);
		}

		public NumberListener(String num) {
			display = num;
		}
	}

	private class ZeroListener implements ActionListener {
		private String display;

		public void actionPerformed(ActionEvent e) {
			// special case: enter is empty or 0 - do nothing
			if (enter.isEmpty() || enter.equals("0"))
				return;
			// special case: the last of enter is an operator - do nothing
			if (lastIsOperator(enter, OPERATORS))
				return;
			// execute
			enter += display;
			labelEnter.setText(enter);
		}

		public ZeroListener(String num) {
			display = num;
		}
	}

	private class OperatorListener implements ActionListener {
		private char display;

		public void actionPerformed(ActionEvent e) {
			// special case: enter is empty or 0 - do nothing
			if (enter.isEmpty() || enter.equals("0"))
				return;
			// special case: last of enter is an operator - remove it
			if (lastIsOperator(enter, OPERATORS))
				enter = enter.substring(0, enter.length() - 1);
			// execute
			enter += display;
			labelEnter.setText(enter);
		}

		public OperatorListener(char op) {
			display = op;
		}
	}

	/* ignore it (keep it in the future)
	 * private class SwitchListener implements ActionListener{ public void
	 * actionPerformed(ActionEvent e){ if (enter.equals("0")) //special case
	 * return; if(enter.charAt(0)=='-') enter=enter.substring(1,enter.length());
	 * else enter="-"+ enter; labelEnter.setText(enter); } }
	 */

	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			enter = "0";
			result = INITIAL_RESULT;
			labelEnter.setText(enter);
			labelResult.setText(result);
		}
	}

	private class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			enter = enter.substring(0, enter.length() - 1);
			labelEnter.setText(enter);
		}
	}

	private class CalculateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			// special case: enter is empty or 0 - do nothing
			if (enter.isEmpty() || enter.equals(0))
				return; 
			// special case: the last of enter is an operator - do nothing
			if (lastIsOperator(enter, OPERATORS)) {
				labelResult.setText("error");
				return;
			}
			
			// use StringCalculater to calculate
			result = String.valueOf(sc.execute(enter));
			labelResult.setText("= " + result);
		}
	}

	public static boolean lastIsOperator(String s, String[] ope) {
		int size = s.length();
		String last = s.substring(size - 1, size);
		for (int i = 0; i < ope.length; i++) {
			if (last.equals(ope[i]))
				return true;
		}
		return false;
	}

	public JButton makeNumberButton(String num) {
		JButton retVal = new JButton(num);
		if (num.equals("0") || num.equals("00")) // special case: zero listener
			retVal.addActionListener(new ZeroListener(num));
		else
			retVal.addActionListener(new NumberListener(num));
		return retVal;
	}

	public JButton makeOperatorButton(char op) {
		JButton retVal = new JButton("" + op); // cast from char (or int) to
												// String
		retVal.addActionListener(new OperatorListener(op));
		return retVal;
	}

	// Q create a method called makeHelperButton seems easier and make the codes
	// shorter but what should i do?
	// because methods bellow are doing basically same thing.

	public JButton makeDeleteButton(char ch) {
		JButton retVal = new JButton("" + ch);
		retVal.addActionListener(new DeleteListener());
		return retVal;
	}

	public JButton makeClearButton(char ch) {
		JButton retVal = new JButton("" + ch);
		retVal.addActionListener(new ClearListener());
		return retVal;
	}

	public JButton makeEqualButton(char ch) {
		JButton retVal = new JButton("" + ch);
		retVal.addActionListener(new CalculateListener());
		return retVal;
	}

	/* ignore it (but keep it in the future)
	 * public JButton makeSwitchButton(char ch){ JButton retVal=new
	 * JButton(""+ch); retVal.addActionListener(new SwitchListener()); return
	 * retVal; }
	 */

	public static void main(String[] args) {
		new CalculatorGUI();
	}

}
