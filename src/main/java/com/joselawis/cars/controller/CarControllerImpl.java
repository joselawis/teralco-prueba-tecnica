package com.joselawis.cars.controller;

import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.entity.Precio;
import com.joselawis.cars.exception.NotFoundException;
import com.joselawis.cars.service.GetCarsService;
import com.joselawis.cars.vo.CarVO;
import com.joselawis.cars.vo.PrecioVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarControllerImpl implements CarController {

  private final GetCarsService getCarsService;

  public CarControllerImpl(GetCarsService getCarsService) {
    this.getCarsService = getCarsService;
  }

  @Override
  @GetMapping("/cars/{date}/{id}")
  public List<CarVO> getCars(
      @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @PathVariable(name = "id") Long id) {
    List<Coche> coches = getCarsService.getByDateAndId(date, id);
    if (coches.isEmpty()) throw new NotFoundException();
    return coches.stream().map(this::cocheMapper).collect(Collectors.toList());
  }

  private Date timestampToDate(Timestamp timestamp) {
    return new Date(timestamp.getTime());
  }

  private CarVO cocheMapper(Coche coche) {
    return CarVO.builder()
        .id(coche.getId())
        .marcaId(coche.getMarca() != null ? coche.getMarca().getId() : null)
        .precio(
            coche.getPrecios() != null && !coche.getPrecios().isEmpty()
                ? precioMapper(coche.getPrecios().get(0))
                : null)
        .build();
  }

  private PrecioVO precioMapper(Precio precio) {
    return PrecioVO.builder()
        .id(precio.getId())
        .startDate(precio.getStartDate() != null ? timestampToDate(precio.getStartDate()) : null)
        .endDate(precio.getEndDate() != null ? timestampToDate(precio.getEndDate()) : null)
        .price(precio.getPrice())
        .build();
  }
}
