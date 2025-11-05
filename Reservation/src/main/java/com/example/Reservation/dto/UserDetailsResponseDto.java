package com.example.User.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserDetailsResponseDto(
        @Schema(description = "The unique identifier of the user.")
        String id,

        @Schema(description = "The username of the user.")
        String username,

        @Schema(description = "The full name of the user.")
        String name,

        @Schema(description = "The email address of the user.")
        String email,

        @Schema(description = "The address of the user.")
        String address,

        @Schema(description = "The country where the user resides.")
        String country,

        @Schema(description = "The age of the user.")
        Integer age,

        @Schema(description = "The gender of the user.")
        String sex
) {
}

