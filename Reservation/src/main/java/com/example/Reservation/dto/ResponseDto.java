package com.example.Hotel.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseDto<T>(

        @Schema(
                description = "Status code in the response"
        )
        String statusCode,

        @Schema(
                description = "Status message in the response"
        )
        String statusMsg,

        @Schema(
                description = "Optional data object in the response"
        )
        T data
) {}
