package hanwha.util;

import static hanwha.util.Extend.extend;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 객체 집합
 */
public class Set<E extends Comparable<E>> {
	private Object[] set;  // 집합
	private int[]  index;  // 정렬 인덱스
	private int     size;  // 원소 수

	/**
	 * @param initialCapacity 처음 집합 용량 -- 원소 수 최대값
	 */
	public Set(int initialCapacity) {
		set   = new Object[initialCapacity];
		index = new    int[initialCapacity];
	}

	/**
	 * 집합에서 객체를 찾는다 -- 없으면 넣는다.
	 * @param data 찾을 원소
	 * @return 찾은 위치 인덱스
	 */
	public int find(E data) {
		int a = 0, b = size - 1;      // 구간 양쪽 끝 인덱스: 왼쪽, 오른쪽
		while (a <= b) {
			int c = (a + b) >>> 1;    // 구간 중앙 인덱스
			@SuppressWarnings("unchecked")
			int diff = data.compareTo((E) set[index[c]]);
			if (0 < diff) {
				a = c + 1;            // 오른쪽 반 구간에서 더 찾는다
			} else if (diff < 0) {
				b = c - 1;            // 왼쪽 반 구간에서 더 찾는다
			} else {
				return index[c];      // 찾았다
			}
		}
		if (set.length <= size) {
			set   = Arrays.copyOf(set,   extend(size));
			index = Arrays.copyOf(index, extend(size));
		}
		if (a < size) {
			System.arraycopy(index, a, index, a + 1, size - a);
		}
		set[size] = data;
		return index[a] = size++;
	}

	/**
	 * 집합을 배열로 복사한다.
	 * @return 배열
	 */
	public E[] copy() {
		if (size != 0) {
			@SuppressWarnings("unchecked")
			E[] array = (E[]) Array.newInstance(set[0].getClass(), size);
			System.arraycopy(set, 0, array, 0, size);
			return array;
		}
		return null;
	}

	/**
	 * 데이터의 인덱스를 정렬된 int형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			int binarySearch(E[] set, int[] index, int from, int to, E data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int c = (a + b) >>> 1;    // 구간 중앙 인덱스
			int diff = data.compareTo((E) set[index[c]]);
			if (0 < diff) {
				a = c + 1;            // 오른쪽 반 구간에서 더 찾는다
			} else if (diff < 0) {
				b = c - 1;            // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;             // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * 데이터의 인덱스를 정렬된 int형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			  int binarySearch(E[] set, int[] index, E data) {
		return binarySearch(set, index, 0, index.length, data);
	}

	/**
	 * 데이터의 인덱스를 정렬된 short형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			int binarySearch(E[] set, short[] index, int from, int to, E data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int c = (a + b) >>> 1;    // 구간 중앙 인덱스
			int diff = data.compareTo((E) set[index[c]]);
			if (0 < diff) {
				a = c + 1;            // 오른쪽 반 구간에서 더 찾는다
			} else if (diff < 0) {
				b = c - 1;            // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;      // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * 데이터의 인덱스를 정렬된 short형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			  int binarySearch(E[] set, short[] index, E data) {
		return binarySearch(set, index, 0, index.length, data);
	}

	/**
	 * 데이터의 인덱스를 정렬된 byte형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			int binarySearch(E[] set, byte[] index, int from, int to, E data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int c = (a + b) >>> 1;    // 구간 중앙 인덱스
			int diff = data.compareTo((E) set[index[c]]);
			if (0 < diff) {
				a = c + 1;            // 오른쪽 반 구간에서 더 찾는다
			} else if (diff < 0) {
				b = c - 1;            // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;      // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * 데이터의 인덱스를 정렬된 byte형 인덱스 배열에서 찾는다.
     * @param <E>   인덱스를 찾을 데이터의 형
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static <E extends Comparable<E>>
			  int binarySearch(E[] set, byte[] index, E data) {
		return binarySearch(set, index, 0, index.length, data);
	}
}
