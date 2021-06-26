package util;

import util.collect.IntRange;

/**
 * このクラスは配列に対する様々な操作を提供します。
 * @author 31536000
 *
 */
public class ArrayUtility {

	private ArrayUtility() {
		throw new AssertionError();
	}

	/**
	 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
	 * @complexity O(length)
	 * @param length 配列の長さ
	 * @param init 配列の初期値を決める関数
	 * @return 配列
	 */
	public static int[] create(int length, java.util.function.IntUnaryOperator init) {
		int[] ret = new int[length];
		for (int i = 0;i < length;++ i) ret[i] = init.applyAsInt(i);
		return ret;
	}

	/**
	 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
	 * @complexity O(length)
	 * @param length 配列の長さ
	 * @param init 配列の初期値を決める関数
	 * @return 配列
	 */
	public static long[] create(int length, java.util.function.LongUnaryOperator init) {
		long[] ret = new long[length];
		for (int i = 0;i < length;++ i) ret[i] = init.applyAsLong(i);
		return ret;
	}

	/**
	 * initを用いて配列を生成します。配列のi番目の要素はinit.applyAsInt(i)になります。
	 * @complexity O(length)
	 * @param length 配列の長さ
	 * @param init 配列の初期値を決める関数
	 * @return 配列
	 */
	public static double[] create(int length, java.util.function.DoubleUnaryOperator init) {
		double[] ret = new double[length];
		for (int i = 0;i < length;++ i) ret[i] = init.applyAsDouble(i);
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static boolean[] add(boolean[] array, boolean element) {
		if (array == null) {
			boolean[] ret = {element};
			return ret;
		}
		boolean[] ret = new boolean[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static byte[] add(byte[] array, byte element) {
		if (array == null) {
			byte[] ret = {element};
			return ret;
		}
		byte[] ret = new byte[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static short[] add(short[] array, short element) {
		if (array == null) {
			short[] ret = {element};
			return ret;
		}
		short[] ret = new short[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static int[] add(int[] array, int element) {
		if (array == null) {
			int[] ret = {element};
			return ret;
		}
		int[] ret = new int[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static long[] add(long[] array, long element) {
		if (array == null) {
			long[] ret = {element};
			return ret;
		}
		long[] ret = new long[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static float[] add(float[] array, float element) {
		if (array == null) {
			float[] ret = {element};
			return ret;
		}
		float[] ret = new float[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static double[] add(double[] array, double element) {
		if (array == null) {
			double[] ret = {element};
			return ret;
		}
		double[] ret = new double[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static char[] add(char[] array, char element) {
		if (array == null) {
			char[] ret = {element};
			return ret;
		}
		char[] ret = new char[array.length + 1];
		System.arraycopy(array, 0, ret, 0, array.length);
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 配列の最後に要素を一つ増やした新しい配列を返します。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param element 加えたい要素
	 * @return 配列の後ろに要素を加えた配列
	 */
	public static <T> T[] add(T[] array, T element) {
		if (array == null) {
			return addAll(array, element);
		}
		@SuppressWarnings("unchecked")
		T[] ret  = (T[])java.util.Arrays.copyOfRange(array, 0, array.length + 1, array.getClass());
		ret[array.length] = element;
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static boolean[] addAll(boolean[] array, boolean... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		boolean[] ret = new boolean[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static byte[] addAll(byte[] array, byte... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		byte[] ret = new byte[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static short[] addAll(short[] array, short... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		short[] ret = new short[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static int[] addAll(int[] array, int... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		int[] ret = new int[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static long[] addAll(long[] array, long... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		long[] ret = new long[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static float[] addAll(float[] array, float... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		float[] ret = new float[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static double[] addAll(double[] array, double... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		double[] ret = new double[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	public static char[] addAll(char[] array, char... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		char[] ret = new char[array.length + array2.length];
		System.arraycopy(array, 0, ret, 0, array.length);
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 2つの配列を結合した新しい配列を返します。
	 * @complexity O(array.length + array2.length)
	 * @param array 左側の配列
	 * @param array2 右側の配列
	 * @return 2つの配列を結合した配列
	 */
	@SafeVarargs
	public static <T> T[] addAll(T[] array, T... array2) {
		if (array == null) return array2 == null ? null : array2.clone();
		if (array2 == null) return array.clone();
		@SuppressWarnings("unchecked")
		T[] ret  = (T[])java.util.Arrays.copyOfRange(array, 0, array.length + array2.length, array.getClass());
		System.arraycopy(array2, 0, ret, array.length, array2.length);
		return ret;
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(boolean[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(boolean[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(boolean[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(byte[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(byte[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(byte[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(short[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(short[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(short[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(int[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(int[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(int[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(long[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(long[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(long[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(float[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(float[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(float[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(double[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(double[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(double[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(char[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(char[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(char[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void reverse(Object[] array) {
		if (array != null) for (int i = 0, l = array.length + 1 >> 1;i < l;++ i) swap(array, i, array.length - 1 - i);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex 逆順にする左閉区間
	 * @param toIndex 逆順にする右開区間
	 */
	public static void reverse(Object[] array, int fromIndex, int toIndex) {
		for (-- toIndex;fromIndex < toIndex;++ fromIndex, -- toIndex) swap(array, fromIndex, toIndex);
	}

	/**
	 * 配列を逆順にします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range 逆順にする区間
	 */
	public static void reverse(Object[] array, IntRange range) {
		reverse(array, range.getClosedLower(), range.getOpenUpper());
	}

	private static java.util.Random rnd;

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(boolean[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(boolean[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(boolean[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(boolean[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(boolean[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(boolean[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(byte[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(byte[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(byte[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(byte[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(byte[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(byte[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(short[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(short[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(short[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(short[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(short[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(short[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(int[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(int[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(int[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(int[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(int[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(int[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(long[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(long[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(long[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(long[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(long[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(long[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(float[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(float[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(float[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(float[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(float[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(float[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(double[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(double[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(double[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(double[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(double[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(double[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(char[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(char[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(char[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(char[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(char[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(char[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 */
	public static void shuffle(Object[] array) {
		shuffle(array, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 */
	public static void shuffle(Object[] array, int fromIndex, int toIndex) {
		shuffle(array, fromIndex, toIndex, rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 */
	public static void shuffle(Object[] array, IntRange range) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), rnd == null ? rnd = new java.util.Random() : rnd);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(array.length)
	 * @param array 元の配列
	 * @param random 乱数
	 */
	public static void shuffle(Object[] array, java.util.Random random) {
		if (array != null) for (int i = array.length - 1;i > 0;-- i) swap(array, i, random.nextInt(i+1));
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 元の配列
	 * @param fromIndex シャッフルする左閉区間
	 * @param toIndex シャッフルする右開区間
	 * @param random 乱数
	 */
	public static void shuffle(Object[] array, int fromIndex, int toIndex, java.util.Random random) {
		if (array != null) for (int i = toIndex - 1;i > fromIndex;-- i) swap(array, i, random.nextInt(i-fromIndex)+fromIndex);
	}

	/**
	 * 配列をシャッフルします。
	 * @complexity O(range.getDistance())
	 * @param array 元の配列
	 * @param range シャッフルする区間
	 * @param random 乱数
	 */
	public static void shuffle(Object[] array, IntRange range, java.util.Random random) {
		shuffle(array, range.getClosedLower(), range.getOpenUpper(), random);
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static boolean[] getArray(int size, boolean value) {
		boolean[] ret = new boolean[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static byte[] getArray(int size, byte value) {
		byte[] ret = new byte[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static short[] getArray(int size, short value) {
		short[] ret = new short[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static int[] getArray(int size, int value) {
		int[] ret = new int[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static long[] getArray(int size, long value) {
		long[] ret = new long[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static float[] getArray(int size, float value) {
		float[] ret = new float[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static double[] getArray(int size, double value) {
		double[] ret = new double[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * 指定した長さと初期値を持つ配列を生成します。
	 * @complexity O(size)
	 * @param size 配列の長さ
	 * @param value 配列の初期値
	 * @return 生成された配列
	 */
	public static char[] getArray(int size, char value) {
		char[] ret = new char[size];
		java.util.Arrays.fill(ret, value);
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Boolean[] toObject(boolean[] array) {
		if (array == null) return null;
		Boolean[] ret = new Boolean[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Byte[] toObject(byte[] array) {
		if (array == null) return null;
		Byte[] ret = new Byte[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Short[] toObject(short[] array) {
		if (array == null) return null;
		Short[] ret = new Short[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Integer[] toObject(int[] array) {
		if (array == null) return null;
		Integer[] ret = new Integer[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Long[] toObject(long[] array) {
		if (array == null) return null;
		Long[] ret = new Long[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Float[] toObject(float[] array) {
		if (array == null) return null;
		Float[] ret = new Float[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Double[] toObject(double[] array) {
		if (array == null) return null;
		Double[] ret = new Double[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * プリミティブ型の配列と中身が対応するオブジェクト型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array プリミティブ型の配列
	 * @return オブジェクト型の配列
	 */
	public static Character[] toObject(char[] array) {
		if (array == null) return null;
		Character[] ret = new Character[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static boolean[] toPrimitive(Boolean[] array) {
		if (array == null) return null;
		boolean[] ret = new boolean[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
		if (array == null) return null;
		boolean[] ret = new boolean[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static byte[] toPrimitive(Byte[] array) {
		if (array == null) return null;
		byte[] ret = new byte[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
		if (array == null) return null;
		byte[] ret = new byte[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static short[] toPrimitive(Short[] array) {
		if (array == null) return null;
		short[] ret = new short[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static short[] toPrimitive(Short[] array, short valueForNull) {
		if (array == null) return null;
		short[] ret = new short[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static int[] toPrimitive(Integer[] array) {
		if (array == null) return null;
		int[] ret = new int[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static int[] toPrimitive(Integer[] array, int valueForNull) {
		if (array == null) return null;
		int[] ret = new int[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static long[] toPrimitive(Long[] array) {
		if (array == null) return null;
		long[] ret = new long[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static long[] toPrimitive(Long[] array, long valueForNull) {
		if (array == null) return null;
		long[] ret = new long[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static float[] toPrimitive(Float[] array) {
		if (array == null) return null;
		float[] ret = new float[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static float[] toPrimitive(Float[] array, float valueForNull) {
		if (array == null) return null;
		float[] ret = new float[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static double[] toPrimitive(Double[] array) {
		if (array == null) return null;
		double[] ret = new double[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static double[] toPrimitive(Double[] array, double valueForNull) {
		if (array == null) return null;
		double[] ret = new double[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @return プリミティブ型の配列
	 * @throws NullPointerException 配列の要素にnullが含まれていた場合
	 */
	public static char[] toPrimitive(Character[] array) {
		if (array == null) return null;
		char[] ret = new char[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i];
		return ret;
	}

	/**
	 * オブジェクト型の配列と中身が対応するプリミティブ型の配列を生成します。
	 * @complexity O(array.length)
	 * @param array オブジェクト型の配列
	 * @param valueForNull nullの値に対応させる値
	 * @return プリミティブ型の配列
	 */
	public static char[] toPrimitive(Character[] array, char valueForNull) {
		if (array == null) return null;
		char[] ret = new char[array.length];
		for (int i = 0;i < ret.length;++ i) ret[i] = array[i] == null ? valueForNull : array[i];
		return ret;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最小値
	 * @throws NullPointerException comparatorがnullの場合
	 */
	public static <T> T min(T[] array, java.util.Comparator<T> comparator) {
		if (array == null || array.length == 0) return null;
		T min = array[0];
		for (int i = 1;i < array.length;++ i) if (comparator.compare(min, array[i]) > 0) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static <T extends Comparable<T>> T min(T[] array) {
		return min(array, java.util.Comparator.naturalOrder());
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static byte min(byte[] array) {
		byte min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static short min(short[] array) {
		short min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static int min(int[] array) {
		int min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static long min(long[] array) {
		long min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static float min(float[] array) {
		float min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 */
	public static double min(double[] array) {
		double min = array[0];
		for (int i = 1;i < array.length;++ i) if (min > array[i]) min = array[i];
		return min;
	}

	/**
	 * 配列の最小要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最小値
	 * @throws NullPointerException comparatorがnullの場合
	 */
	public static <T> T max(T[] array, java.util.Comparator<T> comparator) {
		if (array == null || array.length == 0) return null;
		T max = array[0];
		for (int i = 1;i < array.length;++ i) if (comparator.compare(max, array[i]) < 0) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 */
	public static <T extends Comparable<T>> T max(T[] array) {
		return max(array, java.util.Comparator.naturalOrder());
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static byte max(byte[] array) {
		byte max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static short max(short[] array) {
		short max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static int max(int[] array) {
		int max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static long max(long[] array) {
		long max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static float max(float[] array) {
		float max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列の最大要素を返します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列がnullか要素数が0の場合はnull、それ以外の場合は配列の最大値
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static double max(double[] array) {
		double max = array[0];
		for (int i = 1;i < array.length;++ i) if (max < array[i]) max = array[i];
		return max;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(boolean[] array, int n, int m) {
		boolean swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(byte[] array, int n, int m) {
		byte swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(short[] array, int n, int m) {
		short swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(int[] array, int n, int m) {
		int swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(long[] array, int n, int m) {
		long swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(float[] array, int n, int m) {
		float swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(double[] array, int n, int m) {
		double swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(char[] array, int n, int m) {
		char swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列のn番目とm番目を入れ替えます。
	 * @complexity O(1)
	 * @param array 配列
	 * @param n 中身をswapするindex
	 * @param m 中身をswapするindex
	 * @throws ArrayIndexOutOfBoundsException n, m < 0 || array.length <= n, mのとき
	 * @throws NullPointerException arrayがnullの場合
	 */
	public static void swap(Object[] array, int n, int m) {
		Object swap = array[n];
		array[n] = array[m];
		array[m] = swap;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static <T extends Comparable<T>> boolean nextPermutation(T[] array) {
		return nextPermutation(array, java.util.Comparator.naturalOrder());
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 * @return 配列を書き換えたならばtrue
	 * @throws NullPointerException comparatorがnullの場合
	 */
	public static <T> boolean nextPermutation(T[] array, java.util.Comparator<T> comparator) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (comparator.compare(array[change], array[change + 1]) < 0) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (comparator.compare(array[change], array[mid = min + halfDiff]) < 0) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(byte[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(short[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(int[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(long[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(float[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(double[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で次の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean nextPermutation(char[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] < array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] < array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static <T extends Comparable<T>> boolean prevPermutation(T[] array) {
		return prevPermutation(array, java.util.Comparator.naturalOrder());
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param comparator 比較関数
	 * @return 配列を書き換えたならばtrue
	 * @throws NullPointerException comparatorがnullの場合
	 */
	public static <T> boolean prevPermutation(T[] array, java.util.Comparator<T> comparator) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (comparator.compare(array[change], array[change + 1]) > 0) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (comparator.compare(array[change], array[mid = min + halfDiff]) > 0) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(byte[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(short[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(int[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(long[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(float[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(double[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列を辞書式順序で前の配列に書き換えます。そのような配列が無い場合、何もしません。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @return 配列を書き換えたならばtrue
	 */
	public static boolean prevPermutation(char[] array) {
		if (array == null) return false;
		for (int change = array.length - 2;change >= 0;-- change) {
			if (array[change] > array[change + 1]) {
				int min = change, max = array.length, halfDiff, mid;
				while((halfDiff = max - min >> 1) != 0) if (array[change] > array[mid = min + halfDiff]) min = mid; else max = mid;
				swap(array, change, min);
				for (min = change + 1, max = array.length - 1;min < max;++ min, -- max) swap(array, min, max);
				return true;
			}
		}
		return false;
	}

	/**
	 * 配列の各要素を与えられた関数に適用した配列を生成します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param map 各要素に適用する関数
	 * @return 配列の各要素にmapを適用した配列
	 */
	public static <T> T[] map(T[] array, java.util.function.UnaryOperator<T> map) {
		T[] ret = java.util.Arrays.copyOf(array, array.length);
		for (int i = 0;i < ret.length;++ i) ret[i] = map.apply(ret[i]);
		return ret;
	}

	/**
	 * 配列の各要素を与えられた関数に適用した配列を生成します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param map 各要素に適用する関数
	 * @return 配列の各要素にmapを適用した配列
	 */
	public static int[] map(int[] array, java.util.function.IntUnaryOperator map) {
		int[] ret = java.util.Arrays.copyOf(array, array.length);
		for (int i = 0;i < ret.length;++ i) ret[i] = map.applyAsInt(ret[i]);
		return ret;
	}

	/**
	 * 配列の各要素を与えられた関数に適用した配列を生成します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param map 各要素に適用する関数
	 * @return 配列の各要素にmapを適用した配列
	 */
	public static long[] map(long[] array, java.util.function.LongUnaryOperator map) {
		long[] ret = java.util.Arrays.copyOf(array, array.length);
		for (int i = 0;i < ret.length;++ i) ret[i] = map.applyAsLong(ret[i]);
		return ret;
	}

	/**
	 * 配列の各要素を与えられた関数に適用した配列を生成します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param map 各要素に適用する関数
	 * @return 配列の各要素にmapを適用した配列
	 */
	public static double[] map(double[] array, java.util.function.DoubleUnaryOperator map) {
		double[] ret = java.util.Arrays.copyOf(array, array.length);
		for (int i = 0;i < ret.length;++ i) ret[i] = map.applyAsDouble(ret[i]);
		return ret;
	}

	/**
	 * 配列の各要素を与えられた関数に適用した配列を生成します。
	 * @complexity O(array.length)
	 * @param array 配列
	 * @param map 各要素に適用する関数
	 * @param generator 新しい配列を生成するための関数、U::newを引数に取る
	 * @return 配列の各要素にmapを適用した配列
	 */
	public static <T, U> U[] map(T[] array, java.util.function.Function<T, U> map, java.util.function.IntFunction<U[]> generator) {
		U[] ret = generator.apply(array.length);
		for (int i = 0;i < ret.length;++ i) ret[i] = map.apply(array[i]);
		return ret;
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity O(array.length)
	 * @param array 配列
	 */
	public static void sort(final byte[] array) {
		if (array.length < 128) {
			for (int i = 0, j;i < array.length;++ i) {
				byte tmp = array[i], tmp2;
				for (j = i;j > 0 && (tmp2 = array[j - 1]) > tmp;-- j) array[j] = tmp2;
				array[j] = tmp;
			}
			return;
		}
		int[] count = new int[256];
		for (byte i : array) ++ count[i & 0xff];
		for (int i = 0, j = 0;j < count.length;++ j) java.util.Arrays.fill(array, i, i += count[j], (byte)j);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity O(toIndex-fromIndex)
	 * @param array 配列
	 */
	public static void sort(final byte[] array, int fromIndex, int toIndex) {
		if (toIndex - fromIndex < 128) {
			for (int i = fromIndex, j;i < toIndex;++ i) {
				byte tmp = array[i], tmp2;
				for (j = i;j > fromIndex && (tmp2 = array[j - 1]) > tmp;-- j) array[j] = tmp2;
				array[j] = tmp;
			}
			return;
		}
		int[] count = new int[256];
		for (int i = fromIndex;i < toIndex;++ i) ++ count[array[i] & 0xff];
		for (int i = fromIndex, j = 0;j < count.length;++ j) java.util.Arrays.fill(array, i, i += count[j], (byte)j);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity O(range.getDistance())
	 * @param array 配列
	 */
	public static void sort(final byte[] array, IntRange range) {
		sort(array, range.getClosedLower(), range.getOpenUpper());
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity Nを配列長として O(N log N)
	 * @param array 配列
	 */
	public static void sort(final short[] array) {
		if (array.length < 1024) java.util.Arrays.sort(array);
		else sort(array, 0, array.length, 0, new short[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=toIndex-fromIndex として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final short[] array, int fromIndex, int toIndex) {
		if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
		else sort(array, fromIndex, toIndex, 0, new short[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=range.getDistance() として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final short[] array, IntRange range) {
		sort(array, range.getClosedLower(), range.getOpenUpper());
	}

	private static final void sort(short[] a, final int from, final int to, final int l, final short[] bucket) {
		final int BUCKET_SIZE = 256;
		final int SHORT_RECURSION = 2;
		final int MASK = 0xff;
		final int shift = l << 3;
		final int[] cnt = new int[BUCKET_SIZE + 1];
		final int[] put = new int[BUCKET_SIZE];
		for (int i = from; i < to; i++) ++ cnt[(a[i] >>> shift & MASK) + 1];
		for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
		for (int i = from; i < to; i++) {
			int bi = a[i] >>> shift & MASK;
			bucket[cnt[bi] + put[bi]++] = a[i];
		}
		for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
			int begin = cnt[i];
			int len = cnt[i + 1] - begin;
			System.arraycopy(bucket, begin, a, idx, len);
			idx += len;
	    }
		final int nxtL = l + 1;
		if (nxtL < SHORT_RECURSION) {
			sort(a, from, to, nxtL, bucket);
			if (l == 0) {
				int lft, rgt;
				lft = from - 1; rgt = to;
				while (rgt - lft > 1) {
					int mid = lft + rgt >> 1;
					if (a[mid] < 0) lft = mid;
					else rgt = mid;
				}
				reverse(a, from, rgt);
				reverse(a, rgt, to);
	        }
	    }
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity Nを配列長として O(N log N)
	 * @param array 配列
	 */
	public static void sort(final int[] array) {
		if (array.length < 1024) java.util.Arrays.sort(array);
		else sort(array, 0, array.length, 0, new int[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=toIndex-fromIndex として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final int[] array, int fromIndex, int toIndex) {
		if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
		else sort(array, fromIndex, toIndex, 0, new int[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=range.getDistance() として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final int[] array, IntRange range) {
		sort(array, range.getClosedLower(), range.getOpenUpper());
	}

	private static final void sort(int[] a, final int from, final int to, final int l, final int[] bucket) {
		final int BUCKET_SIZE = 256;
		final int INT_RECURSION = 4;
		final int MASK = 0xff;
		final int shift = l << 3;
		final int[] cnt = new int[BUCKET_SIZE + 1];
		final int[] put = new int[BUCKET_SIZE];
		for (int i = from; i < to; i++) ++ cnt[(a[i] >>> shift & MASK) + 1];
		for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
		for (int i = from; i < to; i++) {
			int bi = a[i] >>> shift & MASK;
			bucket[cnt[bi] + put[bi]++] = a[i];
		}
		for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
			int begin = cnt[i];
			int len = cnt[i + 1] - begin;
			System.arraycopy(bucket, begin, a, idx, len);
			idx += len;
	    }
		final int nxtL = l + 1;
		if (nxtL < INT_RECURSION) {
			sort(a, from, to, nxtL, bucket);
			if (l == 0) {
				int lft, rgt;
				lft = from - 1; rgt = to;
				while (rgt - lft > 1) {
					int mid = lft + rgt >> 1;
					if (a[mid] < 0) lft = mid;
					else rgt = mid;
				}
				reverse(a, from, rgt);
				reverse(a, rgt, to);
	        }
	    }
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity Nを配列長として O(N log N)
	 * @param array 配列
	 */
	public static void sort(final long[] array) {
		if (array.length < 1024) java.util.Arrays.sort(array);
		else sort(array, 0, array.length, 0, new long[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=toIndex-fromIndex として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final long[] array, int fromIndex, int toIndex) {
		if (toIndex - fromIndex < 1024) java.util.Arrays.sort(array, fromIndex, toIndex);
		else sort(array, fromIndex, toIndex, 0, new long[array.length]);
	}

	/**
	 * 配列を昇順にソートします。
	 * @complexity N=range.getDistance() として O(N log N)
	 * @param array 元の配列
	 * @param fromIndex ソートする左閉区間
	 * @param toIndex ソートする右開区間
	 */
	public static void sort(final long[] array, IntRange range) {
		sort(array, range.getClosedLower(), range.getOpenUpper());
	}

	private static final void sort(long[] a, final int from, final int to, final int l, final long[] bucket) {
		final int BUCKET_SIZE = 256;
		final int LONG_RECURSION = 8;
		final int MASK = 0xff;
		final int shift = l << 3;
		final int[] cnt = new int[BUCKET_SIZE + 1];
		final int[] put = new int[BUCKET_SIZE];
		for (int i = from; i < to; i++) ++ cnt[(int) ((a[i] >>> shift & MASK) + 1)];
		for (int i = 0; i < BUCKET_SIZE; i++) cnt[i + 1] += cnt[i];
		for (int i = from; i < to; i++) {
			int bi = (int) (a[i] >>> shift & MASK);
			bucket[cnt[bi] + put[bi]++] = a[i];
		}
		for (int i = BUCKET_SIZE - 1, idx = from; i >= 0; i--) {
			int begin = cnt[i];
			int len = cnt[i + 1] - begin;
			System.arraycopy(bucket, begin, a, idx, len);
			idx += len;
	    }
		final int nxtL = l + 1;
		if (nxtL < LONG_RECURSION) {
			sort(a, from, to, nxtL, bucket);
			if (l == 0) {
				int lft, rgt;
				lft = from - 1; rgt = to;
				while (rgt - lft > 1) {
					int mid = lft + rgt >> 1;
					if (a[mid] < 0) lft = mid;
					else rgt = mid;
				}
				reverse(a, from, rgt);
				reverse(a, rgt, to);
	        }
	    }
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nを配列長として O(N log N)
	 * @param array 座標圧縮を行う配列
	 * @return arrayを座標圧縮した配列
	 */
	public static int[] compress(int[] array) {
		int[] ret = new int[array.length];
		int[] copy = java.util.Arrays.copyOf(array, array.length);
		sort(copy);
		int len = 1;
		for (int j = 1;j < array.length;++ j) {
			if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
		}
		for (int i = 0;i < array.length;++ i) {
			int min = 0, max = len;
			int comp = array[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copy[mid] <= comp) min = mid;
				else max = mid;
			}
			ret[i] = min;
		}
		return ret;
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nを配列長として O(N log N)
	 * @param array 座標圧縮を行う配列
	 * @return arrayを座標圧縮した配列
	 */
	public static int[] compress(long[] array) {
		int[] ret = new int[array.length];
		long[] copy = java.util.Arrays.copyOf(array, array.length);
		sort(copy);
		int len = 1;
		for (int j = 1;j < array.length;++ j) {
			if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
		}
		for (int i = 0;i < array.length;++ i) {
			int min = 0, max = len;
			long comp = array[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copy[mid] <= comp) min = mid;
				else max = mid;
			}
			ret[i] = min;
		}
		return ret;
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nを配列長として O(N log N)
	 * @param array 座標圧縮を行う配列
	 * @return arrayを座標圧縮した配列
	 */
	public static <T extends Comparable<T>> int[] compress(T[] array) {
		int[] ret = new int[array.length];
		T[] copy = java.util.Arrays.copyOf(array, array.length);
		java.util.Arrays.sort(copy);
		int len = 1;
		for (int j = 1;j < array.length;++ j) {
			if (copy[len - 1] != copy[j]) copy[len++] = copy[j];
		}
		for (int i = 0;i < array.length;++ i) {
			int min = 0, max = len;
			T comp = array[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copy[mid].compareTo(comp) <= 0) min = mid;
				else max = mid;
			}
			ret[i] = min;
		}
		return ret;
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]&lt;array[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、array[i]==array[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nを配列長として O(N log N)
	 * @param array 座標圧縮を行う配列
	 * @param comparator 比較関数
	 * @return arrayを座標圧縮した配列
	 */
	public static <T> int[] compress(T[] array, java.util.Comparator<T> comparator) {
		int[] ret = new int[array.length];
		T[] copy = java.util.Arrays.copyOf(array, array.length);
		java.util.Arrays.sort(copy, comparator);
		int len = 1;
		for (int j = 1;j < array.length;++ j) {
			if (!copy[len - 1].equals(copy[j])) copy[len++] = copy[j];
		}
		for (int i = 0;i < array.length;++ i) {
			int min = 0, max = len;
			T comp = array[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (comparator.compare(copy[mid], comp) <= 0) min = mid;
				else max = mid;
			}
			ret[i] = min;
		}
		return ret;
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]&lt;list[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]==list[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nをリスト長として O(N log N)
	 * @param list 座標圧縮を行うリスト
	 * @return listを座標圧縮した配列
	 * @throws NullPointerException listがnullの場合
	 */
	public static <T extends Comparable<T>> int[] compress(java.util.List<T> list) {
		int size = list.size();
		int[] ret = new int[size];
		java.util.ArrayList<T> copy = new java.util.ArrayList<>(list);
		copy.sort(java.util.Comparator.naturalOrder());
		int len = 1;
		for (int j = 1;j < size;++ j) {
			if (!copy.get(len - 1).equals(copy.get(j))) copy.set(len++, copy.get(j));
		}
		java.util.Iterator<T> iter = list.iterator();
		for (int i = 0;i < size;++ i) {
			int min = 0, max = len;
			T comp = iter.next();
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copy.get(mid).compareTo(comp) <= 0) min = mid;
				else max = mid;
			}
			ret[i] = min;
		}
		return ret;
	}

	/**
	 * 座標圧縮した配列を返します。
	 * この関数によって返される配列をretとしたとき、retは次の条件を満たします。
	 * <ul>
	 * <li>任意の正整数nに対し、contains(ret, n)がtrueならcontains(ret, n-1)もtrue</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]&lt;list[j]ならret[i]&lt;ret[j]</li>
	 * <li>0≦i, j&lt;nを満たすi, jに対し、list[i]==list[j]ならret[i]==ret[j]</li>
	 * </ul>
	 * @complexity Nをリスト長として O(N log N)
	 * @param list 座標圧縮を行うリスト
	 * @param comparator 比較関数
	 * @return listを座標圧縮した配列
	 */
	public static <T> int[] compress(java.util.List<T> list, java.util.Comparator<T> comparator) {
		int[] ret = new int[list.size()];
		java.util.ArrayList<T> copy = new java.util.ArrayList<>(list);
		copy.sort(comparator);
		int[] bit = new int[list.size() + 1];
		java.util.Iterator<T> iter = list.iterator();
		for (int i = 0;i < list.size();++ i) {
			int min = 0, max = list.size();
			T comp = iter.next();
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (comparator.compare(copy.get(mid), comp) <= 0) min = mid;
				else max = mid;
			}
			for (int j = max;j != 0;j -= j & -j) ret[i] += bit[j];
			for (int j = max;j < bit.length;j += j & -j) ++ bit[j];
		}
		return ret;
	}

	/**
	 * 配列の転倒数を求めます。すなわち、i<jかつarray[i]>array[j]となる(i, j)の個数を求めます。
	 * @complexity Nを配列長として O(N log N)
	 * @param array 配列
	 * @return 転倒数
	 */
	public static long inversionNumber(int[] array) {
		if (array == null) return 0;
		int[] copy = java.util.Arrays.copyOf(array, array.length);
		sort(copy);
		int[] bit = new int[array.length + 1];
		long ans = (long)array.length * (array.length - 1) >> 1;
		for (int i = 0;i < array.length;++ i) {
			int min = 0, max = array.length;
			int comp = array[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copy[mid] <= comp) min = mid;
				else max = mid;
			}
			for (int j = max;j != 0;j -= j & -j) ans -= bit[j];
			for (int j = max;j < bit.length;j += j & -j) ++ bit[j];
		}
		return ans;
	}

	/**
	 * 2つの配列の転倒距離を求めます。つまり、配列srcの隣接する2要素をswapして配列destと一致させるまでのswap回数の最小値を求めます。
	 * @complexity N=src.length, M=dest.lengthとしてO((N+M)log(N+M))
	 * @param src 配列
	 * @param dest 配列
	 * @return srcとdestの転倒距離、ただしsrcを隣接swapすることでdestが構築できない場合は-1
	 */
	public static long inversionDistance(int[] src, int[] dest) {
		if (src == null || dest == null) return src == null && dest == null ? 0 : -1;
		int[] copySrc = java.util.Arrays.copyOf(src, src.length), copyDest = java.util.Arrays.copyOf(dest, dest.length);
		sort(copySrc);
		sort(copyDest);
		if (!java.util.Arrays.equals(copySrc, copyDest)) return -1;
		int[] key = new int[dest.length];
		for (int i = 0;i < dest.length;++ i) {
			int min = -1, max = dest.length;
			int comp = dest[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copyDest[mid] < comp) min = mid;
				else max = mid;
			}
			key[max] = i;
			copyDest[max] = max == 0 ? Integer.MIN_VALUE : copyDest[max - 1];
		}
		int[] bit = new int[src.length + 1];
		long ans = (long)src.length * (src.length - 1) >> 1;
		for (int i = 0;i < src.length;++ i) {
			int min = -1, max = src.length;
			int comp = src[i];
			while(max - min > 1) {
				int mid = min + max >> 1;
				if (copySrc[mid] < comp) min = mid;
				else max = mid;
			}
			copySrc[max] = max == 0 ? Integer.MIN_VALUE : copySrc[max - 1];
			max = key[max] + 1;
			for (int j = max;j != 0;j -= j & -j) ans -= bit[j];
			for (int j = max;j < bit.length;j += j & -j) ++ bit[j];
		}
		return ans;
	}
}