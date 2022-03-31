package com._31536000.algo;

import java.awt.Point;
import java.util.Arrays;

public final class Distance {

	private Distance() {
		throw new AssertionError();
	}

	/**
	 * aとbのユークリッド距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離
	 */
	public static double euclidDistance(final int[] a, final int[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (long)(a[i] - b[i]) * (a[i] - b[i]);
		return Math.sqrt(ans);
	}

	/**
	 * aとbのユークリッド距離の2乗を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離の2乗
	 */
	public static long squareEuclidDistance(final int[] a, final int[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		long ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (long)(a[i] - b[i]) * (a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのユークリッド距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離
	 */
	public static double euclidDistance(final long[] a, final long[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (a[i] - b[i]) * (a[i] - b[i]);
		return Math.sqrt(ans);
	}

	/**
	 * aとbのユークリッド距離の2乗を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離の2乗
	 */
	public static long squareEuclidDistance(final long[] a, final long[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		long ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (a[i] - b[i]) * (a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのユークリッド距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離
	 */
	public static double euclidDistance(final float[] a, final float[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (a[i] - b[i]) * (a[i] - b[i]);
		return Math.sqrt(ans);
	}

	/**
	 * aとbのユークリッド距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離
	 */
	public static double euclidDistance(final double[] a, final double[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += (a[i] - b[i]) * (a[i] - b[i]);
		return Math.sqrt(ans);
	}

	/**
	 * aとbのユークリッド距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離
	 */
	public static double euclidDistance(final Point a, final Point b) {
		return Math.sqrt((long)(b.x - a.x) * (b.x - a.x) + (long)(b.y - a.y) * (b.y - a.y));
	}

	/**
	 * aとbのユークリッド距離の2乗を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのユークリッド距離の2乗
	 */
	public static long squareEuclidDistance(final Point a, final Point b) {
		return (long)(b.x - a.x) * (b.x - a.x) + (long)(b.y - a.y) * (b.y - a.y);
	}

	/**
	 * aとbのマンハッタン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのマンハッタン距離
	 */
	public static long manhattanDistance(final int[] a, final int[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		long ans = 0;
		for (int i = 0;i < a.length;++ i) ans += Math.abs(a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのマンハッタン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのマンハッタン距離
	 */
	public static long manhattanDistance(final long[] a, final long[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		long ans = 0;
		for (int i = 0;i < a.length;++ i) ans += Math.abs(a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのマンハッタン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのマンハッタン距離
	 */
	public static double manhattanDistance(final float[] a, final float[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += Math.abs(a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのマンハッタン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのマンハッタン距離
	 */
	public static double manhattanDistance(final double[] a, final double[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans += Math.abs(a[i] - b[i]);
		return ans;
	}

	/**
	 * aとbのマンハッタン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのマンハッタン距離
	 */
	public static long manhattanDistance(final Point a, final Point b) {
		return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
	}

	/**
	 * aとbのチェビシェフ距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのチェビシェフ距離
	 */
	public static int chebyshevDistance(final int[] a, final int[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		int ans = 0;
		for (int i = 0;i < a.length;++ i) ans = Math.max(ans, Math.abs(a[i] - b[i]));
		return ans;
	}

	/**
	 * aとbのチェビシェフ距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのチェビシェフ距離
	 */
	public static long chebyshevDistance(final long[] a, final long[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		long ans = 0;
		for (int i = 0;i < a.length;++ i) ans = Math.max(ans, Math.abs(a[i] - b[i]));
		return ans;
	}

	/**
	 * aとbのチェビシェフ距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのチェビシェフ距離
	 */
	public static float chebyshevDistance(final float[] a, final float[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		float ans = 0;
		for (int i = 0;i < a.length;++ i) ans = Math.max(ans, Math.abs(a[i] - b[i]));
		return ans;
	}

	/**
	 * aとbのチェビシェフ距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのチェビシェフ距離
	 */
	public static double chebyshevDistance(final double[] a, final double[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("invalid dimension");
		double ans = 0;
		for (int i = 0;i < a.length;++ i) ans = Math.max(ans, Math.abs(a[i] - b[i]));
		return ans;
	}

	/**
	 * aとbのチェビシェフ距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのチェビシェフ距離
	 */
	public static int chebyshevDistance(final Point a, final Point b) {
		return Math.max(Math.abs(b.x - a.x), Math.abs(b.y - a.y));
	}

	/**
	 * aとbのハミング距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのハミング距離
	 */
	public static int hammingDistance(final String a, final String b) {
		if (a.length() != b.length()) throw new IllegalArgumentException("not equal length");
		int ret = 0;
		for (int i = 0, l = a.length();i < l;++ i) if (a.charAt(i) != b.charAt(i)) ++ ret;
		return ret;
	}

	/**
	 * aとbのハミング距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのハミング距離
	 */
	public static int hammingDistance(final char[] a, final char[] b) {
		if (a.length != b.length) throw new IllegalArgumentException("not equal length");
		int ret = 0;
		for (int i = 0, l = a.length;i < l;++ i) if (a[i] != b[i]) ++ ret;
		return ret;
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final String a, final String b) {
		return levenshteinDistance(a, b, 1, 1, 1);
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @param insertion 挿入コスト
	 * @param deletion 削除コスト
	 * @param replacement 変更コスト
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final String a, final String b, final int insertion, final int deletion, final int replacement) {
		final int[][] dp = new int[a.length() + 1][b.length() + 1];
		for (int i = 0;i < dp.length;++ i) {
			Arrays.fill(dp[i], 0);
			dp[i][0] = i;
		}
		for (int i = 0;i <= b.length();++ i) dp[0][i] = i;
		for (int i = 1;i < dp.length;++ i) {
			for (int j = 1;j < dp[i].length;++ j) {
				dp[i][j] = Math.min(Math.min(dp[i - 1][j] + insertion, dp[i][j - 1] + deletion), dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : replacement));
			}
		}
		return dp[a.length()][b.length()];
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final char[] a, final char[] b) {
		return levenshteinDistance(a, b, 1, 1, 1);
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @param insertion 挿入コスト
	 * @param deletion 削除コスト
	 * @param replacement 変更コスト
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final char[] a, final char[] b, final int insertion, final int deletion, final int replacement) {
		final int[][] dp = new int[a.length + 1][b.length + 1];
		for (int i = 0;i < dp.length;++ i) {
			Arrays.fill(dp[i], 0);
			dp[i][0] = i;
		}
		for (int i = 0;i <= b.length;++ i) dp[0][i] = i;
		for (int i = 1;i < dp.length;++ i) {
			for (int j = 1;j < dp[i].length;++ j) {
				dp[i][j] = Math.min(Math.min(dp[i - 1][j] + insertion, dp[i][j - 1] + deletion), dp[i - 1][j - 1] + (a[i - 1] == b[j - 1] ? 0 : replacement));
			}
		}
		return dp[a.length][b.length];
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final int[] a, final int[] b) {
		return levenshteinDistance(a, b, 1, 1, 1);
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @param insertion 挿入コスト
	 * @param deletion 削除コスト
	 * @param replacement 変更コスト
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final int[] a, final int[] b, final int insertion, final int deletion, final int replacement) {
		final int[][] dp = new int[a.length + 1][b.length + 1];
		for (int i = 0;i < dp.length;++ i) {
			Arrays.fill(dp[i], 0);
			dp[i][0] = i;
		}
		for (int i = 0;i <= b.length;++ i) dp[0][i] = i;
		for (int i = 1;i < dp.length;++ i) {
			for (int j = 1;j < dp[i].length;++ j) {
				dp[i][j] = Math.min(Math.min(dp[i - 1][j] + insertion, dp[i][j - 1] + deletion), dp[i - 1][j - 1] + (a[i - 1] == b[j - 1] ? 0 : replacement));
			}
		}
		return dp[a.length][b.length];
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final long[] a, final long[] b) {
		return levenshteinDistance(a, b, 1, 1, 1);
	}

	/**
	 * aとbのレーベンシュタイン距離を導出します。
	 * @param a 始点
	 * @param b 終点
	 * @param insertion 挿入コスト
	 * @param deletion 削除コスト
	 * @param replacement 変更コスト
	 * @return aとbのレーベンシュタイン距離
	 */
	public static int levenshteinDistance(final long[] a, final long[] b, final int insertion, final int deletion, final int replacement) {
		final int[][] dp = new int[a.length + 1][b.length + 1];
		for (int i = 0;i < dp.length;++ i) {
			Arrays.fill(dp[i], 0);
			dp[i][0] = i;
		}
		for (int i = 0;i <= b.length;++ i) dp[0][i] = i;
		for (int i = 1;i < dp.length;++ i) {
			for (int j = 1;j < dp[i].length;++ j) {
				dp[i][j] = Math.min(Math.min(dp[i - 1][j] + insertion, dp[i][j - 1] + deletion), dp[i - 1][j - 1] + (a[i - 1] == b[j - 1] ? 0 : replacement));
			}
		}
		return dp[a.length][b.length];
	}
}
