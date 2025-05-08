package com.example.usersubservice.controller;

import com.example.usersubservice.model.Subscription;
import com.example.usersubservice.model.SubscriptionType;
import com.example.usersubservice.requests.SubscriptionRequest;
import com.example.usersubservice.service.SubscriptionService;
import com.example.usersubservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users/{id}/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription Management", description = "Операции с подписками")
public class SubscriptionController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Создать подписку для пользователя", description = "Добавляет подписку пользователю")
    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long id,
                                                        @RequestBody SubscriptionRequest request){

        Subscription subscription = new Subscription();
        subscription.setServiceName(request.serviceName());
        subscription.setStartDate(request.startDate());
        subscription.setMonthlyFee(request.monthlyFee());

        Long serviceId = SubscriptionType.getIdForService(subscription.getServiceName());

        subscription.setId(serviceId);
        subscription.setUser(userService.getUserById(id));

        Subscription created = subscriptionService.createSubscription(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Получение списка подписок для пользователя", description = "Получение списка подписок для пользователя")
    @GetMapping
    public ResponseEntity<String> getUserSubscription(@PathVariable Long id){
        List<Subscription> subscriptionList = subscriptionService.getUserSubscription(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Subscription subscription : subscriptionList){
            stringBuilder.append(subscription.getServiceName()).append("\n");
        }
        return ResponseEntity.status(HttpStatus.OK).body(stringBuilder.toString());
    }

    @Operation(summary = "Удаление подписки для пользователя", description = "Удаление подписки для пользователя")
    @DeleteMapping("/{sub_id}")
    public ResponseEntity<Void> deleteSubscriptionFromUser(@PathVariable Long id, @PathVariable Long sub_id){
        subscriptionService.deleteSubscription(id, sub_id);
        return ResponseEntity.noContent().build();
    }

}
