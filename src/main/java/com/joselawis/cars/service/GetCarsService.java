package com.joselawis.cars.service;

import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GetCarsService {
  @Autowired private CocheRepository cocheRepository;

  public List<Coche> getByDateAndId(Timestamp time, Long id) {
    return cocheRepository.getAllByIdAndDate(time, id);
  }
}
