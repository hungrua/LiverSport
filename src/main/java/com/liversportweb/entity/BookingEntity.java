package com.liversportweb.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="booking")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="bookingDate")
	private Date bookingDate;
	
	@Column(name="bookingTime")
	private Time bookingTime;
	@Column(name="categoryId")
	private Long categoryId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	@ManyToOne
	@JoinColumn(name="sport_field_id")
	private SportFieldEntity sport_field_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public SportFieldEntity getSport_field_id() {
		return sport_field_id;
	}

	public void setSport_field_id(SportFieldEntity sport_field_id) {
		this.sport_field_id = sport_field_id;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}	
}
