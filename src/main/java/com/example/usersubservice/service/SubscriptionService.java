package com.example.usersubservice.service;

import com.example.usersubservice.exception.SubscriptionNotFoundException;
import com.example.usersubservice.exception.UserNotFoundException;
import com.example.usersubservice.model.Subscription;
import com.example.usersubservice.model.User;
import com.example.usersubservice.repository.SubscriptionRepository;
import com.example.usersubservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Subscription createSubscription(Subscription subscription){
        log.info("Creating new subscription for: {}", subscription.getServiceName());
        return subscriptionRepository.save(subscription);
    }

    @Transactional(readOnly = true)
    public List<Subscription> getUserSubscription(Long userId){
        log.info("Getting All subscriptions for user with id: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return subscriptionRepository.findByUserId(user.getId());
    }

    @Transactional
    public void deleteSubscription(Long userId, Long subscriptionId){
        log.info("Deleting subscription with id: {} for user with id: {}",subscriptionId, userId);
        List<Subscription> deleteSubscription = subscriptionRepository.findByUserId(userId);
        deleteSubscription.stream()
                .filter(sub -> sub.getId().equals(subscriptionId))
                .findFirst()
                .ifPresentOrElse(
                        subscriptionRepository::delete,
                        () -> { throw new SubscriptionNotFoundException(subscriptionId); }
                );
    }

    @Transactional(readOnly = true)
    public String getThreePopularSubscription(){
        List<Subscription> topThreeSubs = subscriptionRepository.findThreePopular();
        StringBuilder topThree = new StringBuilder();

        for (Subscription subscription : topThreeSubs){
            topThree.append(subscription.getServiceName()).append(" ");
        }

        log.info("Getting top 3 subscription: {}", topThree);
        return topThree.toString();
    }
}
