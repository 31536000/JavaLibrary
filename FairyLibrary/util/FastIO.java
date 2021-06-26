package util;


/**
 * 高速な入出力を提供します。
 *
 * @author 31536000
 *
 */
public final class FastIO implements AutoCloseable {

	private Input in;
	private Output out;
	private Output err;
	private boolean outFlush = false;
	private boolean autoOutFlush = true;
	public static final java.io.PrintStream DUMMY_OUT = new DummyOut();

	public FastIO() {
		this(System.in, System.out, System.err);
	}

	public FastIO(final java.io.InputStream in, final java.io.PrintStream out, final java.io.PrintStream err) {
		this.in = in instanceof Input ? (Input) in : new Input(in);
		if (out instanceof Output) {
			this.out = (Output) out;
		} else {
			this.out = new Output(out);
			this.out.setAutoFlush(false);
		}
		if (err instanceof Output) {
			this.err = (Output) err;
		} else {
			this.err = new Output(err);
			this.err.setAutoFlush(false);
		}
	}

	public static void setFastStandardOutput(final boolean set) {
		final java.io.FileOutputStream fdOut = new java.io.FileOutputStream(java.io.FileDescriptor.out);
		final java.io.FileOutputStream fdErr = new java.io.FileOutputStream(java.io.FileDescriptor.err);
		if (set) {
			System.out.flush();
			final Output out = new Output(fdOut);
			out.setAutoFlush(false);
			System.setOut(out);
			System.err.flush();
			final Output err = new Output(fdErr);
			err.setAutoFlush(false);
			System.setErr(err);
		} else {
			System.out.flush();
			final java.io.PrintStream out = new java.io.PrintStream(new java.io.BufferedOutputStream(fdOut, 128), true);
			System.setOut(out);
			System.err.flush();
			final java.io.PrintStream err = new java.io.PrintStream(new java.io.BufferedOutputStream(fdErr, 128), true);
			System.setErr(err);
		}
	}

	public void setInputStream(final java.io.InputStream in) {
		if (this.in == in) return;
		this.in.close();
		this.in = in instanceof Input ? (Input) in : new Input(in);
	}

