package com.lina.controllers;

import com.lina.services.EtatsService;
import com.lina.services.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("etats")
@CrossOrigin(origins = "*")
public class EtatsController {
	@PostMapping(value="insert")
	public Response postEtats(@RequestHeader(name="Authorization") String token,String nomEtat,double budget,double delai,double coef,double unitesDistances) {
		return EtatsService.nouvelEtat(token, nomEtat, budget, delai, coef,unitesDistances);
	}
	@GetMapping(value="")
	public Response getEtats() {
		return EtatsService.listeEtat();
	}	
	@GetMapping(value="/{id}")
	public Response getEtatById(@PathVariable int id) {
		return EtatsService.findById(id);
	}
	@PutMapping(value="/{id}")
	public Response updateEtat(@RequestHeader(name="Authorization") String token,@PathVariable int id, String nomEtat, double budget, double delai, double coef, double unitesDistances) {
		return EtatsService.updateEtat(token,id, nomEtat, budget, delai, coef, unitesDistances);
	}
}
