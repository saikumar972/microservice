package com.acc.user.aop;

import com.acc.user.customExceptions.InvalidAmountException;
import com.acc.user.customExceptions.InvalidIdException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class UserExceptions {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validateInputdata(MethodArgumentNotValidException ex){
        Map<String, String> errorMap=new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach((error)-> errorMap.put(error.getField(),error.getDefaultMessage()));
        log.info("UserExceptions :: The exceptions occurred while entering the user data were {} and general exception is {}",errorMap,ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidIdException.class)
    public String validateId(InvalidIdException ex){
        return "the given id is invalid";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAmountException.class)
    public String validateId(InvalidAmountException ex){
        return "The entered amount is above the available amount please try small amounts";
    }
}
