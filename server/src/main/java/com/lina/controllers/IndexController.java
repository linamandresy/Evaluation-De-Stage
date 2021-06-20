package com.lina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class IndexController {
	@GetMapping(value="")
	public String index(){
		return "index.html";
	}
}
