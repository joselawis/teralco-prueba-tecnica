package com.joselawis.cars.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "PRECIO")
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "startDate")
    private Timestamp startDate;

    @Column(name = "endDate")
    private Timestamp endDate;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coche coche;

}
