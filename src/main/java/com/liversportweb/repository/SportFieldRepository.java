package com.liversportweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liversportweb.entity.SportFieldEntity;
@Repository
public interface SportFieldRepository extends JpaRepository<SportFieldEntity, Long> {
	SportFieldEntity findOne(Long id);
	List<SportFieldEntity> findAllByCategory_id(Long id);
	List<SportFieldEntity> findAllByCityAndCategory_id(String city,Long category_id);
	List<SportFieldEntity> findAllByCityAndDistrictAndCategory_id(String city, String district,Long category_id);
	List<SportFieldEntity> findAllByCityAndCategory_idAndName(String City,Long category_id, String name);
	List<SportFieldEntity> findAllByCityAndDistrictAndCategory_idAndName(String city, String district,Long category_id, String name);
}
