package com.ofr.userexception;

/*
 * This class used to get the time stamp, user defined message and status
 * 
 * 	@Author : Sathya Sivam R
 */
import java.time.LocalDateTime;

public class ExceptionResponse {

 

    private int status;
    private String message;
    private LocalDateTime time; 
    
    /*
	 * Default Constructor
	 */
    
    public ExceptionResponse() {
        super();
    }
    
    /*
	 * Parameterized Constructor
	 */
    public ExceptionResponse(int status, String message, LocalDateTime time) {
        super();
        this.status = status;
        this.message = message;
        this.time = time;
    }
    
    /*
	 * Getters and Setters for all private variables
	 */
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
 