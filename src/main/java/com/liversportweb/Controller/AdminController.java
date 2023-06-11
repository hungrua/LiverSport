package com.liversportweb.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.liversportweb.DTO.BookingDTO;
import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.service.IBookingService;
import com.liversportweb.service.ISportFieldService;
import com.liversportweb.service.IUserService;

@Controller
public class AdminController {
	@Autowired
	IBookingService bookingService;
	@Autowired
	IUserService userService;
	@Autowired
	ISportFieldService sportFieldService;

	@GetMapping("/admin/match")
	public String getMatch(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		List<BookingDTO> matches = new ArrayList<BookingDTO>();
		matches = bookingService.getAllMatchBySportField(user.getMySportField());
		SportFieldDTO sportField = sportFieldService.findOneById(user.getMySportField());
		model.addAttribute("user",user);
		model.addAttribute("matches",matches);
		model.addAttribute("sportField",sportField);
		return "list-match-admin";
	}
	@GetMapping("/admin/first-login")
	public String firstLogin(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		SportFieldDTO sportField = new SportFieldDTO();
		if(user.isFirstLogin()==0) {
			sportField = sportFieldService.findOneById(user.getMySportField());
		}
		model.addAttribute("user",user);
		model.addAttribute("sportField",sportField);
		return "adminInfo";
	}	
	@PostMapping(value ="/admin/field")
	public String save( Model model, SportFieldDTO dto) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		if(user.isFirstLogin()==1) {
			SportFieldDTO sportField = sportFieldService.save(dto);
			user.setMySportField(sportField.getId());
			userService.edit(user, user.getId());
		}
		else {
			dto.setId(user.getMySportField());
			sportFieldService.save(dto);
		}
		return "redirect:/admin/match";
	}
	
	@GetMapping("/admin/information")
	public String userInfoString(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
		return "adminPersonalInfoRender";
	}
	@GetMapping("/admin/information/editPage")
	public String toEditUser(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
		return "adminPersonalInfo";
	}
	@PutMapping("admin/information/save/{id}")
	public String editUser(UserDTO dto, @PathVariable("id") Long id, Model model) {
		UserDTO user = userService.edit(dto,id);
		model.addAttribute("user",user);
		return "adminPersonalInfoRender";
	}
}