package es.cristian.productos.infrastructure.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import es.cristian.productos.infrastructure.security.JwtTokenProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "test.security.enabled=true")
@ActiveProfiles("test")
public class ProductControllerSecurityTest {

	@LocalServerPort
	int port;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@BeforeEach
	void setup() {
		RestAssured.port = port;
	}

	@Test
	void sortProducts_returnSortedListaTest() {
		String token = jwtTokenProvider.generateToken("test");
		given().header("Authorization", String.format("Bearer %s", token)).contentType(ContentType.JSON)
				.body("{\"salesWeight\": 0.6, \"stockRatioWeight\": 0.4}").when().post("/products/sort").then()
				.statusCode(200).body("$", not(empty())).body("[0].score", greaterThanOrEqualTo(0f));
	}

	@Test
	void sortProducts_invalidValues_return400Test() {
		String token = jwtTokenProvider.generateToken("test");
		given().header("Authorization", String.format("Bearer %s", token)).contentType(ContentType.JSON)
				.body("{\"salesWeight\": -1, \"stockRatioWeight\": 0.4}").when().post("/products/sort").then()
				.statusCode(400);
	}

	@Test
	void sortProducts_invalidTokenTest() {
		given().header("Authorization", "Bearer erroneo").contentType(ContentType.JSON)
				.body("{\"salesWeight\": 0.6, \"stockRatioWeight\": 0.4}").when().post("/products/sort").then()
				.statusCode(401);
	}

	@Test
	void sortProducts_noTokenTest() {
		given().contentType(ContentType.JSON).body("{\"salesWeight\": 0.6, \"stockRatioWeight\": 0.4}").when()
				.post("/products/sort").then().statusCode(401);
	}
}
