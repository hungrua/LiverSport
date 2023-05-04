package com.liversportweb.api.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.service.ISportFieldService;
import com.liversportweb.service.IUserService;

@RestController(value="admin-sport-field")
public class SportFieldAPI {

	@Autowired
	private ISportFieldService sportFieldService;
	@Autowired
	private IUserService userService;
	@GetMapping(value="/admin/field")
	public List<SportFieldDTO> get(@RequestBody SportFieldDTO dto){
		return sportFieldService.getByCondition( dto);
	}

//	@PostMapping(value ="/admin/field")
//	public SportFieldDTO save( SportFieldDTO dto) {
//		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//		String username = loggedInUser.getName();
//		UserDTO user = userService.getUser(username);
//		SportFieldDTO sportField = sportFieldService.save(dto);
//		user.setMySportField(sportField.getId());
//		userService.edit(user, user.getId());
//		return sportField;
//	}
	@PutMapping(value="/admin/field/{id}")
	public SportFieldDTO update(@RequestBody SportFieldDTO model, @PathVariable("id") long id ) {
		model.setId(id);
		return sportFieldService.save(model);
	}
	
	@DeleteMapping(value="/admin/field")
	public void delete(@RequestBody Long []ids) {
		sportFieldService.delete(ids);
	}
}