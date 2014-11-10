import static org.junit.Assert.assertEquals;
import hanwha.util.IntSet;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IntSetTest {
	@Parameters
	public static Collection<Object[]> cases() {
		return Arrays.asList(new Object[][] {
				{3, 0}, {2, 1}, {3, 0}, {9, 2}, {2, 1}
		});
	}

	private static IntSet set = new IntSet(4);
	private int data, index;

	public IntSetTest(int data, int index) {
		this.data  = data;
		this.index = index;
	}

	@Test
	public void test() {
		assertEquals(index, set.find(data));
	}
}
