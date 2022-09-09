package com.project.springboot.tradingplatform.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.tradingplatform.model.CreateUserRequestModel;
import com.project.springboot.tradingplatform.service.UsersService;
import com.project.springboot.tradingplatform.shared.UserDto;
import com.project.springboot.tradingplatform.users.data.UserEntity;

@RestController
@RequestMapping("/trading/traders")
public class Usercontroller {

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Autowired
	UsersService usersService;

	@GetMapping
	public ResponseEntity<UserEntity> RetrieveByEmail(@RequestParam(value = "email") String email) {
		UserEntity ReceivedEntity = new UserEntity();
		ReceivedEntity = usersService.RetrieveByEmail(email);
		if (ReceivedEntity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else
			return ResponseEntity.ok(ReceivedEntity);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> RetrieveByEmail() {
		List<UserEntity> ListOfUsers = usersService.findallusers();
		return ResponseEntity.ok(ListOfUsers);
	}

	@PostMapping("/register")
	public ResponseEntity<CreateUserRequestModel> createTrader(@Valid @RequestBody CreateUserRequestModel userDetails) {
		LocalDateTime now = LocalDateTime.now();
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelmapper.map(userDetails, UserDto.class);
		userDto.setCreatedAt(dtf.format(now));

		UserEntity ReceivedEntity = new UserEntity();
		ReceivedEntity = usersService.RetrieveByEmail(userDto.getEmail());

		if (ReceivedEntity != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		else {
			UserDto CreatedUser = usersService.createUser(userDto);
			CreateUserRequestModel returnValue = modelmapper.map(CreatedUser, CreateUserRequestModel.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

		}
	}

	@PutMapping
	public ResponseEntity<UserEntity> updateName(@RequestBody CreateUserRequestModel userDetails) {
		LocalDateTime now1 = LocalDateTime.now();
		UserEntity ReceivedEntity = new UserEntity();
		ReceivedEntity = usersService.RetrieveByEmail(userDetails.getEmail());
		if (ReceivedEntity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else
			ReceivedEntity.setName(userDetails.getName());
		ReceivedEntity.setUpdatedAt(dtf.format(now1));
		usersService.Update(ReceivedEntity);
		return ResponseEntity.ok(ReceivedEntity);
	}

	@PutMapping("/add")
	public ResponseEntity<UserEntity> AddMoney(@RequestBody CreateUserRequestModel userDetails) {
		LocalDateTime now2 = LocalDateTime.now();
		UserEntity ReceivedEntity = new UserEntity();
		ReceivedEntity = usersService.RetrieveByEmail(userDetails.getEmail());
		if (ReceivedEntity == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		else if (userDetails.getBalance() != 0)
			ReceivedEntity.setBalance(userDetails.getBalance());
		ReceivedEntity.setUpdatedAt(dtf.format(now2));
		usersService.Update(ReceivedEntity);
		return ResponseEntity.ok(ReceivedEntity);
	}

}