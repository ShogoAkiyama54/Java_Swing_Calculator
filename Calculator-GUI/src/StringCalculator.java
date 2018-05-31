import java.util.ArrayList;

public class StringCalculator {
	private ArrayList<Integer> nums;
	private ArrayList<Character> ops;
	private ArrayList<Integer> opOrder;

	public StringCalculator() {
		nums = new ArrayList<Integer>();
		ops = new ArrayList<Character>();
		opOrder = new ArrayList<Integer>();
	}

	public int execute(String input) {
		convert(input);
		sortOperator(); // Q: should i use nums and ops as argument, so that the
						// method can work even in other class? - no need
		int result = calculate();

		// clear the current data for the next execution
		nums.clear();
		ops.clear();
		opOrder.clear();
		return result;
	}

	private void convert(String s) { // recursive
		for (int i = 0; i < s.length(); i++) {
			String figure = s.substring(i, i + 1);
			if (CalculatorGUI.lastIsOperator(figure, CalculatorGUI.OPERATORS)) {
				nums.add(Integer.parseInt(s.substring(0, i)));
				ops.add(s.charAt(i));
				s = s.substring(i + 1);
				break;
			}
			if (i == s.length() - 1) { // base case
				nums.add(Integer.parseInt(s.substring(0, i + 1)));
				return;
			}
		}
		convert(s);
	}

	private int calculate() { // recursive
		if (nums.size() == 1) // base case:
			return nums.get(0);

		//recursive case: 
		int i = opOrder.get(0);
		char op = ops.get(i);
		switch (op) {
		case '+':
			nums.set(i, nums.get(i) + nums.get(i + 1));
			break;
		case '-':
			nums.set(i, nums.get(i) - nums.get(i + 1));
			break;
		case '*':
			nums.set(i, nums.get(i) * nums.get(i + 1));
			break;
		case '/':
			nums.set(i, nums.get(i) / nums.get(i + 1));
			break;
		}
		System.out.println(nums.size() + "/" + ops.size() + "/" + opOrder.size());
		nums.remove(i + 1);
		ops.remove(i);
		for (int j = 0; j < opOrder.size(); j++) {
			if (opOrder.get(j) > opOrder.get(0))
				opOrder.set(j, opOrder.get(j) - 1);
		}
		opOrder.remove(0);
		System.out.println(nums.size() + "/" + ops.size() + "/" + opOrder.size());
		return calculate();
	}

	private void sortOperator() {
		for (int i = 0; i < ops.size(); i++) {
			char op = ops.get(i);
			if (op == '/' || op == '*')
				opOrder.add(i);
		}
		for (int i = 0; i < ops.size(); i++) {
			char op = ops.get(i);
			if (op == '-' || op == '+')
				opOrder.add(i);
		}
	}
}
