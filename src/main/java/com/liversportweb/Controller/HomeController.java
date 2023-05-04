package com.liversportweb.Controller;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.liversportweb.DTO.BookingDTO;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.service.IBookingService;
import com.liversportweb.service.IUserService;
@Controller
public class HomeController {
	@Autowired
	IUserService userService;
	@Autowired
	IBookingService bookingService;
	@GetMapping("/")
    public String home() {
        return "index";  
    }
	@GetMapping("/user/page")
	public String userPage(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("greeting", user.getFullName());
		model.addAttribute("img",user.getImage());
		model.addAttribute("user",user);
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		return "login";  
    }
	@GetMapping("/register")
	public String register() {
       return "register";  
    }
	@GetMapping("/booking")
    public String booking(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
        return "booking";  
    }
	@GetMapping("/sport_field")
    public String sport_field(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
        return "sport_field";  
    }
	@GetMapping("/yourMatch")
	public String yourMatch(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		List<BookingDTO> matches = bookingService.getAllByUserName(user.getUserName());
		model.addAttribute("user",user);
		model.addAttribute("matches",matches);
		model.addAttribute("count",matches.size());
		return "match";
	}
	@GetMapping("/favorite")
	public String getFavorite(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
		return "favorite";
	}
	
	@GetMapping("/user/information")
	public String userInfoString(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
		return "userInfoRender";
	}
	@GetMapping("/user/information/editPage")
	public String toEditUser(Model model, Principal principal) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		UserDTO user = userService.getUser(username);
		model.addAttribute("user",user);
		return "userInfo";
	}
}