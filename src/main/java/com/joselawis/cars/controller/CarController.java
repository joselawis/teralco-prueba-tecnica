package com.joselawis.cars.controller;

import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.entity.Precio;
import com.joselawis.cars.service.GetCarsService;
import com.joselawis.cars.vo.CarVO;
import com.joselawis.cars.vo.PrecioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    @Autowired
    private GetCarsService getCarsService;

    @GetMapping("/cars/{date}/{id}")
    public List<CarVO> getCars(@PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable(name = "id") Long id) {
        Timestamp time = dateToTimestamp(date);
        List<Coche> coches = getCarsService.getByDateAndId(time, id);
        return coches.stream().map(this::cocheMapper).collect(Collectors.toList());
    }

    private Timestamp dateToTimestamp(Date date) {
        return Timestamp.from(date.toInstant());
    }

    private Date timestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    private CarVO cocheMapper(Coche coche) {
        return CarVO.builder()
                .id(coche.getId())
                .marcaId(coche.getMarca().getId())
                .precio(precioMapper(coche.getPrecios().get(0))).build();
    }

    private PrecioVO precioMapper(Precio precio) {
        return PrecioVO.builder()
                .id(precio.getId())
                .startDate(timestampToDate(precio.getStartDate()))
                .endDate(timestampToDate(precio.getEndDate()))
                .price(precio.getPrice()).build();
    }
}
