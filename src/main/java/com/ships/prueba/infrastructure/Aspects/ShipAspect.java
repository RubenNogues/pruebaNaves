package com.ships.prueba.infrastructure.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ShipAspect {

  private static final Logger logger = LoggerFactory.getLogger(ShipAspect.class);

  @Before("execution(* com.ships.prueba.infrastructure.controller.impl.ShipsControllerImpl.getShipById(..)) && args(id)")
  public void logIfIdNegative(JoinPoint joinPoint, Long id) {
    if (id < 0) {
      logger.info("Se ha solicitado una nave con id negativo: {}", id);
    }
  }
}
