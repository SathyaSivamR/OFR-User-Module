package com.ofr.userexception;

/*
 * @Author : Sathya Sivam R
*/
public class UserNotFoundException extends Exception{

	/**
	 * Default Serial version
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Parameterized constructor for UserNotFoundException class
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}
}
