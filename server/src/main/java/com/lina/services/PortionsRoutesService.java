package com.lina.services;

import java.sql.Connection;

import com.lina.models.PortionsRoutes;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class PortionsRoutesService {
	public static Response nouvellePortionRoutes(String token, int idRoutes, double debut, double arrive, int idEtats) {
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			AdminsService.checkToken(c, token);
			PortionsRoutes portionsRoutes = new PortionsRoutes(idRoutes, debut, arrive, idEtats);
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
}
