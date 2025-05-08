package es.cristian.productos.infrastructure.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "test.security.enabled=false")
@ActiveProfiles("test")
public class ProductControllerNOSecurityTest {

	@LocalServerPort
	int port;

	@BeforeEach
	void setup() {
		RestAssured.port = port;
	}

	@Test
	void sortProducts_returnSortedListaTest() {
		given().contentType(ContentType.JSON).body("{\"salesWeight\": 0.6, \"stockRatioWeight\": 0.4}").when()
				.post("/products/sort").then().statusCode(200).body("$", not(empty()))
				.body("[0].score", greaterThanOrEqualTo(0f));
	}

	@Test
	void sortProducts_invalidValues_return400Test() {
		given().contentType(ContentType.JSON).body("{\"salesWeight\": -1, \"stockRatioWeight\": 0.4}").when()
				.post("/products/sort").then().statusCode(400);
	}
}
