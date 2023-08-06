package com.fronchak.apidocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.apidocker.dtos.user.UserInputDTO;
import com.fronchak.apidocker.dtos.user.UserOutputDTO;
import com.fronchak.apidocker.entities.User;
import com.fronchak.apidocker.exceptions.DatabaseException;
import com.fronchak.apidocker.exceptions.ResourceNotFoundException;
import com.fronchak.apidocker.mappers.UserMapper;
import com.fronchak.apidocker.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserMapper mapper;
	
	@Transactional
	public UserOutputDTO save(UserInputDTO inputDTO) {
		User entity = new User();
		mapper.copyToUser(entity, inputDTO);
		entity = repository.save(entity);
		return mapper.mapToUserOutputDTO(entity);
	}
	
	@Transactional
	public UserOutputDTO update(UserInputDTO inputDTO, Long id) {
		try {
			User entity = repository.getReferenceById(id);
			mapper.copyToUser(entity, inputDTO);
			entity = repository.save(entity);
			return mapper.mapToUserOutputDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("User not found by ID: " + id);
		}
	}
	
	public UserOutputDTO findById(Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found by ID: " + id));
		return mapper.mapToUserOutputDTO(entity);
	}
	
	public List<UserOutputDTO> findAll() {
		List<User> entities = repository.findAll();
		return mapper.mapToUsersOutputDTO(entities);
	}
	
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("User not found by ID: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("This user cannot be deleted");
		}
	}
}
