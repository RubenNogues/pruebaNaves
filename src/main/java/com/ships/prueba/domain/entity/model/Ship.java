package com.ships.prueba.domain.entity.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "SHIP")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ship {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "FILM")
  private String film;

  @Column(name = "CREATION_DATE")
  private LocalDateTime creationDate;

  @Column(name = "UPDATE_DATE")
  private LocalDateTime updateDate;
}
