package com.liversportweb.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liversportweb.DTO.BookingDTO;
import com.liversportweb.service.IBookingService;
@RestController(value="user-booking")
public class BookingAPI {
	
	@Autowired
	private IBookingService bookingService;
	
	
	@PostMapping(value="/user/bookings")
	public BookingDTO save(@RequestBody BookingDTO model) {
		BookingDTO book = bookingService.save(model);
		return book;
	}
	
	@PutMapping(value="/user/bookings/{id}")
	public BookingDTO update(@RequestBody BookingDTO model, @PathVariable("id") long id)  {
		model.setId(id);
		return bookingService.save(model);
	}
	
}