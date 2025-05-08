package com.example.usersubservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String email){
        super("Email '" + email + "' already exists");
        log.error("Email {} already exists", email);
    }
}
