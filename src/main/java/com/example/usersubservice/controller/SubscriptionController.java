package com.example.usersubservice.controller;

import com.example.usersubservice.model.Subscription;
import com.example.usersubservice.model.User;
import com.example.usersubservice.service.SubscriptionService;
import com.example.usersubservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users/{id}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @PostMapping
    private ResponseEntity<Subscription> addSubscription(@PathVariable Long id,
                                                         @RequestBody Subscription subscription){
        Subscription sub = subscriptionService.createSubscription(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(sub);
    }

    @GetMapping
    private ResponseEntity<String> getUserSubscription(@PathVariable Long id){
        List<Subscription> subscriptionList = subscriptionService.getUserSubscription(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Subscription subscription : subscriptionList){
            stringBuilder.append(subscription.getServiceName()).append("\n");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());
    }

    @DeleteMapping("/{sub_id}")
    private ResponseEntity<Void> deleteSubscriptionFromUser(@PathVariable Long id, @PathVariable Long sub_id){
        subscriptionService.deleteSubscription(id, sub_id);
        return ResponseEntity.noContent().build();
    }

}
