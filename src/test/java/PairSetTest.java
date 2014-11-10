import static org.junit.Assert.assertEquals;
import hanwha.util.PairSet;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PairSetTest {
	@Parameters
	public static Collection<Object[]> cases() {
		return Arrays.asList(new Object[][] {
				{3,6, 0}, {2,4, 1}, {3,6, 0}, {3,9, 2}, {2,4, 1}
		});
	}

	private static PairSet set = new PairSet(4);
	private int x, y, index;

	public PairSetTest(int x, int y, int index) {
		this.x     = x;
		this.y     = y;
		this.index = index;
	}

	@Test
	public void test() {
		assertEquals(index, set.find(x, y));
	}
}
