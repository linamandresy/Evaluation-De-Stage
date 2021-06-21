package com.lina.services;

import java.sql.Connection;

import com.lina.models.Etats;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class EtatsService {
	public static Response nouvelEtat(String token, String nomEtat, double budget, double delai, double coef,
			double unitesDistances) {
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			AdminsService.checkToken(c, token);
			Etats etats = new Etats(nomEtat, budget, delai, coef, unitesDistances);
			dao.insert(c, etats);
			c.commit();
			return new Response(200, "Etat inséré");
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

	public static Response listeEtat() {
		try {
			return new Response(200, Etats.find());
		} catch (Exception ex) {
			return new Response(500, ex.getMessage());
		}
	}

	public static Response findById(int id) {
		try {
			return new Response(200, Etats.findById(id));
		} catch (Exception ex) {
			return new Response(500, ex.getMessage());
		}
	}

	public static Response updateEtat(String token, int idEtats, String nomEtat, double budget, double delai,
			double coef, double unitesDistances) {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			AdminsService.checkToken(c, token);
			Etats etats = new Etats(idEtats, nomEtat, budget, delai, coef, unitesDistances);
			DBConnect.getDAO().update(c, etats);
			c.commit();
			return new Response(200, "Update réussi");
		} catch (Exception e) {
			try {
				if(c!=null) c.rollback();
			} catch (Exception ex) {}
			return new Response(500, e.getMessage());
		}finally{
			try {
				if(c!=null) c.close();
			} catch (Exception e) {}
		}
	}
}
