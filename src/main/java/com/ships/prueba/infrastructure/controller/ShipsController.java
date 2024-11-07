package com.ships.prueba.infrastructure.controller;


import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.ships.prueba.application.dto.RequestCreateShip;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ShipsController {

  @GetMapping(value = "/naves", params = {"size", "page"}, produces = {"application/json"})
  ResponseEntity<Page<ShipDto>> getShips(@RequestParam("size") Integer size,
      @RequestParam("page") Integer page);

  @GetMapping(value = "/naves/{id}", produces = {"application/json"})
  ResponseEntity<ShipDto> getShipById(@PathVariable("id") Long id);

  @GetMapping(value = "/naves", params = {"name"}, produces = {"application/json"})
  ResponseEntity<List<ShipDto>> getShipsByName(@RequestParam("name") String name);

  @PostMapping(value = "/naves", produces = {"application/json"})
  ResponseEntity<ShipDto> addShip(@RequestBody() RequestCreateShip ship);

  @PutMapping(value = "/naves", produces = {"application/json"})
  ResponseEntity<ShipDto> editShip(@RequestBody() RequestUpdateShip ship);

  @DeleteMapping(value = "/naves", params = {"id"}, produces = {"application/json"})
  ResponseEntity<Void> deleteShip(@RequestParam("id") Long id);
}
