package com.example.usersubservice.controller;

import com.example.usersubservice.model.Subscription;
import com.example.usersubservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscription/top")
@RequiredArgsConstructor
public class SubscriptionStatsController {
    private final SubscriptionService subscriptionService;

    @GetMapping
    private ResponseEntity<String> getTopSubscriptions(){
        String list = subscriptionService.getThreePopularSubscription();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
