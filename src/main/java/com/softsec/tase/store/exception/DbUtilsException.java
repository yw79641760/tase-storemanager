package com.softsec.tase.store.exception;

/**
 *  @author yanwei 
 *  @version 2012-7-15 04:12:27
 *  
 */

public class DbUtilsException extends RuntimeException {

	private static final long serialVersionUID = -7095382640117316967L;

	public DbUtilsException() {
		super();
	}

	public DbUtilsException(String msg) {
		super(msg);
	}
	
	public DbUtilsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public DbUtilsException(Throwable cause) {
		super(cause);
	}
}
