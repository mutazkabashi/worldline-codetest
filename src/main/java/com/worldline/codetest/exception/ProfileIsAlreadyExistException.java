package com.worldline.codetest.exception;

public class ProfileIsAlreadyExistException extends Exception{
	public ProfileIsAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }

}
