package com.liversportweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.liversportweb.DTO.UserDTO;
import com.liversportweb.service.IUserService;

@Controller
public class UserController {
	@Autowired
	IUserService userService;
	@PutMapping("user/information/save/{id}")
	public String editUser(UserDTO dto, @PathVariable("id") Long id, Model model) {
		UserDTO user = userService.edit(dto,id);
		model.addAttribute("user",user);
		return "userInfoRender";
	}
}