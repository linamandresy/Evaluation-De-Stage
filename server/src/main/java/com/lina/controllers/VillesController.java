package com.lina.controllers;

import com.lina.services.Response;
import com.lina.services.VillesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("villes")
@CrossOrigin(origins="*")
public class VillesController {
	@PostMapping(value="")
	public Response nouvelleVille(@RequestHeader(name="Authorization") String token,String nomVilles) {
		return VillesService.nouvelleVille(token, nomVilles);
	}
	@GetMapping(value="")
	public Response listeVilles() {
		return VillesService.listeVilles();
	}
}
