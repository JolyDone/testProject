package com.example.usersubservice.service;

import com.example.usersubservice.exception.EmailAlreadyExistException;
import com.example.usersubservice.exception.EmailAreNotExistsException;
import com.example.usersubservice.exception.UserNotFoundException;
import com.example.usersubservice.model.User;
import com.example.usersubservice.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user){
        log.info("Creating new user with email: {}", user.getEmail());
        if (userRepository.findEmailExist(user.getEmail())) {
            throw new EmailAlreadyExistException(user.getEmail());
        }

        return userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public boolean findUserByEmail(String email) throws EmailAreNotExistsException {
        log.info("Finding user with email: {}", email);
        return userRepository.findEmailExist(email);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long userId){
        log.info("Gerring user with id: {}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public void updateUser(User user){
        log.info("Updating user with email: {}", user.getEmail());
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new UserNotFoundException(user.getId());
        }
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long userId){
        log.info("Deleting user with id: {}", userId);
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(userRepository::delete);
        if(user.isEmpty()){
            throw new UserNotFoundException(userId);
        }
    }
}
