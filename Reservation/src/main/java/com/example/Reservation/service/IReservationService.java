package com.example.Reservation.service;

import com.example.Reservation.dto.ReservationRequestDto;
import com.example.Reservation.dto.ReservationResponseDto;

import java.util.List;

public interface IReservationService {
    ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto);
    ReservationResponseDto getReservationById(String reservationId);
    List<ReservationResponseDto> getAllReservations();
    ReservationResponseDto updateReservation(String reservationId, ReservationRequestDto reservationRequestDto);
    void deleteReservation(String reservationId);

}
