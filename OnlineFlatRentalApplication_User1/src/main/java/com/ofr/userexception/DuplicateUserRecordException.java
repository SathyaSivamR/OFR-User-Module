package com.ofr.userexception;

/*
 * @Author : Sathya Sivam R
*/
public class DuplicateUserRecordException extends Exception {

	/**
	 * Default Serial version
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Parameterized constructor for DuplicateUserRecordException class
	 */
	public DuplicateUserRecordException(String message) {
		super(message);
	}
}
