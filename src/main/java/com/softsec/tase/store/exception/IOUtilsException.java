/**
 * 
 */
package com.softsec.tase.store.exception;

/**
 * IOUtilsException.java
 * @author yanwei
 * @date 2013-1-25 上午9:14:42
 * @description
 */
public class IOUtilsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5379488302788522957L;

	public IOUtilsException() {
		super();
	}
	
	public IOUtilsException(String msg) {
		super(msg);
	}

	public IOUtilsException(Throwable cause) {
		super(cause);
	}
	
	public IOUtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
