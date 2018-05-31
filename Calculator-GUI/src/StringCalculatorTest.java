import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator sc;

	@Before
	public void setUp() throws Exception {
		sc=new StringCalculator();
	}

	@Test
	public void test() {
		assertEquals(8, sc.execute("5+3"));
		assertEquals(15, sc.execute("5*3"));
		assertEquals(2, sc.execute("5-3"));
		assertEquals(5, sc.execute("15/3"));
		assertEquals(3, sc.execute("5*3-8/2*3"));
		
		/*
		sc.convert("5+3");
		assertEquals(2, sc.nums.size());
		assertEquals(5,(int) sc.nums.get(0));
		assertEquals(3,(int) sc.nums.get(1));
		assertEquals(1, sc.ops.size());
		assertEquals('+', (char) sc.ops.get(0));
		assertEquals(0, sc.opOrder.size());
		sc.sortOperator();
		assertEquals(2, sc.nums.size());
		assertEquals(5,(int) sc.nums.get(0));
		assertEquals(3,(int) sc.nums.get(1));
		assertEquals(1, sc.ops.size());
		assertEquals('+', (char) sc.ops.get(0));
		assertEquals(1, sc.opOrder.size());
		assertEquals(0, (int) sc.opOrder.get(0));
		sc.calculate();
		*/
	}

}
