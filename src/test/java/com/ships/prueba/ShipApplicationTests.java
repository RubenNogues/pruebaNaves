package com.ships.prueba;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ShipsApplication.class)
class ShipApplicationTests {


	@Test
	void contextLoads() {
        ShipsApplication application = new ShipsApplication();
        assertNotNull(application);
	}

}
