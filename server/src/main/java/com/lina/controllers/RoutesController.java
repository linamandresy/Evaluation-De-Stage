package com.lina.controllers;

import com.lina.services.Response;
import com.lina.services.RoutesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("routes")
@CrossOrigin(origins="*")
public class RoutesController {
	@PostMapping(value="")
	public Response postRoutes(@RequestHeader(name="Authorization")String token,int noRn,int idVilleDepart,int idVilleArrive,double distance) {
		return RoutesService.nouvelleRoutes(token, noRn, idVilleDepart, idVilleArrive, distance);
	}
	
}
