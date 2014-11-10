import static org.junit.Assert.assertEquals;
import hanwha.util.Set;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SetTest {
	@Parameters
	public static Collection<Object[]> cases() {
		return Arrays.asList(new Object[][] {
				{"abc", 0}, {"def", 1}, {"xyz", 2}, {"def", 1}
		});
	}

	private static Set<String> set = new Set<>(3);
	private String data;
	private int    index;

	public SetTest(String data, int index) {
		this.data  = data;
		this.index = index;
	}

	@Test
	public void test() {
		assertEquals(index, set.find(data));
	}
}
