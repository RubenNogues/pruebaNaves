package com.ships.prueba.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.ships.prueba.application.dto.RequestCreateShip;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import com.ships.prueba.domain.entity.model.Ship;
import com.ships.prueba.infrastructure.repository.ShipsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ShipServiceTest {

  @InjectMocks
  private ShipsServiceImpl shipsService;

  @Mock
  private ShipsRepository shipsRepository;

  private ShipDto shipDto;
  private Ship ship;

  @BeforeEach
  void setUp() {
    shipDto = new ShipDto(1L, "ship Test", "film test");
    ship = new Ship(1L, "ship Test", "film test", LocalDateTime.now(), LocalDateTime.now());
  }

  @Test
  void testGetShips() {

    Pageable pageable = PageRequest.of(0, 1);
    Page<Ship> shipPage = new PageImpl<>(List.of(ship), pageable, 0);
    when(shipsRepository.findAll(pageable)).thenReturn(shipPage);
    Page<ShipDto> ships = shipsService.getShips(pageable);
    assertEquals(1, ships.getTotalElements());
    assertEquals(List.of(shipDto), ships.getContent());
  }

  @Test
  void testGetShipById() {

    when(shipsRepository.findById(any())).thenReturn(Optional.of(ship));
    ShipDto shipById = shipsService.getShipById(1L);
    assertEquals(shipById, shipDto);
  }

  @Test
  void testGetShipsByName() {

    when(shipsRepository.findByName(any())).thenReturn(List.of(ship));
    List<ShipDto> shipsByName = shipsService.getShipsByName("name");
    assertEquals(1, shipsByName.size());
    assertEquals(List.of(shipDto), shipsByName);
  }

  @Test
  void testCreateShip() {
    RequestCreateShip requestCreateShip = new RequestCreateShip(ship.getName(), ship.getFilm());
    when(shipsRepository.save(any())).thenReturn(ship);
    ShipDto newShip = shipsService.createShip(requestCreateShip);
    assertEquals(shipDto, newShip);
  }

  @Test
  void testUpdateShip() {
    RequestUpdateShip requestUpdateShip = new RequestUpdateShip(ship.getName(), ship.getFilm(), 1L);
    when(shipsRepository.findById(1L)).thenReturn(Optional.of(ship));
    when(shipsRepository.save(any())).thenReturn(ship);
    ShipDto updatedShip = shipsService.updateShip(requestUpdateShip);
    assertEquals(shipDto, updatedShip);
  }

  @Test
  void testDeleteShip() {

    when(shipsRepository.findById(any())).thenReturn(Optional.of(ship));
    doNothing().when(shipsRepository).delete(any());
    shipsService.deleteShip(1L);
  }
}