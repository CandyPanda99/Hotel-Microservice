package com.example.Reservation.service.client;

import com.example.Reservation.dto.PackageResponseDto;
import com.example.Reservation.dto.ResponseDto;
import com.example.Reservation.dto.RoomResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("hotel")
public interface HotelFeingClient {
    @GetMapping(value = "/api/v1/package/{id}",consumes = "application/json")
    public ResponseEntity<ResponseDto<PackageResponseDto>> getPackageById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id);

    @GetMapping(value = "/api/v1/room/{id}",consumes = "application/json")
    public ResponseEntity<ResponseDto<RoomResponseDto>> getRoomById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id);
}
