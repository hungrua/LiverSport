package com.liversportweb.DTO;

import java.sql.Date;
import java.sql.Time;

public class BookingDTO implements Comparable<BookingDTO> {
	private Long id, sportFieldId,userId,categoryId;
	private Date bookingDate;
	private Time bookingTime;
	private Long price;
	private String sportFieldName;
	private String userName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Time getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getSportFieldName() {
		return sportFieldName;
	}
	public void setSportFieldName(String sportFieldName) {
		this.sportFieldName = sportFieldName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getSportFieldId() {
		return sportFieldId;
	}
	public void setSportFieldId(Long sportFieldId) {
		this.sportFieldId = sportFieldId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@Override
	public int compareTo(BookingDTO o) {
		if(this.bookingDate.after(o.bookingDate)) return 1;
		else if(this.bookingDate.equals(o.getBookingDate())) {
			if(this.bookingTime.before(o.bookingTime)) return 1;
			else return -1;
		}
		else return -1;
	}
		
}
