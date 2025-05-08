package com.example.usersubservice.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Запрос на создание подписки")
public record SubscriptionRequest(
        @Schema(description = "Название сервиса", example = "Netflix")
        String serviceName,

        @Schema(description = "Дата начала", example = "2025-05-08")
        LocalDate startDate,

        @Schema(description = "Ежемесячная плата", example = "9.99")
        BigDecimal monthlyFee
) {}
