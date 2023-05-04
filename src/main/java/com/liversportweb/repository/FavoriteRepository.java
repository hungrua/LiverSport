package com.liversportweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.liversportweb.entity.FavoriteEntity;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long>{
	@Query(value="select * from favorite where user_id = ?", nativeQuery= true)
	List <FavoriteEntity> getAllByUser(@Param("user_id") Long id);
	@Query(value="select * from favorite where user_id = ?1 and sportfield_id=?2", nativeQuery= true)
	FavoriteEntity findByUserAndSportField(@Param("user_id") Long id1, @Param("sportfield_id") Long id2);
}
