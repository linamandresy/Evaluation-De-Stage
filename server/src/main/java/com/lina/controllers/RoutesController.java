package com.lina.controllers;

import com.lina.services.Response;
import com.lina.services.RoutesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("routes")
@CrossOrigin(origins="*")
public class RoutesController {
	@PostMapping(value="")
	public Response postRoutes(@RequestHeader(name="Authorization")String token,int noRn,int idVilleDepart,int idVilleArrive,double distance) {
		return RoutesService.nouvelleRoutes(token, noRn, idVilleDepart, idVilleArrive, distance);
	}
	@GetMapping(value="")
	public Response getRoutes() {
		return RoutesService.listeRoutes();
	}
	@GetMapping(value="label")
	public Response getLabeledRoutes() {
		return RoutesService.listeRoutesLabel();
	}
	
		
}
