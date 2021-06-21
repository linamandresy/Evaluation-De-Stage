package com.lina.controllers;

import com.lina.services.PortionsRoutesService;
import com.lina.services.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("portions")
@CrossOrigin(origins = "*")
public class PortionsRoutesController {
	@PostMapping(value="insert")
	public Response postPortion(@RequestHeader(name="Authorization") String token, int idRoutes, double debut, double arrive, int idEtats) {
		return PortionsRoutesService.nouvellePortionRoutes(token, idRoutes, debut, arrive, idEtats);
	}
	@GetMapping(value="/{id}")
	public Response putMethodName(@PathVariable int id) {
		return PortionsRoutesService.listePortionParRoute(id);
	}
	
}