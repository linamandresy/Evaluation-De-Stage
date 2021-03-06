package com.lina.services;

import java.sql.Connection;

import com.lina.models.PortionsAll;
import com.lina.models.PortionsLabel;
import com.lina.models.PortionsRoutes;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class PortionsRoutesService {
	public static Response nouvellePortionRoutes(String token, int idRoutes, double distance, int idEtats) {
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			AdminsService.checkToken(c, token);
			PortionsRoutes portionsRoutes = new PortionsRoutes(idRoutes, distance, idEtats);
			dao.insert(c, portionsRoutes);
			c.commit();
			return new Response(200, "Portion de routes inseré");
		} catch (Exception ex) {
			try {
				if (c != null)
					c.rollback();
			} catch (Exception e) {
			}
			return new Response(500, ex.getMessage());
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
			}
		}
	}
	public static Response listePortionParRoute(int idRoute){
		try {
			return new Response(200,PortionsRoutes.findByIdRoute(idRoute));
		} catch (Exception e) {
			return new Response(500,e.getMessage());
		}
	}
	public static Response listePortionLabelParRoute(int idRoute){
		try {
			return new Response(200,new PortionsAll(idRoute));
		} catch (Exception e) {
			return new Response(500,e.getMessage());
		}
	}
	public static Response updatePortions(String token,int idPortionsRoutes,int idRoutes,double distance,int idEtats){
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			AdminsService.checkToken(c, token);
			PortionsRoutes pr = new PortionsRoutes(idPortionsRoutes, idRoutes, distance, idEtats);
			dao.update(c, pr);
			c.commit();
			return new Response(200,"Portions mis à jour");
		} catch (Exception e) {
			try {
				if(c!=null) c.rollback();
			} catch (Exception ex) {
			}
			return new Response(500,e.getMessage());
		}finally{
			try {
				if(c!=null) c.close();
			} catch (Exception e) {}
		}
	}
	public static Response findById(int id){
		try{
			return new Response(200,PortionsRoutes.findById(id));
		}catch(Exception ex){
			return new Response(500,ex.getMessage());
		}
	}
}
