package com.example.Hotel.controller;

import com.example.Hotel.constants.HotelConstants;
import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;
import com.example.Hotel.dto.ResponseDto;
import com.example.Hotel.service.IHotelService;
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
@RequestMapping(value = "/api/v1/hotel", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
    @Autowired
    IHotelService hotelService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<HotelResponseDto>>> getHotels(@RequestHeader("correlation-id") String correlationId){
        logger.debug("correlation-id found in HotelController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelService.getAllHotels()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<HotelResponseDto>> getHotelById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in HotelController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelService.getHotelById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<HotelResponseDto>> createHotel(@RequestHeader("correlation-id") String correlationId, @Valid @RequestBody final HotelRequestDto hotelDetailsRequestDto){
        logger.debug("correlation-id found in HotelController: {}", correlationId);
        HotelResponseDto hotelResponseDto = hotelService.createHotel(hotelDetailsRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(HotelConstants.STATUS_201, HotelConstants.MESSAGE_201, hotelResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<HotelResponseDto>> updateHotel(@RequestHeader("correlation-id") String correlationId, @PathVariable String id, @Valid @RequestBody final HotelRequestDto hotelDetailsRequestDto){
        logger.debug("correlation-id found in HotelController: {}", correlationId);
        HotelResponseDto hotelResponseDto = hotelService.updateHotel(id, hotelDetailsRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, hotelResponseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteHotel(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in HotelController: {}", correlationId);
        hotelService.deleteHotel(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(HotelConstants.STATUS_200, HotelConstants.MESSAGE_200, "Hotel deleted successfully"));
    }

}
