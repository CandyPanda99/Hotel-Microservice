package com.example.Reservation.dto.mapper;

import com.example.Reservation.dto.ReservationRequestDto;
import com.example.Reservation.dto.ReservationResponseDto;
import com.example.Reservation.enums.BoardBasis;
import com.example.Reservation.model.Reservation;
import jakarta.persistence.Column;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface ReservationDtoMapper {
    ReservationDtoMapper INSTANCE = Mappers.getMapper(ReservationDtoMapper.class);

    @Mapping(source = "id", target = "reservationId")
    @Mapping(source = "checkinDate", target = "checkInDate")
    @Mapping(source = "checkoutDate", target = "checkOutDate")
    ReservationResponseDto reservationToReservationResponseDto(Reservation reservation);

    @Mapping(source = "checkInDate", target = "checkinDate")
    @Mapping(source = "checkOutDate", target = "checkoutDate")
    Reservation reservationRequestDtoToReservation(ReservationRequestDto reservationRequestDto);
}