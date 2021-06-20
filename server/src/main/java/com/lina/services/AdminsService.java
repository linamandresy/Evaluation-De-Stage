package com.lina.services;

import java.sql.Connection;

import com.lina.models.Admins;
import com.lina.models.AdminsToken;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class AdminsService {
	public static Response checkToken(String token){
		try{
			return new Response(200,AdminsToken.findAdminsToken(token.substring(7)));
		}catch(Exception ex){
			return new Response(500,ex.getMessage());
		}
	}
	public static Response authentificate(String login, String password) {

		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			int id = Admins.getId(c, login, password);
			AdminsToken token = new AdminsToken(id);
			dao.insertAll(c, token);
			c.commit();
			return new Response(200, token.getAdminsToken());
		} catch (Exception ex) {
			try {
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

	public static Response signup(String logins, String passwords) {
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			Admins admin = new Admins(logins, passwords);
			dao.insert(c, admin);
			c.commit();
			return authentificate(logins, passwords);
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
}
