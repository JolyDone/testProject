package com.example.usersubservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(Long userID){
        super("Subscription not found with id: " + userID);
        log.error("Subscription not found with id: " + userID);
    }
}
