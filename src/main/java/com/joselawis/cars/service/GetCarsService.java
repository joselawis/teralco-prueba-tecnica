package com.joselawis.cars.service;

import com.joselawis.cars.controller.filters.CocheFilter;
import com.joselawis.cars.controller.specification.CocheSpecificationBuilder;
import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.repository.CocheRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GetCarsService {
  private final CocheRepository cocheRepository;
  private final CocheSpecificationBuilder builder;

  public GetCarsService(CocheRepository cocheRepository) {
    this.cocheRepository = cocheRepository;
    builder = new CocheSpecificationBuilder();
  }

  public List<Coche> getByDateAndId(Date date, Long id) {
    return cocheRepository.getAllByIdAndDate(dateToTimestamp(date), id);
  }

  private Timestamp dateToTimestamp(Date date) {
    return Timestamp.from(date.toInstant());
  }

  public List<Coche> getCoches(CocheFilter cochesFilters) {
    cochesFilters
        .getCriteria()
        .forEach(c -> builder.with(c.getKey(), c.getOperation(), c.getValue()));
    return cocheRepository.findAll(builder.build());
  }

  public List<Coche> getAll() {
    return (List<Coche>) cocheRepository.findAll();
  }
}
