package hanwha.util;

import static hanwha.util.Extend.extend;

import java.util.Arrays;

/**
 * 정수 수열.
 *<br><br><pre>
 * append(3);    --&gt; () 3 
 * append(9);    --&gt; () 3 9
 * append(6);    --&gt; () 3 9 6
 * find();       --&gt; (3 9 6)          -- returns (0 3)
 *
 * append(3);    --&gt; (3 9 6) 3
 * append(9);    --&gt; (3 9 6) 3 9
 * find();       --&gt; (3 9 6)          -- returns (0 2)
 *
 * append(9);    --&gt; (3 9 6) 9
 * append(6);    --&gt; (3 9 6) 9 6
 * append(9);    --&gt; (3 9 6) 9 6 9
 * append(7);    --&gt; (3 9 6) 9 6 9 7
 * find();       --&gt; (3 9 6 9 7)      -- returns (1 5)
 *
 * append(6);    --&gt; (3 9 6 9 7) 6
 * append(9);    --&gt; (3 9 6 9 7) 6 9
 * find();       --&gt; (3 9 6 9 7)      -- returns (2 4)</pre>
 */
public class IntList {
	private int[] list;         // 수열
	private int   size, sizeA;  // 수열 길이, 수열 길이 + 끝에 넣은 부분수열 길이

	private int[] subList;      // 인덱스 배열: 찾은 부분수열 시작 위치
	private int[] subSize;      // 인덱스 배열: 찾은 부분수열 길이
	private int   subCount;     // 인덱스 수

	/**
	 * @param initialCapacity      수열 길이 처음 최대값
	 * @param initialIndexCapacity 찾은 부분수열 수 처음 최대값
	 */
	public IntList(int initialCapacity, int initialIndexCapacity) {
		list    = new int[initialCapacity];
		subList = new int[initialIndexCapacity];
		subSize = new int[initialIndexCapacity];
	}

	/**
	 * 정수 하나를 수열 끝에 넣는다.
	 * @param number 정수
	 */
	public void append(int number) {
		if (list.length <= sizeA) {
			list = Arrays.copyOf(list, extend(sizeA));
		}
		list[sizeA++] = number;
	}

	/**
	 * 끝에 넣은 부분수열을 앞 수열에서 찾는다 -- 없으면 앞 수열에 넣는다.
	 * @return 찾은 위치 {시작 인덱스, 끝 인덱스}
	 */
	public int[] find() {
		int len = sizeA - size;        // 찾는 부분수열의 길이

		int a = 0, b = subCount - 1;   // 구간 양쪽 끝 인덱스: 왼쪽, 오른쪽
		while (a <= b) {
			int c = (a + b) >>> 1;     // 구간 중앙 인덱스
			int u, v;                  // 구간 중앙 값, 찾는 값
			if ((u = subSize[c]) == (v = len)) {
				int i = subList[c], j = size;  // 인덱스: 구간 중앙 수열, 찾는 수열
				do {
					if (j == sizeA) {
						sizeA = size;  // 찾았다 -- 뒤에 넣은 부분수열을 제거한다
						return new int[] {subList[c], subList[c] + len};
					}
				} while ((u = list[i++]) == (v = list[j++]));
			}
			if (u < v) {
				a = c + 1;             // 오른쪽 반 구간에서 더 찾는다
			} else {
				b = c - 1;             // 왼쪽 반 구간에서 더 찾는다
			}
		}
		if (subList.length <= subCount) {
			subList = Arrays.copyOf(subList, extend(subCount));
			subSize = Arrays.copyOf(subSize, extend(subCount));
		}
		if (a < subCount) {
			System.arraycopy(subList, a, subList, a + 1, subCount - a);
			System.arraycopy(subSize, a, subSize, a + 1, subCount - a);
		}
		subCount++;

		int i0, i, j;  // 칮은 위치 인덱스, 수열 안의 수 인덱스, 찾는 수 인덱스
		for (i0 = 0; i0 < size; i0++) {
			for (i = i0, j = size; j < sizeA && list[i] == list[j]; i++, j++);
			if (size <= i) {
				if (j < sizeA) {     // 찾는 수열의 앞 부분이 앞 수열 끝에 있다
					System.arraycopy(list, j, list, i, sizeA - j);
					i += sizeA - j;  // 뒷 부분을 복사해 넣는다
				}
				sizeA = i;           // 수열의 끝 = 찾은 수열의 끝
				break;
			}
			if (j == sizeA) {
				sizeA = size;        // 찾았다 -- 끝에 넣은 부분수열을 제거한다
				break;
			}
		}
		size       = sizeA;  // 전체 수열 길이
		subList[a] = i0;     // 새로 찾은 부분수열 시작 위치
		subSize[a] = len;    // 새로 찾은 부분수열 길이 
		return new int[] {i0, i0 + len};
	}

	/**
	 * 부분수열을 찾고, 그 시작 위치와 끝 위치를  인덱스 짝 집합에서 찾는다.
	 * @param pairSet 인덱스 짝 집합
	 * @return 찾은 위치 -- (시작 위치, 끝 위치) 짝의 인덱스
	 */
	public int find(PairSet pairSet) {
		return pairSet.find(find());
	}
	
	/**
	 * 찾은 부분수열 수를 구한다.
	 * @return 찾은 부분수열 수 = 인덱스 배열 길이
	 */
	public int getIndexSize() {
		return subCount;
	}

	/**
	 * 수열을 32-비트 정수 배열로 복사한다.
	 * @return 32-비트 정수 배열
	 */
	public int[] copy() {
		return Arrays.copyOf(list, size);
	}

	/**
	 * 수열을 16-비트 정수 배열로 복사한다.
	 * @return 16-비트 정수 배열
	 * @throws Exception 16-비트 정수 배열로 받을 수 없는 경우.
	 */
	public short[] copyToShorts() throws Exception {
		short[] shorts = new short[size];
		for (int i = 0; i < size; i++) {
			int n = list[i];
			if (n < Short.MIN_VALUE || Short.MAX_VALUE < n) {
				throw new Exception("short형으로 받을 수 없습니다.");
			}
			shorts[i] = (short) n; 
		}
		return shorts;
	}

	/**
	 * 수열을 8-비트 정수 배열로 복사한다.
	 * @return 8-비트 정수 배열
	 * @throws Exception 8-비트 정수 배열로 받을 수 없는 경우.
	 */
	public byte[] copyToBytes() throws Exception {
		byte[] bytes = new byte[size];
		for (int i = 0; i < size; i++) {
			int n = list[i];
			if (n < Byte.MIN_VALUE || Byte.MAX_VALUE < n) {
				throw new Exception("byte형으로 받을 수 없습니다.");
			}
			bytes[i] = (byte) n;
		}
		return bytes;
	}
}
