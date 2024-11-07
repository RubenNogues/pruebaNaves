package com.ships.prueba.infrastructure.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ships.prueba.application.dto.RequestUpdateShip;
import com.ships.prueba.application.dto.ShipDto;
import com.ships.prueba.application.service.ShipsService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ShipControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private ShipsService shipsService;

  private ShipDto shipDTO;
  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  public void setUp() {
    shipDTO = new ShipDto(1L, "ship Test", "film 1");
  }

  @Test
  void testAddShip() throws Exception {

    when(shipsService.createShip(any())).thenReturn(shipDTO);
    mockMvc.perform(post("/naves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(shipDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("ship Test"))
        .andExpect(jsonPath("$.film").value("film 1"));
  }

  @Test
  void testUpdateShip() throws Exception {
    when(shipsService.updateShip(any()))
        .thenReturn(shipDTO);
    shipDTO.setName("nave modificada");
    RequestUpdateShip requestUpdateShip = new RequestUpdateShip(shipDTO.getName(),
        shipDTO.getFilm(), 1L);
    ResultActions perform = mockMvc.perform(put("/naves")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestUpdateShip)))
        .andDo(MockMvcResultHandlers.print());
    perform
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("nave modificada"))
        .andExpect(jsonPath("$.film").value("film 1"));
  }

  @Test
  void testDeleteShip() throws Exception {

    doNothing().when(shipsService).deleteShip(any());
    mockMvc.perform(delete("/naves")
            .queryParam("id", "1"))
        .andExpect(status().isNoContent());
  }

  @Test
  void testObtainShipById() throws Exception {

    when(shipsService.getShipById(anyLong())).thenReturn(shipDTO);

    mockMvc.perform(get("/naves/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("ship Test"))
        .andExpect(jsonPath("$.film").value("film 1"));
  }

  @Test
  void testObtainShipByName() throws Exception {

    when(shipsService.getShipsByName(any())).thenReturn(List.of(shipDTO));

    mockMvc.perform(get("/naves")
            .queryParam("name", "falcon")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}