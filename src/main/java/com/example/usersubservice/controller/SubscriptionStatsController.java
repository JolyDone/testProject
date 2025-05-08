package com.example.usersubservice.controller;

import com.example.usersubservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Subscription Top Management", description = "Операции статистики для пользователя")
public class SubscriptionStatsController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Получение топ 3 подписок", description = "Получение топ 3 подписок")
    @GetMapping
    public ResponseEntity<List<String>> getTopSubscriptions(){
        List<String> list = subscriptionService.getThreePopularSubscription();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
