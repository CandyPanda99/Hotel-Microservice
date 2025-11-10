package com.example.Hotel.controller;

import com.example.Hotel.constants.PackageConstants;
import com.example.Hotel.dto.PackageRequestDto;
import com.example.Hotel.dto.PackageResponseDto;
import com.example.Hotel.dto.PackageUpdateRequestDto;
import com.example.Hotel.dto.ResponseDto;
import com.example.Hotel.service.IPackageService;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/api/v1/package", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class PackageController {

    private static final Logger logger = LoggerFactory.getLogger(PackageController.class);

    @Autowired
    IPackageService packageService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<PackageResponseDto>>> getPackages(@RequestHeader("correlation-id") String correlationId){
        logger.debug("correlation-id found in PackageController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(PackageConstants.STATUS_200, PackageConstants.MESSAGE_200, packageService.getAllPackages()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<PackageResponseDto>> getPackageById(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in PackageController: {}", correlationId);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(PackageConstants.STATUS_200, PackageConstants.MESSAGE_200, packageService.getPackageById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<PackageResponseDto>> createPackage(@RequestHeader("correlation-id") String correlationId, @Valid  @RequestBody final PackageRequestDto packageRequestDto){
        logger.debug("correlation-id found in PackageController: {}", correlationId);
        PackageResponseDto packageResponseDto = packageService.createPackage(packageRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto<>(PackageConstants.STATUS_201, PackageConstants.MESSAGE_201, packageResponseDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDto<PackageResponseDto>> updatePackage(@RequestHeader("correlation-id") String correlationId, @PathVariable String id, @Valid @RequestBody final PackageUpdateRequestDto packageUpdateRequestDto){
        logger.debug("correlation-id found in PackageController: {}", correlationId);
        PackageResponseDto packageResponseDto = packageService.updatePackage(id, packageUpdateRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto<>(PackageConstants.STATUS_200, PackageConstants.MESSAGE_200, packageResponseDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> deletePackage(@RequestHeader("correlation-id") String correlationId, @PathVariable String id){
        logger.debug("correlation-id found in PackageController: {}", correlationId);
        packageService.deletePackage(id);
        return
                ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseDto<>(PackageConstants.STATUS_200, PackageConstants.MESSAGE_200, "Package deleted successfully"));
    }

}
