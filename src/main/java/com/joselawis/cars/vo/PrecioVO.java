package com.joselawis.cars.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrecioVO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Double price;
}