	public void setInputStream(final java.io.File in) {
		try {
			this.in.close();
			final java.io.InputStream input = new java.io.FileInputStream(in);
			this.in = new Input(input);
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Input getInputStream() { return in; }

	public void setOutputStream(final java.io.OutputStream out) {
		if (this.out == out) {
			this.out.flush();
		}
		final boolean flush = this.out.autoFlush;
		this.out.close();
		if (out instanceof Output) {
			this.out = (Output) out;
			this.out.setAutoFlush(flush);
		} else {
			this.out = new Output(out);
			this.out.setAutoFlush(flush);
		}
	}

	public void setOutputStream(final java.io.File out) {
		try {
			setOutputStream(new java.io.FileOutputStream(out));
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setOutputStream(final java.io.FileDescriptor out) {
		setOutputStream(new java.io.FileOutputStream(out));
	}

	public Output getOutputStream() { return out; }

	public void setErrorStream(final java.io.OutputStream err) {
		if (this.err == err) {
			this.err.flush();
		}
		final boolean flush = this.err.autoFlush;
		this.err.close();
		if (err instanceof Output) {
			this.err = (Output) err;
			this.err.setAutoFlush(flush);
		} else {
			this.err = new Output(err);
			this.err.setAutoFlush(flush);
		}
	}

	public void setErrorStream(final java.io.File err) {
		try {
			setErrorStream(new java.io.FileOutputStream(err));
		} catch (final java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setErrorStream(final java.io.FileDescriptor err) {
		setErrorStream(new java.io.FileOutputStream(err));
	}

	public Output getErrorStream() { return err; }

	public void setAutoFlush(final boolean flush) {
		out.setAutoFlush(flush);
		err.setAutoFlush(flush);
	}

	public void setAutoOutFlush(final boolean flush) { autoOutFlush = flush; }

	private void autoFlush() {
		if (outFlush) {
			outFlush = false;
			flush();
		}
	}

	public boolean hasNext() {
		autoFlush();
		return in.hasNext();
	}

	public boolean nextBoolean() {
		autoFlush();
		return in.nextBoolean();
	}

	public boolean[] nextBoolean(final char T) {
		final char[] s = nextChars();
		final boolean[] ret = new boolean[s.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = s[i] == T;
		return ret;
	}

	public boolean[][] nextBoolean(final char T, final int height) {
		final boolean[][] ret = new boolean[height][];
		for (int i = 0; i < ret.length; ++i) {
			final char[] s = nextChars();
			ret[i] = new boolean[s.length];
			for (int j = 0; j < ret[i].length; ++j) ret[i][j] = s[j] == T;
		}
		return ret;
	}

	public byte nextByte() {
		autoFlush();
		return in.nextByte();
	}

	public short nextShort() {
		autoFlush();
		return in.nextShort();
	}

	public short[] nextShort(final int width) {
		final short[] ret = new short[width];
		for (int i = 0; i < width; ++i) ret[i] = nextShort();
		return ret;
	}

	public short[][] nextShort(final int width, final int height) {
		final short[][] ret = new short[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[i][j] = nextShort();
		return ret;
	}

	public int nextInt() {
		autoFlush();
		return in.nextInt();
	}

	public int[] nextInt(final int width) {
		final int[] ret = new int[width];
		for (int i = 0; i < width; ++i) ret[i] = nextInt();
		return ret;
	}

	public int[][] nextInt(final int width, final int height) {
		final int[][] ret = new int[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[i][j] = nextInt();
		return ret;
	}

	public int[] nextInts() {
		return nextInts(" ");
	}

	public int[] nextInts(final String parse) {
		final String[] get = nextLine().split(parse);
		final int[] ret = new int[get.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = Integer.valueOf(get[i]);
		return ret;
	}

	public long nextLong() {
		autoFlush();
		return in.nextLong();
	}

	public long[] nextLong(final int width) {
		final long[] ret = new long[width];
		for (int i = 0; i < width; ++i) ret[i] = nextLong();
		return ret;
	}

	public long[][] nextLong(final int width, final int height) {
		final long[][] ret = new long[height][width];
		for (int i = 0, j; i < height; ++i) for (j = 0; j < width; ++j) ret[j][i] = nextLong();
		return ret;
	}

	public long[] nextLongs() {
		return nextLongs(" ");
	}

	public long[] nextLongs(final String parse) {
		final String[] get = nextLine().split(parse);
		final long[] ret = new long[get.length];
		for (int i = 0; i < ret.length; ++i) ret[i] = Long.valueOf(get[i]);
		return ret;
	}

	public float nextFloat() {
		autoFlush();
		return in.nextFloat();
	}

	public double nextDouble() {
		autoFlush();
		return in.nextDouble();
	}

	public char nextChar() {
		autoFlush();
		return in.nextChar();
	}

	public char[] nextChars() {
		return next().toCharArray();
	}

	public char[] nextChars(final char around) {
		return (around + next() + around).toCharArray();
	}

	public char[][] nextChars(final int height) {
		final char[][] ret = new char[height][];
		for (int i = 0; i < ret.length; ++i) ret[i] = nextChars();
		return ret;
	}

	public char[][] nextChars(final int height, final char around) {
		final char[][] ret = new char[height + 2][];
		for (int i = 1; i <= height; ++i) ret[i] = nextChars(around);
		java.util.Arrays.fill(ret[0] = new char[ret[1].length], around);
		java.util.Arrays.fill(ret[ret.length - 1] = new char[ret[0].length], around);
		return ret;
	}

	public String next() {
		autoFlush();
		return in.next();
	}

	public String nextLine() {
		autoFlush();
		return in.nextLine();
	}

	public java.awt.Point nextPoint() {
		return new java.awt.Point(nextInt(), nextInt());
	}

	public java.awt.Point[] nextPoint(final int width) {
		final java.awt.Point[] ret = new java.awt.Point[width];
		for (int i = 0; i < width; ++i) ret[i] = nextPoint();
		return ret;
	}

	public boolean print(final boolean b) {
		out.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte print(final byte b) {
		out.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short print(final short s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int print(final int i) {
		out.print(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long print(final long l) {
		out.print(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float print(final float f) {
		out.print(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double print(final double d) {
		out.print(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double print(final double d, final int length) {
		out.print(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char print(final char c) {
		out.print(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] print(final char[] s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String print(final String s) {
		out.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object print(final Object obj) {
		if (obj != null && obj.getClass().isArray()) {
			if (obj instanceof boolean[][]) print(obj, "\n", " ");
			else if (obj instanceof byte[][]) print(obj, "\n", " ");
			else if (obj instanceof short[][]) print(obj, "\n", " ");
			else if (obj instanceof int[][]) print(obj, "\n", " ");
			else if (obj instanceof long[][]) print(obj, "\n", " ");
			else if (obj instanceof float[][]) print(obj, "\n", " ");
			else if (obj instanceof double[][]) print(obj, "\n", " ");
			else if (obj instanceof char[][]) print(obj, "\n", " ");
			else if (obj instanceof Object[][]) print(obj, "\n", " ");
			else print(obj, " ");
		} else {
			out.print(obj);
			outFlush = autoOutFlush;
		}
		return obj;
	}

	public Object print(final Object array, final String... parse) {
		print(array, 0, parse);
		return array;
	}

	private Object print(final Object array, final int check, final String... parse) {
		if (check >= parse.length) {
			if (array != null && array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
			print(array);
			return array;
		}
		final String str = parse[check];
		if (array instanceof Object[]) {
			final Object[] obj = (Object[]) array;
			if (obj.length == 0) return array;
			print(obj[0], check + 1, parse);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i], check + 1, parse);
			}
			return array;
		}
		if (array instanceof java.util.Collection) {
			final java.util.Iterator<?> iter = ((java.util.Collection<?>) array).iterator();
			if (!iter.hasNext()) return array;
			print(iter.next(), check + 1, parse);
			while (iter.hasNext()) {
				print(str);
				print(iter.next(), check + 1, parse);
			}
			return array;
		}
		if (!array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
		if (check != parse.length - 1) throw new IllegalArgumentException("not equal dimension");
		if (array instanceof boolean[]) {
			final boolean[] obj = (boolean[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof byte[]) {
			final byte[] obj = (byte[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
			return array;
		} else if (array instanceof short[]) {
			final short[] obj = (short[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof int[]) {
			final int[] obj = (int[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof long[]) {
			final long[] obj = (long[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof float[]) {
			final float[] obj = (float[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof double[]) {
			final double[] obj = (double[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else if (array instanceof char[]) {
			final char[] obj = (char[]) array;
			if (obj.length == 0) return array;
			print(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				print(str);
				print(obj[i]);
			}
		} else throw new AssertionError();
		return array;
	}

	public Object[] print(final String parse, final Object... args) {
		print(args[0]);
		for (int i = 1; i < args.length; ++i) {
			print(parse);
			print(args[i]);
		}
		return args;
	}

	public Object[] printf(final String format, final Object... args) {
		out.printf(format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public Object[] printf(final java.util.Locale l, final String format, final Object... args) {
		out.printf(l, format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public void println() {
		out.println();
		outFlush = autoOutFlush;
	}

	public boolean println(final boolean b) {
		out.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte println(final byte b) {
		out.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short println(final short s) {
		out.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int println(final int i) {
		out.println(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long println(final long l) {
		out.println(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float println(final float f) {
		out.println(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double println(final double d) {
		out.println(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double println(final double d, final int length) {
		out.println(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char println(final char c) {
		out.println(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] println(final char[] s) {
		out.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String println(final String s) {
		out.println(s);
		return s;
	}

	public Object println(final Object obj) {
		print(obj);
		println();
		return obj;
	}

	public Object println(final Object array, final String... parse) {
		print(array, parse);
		println();
		return array;
	}

	public boolean debug(final boolean b) {
		err.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte debug(final byte b) {
		err.print(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short debug(final short s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int debug(final int i) {
		err.print(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long debug(final long l) {
		err.print(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float debug(final float f) {
		err.print(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double debug(final double d) {
		err.print(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double debug(final double d, final int length) {
		err.print(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char debug(final char c) {
		err.print(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] debug(final char[] s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String debug(final String s) {
		err.print(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object debug(final Object obj) {
		if (obj != null && obj.getClass().isArray()) {
			if (obj instanceof boolean[][]) debug(obj, "\n", " ");
			else if (obj instanceof byte[][]) debug(obj, "\n", " ");
			else if (obj instanceof short[][]) debug(obj, "\n", " ");
			else if (obj instanceof int[][]) debug(obj, "\n", " ");
			else if (obj instanceof long[][]) debug(obj, "\n", " ");
			else if (obj instanceof float[][]) debug(obj, "\n", " ");
			else if (obj instanceof double[][]) debug(obj, "\n", " ");
			else if (obj instanceof char[][]) debug(obj, "\n", " ");
			else if (obj instanceof Object[][]) debug(obj, "\n", " ");
			else debug(obj, " ");
		} else {
			err.print(obj);
			outFlush = autoOutFlush;
		}
		return obj;
	}

	public Object debug(final Object array, final String... parse) {
		debug(array, 0, parse);
		return array;
	}

	private Object debug(final Object array, final int check, final String... parse) {
		if (check >= parse.length) {
			if (array != null && array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
			debug(array);
			return array;
		}
		final String str = parse[check];
		if (array instanceof Object[]) {
			final Object[] obj = (Object[]) array;
			if (obj.length == 0) return array;
			debug(obj[0], check + 1, parse);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i], check + 1, parse);
			}
			return array;
		}
		if (array instanceof java.util.Collection) {
			final java.util.Iterator<?> iter = ((java.util.Collection<?>) array).iterator();
			if (!iter.hasNext()) return array;
			debug(iter.next(), check + 1, parse);
			while (iter.hasNext()) {
				debug(str);
				debug(iter.next(), check + 1, parse);
			}
			return array;
		}
		if (!array.getClass().isArray()) throw new IllegalArgumentException("not equal dimension");
		if (check != parse.length - 1) throw new IllegalArgumentException("not equal dimension");
		if (array instanceof boolean[]) {
			final boolean[] obj = (boolean[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof byte[]) {
			final byte[] obj = (byte[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
			return array;
		} else if (array instanceof short[]) {
			final short[] obj = (short[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof int[]) {
			final int[] obj = (int[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof long[]) {
			final long[] obj = (long[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof float[]) {
			final float[] obj = (float[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof double[]) {
			final double[] obj = (double[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else if (array instanceof char[]) {
			final char[] obj = (char[]) array;
			if (obj.length == 0) return array;
			debug(obj[0]);
			for (int i = 1; i < obj.length; ++i) {
				debug(str);
				debug(obj[i]);
			}
		} else throw new AssertionError();
		return array;
	}

	public Object[] debug(final String parse, final Object... args) {
		debug(args[0]);
		for (int i = 1; i < args.length; ++i) {
			debug(parse);
			debug(args[i]);
		}
		return args;
	}

	public Object[] debugf(final String format, final Object... args) {
		err.printf(format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public Object[] debugf(final java.util.Locale l, final String format, final Object... args) {
		err.printf(l, format, args);
		outFlush = autoOutFlush;
		return args;
	}

	public void debugln() {
		err.println();
		outFlush = autoOutFlush;
	}

	public boolean debugln(final boolean b) {
		err.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public byte debugln(final byte b) {
		err.println(b);
		outFlush = autoOutFlush;
		return b;
	}

	public short debugln(final short s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public int debugln(final int i) {
		err.println(i);
		outFlush = autoOutFlush;
		return i;
	}

	public long debugln(final long l) {
		err.println(l);
		outFlush = autoOutFlush;
		return l;
	}

	public float debugln(final float f) {
		err.println(f);
		outFlush = autoOutFlush;
		return f;
	}

	public double debugln(final double d) {
		err.println(d);
		outFlush = autoOutFlush;
		return d;
	}

	public double debugln(final double d, final int length) {
		err.println(d, length);
		outFlush = autoOutFlush;
		return d;
	}

	public char debugln(final char c) {
		err.println(c);
		outFlush = autoOutFlush;
		return c;
	}

	public char[] debugln(final char[] s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public String debugln(final String s) {
		err.println(s);
		outFlush = autoOutFlush;
		return s;
	}

	public Object debugln(final Object obj) {
		debug(obj);
		debugln();
		return obj;
	}

	public Object debugln(final Object array, final String... parse) {
		debug(array, parse);
		debugln();
		return array;
	}

	public void flush() {
		out.flush();
		err.flush();
		outFlush = false;
	}

	@Override
	public void close() {
		out.close();
		err.close();
	}

	public static final class Input extends java.io.InputStream {

		private final java.io.InputStream in;
		private final byte[] buffer = new byte[1 << 13];
		private int read = 0;
		private int length = 0;

		public Input(final java.io.InputStream in) {
			this.in = in;
		}

		@Override
		public int available() {
			try {
				return in.available();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
			return 0;
		}

		@Override
		public void close() {
			try {
				in.close();
				read = length = 0;
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public int read() {
			if (hasNextByte()) return nextByte();
			return 0;
		}

		private boolean hasNextByte() {
			if (read < length) return true;
			read = 0;
			try {
				length = in.read(buffer);
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
			return length > 0;
		}

		private static boolean isPrintableChar(final byte c) {
			return 32 < c || c < 0;
		}

		private static boolean isNumber(final byte c) {
			return '0' <= c && c <= '9';
		}

		private boolean readNewLine() {
			if (hasNextByte()) {
				if (buffer[read] == '\r') {
					++read;
					if (hasNextByte() && buffer[read] == '\n') ++read;
					return true;
				}
				if (buffer[read] == '\n') {
					++read;
					return true;
				}
			}
			return false;
		}

		public boolean hasNext() {
			while (hasNextByte() && !isPrintableChar(buffer[read])) read++;
			return hasNextByte();
		}

		private byte nextTokenByte() {
			while (hasNextByte() && !isPrintableChar(buffer[read])) read++;
			return buffer[read++];
		}

		public boolean nextBoolean() {
			return Boolean.valueOf(next());
		}

		public byte nextByte() {
			if (hasNextByte()) return buffer[read++];
			throw new java.util.NoSuchElementException();
		}

		public short nextShort() {
			byte b = nextTokenByte();
			short n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = (short) (n * 10 + '0' - b);
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = (short) (n * 10 + b - '0'); while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public int nextInt() {
			byte b = nextTokenByte();
			int n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = n * 10 + '0' - b;
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = n * 10 + b - '0'; while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public long nextLong() {
			byte b = nextTokenByte();
			long n = 0;
			try {
				if (b == '-') {
					while (isNumber(b = nextByte())) n = n * 10 + '0' - b;
					return n;
				} else if (!isNumber(b)) throw new NumberFormatException();
				do n = n * 10 + b - '0'; while (isNumber(b = nextByte()));
				return n;
			} catch (final java.util.NoSuchElementException e) {
				return n;
			}
		}

		public float nextFloat() {
			return Float.parseFloat(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char nextChar() {
			final byte b = nextByte();
			if ((b & 0x80) == 0) return (char) b;
			if ((b & 0x20) == 0) return (char) ((b & 0x1F) << 6 | nextByte() & 0x3F);
			return (char) ((b & 0xF) << 12 | (nextByte() & 0x3F) << 6 | nextByte() & 0x3F);
		}

		public String next() {
			if (!hasNext()) throw new java.util.NoSuchElementException();
			final StringBuilder sb = new StringBuilder();
			do sb.append(nextChar()); while (hasNextByte() && isPrintableChar(buffer[read]));
			return sb.toString();
		}

		public String nextLine() {
			final StringBuilder sb = new StringBuilder();
			while (!readNewLine()) sb.append(nextChar());
			return sb.toString();
		}

	}

	public static final class Output extends java.io.PrintStream {

		private final byte[] buffer = new byte[1 << 13];
		private int read = 0;
		private boolean autoFlush = true;

		public Output(final java.io.OutputStream out) {
			super(out);
		}

		public void setAutoFlush(final boolean autoFlush) { this.autoFlush = autoFlush; }

		@Override
		public void close() {
			if (out == System.out || out == System.err || this == System.out || this == System.err) {
				flush();
				return;
			}
			try {
				flush();
				out.close();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void flush() {
			try {
				write();
				out.flush();
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void write(final byte[] b) {
			if (b.length < buffer.length) {
				ensureBuffer(b.length);
				System.arraycopy(b, 0, buffer, read, b.length);
				read += b.length;
			} else {
				write();
				try {
					out.write(b);
				} catch (final java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void write(final byte[] b, final int off, final int len) {
			if (len < buffer.length) {
				ensureBuffer(len);
				System.arraycopy(b, off, buffer, read, len);
				read += len;
			} else {
				write();
				try {
					out.write(b, off, len);
				} catch (final java.io.IOException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void write(final int b) {
			print((byte) b);
		}

		private void write() {
			try {
				out.write(buffer, 0, read);
				read = 0;
			} catch (final java.io.IOException e) {
				e.printStackTrace();
			}
		}

		private void ensureBuffer(final int size) {
			if (read + size > buffer.length) {
				write();
			}
		}

		@Override
		public void print(final boolean b) {
			if (b) {
				ensureBuffer(4);
				buffer[read++] = 't';
				buffer[read++] = 'r';
				buffer[read++] = 'u';
				buffer[read++] = 'e';
			} else {
				ensureBuffer(5);
				buffer[read++] = 'f';
				buffer[read++] = 'a';
				buffer[read++] = 'l';
				buffer[read++] = 's';
				buffer[read++] = 'e';
			}
		}

		public void print(final byte b) {
			ensureBuffer(1);
			buffer[read++] = b;
		}

		private static int digit(final short s) {
			return s >= 100 ? s >= 1000 ? s >= 10000 ? 5 : 4 : 3 : s >= 10 ? 2 : 1;
		}

		public void print(short s) {
			ensureBuffer(6);
			if (s < 0) {
				if (s == -32768) {
					buffer[read++] = '-';
					buffer[read++] = '3';
					buffer[read++] = '2';
					buffer[read++] = '7';
					buffer[read++] = '6';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				s = (short) -s;
			}
			final int digit = digit(s);
			int i = read + digit;
			while (i-- > read) {
				buffer[i] = (byte) (s % 10 + '0');
				s /= 10;
			}
			read += digit;
		}

		private static int digit(final int i) {
			if (i >= 1000000000) return 10;
			if (i >= 100000000) return 9;
			if (i >= 10000000) return 8;
			if (i >= 1000000) return 7;
			if (i >= 100000) return 6;
			if (i >= 10000) return 5;
			if (i >= 1000) return 4;
			if (i >= 100) return 3;
			if (i >= 10) return 2;
			return 1;
		}

		@Override
		public void print(int i) {
			ensureBuffer(11);
			if (i < 0) {
				if (i == -2147483648) {
					buffer[read++] = '-';
					buffer[read++] = '2';
					buffer[read++] = '1';
					buffer[read++] = '4';
					buffer[read++] = '7';
					buffer[read++] = '4';
					buffer[read++] = '8';
					buffer[read++] = '3';
					buffer[read++] = '6';
					buffer[read++] = '4';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				i = -i;
			}
			final int digit = digit(i);
			int j = read + digit;
			while (j-- > read) {
				buffer[j] = (byte) (i % 10 + '0');
				i /= 10;
			}
			read += digit;
		}

		private static int digit(final long l) {
			if (l >= 1000000000000000000L) return 19;
			if (l >= 100000000000000000L) return 18;
			if (l >= 10000000000000000L) return 17;
			if (l >= 1000000000000000L) return 16;
			if (l >= 100000000000000L) return 15;
			if (l >= 10000000000000L) return 14;
			if (l >= 1000000000000L) return 13;
			if (l >= 100000000000L) return 12;
			if (l >= 10000000000L) return 11;
			if (l >= 1000000000L) return 10;
			if (l >= 100000000L) return 9;
			if (l >= 10000000L) return 8;
			if (l >= 1000000L) return 7;
			if (l >= 100000L) return 6;
			if (l >= 10000L) return 5;
			if (l >= 1000L) return 4;
			if (l >= 100L) return 3;
			if (l >= 10L) return 2;
			return 1;
		}

		@Override
		public void print(long l) {
			ensureBuffer(20);
			if (l < 0) {
				if (l == -9223372036854775808L) {
					buffer[read++] = '-';
					buffer[read++] = '9';
					buffer[read++] = '2';
					buffer[read++] = '2';
					buffer[read++] = '3';
					buffer[read++] = '3';
					buffer[read++] = '7';
					buffer[read++] = '2';
					buffer[read++] = '0';
					buffer[read++] = '3';
					buffer[read++] = '6';
					buffer[read++] = '8';
					buffer[read++] = '5';
					buffer[read++] = '4';
					buffer[read++] = '7';
					buffer[read++] = '7';
					buffer[read++] = '5';
					buffer[read++] = '8';
					buffer[read++] = '0';
					buffer[read++] = '8';
					return;
				}
				buffer[read++] = '-';
				l = -l;
			}
			final int digit = digit(l);
			int i = read + digit;
			while (i-- > read) {
				buffer[i] = (byte) (l % 10 + '0');
				l /= 10;
			}
			read += digit;
		}

		@Override
		public void print(final float f) {
			print(Float.toString(f));
		}

		@Override
		public void print(final double d) {
			print(Double.toString(d));
		}

		public void print(double d, final int n) {
			if (d < 0) {
				ensureBuffer(1);
				buffer[read++] = '-';
				d = -d;
			}
			d += Math.pow(10, -n) / 2;
			final long l = (long) d;
			print(l);
			ensureBuffer(n + 1);
			buffer[read++] = '.';
			d -= l;
			for (int i = 0; i < n; i++) {
				d *= 10;
				final int in = (int) d;
				buffer[read++] = (byte) (in + '0');
				d -= in;
			}
		}

		@Override
		public void print(final char c) {
			if (c < 0x80) {
				ensureBuffer(1);
				buffer[read++] = (byte) c;
			} else if (c < 0x07FF) {
				ensureBuffer(2);
				buffer[read++] = (byte) (c >> 6 & 0x3F | 0x80);
				buffer[read++] = (byte) (c & 0x3F | 0x80);
			} else {
				ensureBuffer(3);
				buffer[read++] = (byte) (c >> 12 & 0xF | 0xE0);
				buffer[read++] = (byte) (c >> 6 & 0x3F | 0x80);
				buffer[read++] = (byte) (c & 0x3F | 0x80);
			}
		}

		@Override
		public void print(final char[] s) {
			for (final char i : s) print(i);
		}

		@Override
		public void print(final String s) {
			print(s.toCharArray());
		}

		@Override
		public void print(final Object o) {
			print(o.toString());
		}

		@Override
		public Output printf(final java.util.Locale l, final String format, final Object... args) {
			print(String.format(l, format, args));
			return this;
		}

		@Override
		public Output printf(final String format, final Object... args) {
			print(String.format(format, args));
			return this;
		}

		@Override
		public void println() {
			ensureBuffer(1);
			buffer[read++] = '\n';
			if (autoFlush) flush();
		}

		@Override
		public void println(final boolean b) {
			print(b);
			println();
		}

		public void println(final byte b) {
			print(b);
			println();
		}

		public void println(final short s) {
			print(s);
			println();
		}

		@Override
		public void println(final int i) {
			print(i);
			println();
		}

		@Override
		public void println(final long l) {
			print(l);
			println();
		}

		@Override
		public void println(final float f) {
			print(f);
			println();
		}

		@Override
		public void println(final double d) {
			print(d);
			println();
		}

		public void println(final double d, final int n) {
			print(d, n);
			println();
		}

		@Override
		public void println(final char c) {
			print(c);
			println();
		}

		@Override
		public void println(final char[] s) {
			print(s);
			println();
		}

		@Override
		public void println(final String s) {
			print(s);
			println();
		}

		@Override
		public void println(final Object o) {
			print(o);
			println();
		}

		@Override
		public Output append(final char c) {
			print(c);
			return this;
		}

		@Override
		public Output append(CharSequence csq) {
			if (csq == null) csq = "null";
			print(csq.toString());
			return this;
		}

		@Override
		public Output append(CharSequence csq, final int start, final int end) {
			if (csq == null) csq = "null";
			print(csq.subSequence(start, end).toString());
			return this;
		}
	}

	public static final class DummyOut extends java.io.PrintStream {

		public DummyOut() {
			super(new Dummy());
		}

		private static class Dummy extends java.io.OutputStream {

			@Override
			public void close() {

			}

			@Override
			public void flush() {

			}

			@Override
			public void write(final byte[] b) {

			}

			@Override
			public void write(final byte[] b, final int off, final int len) {

			}

			@Override
			public void write(final int b) {

			}
		}
	}
}