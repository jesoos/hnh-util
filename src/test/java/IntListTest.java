import static org.junit.Assert.assertArrayEquals;
import hanwha.util.IntList;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IntListTest {
	@Parameters
	public static Collection<Object[]> cases() {
		return Arrays.asList(new Object[][] {{new int[] {3,9,6},   0,3}, 
				                             {new int[] {3,9},     0,2}, 
				                             {new int[] {9,6,9,7}, 1,5}, 
				                             {new int[] {6,9},     2,4}}); 
	}

	private static IntList list = new IntList(9, 3);
	private int[]  sublist;
	private int[]  fromTo;

	public IntListTest(int[] sublist, int from, int to) {
		this.sublist = sublist;
		fromTo       = new int[] {from, to};
	}

	@Test
	public void test() {
		for (int n: sublist) {
			list.append(n);
		}
		assertArrayEquals(fromTo, list.find());
	}
}
