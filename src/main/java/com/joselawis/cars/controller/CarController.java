package com.joselawis.cars.controller;

import com.joselawis.cars.controller.filters.CocheFilter;
import com.joselawis.cars.vo.CarVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface CarController {

  @GetMapping("/cars/{date}/{id}")
  @ResponseBody
  List<CarVO> getCars(
      @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @PathVariable(name = "id") Long id);

  @GetMapping("/coches")
  @ResponseBody
  List<CarVO> getCoches(@RequestParam(value = "filter", required = false) CocheFilter filter);

  @GetMapping("/excel")
  @ResponseBody
  void exportToExcel(HttpServletResponse response) throws IOException;
}
