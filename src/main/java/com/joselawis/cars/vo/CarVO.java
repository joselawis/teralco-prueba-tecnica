package com.joselawis.cars.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarVO {
    private Long id;
    private Long marcaId;
    private PrecioVO precio;
}
