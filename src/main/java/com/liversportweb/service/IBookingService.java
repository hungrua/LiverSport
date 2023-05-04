package com.liversportweb.service;
import java.util.List;
import com.liversportweb.DTO.BookingDTO;
public interface IBookingService {
	BookingDTO save(BookingDTO dto);
	void delete(Long id);
	List<BookingDTO> getAllByUserName(String userName);
	List<BookingDTO> getAllByCategory(Long id);
	List<BookingDTO> getAllMatchBySportField(Long id);

}