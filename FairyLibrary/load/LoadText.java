package com._31536000.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Deque;
import java.util.LinkedList;

public class LoadText extends Load{

	/** 読み込んだ内容 */
	private String text[];

	public LoadText(String path) {
		super(path);
		
	}

	@Override
	protected boolean loadFile() {
		Deque<String> que = new LinkedList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String dat;
			while ((dat = br.readLine()) != null) que.addLast(dat);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		text = new String[que.size()];
		for (int i = 0;i < text.length;++ i) text[i] = que.pop();
		return true;
	}

	@Override
	protected void unloadFile() {
		text = null;
	}

	/**
	 * 読み込んだ文章を取得します。
	 * @return 読み込んだ文章
	 */
	public String[] getText() {
		return text;
	}

}
