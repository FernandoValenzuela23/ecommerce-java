package com.fva.microservices.inventory;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import com.mysql.cj.MysqlConnection;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {
	
	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");
	
	@LocalServerPort
	private Integer port;
	
	@BeforeEach
	void setup()
	{
		RestAssured.baseURI = "http;//localhost";
		RestAssured.port = port;
	}
	
	static 
	{
		mySQLContainer.start();
	}

	@Test
	void shouldReadInventory() {
		boolean positiveResponse = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone15&quantity=1")
				.then()
				.extract()
				.body()
				.as(Boolean.class);
		
		assertTrue(positiveResponse);
		
		boolean negativeResponse = RestAssured.given()
				.when()
				.get("/api/inventory?skuCode=iphone15&quantity=1000")
				.then()
				.extract()
				.body()
				.as(Boolean.class);
		
		assertTrue(negativeResponse);
	}

}
