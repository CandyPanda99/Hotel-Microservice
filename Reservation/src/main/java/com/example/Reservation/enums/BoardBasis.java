package com.example.Hotel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardBasis {
    ROOM_ONLY("ROOM_ONLY"),
    BED_AND_BREAKFAST("BED_AND_BREAKFAST"),
    HALF_BOARD("HALF_BOARD"),
    FULL_BOARD("FULL_BOARD"),
    ALL_INCLUSIVE("ALL_INCLUSIVE");

    private final String boardBasis;
}
