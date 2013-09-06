package com.softsec.tase.store.exception;

/**
 *  @author yanwei 
 *  @version 2012-7-15 04:12:27
 *  
 */

public class FtpUtilsException extends RuntimeException {

	private static final long serialVersionUID = -7095382640117316967L;

	public FtpUtilsException() {
		super();
	}

	public FtpUtilsException(String msg) {
		super(msg);
	}
	
	public FtpUtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public FtpUtilsException(Throwable cause) {
		super(cause);
	}
}
