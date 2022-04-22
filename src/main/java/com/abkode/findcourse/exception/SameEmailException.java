package com.abkode.findcourse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SameEmailException extends RuntimeException implements Serializable {

    private static final Long serialVersionUID=2L;

    public SameEmailException(String message){
        super(message);

    }

}