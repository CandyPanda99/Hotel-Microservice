package com.example.Reservation.service.impl;

import com.example.Reservation.dto.*;
import com.example.Reservation.dto.mapper.ReservationDtoMapper;
import com.example.Reservation.exceptions.ResourceNotFoundException;
import com.example.Reservation.model.Reservation;
import com.example.Reservation.repository.ReservationRepository;
import com.example.Reservation.service.IReservationService;
import com.example.Reservation.service.client.HotelFeingClient;
import com.example.Reservation.service.client.UserFeingClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelFeingClient hotelFeingClient;
    private final UserFeingClient userFeingClient;


    @Override
    public ReservationResponseDto createReservation(ReservationRequestDto reservationRequestDto) {
        UserDetailsResponseDto user = userFeingClient.getUserById(reservationRequestDto.customerId()).getBody().data();
        PackageResponseDto hotelPackage = hotelFeingClient.getPackageById(reservationRequestDto.packageId()).getBody().data();
        RoomResponseDto room = hotelFeingClient.getRoomById(hotelPackage.roomId()).getBody().data();

        Reservation reservation = ReservationDtoMapper.INSTANCE.reservationRequestDtoToReservation(reservationRequestDto);
        reservation.setCustomerName(user.name());
        reservation.setEmail(user.email());
        reservation.setCountry(user.country());
        reservation.setRoomId(room.id());
        reservation.setRoomName(room.roomName());
        reservation.setPackageId(hotelPackage.packageId());
        reservation.setPackageName(hotelPackage.packageName());
        reservation.setPrice(hotelPackage.price());
        reservation.setTax(hotelPackage.tax());
        reservation.setOccupancy(hotelPackage.occupancy());
        reservation.setBoardBasis(hotelPackage.boardBasis());

        return ReservationDtoMapper.INSTANCE.reservationToReservationResponseDto(reservationRepository.save(reservation));
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
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation","id", reservationId));

        UserDetailsResponseDto user = userFeingClient.getUserById(reservationRequestDto.customerId()).getBody().data();
        PackageResponseDto hotelPackage = hotelFeingClient.getPackageById(reservationRequestDto.packageId()).getBody().data();
        RoomResponseDto room = hotelFeingClient.getRoomById(hotelPackage.roomId()).getBody().data();

        reservation.setCustomerId(reservationRequestDto.customerId());
        reservation.setCheckinDate(reservationRequestDto.checkInDate());
        reservation.setCheckoutDate(reservationRequestDto.checkOutDate());
        reservation.setCustomerName(user.name());
        reservation.setEmail(user.email());
        reservation.setCountry(user.country());
        reservation.setRoomId(room.id());
        reservation.setRoomName(room.roomName());
        reservation.setPackageId(hotelPackage.packageId());
        reservation.setPackageName(hotelPackage.packageName());
        reservation.setPrice(hotelPackage.price());
        reservation.setTax(hotelPackage.tax());
        reservation.setOccupancy(hotelPackage.occupancy());
        reservation.setBoardBasis(hotelPackage.boardBasis());

        return ReservationDtoMapper.INSTANCE.reservationToReservationResponseDto(reservationRepository.save(reservation));
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
