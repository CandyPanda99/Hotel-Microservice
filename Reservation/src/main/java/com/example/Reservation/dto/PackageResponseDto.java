package com.example.Reservation.dto;

import com.example.Reservation.enums.BoardBasis;

import java.math.BigDecimal;

public record PackageResponseDto(
    String packageId,
    String packageName,
    String roomId,
    BigDecimal price,
    BigDecimal tax,
    Integer occupancy,
    BoardBasis boardBasis
) {
}
