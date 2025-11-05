package com.example.Reservation.repository;

import com.example.Reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
