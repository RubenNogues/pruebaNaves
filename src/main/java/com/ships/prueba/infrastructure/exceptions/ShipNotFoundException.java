package com.ships.prueba.infrastructure.exceptions;

public class ShipNotFoundException extends RuntimeException {

  public ShipNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
