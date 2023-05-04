package com.liversportweb.converter;

import org.springframework.stereotype.Component;

import com.liversportweb.DTO.CommentDTO;
import com.liversportweb.entity.CommentEntity;

@Component
public class CommentConverter {

	public CommentEntity toEntity(CommentDTO dto) {
		CommentEntity entity = new CommentEntity();
		entity.setContent(dto.getContent());
		return entity;
	}

	public CommentDTO toDto(CommentEntity entity) {

		CommentDTO dto = new CommentDTO();
		dto.setId(entity.getId());
		dto.setAvatar(entity.getUser().getImage());
		dto.setContent(entity.getContent());
		dto.setCreateDate(entity.getCreateDate());
		dto.setUserName(entity.getUser().getUserName());
		return dto;
	}
}