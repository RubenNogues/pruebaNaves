package com.ships.prueba.infrastructure.controller.impl;

import com.ships.prueba.application.dto.RequestCreateShip;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import com.ships.prueba.application.service.ShipsService;
import com.ships.prueba.infrastructure.controller.ShipsController;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ShipsControllerImpl implements ShipsController {

  private static final Logger log = LoggerFactory.getLogger(ShipsControllerImpl.class);

  private final ShipsService shipsService;

  public ShipsControllerImpl(ShipsService shipsService) {
    this.shipsService = shipsService;
  }

  @Override
  public ResponseEntity<Page<ShipDto>> getShips(Integer size, Integer page) {
    log.atInfo().log("Obteniendo naves paginadas");
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(shipsService.getShips(pageable));
  }

  @Override
  public ResponseEntity<ShipDto> getShipById(Long id) {
    log.atInfo().log("Obteniendo naves por Id");
    return ResponseEntity.ok(shipsService.getShipById(id));
  }

  @Override
  public ResponseEntity<List<ShipDto>> getShipsByName(String name) {
    log.atInfo().log("Obteniendo naves por nombre");
    return ResponseEntity.ok(shipsService.getShipsByName(name));
  }

  @Override
  public ResponseEntity<ShipDto> addShip(RequestCreateShip ship) {
    log.atInfo().log("creando nueva nave");
    return new ResponseEntity<>(shipsService.createShip(ship), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<ShipDto> editShip(RequestUpdateShip ship) {
    log.atInfo().log("modificando nave");
    return ResponseEntity.ok(shipsService.updateShip(ship));
  }

  @Override
  public ResponseEntity<Void> deleteShip(Long id) {
    log.atInfo().log("eliminando nave");
    shipsService.deleteShip(id);
    return ResponseEntity.noContent().build();
  }
}
