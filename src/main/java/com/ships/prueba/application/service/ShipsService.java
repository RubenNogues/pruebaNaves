package com.ships.prueba.application.service;

import com.ships.prueba.application.dto.RequestCreateShip;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShipsService {

  Page<ShipDto> getShips(Pageable pageable);

  ShipDto getShipById(Long id);

  List<ShipDto> getShipsByName(String name);

  ShipDto createShip(RequestCreateShip ship);

  ShipDto updateShip(RequestUpdateShip ship);

  void deleteShip(Long id);
}
