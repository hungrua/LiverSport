package com.liversportweb.repository;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.liversportweb.entity.BookingEntity;
import com.liversportweb.entity.UserEntity;
@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
//	List<BookingEntity> findAllByUserId(Long id);
//	List<BookingEntity> findAllBySportFieldId(Long id);
//	@Query(value = "select * from booking where bookingTime = '?1' and  bookingDate = '?2' ", nativeQuery = true)
	BookingEntity findByBookingTimeAndBookingDate(Time time, Date date);
	List<BookingEntity> findAllByUser(UserEntity user);
	@Query(value="SELECT * FROM booking where sport_field_id =?", nativeQuery = true)
	List<BookingEntity> findAllByMySportField(@Param("sport_field_id") Long id);

}