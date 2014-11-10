package hanwha.util;

/**
 * 배열 확장
 */
public class Extend {
	/**
	 * 확장할 배열의 길이를 구한다.
	 * @param size 확장 전 배열 길이
	 * @return     확장 후 배열 길이
	 */
	public static int extend(int size) {
		return (int) (size + size / 3);
	}
	
	private Extend() {}
}
