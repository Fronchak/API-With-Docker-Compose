package com.fronchak.apidocker.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.apidocker.dtos.user.UserInputDTO;
import com.fronchak.apidocker.dtos.user.UserOutputDTO;
import com.fronchak.apidocker.entities.User;

@Service
public class UserMapper {

	public UserOutputDTO mapToUserOutputDTO(User entity) {
		UserOutputDTO dto = new UserOutputDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		return dto;
	}
	
	public List<UserOutputDTO> mapToUsersOutputDTO(Collection<User> entities) {
		return entities.stream()
				.map((entity) -> mapToUserOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyToUser(User entity, UserInputDTO dto) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
	}
}
