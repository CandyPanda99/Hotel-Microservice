package com.example.Reservation.service.client;

import com.example.Reservation.dto.ResponseDto;
import com.example.Reservation.dto.UserDetailsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("user")
public interface UserFeingClient {
    @GetMapping(value = "/api/v1/user/{id}",consumes = "application/json")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> getUserById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id);
}
