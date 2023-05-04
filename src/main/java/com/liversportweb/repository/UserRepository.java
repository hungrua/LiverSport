package com.liversportweb.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.liversportweb.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserName(String user);
	UserEntity findOneByEmail(String email);
}