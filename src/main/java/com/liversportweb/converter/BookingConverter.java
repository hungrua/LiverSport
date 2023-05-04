package com.liversportweb.converter;

import org.springframework.stereotype.Component;

import com.liversportweb.DTO.BookingDTO;
import com.liversportweb.entity.BookingEntity;

@Component
public class BookingConverter {
	public BookingDTO toDTO(BookingEntity entity) {
		BookingDTO result = new BookingDTO();
		result.setId(entity.getId());
		result.setBookingTime(entity.getBookingTime());
		result.setBookingDate(entity.getBookingDate());
		result.setPrice(entity.getSport_field_id().getPrice());
		result.setSportFieldName(entity.getSport_field_id().getName());
		result.setUserName(entity.getUser().getUserName());
		result.setUserId(entity.getUser().getId());
		result.setSportFieldId(entity.getSport_field_id().getId());
		result.setCategoryId(entity.getSport_field_id().getCategory().getId());
		return result;
	}
	
	public BookingEntity toEntity(BookingDTO dto) {
		BookingEntity entity = new BookingEntity();
		entity.setId(dto.getId());
		entity.setBookingDate(dto.getBookingDate());
		entity.setBookingTime(dto.getBookingTime());
		return entity;
	}
	public BookingEntity toEntity(BookingDTO dto,BookingEntity entity) {
		entity.setId(dto.getId());
		entity.setBookingDate(dto.getBookingDate());
		entity.setBookingTime(dto.getBookingTime());
		return entity;
	}
	
}
