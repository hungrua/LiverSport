package com.liversportweb.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.service.IUserService;
@RestController
public class UserAPI {
	@Autowired
	private IUserService userSerivce;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/user/add")
	public String addUser(@RequestBody UserDTO userDTO) {
		String tmp = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(tmp);
		return userSerivce.save(userDTO);
	}
}