package com._31536000.load;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class LoadImage extends Load{

	/** 読み込んだ内容 */
	private Image image;

	public LoadImage(String path) {
		super(path);
	}

	@Override
	protected boolean loadFile() {
		try {
			image = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	protected void unloadFile() {
		image = null;
	}

	/**
	 * 読み込んだ画像を取得します。
	 * @return 読み込んだ画像
	 */
	public Image getImage() {
		return image;
	}

}
