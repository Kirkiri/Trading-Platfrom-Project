package com.project.springboot.tradingplatform.users.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	 public UserEntity findByEmail(String email);
}
