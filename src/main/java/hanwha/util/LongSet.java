package hanwha.util;

import static hanwha.util.Extend.extend;

import java.util.Arrays;

/**
 * 64-비트 정수 집합
 */
public class LongSet {
	private long[]  set;  // 집합
	private int[] index;  // 정렬 인덱스
	private int    size;  // 원소 수

	/**
	 * @param initialCapacity 처음 집합 용량 -- 원소 수 최대값
	 */
	public LongSet(int initialCapacity) {
		set   = new long[initialCapacity];
		index = new  int[initialCapacity];
	}

	/**
	 * 집합에서 정수를 찾는다 -- 없으면 넣는다.
	 * @param data 찾는 정수
	 * @return  찾은 위치 인덱스
	 */
	public int find(long data) {
		int a = 0, b = size - 1;         // 양쪽 끝 인덱스 위치: 왼쪽, 오른쪽
		while (a <= b) {
			int c = (a + b) >>> 1;       // 중앙 인덱스 위치
			int i = index[c];            // 중앙 원소 인덱스
			if (set[i] < data) {
				a = c + 1;               // 오른쪽 반 구간에서 더 찾는다
			} else if (data < set[i]) {
				b = c - 1;               // 왼쪽 반 구간에서 더 찾는다
			} else {
				return i;                // 찾았다
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
	 * 집합을 정수 배열로 복사한다.
	 * @return 정수 배열
	 */
	public long[] copy() {
		return Arrays.copyOf(set, size);
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 int형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, int[] index,
			                         int from, int to, long data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int        c = (a + b) >>> 1;  // 구간 중앙 인덱스
			long dataAtC = set[index[c]];  // 구간 중앙 데이터
			if (dataAtC < data) {
				a = c + 1;                 // 오른쪽 반 구간에서 더 찾는다
			} else if (data < dataAtC) {
				b = c - 1;                 // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;                  // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 int형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, int[] index, long data) {
		return binarySearch(set, index, 0, index.length, data);
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 short형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, short[] index,
			                         int from, int to, long data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int        c = (a + b) >>> 1;  // 구간 중앙 인덱스
			long dataAtC = set[index[c]];  // 구간 중앙 데이터
			if (dataAtC < data) {
				a = c + 1;                 // 오른쪽 반 구간에서 더 찾는다
			} else if (data < dataAtC) {
				b = c - 1;                 // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;                  // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 short형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, short[] index, long data) {
		return binarySearch(set, index, 0, index.length, data);
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 byte형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param from  인덱스 배열에서 찾는 범위 시작
	 * @param to    인덱스 배열에서 찾는 범위 끝
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, byte[] index,
			                         int from, int to, long data) {
		int a = from, b = to - 1;
		while (a <= b) {
			int        c = (a + b) >>> 1;  // 구간 중앙 인덱스
			long dataAtC = set[index[c]];  // 구간 중앙 데이터
			if (dataAtC < data) {
				a = c + 1;                 // 오른쪽 반 구간에서 더 찾는다
			} else if (data < dataAtC) {
				b = c - 1;                 // 왼쪽 반 구간에서 더 찾는다
			} else {
				return c;                  // 찾았다
			}
		}
		return -a - 1;
	}

	/**
	 * long형 데이터의 인덱스를 정렬된 byte형 인덱스 배열에서 찾는다.
	 * @param set   데이터 배열
	 * @param index 인덱스 배열
	 * @param data  찾는 데이터
	 * @return      인덱스 배열에서 찾은 위치 인덱스  -- 없으면 -(넣을 곳 인덱스) - 1
	 */
	public static int binarySearch(long[] set, byte[] index, long data) {
		return binarySearch(set, index, 0, index.length, data);
	}
}
