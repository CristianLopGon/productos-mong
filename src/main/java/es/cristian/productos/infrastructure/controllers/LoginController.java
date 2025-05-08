package es.cristian.productos.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cristian.productos.infrastructure.security.AuthResponse;
import es.cristian.productos.infrastructure.security.JwtTokenProvider;

@RestController
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenProvider provider;

	public LoginController(JwtTokenProvider provider) {
		this.provider = provider;
	}

	@GetMapping("/token")
	public ResponseEntity<AuthResponse> login() {
		return ResponseEntity.ok(new AuthResponse(provider.generateToken("test")));
	}
}
