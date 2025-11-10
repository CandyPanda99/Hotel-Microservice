package com.example.User.controller;

import com.example.User.constants.UserConstants;
import com.example.User.dto.ResponseDto;
import com.example.User.dto.UserDetailsRequestDto;
import com.example.User.dto.UserDetailsResponseDto;
import com.example.User.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "User", description = "Endpoints to manage auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @Operation(
            summary = "Get all users",
            description = "Get all users"
    )
    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<UserDetailsResponseDto>>> getUsers(@RequestHeader("correlation-id") String correlationId){
        logger.debug("correlation-id found in UserController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.getAllUsers()));
    }

    @Operation(
            summary = "Get user by id",
            description = "Get user by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> getUserById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in UserController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.getUserById(id)));
    }

    @Operation(
            summary = "Delete user by id",
            description = "Delete user by id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deleteUser(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in UserController: {}", correlationId);
        userService.deleteUser(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, "User deleted successfully"));
    }

    @Operation(
            summary = "Update user by id",
            description = "Update user by id"
    )
    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<UserDetailsResponseDto>> updateUser(@RequestHeader("correlation-id") String correlationId, @PathVariable String id, @RequestBody UserDetailsRequestDto userDetailsRequestDto){
        logger.debug("correlation-id found in UserController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(UserConstants.STATUS_200, UserConstants.MESSAGE_200, userService.updateUser(id,userDetailsRequestDto )));
    }

}
