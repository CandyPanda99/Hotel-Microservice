package com.example.Hotel.dto;

import com.example.Hotel.enums.BoardBasis;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PackageUpdateRequestDto(
    @Size(max = 100, message = "packageName must be at most 100 characters")
    String packageName,

    @DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")
    BigDecimal price,

    @DecimalMin(value = "0.0", message = "tax must be >= 0")
    BigDecimal tax,

    @Min(value = 1, message = "occupancy must be at least 1")
    @Max(value = 100, message = "occupancy must be at most 100")
    Integer occupancy,

    BoardBasis boardBasis
){
}
