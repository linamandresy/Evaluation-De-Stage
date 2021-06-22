package com.lina.controllers;

import com.lina.services.PortionsRoutesService;
import com.lina.services.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("portions")
@CrossOrigin(origins = "*")
public class PortionsRoutesController {
	@PostMapping(value = "insert")
	public Response postPortion(@RequestHeader(name = "Authorization") String token, int idRoutes, double distance, int idEtats) {
		return PortionsRoutesService.nouvellePortionRoutes(token, idRoutes, distance, idEtats);
	}
	@GetMapping(value = "/{id}")
	public Response getPortions(@PathVariable int id) {
		return PortionsRoutesService.listePortionParRoute(id);
	}

	@GetMapping(value = "label/{id}")
	public Response getLabel(@PathVariable int id) {
		return PortionsRoutesService.listePortionLabelParRoute(id);
	}
	@GetMapping(value="find/{id}")
	public Response getForUpdate(@PathVariable int id) {
		return PortionsRoutesService.findById(id);
	}
	@PutMapping(value="update/{id}")
	public Response update(@RequestHeader(name="Authorization") String token,@PathVariable int id,int idRoutes,double distance,int idEtats) {
		return PortionsRoutesService.updatePortions(token, id, idRoutes, distance, idEtats);
	}
	
}