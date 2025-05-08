package com.example.usersubservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Subscription Service API")
                        .version("1.0")
                        .description("API для управления пользователями и их подписками"))
                .components(new Components()
                        .addSchemas("Subscription", new ObjectSchema()
                                .addProperty("serviceName", new StringSchema().example("Netflix"))
                                .addProperty("startDate", new StringSchema().example("2025-05-08"))
                                .addProperty("monthlyFee", new NumberSchema().example(9.99))
                        )
                );
    }
}
