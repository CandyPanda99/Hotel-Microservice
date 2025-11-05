package com.example.Reservation.dto.mapper;

import com.example.Reservation.dto.ReservationRequestDto;
import com.example.Reservation.dto.ReservationResponseDto;
import com.example.Reservation.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {
    ReservationDtoMapper INSTANCE = Mappers.getMapper(ReservationDtoMapper.class);

    ReservationResponseDto reservationToReservationResponseDto(Reservation reservation);

    Reservation reservationRequestDtoToReservation(ReservationRequestDto reservationRequestDto);
}
