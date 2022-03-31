package com._31536000.load;

import java.util.ArrayList;
import java.util.List;

/**
 * 複数のファイルをいっぺんに読み込むためのメソッドです。
 * 必要な数のLoadを予めLoadsに渡しておけば、Loadsのloadメソッドを呼ぶだけで全部のloadが呼ばれます。
 * @author 31536000
 */
public class Loads extends Load{

	private List<Load> load;

	public Loads() {
		super("");
		this.load = new ArrayList<Load>();
	}

	public Loads(Load load) {
		super("");
		setLoad(load);
	}

	public Loads(Load... load) {
		super("");
		setLoad(load);
	}

	public Loads(List<Load> load) {
		super("");
		setLoad(load);
	}

	public void setLoad(Load load) {
		this.load = new ArrayList<Load>(1);
		fileSum = 0;
		addLoad(load);
	}

	public void setLoad(Load... load) {
		this.load = new ArrayList<Load>();
		fileSum = 0;
		addLoad(load);
	}

	public void setLoad(List<Load> load) {
		this.load = new ArrayList<Load>();
		fileSum = 0;
		addLoad(load);
	}

	public void addLoad(Load load) {
		this.load.add(load);
		fileSum += load.fileSum;
	}

	public void addLoad(Load... load) {
		for (Load i : load) {
			this.load.add(i);
			fileSum += i.fileSum;
		}
	}

	public void addLoad(List<Load> load) {
		this.load.addAll(load);
		for (Load l : load) fileSum += l.fileSum;
	}

	@Override
	protected boolean loadFile() {
		int n = 0; // 読み込み終わったファイル数
		int sum = 0; // ファイル数
		for (Load l : load) {
			sum += l.fileSum;
		}
		fileSum = sum;
		nowRead = 0;
		for (Load l : load) {
			l.load();
			while(l.isRead()) {
				nowRead = n + l.nowRead;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			n += l.fileSum;
		}
		return true;
	}

	@Override
	protected void unloadFile() {
		for (Load l : load) l.unload();
	}

}
