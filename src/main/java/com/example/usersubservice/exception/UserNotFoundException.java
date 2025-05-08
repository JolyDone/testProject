package com.example.usersubservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userID){
        super("User not found with id: " + userID);
        log.error("User not found with id: " + userID);
    }
}
