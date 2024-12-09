package com.acc.user.customExceptions;

public class InvalidAmountException extends RuntimeException{
    String message;
    public InvalidAmountException(String message){
        super(message);
    }
}
