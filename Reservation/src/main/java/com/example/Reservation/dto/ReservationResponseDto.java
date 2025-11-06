package com.example.Reservation.dto;

import com.example.Reservation.enums.BoardBasis;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationResponseDto(
    String reservationId,
    String customerId,
    String customerName,
    String email,
    String roomId,
    BigDecimal price,
    BigDecimal tax,
    BoardBasis boardBasis,
    Integer occupancy,
    LocalDate checkInDate,
    LocalDate checkOutDate
) {
}
