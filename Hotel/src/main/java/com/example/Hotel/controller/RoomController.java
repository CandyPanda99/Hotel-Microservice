package com.example.Hotel.controller;

import com.example.Hotel.constants.HotelConstants;
import com.example.Hotel.constants.RoomConstants;
import com.example.Hotel.dto.*;
import com.example.Hotel.service.IRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/room", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class RoomController {

    @Autowired
    IRoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<RoomResponseDto>> createRoom(@Valid @RequestBody final RoomRequestDto roomRequestDto){
        RoomResponseDto roomResponseDto = roomService.createRoom(roomRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(RoomConstants.STATUS_201, RoomConstants.MESSAGE_201, roomResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<RoomResponseDto>> updateRoom(@PathVariable String id, @Valid @RequestBody final RoomUpdateRequestDto roomUpdateRequestDto){
        RoomResponseDto roomResponseDto = roomService.updateRoom(id, roomUpdateRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomResponseDto));
    }

    @PatchMapping("/update/availabilities/{id}")
    public ResponseEntity<ResponseDto<String>> updateRoomAvailabilities(@RequestParam String id, @RequestBody List<RoomAvailabilityRequestDto> requestDtos){
        roomService.updateRoomAvailabilities(id, requestDtos);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, "Room availabilities updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteRoom(@PathVariable String id){
        roomService.deleteRoom(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, "Room deleted successfully"));
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<RoomResponseDto>>> getRooms(){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomService.getAllRooms()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RoomResponseDto>> getRoomById(@PathVariable String id){
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(RoomConstants.STATUS_200, RoomConstants.MESSAGE_200, roomService.getRoomById(id)));
    }



}
