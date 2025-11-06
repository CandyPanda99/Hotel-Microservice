package com.example.Reservation.dto;

import java.util.List;


public record RoomResponseDto(
        String id,
        String roomName,
        String hotelId,
        List<String> packageIds
) {
}
