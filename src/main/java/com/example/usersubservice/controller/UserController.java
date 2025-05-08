package com.example.usersubservice.controller;

import com.example.usersubservice.model.User;
import com.example.usersubservice.requests.UserRequest;
import com.example.usersubservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "Операции с пользователями")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Создать пользователя", description = "Регистрирует нового пользователя в системе")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
        User user = new User();
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }

    @Operation(summary = "Получение пользователя по id", description = "Получение пользователя в системе")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user =  userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Обновление информации о пользователе по id", description = "Обновление пользователя в системе")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = new User();
        user.setId(id);
        user.setEmail(userRequest.email());
        user.setName(userRequest.name());

        if(userService.findUserByEmail(user.getEmail())){
            userService.updateUser(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Удаление пользователя по id", description = "Удаление пользователя в системе")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
