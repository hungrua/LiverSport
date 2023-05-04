package com.liversportweb.service.impl;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liversportweb.DTO.BookingDTO;
import com.liversportweb.converter.BookingConverter;
import com.liversportweb.entity.BookingEntity;
import com.liversportweb.repository.BookingRepository;
import com.liversportweb.repository.SportFieldRepository;
import com.liversportweb.repository.UserRepository;
import com.liversportweb.service.IBookingService;
@Service
public class BookingService implements IBookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SportFieldRepository sportFieldRepository;
	
	@Autowired
	BookingConverter bookingConverter;

	@Override
	public BookingDTO save(BookingDTO dto)  {
		BookingEntity book = bookingRepository.findByBookingTimeAndBookingDate(dto.getBookingTime(),dto.getBookingDate());
		if(book != null) {
			return new BookingDTO();
		}
		else {
			BookingEntity entity = new BookingEntity();
			entity = bookingConverter.toEntity(dto);
			if(check(entity.getBookingDate(),entity.getBookingTime())) {
				entity.setUser(userRepository.findOne(dto.getUserId()));
				entity.setSport_field_id(sportFieldRepository.findOne(dto.getSportFieldId()));
				entity = bookingRepository.save(entity);
				return bookingConverter.toDTO(entity);
			}
			else {
				BookingDTO exception = new BookingDTO();
				exception.setId((long)-1);
				return exception;
			}
		}
	}
	@Override
	public void delete(Long id) {
			bookingRepository.delete(id);
	}
	@Override
	public List<BookingDTO> getAllByUserName(String userName) {
		List<BookingDTO> matches = new ArrayList<BookingDTO>();
		List<BookingEntity> entities = new ArrayList<BookingEntity>();
		entities = bookingRepository.findAllByUser(userRepository.findOneByUserName(userName));
		for(BookingEntity x : entities) {
			if(check(x.getBookingDate(),x.getBookingTime())) matches.add(bookingConverter.toDTO(x));
			else bookingRepository.delete(x);
		}
		Collections.sort(matches);
		return matches;
	}
	@Override
	public List<BookingDTO> getAllByCategory(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BookingDTO> getAllMatchBySportField( Long id) {
		List<BookingDTO> result = new ArrayList<BookingDTO>();
		List<BookingEntity> matches = bookingRepository.findAllByMySportField(id);
		for(BookingEntity x : matches) {
			result.add(bookingConverter.toDTO(x));
		}
		Collections.sort(result);
		return result;
	}
	public static boolean check(Date date, Time time) {
		LocalDateTime now = LocalDateTime.now();
		String dateLocal = date.toString();
		String timeLocal = time.toString();
		Timestamp timeStamp = Timestamp.valueOf(dateLocal+" "+timeLocal);
		LocalDateTime tmp = timeStamp.toLocalDateTime();
		if(tmp.isAfter(now)) return true;
		else return false;
		
	}


}