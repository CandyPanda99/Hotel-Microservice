package com.example.Hotel.service.impl;

import com.example.Hotel.dto.PackageRequestDto;
import com.example.Hotel.dto.PackageResponseDto;
import com.example.Hotel.dto.PackageUpdateRequestDto;
import com.example.Hotel.dto.mapper.PackageDtoMapper;
import com.example.Hotel.exceptions.ResourceNotFoundException;
import com.example.Hotel.model.Packages;
import com.example.Hotel.model.Room;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.repository.PackageRepository;
import com.example.Hotel.repository.RoomRepository;
import com.example.Hotel.service.IPackageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService implements IPackageService {

    @Autowired
    PackageRepository packageRepository;


    @Autowired
    RoomRepository roomRepository;

    @Override
    public PackageResponseDto createPackage(PackageRequestDto packageRequestDto) {
        Room room = roomRepository.findById(packageRequestDto.roomId())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        Packages packages = PackageDtoMapper.INSTANCE.packageDtoToPackage(packageRequestDto);
        packages.setRoom(room);
        Packages savedPackage = packageRepository.save(packages);
        return PackageDtoMapper.INSTANCE.packageToPackageResponseDto(savedPackage);
    }

    @Override
    public PackageResponseDto getPackageById(String id) {
        return packageRepository.findById(id)
                .map(PackageDtoMapper.INSTANCE::packageToPackageResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Package","id", id));
    }

    @Override
    public List<PackageResponseDto> getAllPackages() {
        return packageRepository.findAll().stream()
                .map( PackageDtoMapper.INSTANCE::packageToPackageResponseDto)
                .toList();
    }

    @Override
    public PackageResponseDto updatePackage(String id, PackageUpdateRequestDto packageUpdateRequestDto) {
        Packages packages = packageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package","id", id));
        packages.setPackageName(packageUpdateRequestDto.packageName());
        packages.setPrice(packageUpdateRequestDto.price());
        packages.setTax(packageUpdateRequestDto.tax());
        packages.setBoardBasis(packageUpdateRequestDto.boardBasis());

        Packages updatedPackage = packageRepository.save(packages);
        return PackageDtoMapper.INSTANCE.packageToPackageResponseDto(updatedPackage);
    }

    @Override
    public void deletePackage(String id) {
        Packages packages = packageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Package","id", id));
        packageRepository.delete(packages);
    }
}
