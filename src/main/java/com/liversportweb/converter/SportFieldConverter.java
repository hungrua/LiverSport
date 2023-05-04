package com.liversportweb.converter;

import org.springframework.stereotype.Component;

import com.liversportweb.DTO.SportFieldDTO;
import com.liversportweb.entity.SportFieldEntity;

@Component
public class SportFieldConverter {
	public SportFieldDTO toDTO(SportFieldEntity model) {
		SportFieldDTO result = new SportFieldDTO();
		if(result.getId()==null) {
			result.setId(model.getId());
		}
		result.setCategoryId(model.getCategory().getId());
		result.setName(model.getName());
		result.setPrice(model.getPrice());
		result.setStatus(model.getStatus());
		result.setInfo(model.getInfo());
		result.setDistrict(model.getDistrict());
		result.setCity(model.getCity());
		result.setAddress(model.getAddress());
		result.setImage(model.getImage());
		return result;
	}
	public SportFieldEntity toEntity(SportFieldDTO dto) {
		SportFieldEntity entity = new SportFieldEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setStatus(dto.getStatus());
		entity.setInfo(dto.getInfo());
		entity.setDistrict(dto.getDistrict());
		entity.setCity(dto.getCity());
		entity.setAddress(dto.getAddress());
		entity.setImage(dto.getImage());
		return entity;
	}
	public SportFieldEntity toEntity(SportFieldDTO dto,	SportFieldEntity entity) {
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setStatus(dto.getStatus());
		entity.setInfo(dto.getInfo());
		entity.setDistrict(dto.getDistrict());
		entity.setCity(dto.getCity());
		entity.setAddress(dto.getAddress());
		entity.setImage(dto.getImage());
		return entity;
	}
}
