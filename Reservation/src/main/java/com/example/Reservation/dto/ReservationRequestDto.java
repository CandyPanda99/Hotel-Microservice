package com.example.Reservation.dto;

import java.time.LocalDate;

public record ReservationRequestDto (
     String customerId,
     String packageId,
     LocalDate checkInDate,
     LocalDate checkOutDate
){
}