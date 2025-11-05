package com.example.Reservation.service.impl;

import com.example.Reservation.dto.ReservationRequestDto;
import com.example.Reservation.dto.ReservationResponseDto;
import com.example.Reservation.dto.mapper.ReservationDtoMapper;
import com.example.Reservation.exceptions.ResourceNotFoundException;
import com.example.Reservation.repository.ReservationRepository;
import com.example.Reservation.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        return null;
    }

    @Override
    public ReservationResponseDto getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId)
                .map(ReservationDtoMapper.INSTANCE::reservationToReservationResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation","id", reservationId));
    }

    @Override
    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationDtoMapper.INSTANCE::reservationToReservationResponseDto)
                .toList();
    }

    @Override
    public ReservationResponseDto updateReservation(String reservationId, ReservationRequestDto reservationRequestDto) {
        return null;
    }

    @Override
    public void deleteReservation(String reservationId) {

    }
}
