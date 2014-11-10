import static org.junit.Assert.assertEquals;
import hanwha.util.Set;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

import org.junit.Test;

public class SetSearchTest {
	private BigDecimal[] bds = { valueOf(25, 1),   valueOf(1236, 1),
	                             valueOf(300),     valueOf(300),
	                             valueOf(4055, 1), valueOf(6009, 1),
	                             valueOf(6009, 1), valueOf(789) };
	@Test
	public void test() {
		Set<BigDecimal> ds = new Set<>(bds.length);
		byte[]          ia = new byte[bds.length];
		for (int i = 0; i < bds.length; i++) {
			ia[i] = (byte) ds.find(bds[i]);
		}
		BigDecimal[] da = ds.copy();
		assertEquals( 6, da.length);
		assertEquals( 8, ia.length);
		assertEquals( 1, Set.binarySearch(da, ia, valueOf(1236, 1)));
		assertEquals( 7, Set.binarySearch(da, ia, valueOf(789)));
		assertEquals(-2, Set.binarySearch(da, ia, valueOf(100)));
		assertEquals( 5, Set.binarySearch(da, ia, 3, 6, valueOf(6009, 1)));
		assertEquals(-3, Set.binarySearch(da, ia, 2, 5, valueOf(25, 1)));
	}
}
