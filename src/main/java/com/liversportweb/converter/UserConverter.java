package com.liversportweb.converter;
import org.springframework.stereotype.Component;
import com.liversportweb.DTO.UserDTO;
import com.liversportweb.entity.UserEntity;
@Component
public class UserConverter {
	public UserEntity toEntity(UserDTO model) {
		UserEntity result = new UserEntity();
//		result.setId(model.getId());
		result.setUserName(model.getUserName());
		result.setPassword(model.getPassword());
		result.setEmail(model.getEmail());
		result.setFullname(model.getFullName());
		result.setDistrict(model.getDistrict());
		result.setCity(model.getCity());
		result.setImage("default.png");
		result.setPhone(model.getPhone());
		result.setFirstLogin(1);
		return result;
	}
	public UserDTO toDTO(UserEntity entity) {
		UserDTO result = new UserDTO();
		if(entity.getId()!=null) {
			result.setId(entity.getId());
		}
		result.setId(entity.getId());
		result.setUserName(entity.getUserName());
		result.setPassword(entity.getPassword());
		result.setRoleId(entity.getRole().getId());
		result.setEmail(entity.getEmail());
		result.setFullName(entity.getFullname());
		result.setDistrict(entity.getDistrict());
		result.setCity(entity.getCity());
		result.setImage(entity.getImage());
		result.setPhone(entity.getPhone());
		result.setFirstLogin(entity.isFirstLogin());
		if(entity.getMySportFieldEntity()!=null) result.setMySportField(entity.getMySportFieldEntity().getId());
		return result;
	}
	public UserEntity toEntity(UserDTO model, UserEntity entity) {
		entity.setUserName(model.getUserName());
		entity.setPassword(model.getPassword());
		entity.setEmail(model.getEmail());
		entity.setFullname(model.getFullName());
		entity.setDistrict(model.getDistrict());
		entity.setCity(model.getCity());
		entity.setImage(model.getImage());
		entity.setPhone(model.getPhone());
		entity.setFirstLogin(0);
		return entity;
	}
}