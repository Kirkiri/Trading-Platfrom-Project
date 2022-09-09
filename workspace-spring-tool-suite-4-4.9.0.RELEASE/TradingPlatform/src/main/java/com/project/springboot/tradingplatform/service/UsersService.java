package com.project.springboot.tradingplatform.service;

import java.util.List;

import com.project.springboot.tradingplatform.shared.UserDto;
import com.project.springboot.tradingplatform.users.data.UserEntity;

public interface UsersService {
 UserDto createUser(UserDto UserDetails);
 UserEntity RetrieveByEmail(String email);
 UserEntity Update(UserEntity ReceivedEntity);
 List<UserEntity> findallusers();
}
