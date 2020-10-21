package com.capgemini.exceptions;

public class IPLAnalyserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionType type;

	public enum ExceptionType {
		IO_EXCEPTION, WRONG_FILE, RUNTIME_EXCEPTION, PARSE_EXCEPTION
	}

	public IPLAnalyserException(String message, ExceptionType exception) {
		super(message);
		this.type = exception;
	}

}
