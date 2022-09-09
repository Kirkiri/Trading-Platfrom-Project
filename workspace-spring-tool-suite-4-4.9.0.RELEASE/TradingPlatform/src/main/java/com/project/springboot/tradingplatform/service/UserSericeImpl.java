package com.project.springboot.tradingplatform.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboot.tradingplatform.shared.UserDto;
import com.project.springboot.tradingplatform.users.data.UserEntity;
import com.project.springboot.tradingplatform.users.data.UserRepository;

@Service
public class UserSericeImpl implements UsersService {
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   
   @Autowired
   UserRepository userRepository;
   
	@Override
	public UserDto createUser(UserDto UserDetails) {
		
		LocalDateTime now = LocalDateTime.now();
		UserDetails.setCreatedAt(dtf.format(now));
		UserDetails.setUpdatedAt(null);
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity entity = modelmapper.map(UserDetails, UserEntity.class);
		
		userRepository.save(entity);
		UserDto returnvalue= modelmapper.map(entity, UserDto.class);
		return returnvalue;
	}

@Override
public UserEntity RetrieveByEmail(String email) {
	
	return userRepository.findByEmail(email);
}

@Override
public UserEntity Update(UserEntity ReceivedEntity) {
	userRepository.save(ReceivedEntity);
	return null;
}

@Override
public List<UserEntity> findallusers() {
	return userRepository.findAll();
}



}
