package com.example.Hotel.controller;

import com.example.Hotel.constants.RoomConstants;
import com.example.Hotel.dto.*;
import com.example.Hotel.service.IRoomService;
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
@RequestMapping(value = "/api/v1/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class RoomController {

    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    IRoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<RoomResponseDto>> createRoom(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody final RoomRequestDto roomRequestDto){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        RoomResponseDto roomResponseDto = roomService.createRoom(roomRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(RoomConstants.STATUS_201, RoomConstants.MESSAGE_201, roomResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<RoomResponseDto>> updateRoom(@RequestHeader("correlation-id") String correlationId, @PathVariable String id, @Valid @RequestBody final RoomUpdateRequestDto roomUpdateRequestDto){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        RoomResponseDto roomResponseDto = roomService.updateRoom(id, roomUpdateRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomResponseDto));
    }

    @PatchMapping("/update/availabilities/{id}")
    public ResponseEntity<ResponseDto<String>> updateRoomAvailabilities(@RequestHeader("correlation-id") String correlationId, @RequestParam String id, @RequestBody List<RoomAvailabilityRequestDto> requestDtos){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        roomService.updateRoomAvailabilities(id, requestDtos);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, "Room availabilities updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteRoom(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        roomService.deleteRoom(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, "Room deleted successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<RoomResponseDto>>> getRooms(@RequestHeader("correlation-id") String correlationId){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomService.getAllRooms()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RoomResponseDto>> getRoomById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in RoomController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomService.getRoomById(id)));
    }



}
