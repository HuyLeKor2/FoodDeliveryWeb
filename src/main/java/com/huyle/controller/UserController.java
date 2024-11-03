package com.huyle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huyle.model.User;
import com.huyle.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")//? coi lại cách hoạt động của phương thức này ( cách jwt flow)
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws Exception {

		User user = userService.findUserbyJwtToken(jwt);
//		user.setPassword(null);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
