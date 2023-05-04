package com.liversportweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.liversportweb.service.IBookingService;

@Controller
public class BookingController {
	@Autowired
	IBookingService bookingService;
	@DeleteMapping(value="/user/bookings/{id}")
	public String delete( @PathVariable("id") long id) {
		bookingService.delete(id);
		return "redirect:/yourMatch";
	}
	@DeleteMapping(value="/admin/bookings/{id}")
	public String adminDelete( @PathVariable("id") long id) {
		bookingService.delete(id);
		return "redirect:/admin/match";
	}
}
