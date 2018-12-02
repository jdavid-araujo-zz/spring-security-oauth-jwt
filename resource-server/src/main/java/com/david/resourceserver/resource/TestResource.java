package com.david.resourceserver.resource;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestResource implements Serializable {

	private static final long serialVersionUID = -8914953689494468109L;

	@GetMapping
	private ResponseEntity<String> getTest() {
		return ResponseEntity.status(HttpStatus.OK).body("It´s working");
	}
}
