package com._31536000.load;

/**
 * ファイルの読み書きを行う為の抽象クラスです。<br>
 * このクラスを継承して、各ファイルの読み書きを行います。
 * @author 31536000
 */
public abstract class Load implements Runnable {
	/** ファイルのパス*/
	protected String path;
	/** 読み込んでいるか否か */
	protected boolean isLoad;
	/** ファイルを読み込み中か否か */
	protected boolean isRead;
	/** 読み込むファイルの数 */
	protected int fileSum = 1;
	/** 読み込み終わったファイルの数 */
	protected int nowRead = 0;
	/**
	 * 読み込みたいファイルのパスを最初に指定してください。
	 * @param path 読み込みたいファイルのパス
	 */
	public Load(String path) {
		this.path = path;
		isLoad = false;
		isRead = false;
	}
	/**
	 * このメソッドが呼ばれると、loadFileメソッドが呼ばれます。<br>
	 * このメソッドはファイルの読み込み時に呼ばれるようにしてください。
	 */
	public void load() {
		if (!isLoad && !isRead) {
			isRead = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}
	@Override
	public void run() {
		isLoad = loadFile();
		nowRead = fileSum;
		isRead = false;
	}
	/**
	 * このメソッドの中で、ファイルを読み込んでください。<br>
	 * また、ファイルを読み込み終わる度にnowReadを増やしてください。
	 * @return ファイルの読み込みが成功したか否か
	 */
	protected abstract boolean loadFile();

	/**
	 * このメソッドが呼ばれると、unloadFileメソッドが呼ばれます。<br>
	 * このメソッドはファイルを使わなくなった時に呼ばれるようにしてください。
	 */
	public void unload() {
		isLoad = false;
		nowRead = 0;
		unloadFile();
	}

	/**
	 * このメソッドの中で、ファイルを破棄してください。<br>
	 * nullでも代入すれば良いんじゃないかな。
	 */
	protected abstract void unloadFile();

	/**
	 * ファイルを今現在進行形で読み込んでいるか否かを返します。
	 * @return 読み込み中ならtrue
	 * @return
	 */
	public boolean isRead() {
		return isRead;
	}
	/**
	 * ファイルを読み込めているか否かを返します。
	 * @return ファイルを読み込めているならtrue
	 * @return
	 */
	public boolean isLoad() {
		return isLoad;
	}

	/**
	 * ファイルの読み込み率を百分率表記で返します。
	 * @return ファイルの読み込み率(0-100の間)
	 */
	public int readPercent() {
		return nowRead * 100 / fileSum;
	}
}
