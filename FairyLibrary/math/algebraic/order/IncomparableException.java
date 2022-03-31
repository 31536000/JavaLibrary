package com._31536000.math.algebraic.order;

/**
 * 比較不能な二つのオブジェクトを比較しようとしたときに生成されます。
 * @author 31536000
 *
 */
public class IncomparableException extends RuntimeException{
	private static final long serialVersionUID = -4710928911269198559L;
	/**
	 * 詳細メッセージなしでIncomparableExceptionを構築します。
	 */
	public IncomparableException() {
		super();
	}
	/**
	 * 指定された詳細メッセージを持つIncomparableExceptionを構築します。
	 * @param s 詳細メッセージ。
	 */
	public IncomparableException(String s) {
		super(s);
	}
}
