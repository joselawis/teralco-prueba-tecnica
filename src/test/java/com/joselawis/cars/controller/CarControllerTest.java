package com.joselawis.cars.controller;

import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.exception.NotFoundException;
import com.joselawis.cars.service.GetCarsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {
  @InjectMocks private CarControllerImpl controller;
  @Mock private GetCarsService mockedGetCarsService;

  @Test(expected = NotFoundException.class)
  public void getCarsNotFound() {
    Mockito.when(mockedGetCarsService.getByDateAndId(Mockito.any(), Mockito.eq(1L)))
        .thenReturn(new ArrayList<>());
    controller.getCars(Date.from(Instant.now()), 1L);
  }

  @Test
  public void getCars() {
    List<Coche> coches = new ArrayList<>();
    coches.add(Coche.builder().build());
    Mockito.when(mockedGetCarsService.getByDateAndId(Mockito.any(), Mockito.eq(1L)))
        .thenReturn(coches);
    controller.getCars(Date.from(Instant.now()), 1L);
  }
}
