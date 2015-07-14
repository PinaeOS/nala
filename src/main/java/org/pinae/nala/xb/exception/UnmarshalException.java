package org.pinae.nala.xb.exception;

/**
 * 解组异常
 * 由XML文件生成对象的时候, 发生的异常
 * 
 * @author Huiyugeng
 *
 */
public class UnmarshalException extends Exception {

	private static final long serialVersionUID = -1122009998969538216L;

	/**
	 * 构造函数
	 */
	public UnmarshalException() {
		super();
	}
	
	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 */
	public UnmarshalException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause 异常引发原因
	 */
	public UnmarshalException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 * @param cause 异常引发原因
	 */
	public UnmarshalException(String message, Throwable cause) {
		super(message, cause);
	}

}
