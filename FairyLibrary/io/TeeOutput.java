package com._31536000.io;


public class TeeOutput extends java.io.PrintStream{
	private static class Tee extends java.io.OutputStream {
		private java.io.PrintStream out;
		private java.io.PrintStream file;
		@Override
		public void close() throws java.io.IOException {
			file.close();
		}
		@Override
		public void flush() throws java.io.IOException {
			out.flush();
			file.flush();
		}
		@Override
		public void write(int b) throws java.io.IOException {
			out.write(b);
			file.write(b);
		}
		@Override
		public void write(byte[] b) throws java.io.IOException {
			out.write(b);
			file.write(b);
		}
		@Override
		public void write(byte[] b, int off, int len) throws java.io.IOException {
			out.write(b, off, len);
			file.write(b, off, len);
		}
	}
	private Tee tee;
	public TeeOutput(java.io.OutputStream out) {
		this(new java.io.PrintStream(out));
	}
	public TeeOutput(java.io.PrintStream out) {
		this(new Tee(), out);
	}
	public TeeOutput(String fileName) throws java.io.FileNotFoundException {
		this(new java.io.File(fileName));
	}
	public TeeOutput(String fileName, String csn) throws java.io.FileNotFoundException, java.io.UnsupportedEncodingException {
		this(new java.io.File(fileName), csn);
	}
	public TeeOutput(java.io.File file) throws java.io.FileNotFoundException {
		this(new Tee(), new java.io.PrintStream(file));
	}
	public TeeOutput(java.io.File file, String csn) throws java.io.FileNotFoundException, java.io.UnsupportedEncodingException {
		this(new Tee(), new java.io.PrintStream(file, csn));
	}
	private TeeOutput(Tee tee, java.io.PrintStream file) {
		super(tee);
		this.tee = tee;
		tee.file = file;
		tee.out = System.out;
	}
	public void peepOut() {
		tee.out = System.out;
		System.setOut(this);
	}
	public void peepErr() {
		tee.out = System.err;
		System.setErr(this);
	}
	public void tee(java.io.OutputStream out) {
		tee(new java.io.PrintStream(out));
	}
	public void tee(java.io.PrintStream out) {
		tee.out = out;
	}
}
