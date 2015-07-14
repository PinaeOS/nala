package org.pinae.nala.xb.exception;

/**
 * 编组异常
 * 由对象生成XML文件的时候, 发生的异常
 * 
 * @author Huiyugeng
 *
 */
public class MarshalException extends Exception {

	private static final long serialVersionUID = -4942325314791001491L;

	/**
	 * 构造函数
	 */
	public MarshalException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 */
	public MarshalException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause 异常引发原因
	 */
	public MarshalException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 * @param cause 异常引发原因
	 */
	public MarshalException(String message, Throwable cause) {
		super(message, cause);
	}

}
