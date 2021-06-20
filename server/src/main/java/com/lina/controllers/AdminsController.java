package com.lina.controllers;

import com.lina.services.AdminsService;
import com.lina.services.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admins")
@CrossOrigin(origins = "*")
public class AdminsController {
	@PostMapping(value="login")
	public Response login(String logins,String passwords) {
		return AdminsService.authentificate(logins, passwords);
	}
	@PostMapping(value="signup")
	public Response signup(String logins, String passwords) {
		return AdminsService.signup(logins, passwords);
	}
	@GetMapping(value="check")
	public Response checkToken(@RequestHeader(name = "Authorization")String token) {
		return AdminsService.checkToken(token);
	}

}
