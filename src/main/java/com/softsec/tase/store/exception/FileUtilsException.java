package com.softsec.tase.store.exception;

/**
 *  @author yanwei
 *  @version 2012-7-15 04:12:27
 *  
 */

public class FileUtilsException extends RuntimeException {

	private static final long serialVersionUID = -7095382640117316967L;

	public FileUtilsException() {
		super();
	}

	public FileUtilsException(String msg) {
		super(msg);
	}
	
	public FileUtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public FileUtilsException(Throwable cause) {
		super(cause);
	}
}
