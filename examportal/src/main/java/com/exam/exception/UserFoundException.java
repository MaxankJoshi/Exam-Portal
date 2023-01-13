package com.exam.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFoundException extends RuntimeException{
    public UserFoundException() {
        super("User with this username is already there in DB !! Try with another one");
    }

    public UserFoundException(String msg) {
        super(msg);
    }
}
