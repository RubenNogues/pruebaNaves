package com.ships.prueba.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipDto {

  private Long id;
  private String name;
  private String film;
}
