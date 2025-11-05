package com.example.Reservation.service.client;

import com.example.Reservation.dto.PackageResponseDto;
import com.example.Reservation.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("hotel")
public interface HotelFeingClient {
    @GetMapping("/api/v1/package/{id}")
    public ResponseEntity<ResponseDto<PackageResponseDto>> getPackageById(@PathVariable String id);
}
