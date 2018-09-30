package com.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserAlreadyExistsException extends RuntimeException {

public UserAlreadyExistsException() {
	// TODO Auto-generated constructor stub
}

public UserAlreadyExistsException(String msg) {
	// TODO Auto-generated constructor stub\
	super(msg);
}



}
