package com.example.Reservation.controller;

import com.example.Reservation.constants.ReservationConstants;
import com.example.Reservation.dto.ReservationRequestDto;
import com.example.Reservation.dto.ReservationResponseDto;
import com.example.Reservation.dto.ResponseDto;
import com.example.Reservation.service.IReservationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/reservation", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    IReservationService reservationService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<ReservationResponseDto>>> getAllReservations(@RequestHeader("correlation-id") String correlationId) {
        logger.debug("correlation-id found in ReservationController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(ReservationConstants.STATUS_200, ReservationConstants.MESSAGE_200, reservationService.getAllReservations()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ReservationResponseDto>> getReservationById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id) {
        logger.debug("correlation-id found in ReservationController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(ReservationConstants.STATUS_200, ReservationConstants.MESSAGE_200, reservationService.getReservationById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<ReservationResponseDto>> createReservation(@RequestHeader("correlation-id") String correlationId, @Valid  @RequestBody final ReservationRequestDto reservationRequestDto) {
        logger.debug("correlation-id found in ReservationController: {}", correlationId);
        ReservationResponseDto reservationResponseDto = reservationService.createReservation(reservationRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(ReservationConstants.STATUS_201, ReservationConstants.MESSAGE_201, reservationResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<ReservationResponseDto>> updateReservation(@RequestHeader("correlation-id") String correlationId, @PathVariable String id, @Valid @RequestBody final ReservationRequestDto reservationRequestDto) {
        logger.debug("correlation-id found in ReservationController: {}", correlationId);
        ReservationResponseDto reservationResponseDto = reservationService.updateReservation(id, reservationRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(ReservationConstants.STATUS_200, ReservationConstants.MESSAGE_200, reservationResponseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteReservation(@RequestHeader("correlation-id") String correlationId, @PathVariable String id) {
        logger.debug("correlation-id found in ReservationController: {}", correlationId);
        reservationService.deleteReservation(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(ReservationConstants.STATUS_200, ReservationConstants.MESSAGE_200, "Reservation deleted successfully"));
    }
}
