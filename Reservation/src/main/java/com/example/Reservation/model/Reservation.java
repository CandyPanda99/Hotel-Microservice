package com.example.Reservation.model;


import com.example.Reservation.enums.BoardBasis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private String packageId;

    @Column(nullable = false)
    private String packageName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal tax;

    @Column(nullable = false)
    private Integer occupancy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardBasis boardBasis;

    @Column(nullable = false)
    private LocalDate checkinDate;

    @Column(nullable = false)
    private LocalDate checkoutDate;
}
