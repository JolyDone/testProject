package com.example.usersubservice.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Схема создания пользователя")
public record UserRequest(
        @Schema(description = "Имя", example = "Joe")
        String name,
        @Schema(description = "Email", example = "mail@inbox.ru")
        String email
) {}
