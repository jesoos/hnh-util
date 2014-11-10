package hanwha.util;

import static hanwha.util.Extend.extend;

import java.util.Arrays;

/**
 * 정수 짝 집합.
 * <br><br><pre>
 * find(3, 6);  --&gt; (3)(6)          -- returns 0
 * find(2, 4);  --&gt; (3 2)(6 4)      -- returns 1
 * find(3, 6);  --&gt; (3 2)(6 4)      -- returns 0
 * find(3, 9);  --&gt; (3 2 3)(6 4 9)  -- returns 2
 * find(2, 4);  --&gt; (3 2 3)(6 4 9)  -- returns 1</pre>
 */
public class PairSet {
	private int[][] set;  // 집합
	private int[] index;  // 정렬 인덱스  
	private int    size;  // 원소 수

	/**
	 * @param initialCapacity 처음 집합 용량 -- 원소 수 최대값
	 */
	public PairSet(int initialCapacity) {
		set   = new int[2][initialCapacity];
		index = new int[initialCapacity];
	}

	/**
	 * 정수 짝 (x, y)을 집합에서 찾는다 -- 없으면 넣는다.
	 * @param x 정수1
	 * @param y 정수2
	 * @return 찾은 위치 인덱스
	 */
	public int find(int x, int y) {
		int a = 0, b = size - 1;    // 양쪽 끝 인덱스 위치: 왼쪽, 오른쪽
		while (a <= b) {
			int c = (a + b) >>> 1;  // 중앙 원소 인덱스 위치
			int i = index[c];       // 중앙 원소 인덱스
			int u, v;               // 중앙 원소 값, 찾는 값
			if ((u = set[0][i]) == (v = x) && (u = set[1][i]) == (v = y)) {
				return i;           // 찾았다
			}
			if (u < v) {
				a = c + 1;          // 오른쪽 반 구간에서 더 찾는다
			} else {
				b = c - 1;          // 왼쪽 반 구간에서 더 찾는다
			}
		}
		if (index.length <= size) {
			set   = copy(extend(size));
			index = Arrays.copyOf(index, extend(size));
		}
		if (a < size) {
			System.arraycopy(index, a, index, a + 1, size - a);
		}
		set[0][size] = x;
		set[1][size] = y;
		return index[a] = size++;
	}

	/**
	 * 정수 짝을 집합에서 찾는다 -- 없으면 넣는다.
	 * @param pair 정수 짝 배열
	 * @return 찾은 위치 인덱스
	 */
	public int find(int[] pair) {
		return find(pair[0], pair[1]);
	}

	private int[][] copy(int newSize) {
		return new int[][] { 
			Arrays.copyOf(set[0], newSize),
			Arrays.copyOf(set[1], newSize) 
		};
	}

	/**
	 * 집합을 32-비트 정수 배열로 복사한다.
	 * @return 32-비트 정수 배열
	 */
	public int[][] copy() {
		return copy(size);
	}

	/**
	 * 집합을 16-비트 정수 배열로 복사한다.
	 * @return 16-비트 정수 배열
	 * @throws Exception 16-비트 정수 배열로 받을 수 없는 경우.
	 */
	public short[][] copyToShorts() throws Exception {
		short[][] shorts = new short[2][size];
		for (int i = 0; i < size; i++) {
			int x = set[0][i], y = set[1][i];
			if (x < Short.MIN_VALUE || Short.MAX_VALUE < x ||
			    y < Short.MIN_VALUE || Short.MAX_VALUE < y) {
				throw new Exception("short형으로 받을 수 없습니다.");
			}
			shorts[0][i] = (short) x;
			shorts[1][i] = (short) y;
		}
		return shorts;
	}

	/**
	 * 집합을 8-비트 정수 배열로 복사한다.
	 * @return 8-비트 정수 배열
	 * @throws Exception 8-비트 정수 배열로 받을 수 없는 경우.
	 */
	public byte[][] copyToBytes() throws Exception {
		byte[][] bytes = new byte[2][size];
		for (int i = 0; i < size; i++) {
			int x = set[0][i], y = set[1][i];
			if (x < Byte.MIN_VALUE || Byte.MAX_VALUE < x ||
			    y < Byte.MIN_VALUE || Byte.MAX_VALUE < y) {
				throw new Exception("byte형으로 받을 수 없습니다.");
			}
			bytes[0][i] = (byte) x;
			bytes[1][i] = (byte) y;
		}
		return bytes;
	}
}
