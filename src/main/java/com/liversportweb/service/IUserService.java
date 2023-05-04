package com.liversportweb.service;
import com.liversportweb.DTO.UserDTO;

public interface IUserService {
	String save(UserDTO userDTO);
	UserDTO getUser(String userName);
	UserDTO edit(UserDTO userDTO, Long id);
}