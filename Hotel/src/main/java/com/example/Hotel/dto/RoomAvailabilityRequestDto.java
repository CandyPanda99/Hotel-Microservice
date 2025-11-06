package com.example.Hotel.dto;

import com.example.Hotel.enums.RoomStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RoomAvailabilityRequestDto(
        @NotNull(message = "date is required")
        @FutureOrPresent(message = "date must be today or in the future")
        LocalDate date,

        @NotNull(message = "status is required")
        RoomStatus status
) {
}
