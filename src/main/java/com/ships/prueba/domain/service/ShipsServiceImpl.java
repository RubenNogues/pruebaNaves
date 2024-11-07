package com.ships.prueba.domain.service;

import com.ships.prueba.application.dto.RequestCreateShip;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import com.ships.prueba.application.service.ShipsService;
import com.ships.prueba.domain.entity.model.Ship;
import com.ships.prueba.infrastructure.exceptions.ShipNotFoundException;
import com.ships.prueba.infrastructure.repository.ShipsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShipsServiceImpl implements ShipsService {

  private final ShipsRepository shipsRepository;


  public ShipsServiceImpl(ShipsRepository shipsRepository) {
    this.shipsRepository = shipsRepository;
  }

  @Override
  @Cacheable(value = "ships", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
  public Page<ShipDto> getShips(Pageable pageable) {
    Page<Ship> ships = shipsRepository.findAll(pageable);
    List<ShipDto> shipDtos = ships.getContent().stream()
        .map(ship -> new ShipDto(ship.getId(), ship.getName(), ship.getFilm()))
        .toList();

    return new PageImpl<>(shipDtos, pageable, ships.getTotalElements());
  }

  @Override
  public ShipDto getShipById(Long id) {
    return shipsRepository.findById(id)
        .map(ship -> new ShipDto(ship.getId(), ship.getName(), ship.getFilm()))
        .orElseThrow(() -> new ShipNotFoundException("La nave con el id " + id + " no existe."));
  }

  @Override
  public List<ShipDto> getShipsByName(String name) {
    return shipsRepository.findByName(name).stream()
        .map(ship -> new ShipDto(ship.getId(), ship.getName(), ship.getFilm()))
        .toList();
  }

  @Override
  @CacheEvict(value = "ships", allEntries = true)
  public ShipDto createShip(RequestCreateShip ship) {
    Ship shipToAdd = Ship.builder()
        .name(ship.getName())
        .film(ship.getFilm())
        .creationDate(LocalDateTime.now())
        .build();
    Ship saved = shipsRepository.save(shipToAdd);
    return new ShipDto(saved.getId(), saved.getName(), saved.getFilm());
  }

  @Override
  @CacheEvict(value = "ships", allEntries = true)
  public ShipDto updateShip(RequestUpdateShip ship) {
    Ship shipToUpdate = shipsRepository.findById(ship.getId())
        .orElseThrow(
            () -> new ShipNotFoundException("La nave con el id " + ship.getId() + " no existe."));
    if (ship.getName() != null && !ship.getName().isEmpty()) {
      shipToUpdate.setName(ship.getName());
    }
    if (ship.getFilm() != null && !ship.getFilm().isEmpty()) {
      shipToUpdate.setFilm(ship.getFilm());
    }
    shipToUpdate.setUpdateDate(LocalDateTime.now());
    Ship saved = shipsRepository.save(shipToUpdate);
    return new ShipDto(saved.getId(), saved.getName(), saved.getFilm());
  }

  @Override
  @CacheEvict(value = "ships", allEntries = true)
  public void deleteShip(Long id) {
    shipsRepository.delete(shipsRepository.findById(id)
        .orElseThrow(() -> new ShipNotFoundException("La nave con el id " + id + " no existe.")));
  }
}
