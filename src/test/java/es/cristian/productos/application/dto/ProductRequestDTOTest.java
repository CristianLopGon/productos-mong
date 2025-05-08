package es.cristian.productos.application.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ProductRequestDTOTest {
	private Validator validator;

	@BeforeEach
	void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void dto_constructorOkTest() {
		ProductRequestDTO dto = new ProductRequestDTO(0.6, 0.4);
		Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(dto);
		assertTrue(violations.isEmpty(), "DTO vOK");
	}

	@Test
	void dto_salesNegative_returnErrorTest() {
		ProductRequestDTO dto = new ProductRequestDTO(-1.0, 0.4);
		Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(dto);
		assertFalse(violations.isEmpty(), "DTO negative value -> Error");

		ConstraintViolation<ProductRequestDTO> violation = violations.iterator().next();
		assertTrue(violation.getMessage().contains("salesWeight must be greater than 0"));
	}

	@Test
	void dto_ratioHigherOne_returnErrorTest() {
		ProductRequestDTO dto = new ProductRequestDTO(0.6, 1.5);
		Set<ConstraintViolation<ProductRequestDTO>> violations = validator.validate(dto);
		assertFalse(violations.isEmpty(), "DTO higher than 1 -> Error");

		ConstraintViolation<ProductRequestDTO> violation = violations.iterator().next();
		assertTrue(violation.getMessage().contains("stockRatioWeight must be less than or equal to 1"));
	}

}
