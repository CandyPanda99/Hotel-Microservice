package com.example.Hotel.dto;

import com.example.Hotel.enums.BoardBasis;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PackageRequestDto (
    @NotBlank(message = "Package name cannot be blank")
    String packageName,

    @NotBlank(message = "Room ID cannot be blank")
    String roomId,

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    BigDecimal price,

    @NotNull(message = "Tax cannot be null")
    @DecimalMin(value = "0.0", message = "Tax must be at least 0")
    BigDecimal tax,

    @NotNull(message = "Occupancy cannot be null")
    @Min(value = 1, message = "Occupancy must be at least 1")
    Integer occupancy,

    @NotNull(message = "Board basis cannot be null")
    BoardBasis boardBasis
){
}
