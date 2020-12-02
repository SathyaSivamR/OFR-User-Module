package com.ofr.userexception;

public class DuplicateUserRecordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateUserRecordException(String message) {
		super(message);
	}
}
