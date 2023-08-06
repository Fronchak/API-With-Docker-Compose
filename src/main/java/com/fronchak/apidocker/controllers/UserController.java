package com.fronchak.apidocker.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fronchak.apidocker.dtos.user.UserInputDTO;
import com.fronchak.apidocker.dtos.user.UserOutputDTO;
import com.fronchak.apidocker.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserOutputDTO> save(@RequestBody UserInputDTO inputDTO) {
		UserOutputDTO outputDTO = service.save(inputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(outputDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(outputDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserOutputDTO> update(@RequestBody UserInputDTO inputDTO, @PathVariable Long id) {
		UserOutputDTO outputDTO = service.update(inputDTO, id);
		return ResponseEntity.ok().body(outputDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserOutputDTO> findById(@PathVariable Long id) {
		UserOutputDTO outputDTO = service.findById(id);
		return ResponseEntity.ok().body(outputDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<UserOutputDTO>> findAll() {
		List<UserOutputDTO> dtos = service.findAll();
		return ResponseEntity.ok().body(dtos);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>  deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
