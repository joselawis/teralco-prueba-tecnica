package com.joselawis.cars.controller;

import com.joselawis.cars.controller.filters.CocheFilter;
import com.joselawis.cars.entity.Coche;
import com.joselawis.cars.entity.Precio;
import com.joselawis.cars.excel.CocheExcelExporter;
import com.joselawis.cars.exception.NotFoundException;
import com.joselawis.cars.service.GetCarsService;
import com.joselawis.cars.vo.CarVO;
import com.joselawis.cars.vo.PrecioVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarControllerImpl implements CarController {

  private final GetCarsService getCarsService;

  public CarControllerImpl(GetCarsService getCarsService) {
    this.getCarsService = getCarsService;
  }

  @Override
  @GetMapping("/cars/{date}/{id}")
  public @ResponseBody List<CarVO> getCars(
      @PathVariable(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
      @PathVariable(name = "id") Long id) {
    List<Coche> coches = getCarsService.getByDateAndId(date, id);
    if (coches == null || coches.isEmpty()) throw new NotFoundException();
    return coches.stream().map(this::cocheMapper).collect(Collectors.toList());
  }

  @Override
  @GetMapping("/coches")
  public @ResponseBody List<CarVO> getCoches(
      @RequestParam(value = "filter", required = false) CocheFilter filter) {
    List<Coche> coches = getCarsService.getCoches(filter);
    if (coches == null || coches.isEmpty()) throw new NotFoundException();
    return coches.stream().map(this::cocheMapper).collect(Collectors.toList());
  }

  @Override
  @GetMapping(value = "/excel")
  public void exportToExcel(HttpServletResponse response) throws IOException {
    response.setContentType("application/octet-stream");
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    String currentDateTime = dateFormatter.format(new Date());

    String headerKey = "Content-Disposition";
    String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
    response.setHeader(headerKey, headerValue);

    List<Coche> listCoches = getCarsService.getAll();

    CocheExcelExporter excelExporter = new CocheExcelExporter(listCoches);

    excelExporter.export(response);
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
