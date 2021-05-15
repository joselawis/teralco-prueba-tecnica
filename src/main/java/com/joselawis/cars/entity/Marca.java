package com.joselawis.cars.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "MARCA")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<Coche> coches = new ArrayList<>();

    public void addCoche(Coche coche) {
        if (coches == null) coches = new ArrayList<>();
        coches.add(coche);
        coche.setMarca(this);
    }

    public void removeCoche(Coche coche) {
        if (coches == null) coches = new ArrayList<>();
        coches.remove(coche);
        coche.setMarca(null);
    }
}
