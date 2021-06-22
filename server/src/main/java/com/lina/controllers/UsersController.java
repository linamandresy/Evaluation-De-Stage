package com.lina.controllers;

import com.lina.services.Response;
import com.lina.services.UsersService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UsersController {
	@PostMapping(value="login")
	public Response login(String logins,String passwords) {
		return UsersService.authentificate(logins, passwords);
	}
	@PostMapping(value="signup")
	public Response signup(@RequestHeader(name = "Authorization")String token,String fName, String lName, String logins, String passwords) {
		return UsersService.signup(token,fName, lName, logins, passwords);
	}
	@GetMapping(value="check")
	public Response checkToken(@RequestHeader(name = "Authorization")String token) {
		return UsersService.checkToken(token);
	}
	
}
