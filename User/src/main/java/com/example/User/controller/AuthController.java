package com.example.User.controller;

import com.example.User.constants.UserConstants;
import com.example.User.dto.ResponseDto;
import com.example.User.dto.UserDetailsRequestDto;
import com.example.User.dto.UserDetailsResponseDto;
import com.example.User.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "Auth", description = "Endpoints to manage auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Register a user",
            description = "Register a user with the given username, name, email, address, country"
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> createUser(
            @RequestHeader("correlation-id") String correlationId, @Valid @RequestBody final UserDetailsRequestDto registrationDto){
        logger.debug("correlation-id found in AuthController: {}", correlationId);
        UserDetailsResponseDto userDetailsResponseDto = userService.createUser(registrationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(UserConstants.STATUS_201, UserConstants.MESSAGE_201, userDetailsResponseDto));
    }

}
