package com.example.reading.is.good;

import com.example.reading.is.good.controller.impl.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private CustomerController controller;
	@Test
	void contextLoads() {
		assertNotEquals(controller,null);
	}

}
