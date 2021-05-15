package com.joselawis.cars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COCHE")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    private Marca marca;

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Precio> precios = new ArrayList<>();

    public void addPrecio(Precio precio) {
        if (precios == null) precios = new ArrayList<>();
        precios.add(precio);
        precio.setCoche(this);
    }

    public void removePrecio(Precio precio) {
        if (precios == null) precios = new ArrayList<>();
        precios.remove(precio);
        precio.setCoche(null);
    }
}

