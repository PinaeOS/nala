package org.pinae.nala.xb.exception;

/**
 * 路径异常
 * 搜索文件、URL等资源时, 无法发现路径引发的异常
 * 
 * @author Huiyugeng
 *
 */
public class NoSuchPathException extends Exception {
	
	private static final long serialVersionUID = 1746600805151249818L;

	/**
	 * 构造函数
	 */
	public NoSuchPathException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 */
	public NoSuchPathException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param cause 异常引发原因
	 */
	public NoSuchPathException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常提示
	 * @param cause 异常引发原因
	 */
	public NoSuchPathException(String message, Throwable cause) {
		super(message, cause);
	}

}
