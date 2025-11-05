package com.example.Hotel.service;

import com.example.Hotel.dto.HotelRequestDto;
import com.example.Hotel.dto.HotelResponseDto;

import java.util.List;
import java.util.Set;

public interface IHotelService {
    List<HotelResponseDto> getAllHotels();
    HotelResponseDto getHotelById(String id);
    HotelResponseDto createHotel(HotelRequestDto hotelRequestDto);
    HotelResponseDto updateHotel(String id, HotelRequestDto hotelRequestDto);
    void deleteHotel(String id);
}
