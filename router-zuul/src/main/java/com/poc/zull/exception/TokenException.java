package com.poc.zull.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenException extends RuntimeException{
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public TokenException() {
        super();
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }

}
