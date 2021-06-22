package com.lina.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lina.helpers.RoutesExcelExporter;
import com.lina.models.RoutesLabel;
import com.lina.services.Response;
import com.lina.services.RoutesService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("routes")
@CrossOrigin(origins = "*")
public class RoutesController {
	@PostMapping(value = "")
	public Response postRoutes(@RequestHeader(name = "Authorization") String token, int noRn, int idVilleDepart,
			int idVilleArrive, double distance) {
		return RoutesService.nouvelleRoutes(token, noRn, idVilleDepart, idVilleArrive, distance);
	}

	@GetMapping(value = "")
	public Response getRoutes() {
		return RoutesService.listeRoutes();
	}

	@GetMapping(value = "label")
	public Response getLabeledRoutes() {
		return RoutesService.listeRoutesLabel();
	}

	@GetMapping("excel")
    public void exportToExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date(System.currentTimeMillis()));
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=routes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<RoutesLabel> listRoutes = RoutesLabel.find();
         
        RoutesExcelExporter excelExporter = new RoutesExcelExporter(listRoutes);
         
        excelExporter.export(response);    
    }  

	@PutMapping(value="valider/{id}")
	public Response putMethodName(@RequestHeader(name="Authorization") String token,@PathVariable int id) {		
		return RoutesService.valider(token, id);
	}
}
