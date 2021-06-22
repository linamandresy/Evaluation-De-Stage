package com.lina.services;

import java.sql.Connection;

import com.lina.models.Routes;
import com.lina.models.RoutesLabel;
import com.lina.models.dao.DBConnect;

public class RoutesService {
	public static Response nouvelleRoutes(String token,int noRn,int idVilleDepart,int idVilleArrive,double distance){
		try {
			if(AdminsService.checkToken(token).getStatus()==500)throw new Exception("Token invalide");
			Routes routes = new Routes(noRn, idVilleDepart, idVilleArrive, distance);
			DBConnect.getDAO().insert(routes);
			return new Response(200,"Nouvelle routes inseré");
		} catch (Exception e) {
			return new Response(500,e.getMessage());
		}
	}
	public static  Response listeRoutes(){
		try {
			return new Response(200,DBConnect.getDAO().find(Routes.class));
		} catch (Exception e) {
			return new Response(500,e.getMessage());
		}
	}
	public static Response listeRoutesLabel(){
		try {
			return new Response(200,RoutesLabel.find());
		} catch (Exception e) {
			return new Response(500,e.getMessage());
		}
	}
	public static Response valider(String token,int idRoute){
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			AdminsService.checkToken(c, token);
			Routes.valider(c,idRoute);
			c.commit();
			return new Response(200,"Route validé");
		} catch (Exception e) {
			try {
				if(c!=null) c.rollback();
			} catch (Exception ex) {
			}
			return new  Response(500,e.getMessage());
		}finally{
			try {
				if(c!=null) c.close();
			} catch (Exception e) {
				//TODO: handle exception
			}
		}
	}
}
