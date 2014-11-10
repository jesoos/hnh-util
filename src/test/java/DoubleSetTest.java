import static org.junit.Assert.assertEquals;
import hanwha.util.DoubleSet;

import org.junit.Test;

public class DoubleSetTest {
	private double[] doubles = {2.5, 123.6, 300, 300, 405.5, 600.9, 600.9, 789};

	@Test
	public void test() {
		DoubleSet ds = new DoubleSet(doubles.length);
		byte[]    ia = new byte[doubles.length];
		for (int i = 0; i < doubles.length; i++) {
			ia[i] = (byte) ds.find(doubles[i]);
		}
		double[] da = ds.copy();
		assertEquals( 6, da.length);
		assertEquals( 8, ia.length);
		assertEquals( 1, DoubleSet.binarySearch(da, ia, 123.6));
		assertEquals( 7, DoubleSet.binarySearch(da, ia, 789));
		assertEquals(-2, DoubleSet.binarySearch(da, ia, 100));
		assertEquals( 5, DoubleSet.binarySearch(da, ia, 3, 6, 600.9));
		assertEquals(-3, DoubleSet.binarySearch(da, ia, 2, 5, 2.5));
	}
}
