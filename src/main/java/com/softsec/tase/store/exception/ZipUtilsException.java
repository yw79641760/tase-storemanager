/**
 * 
 */
package com.softsec.tase.store.exception;

/**
 * ZipUtilsException.java
 * @author yanwei
 * @date 2013-1-24 下午5:02:57
 * @description
 */
public class ZipUtilsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1249293814726088323L;

	public ZipUtilsException() {
		super();
	}
	
	public ZipUtilsException(String msg) {
		super(msg);
	}
	
	public ZipUtilsException(Throwable cause) {
		super(cause);
	}
	
	public ZipUtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
