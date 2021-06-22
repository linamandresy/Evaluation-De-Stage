package com.lina.services;

import java.sql.Connection;

import com.lina.models.Users;
import com.lina.models.UsersToken;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class UsersService {
	public static Response checkToken(String token){
		try{
			return new Response(200,UsersToken.findUsersToken(token.substring(7)));
		}catch(Exception ex){
			return new Response(500,ex.getMessage());
		}
	}
	public static Response authentificate(String login, String password) {

		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			int id = Users.getId(c, login, password);
			UsersToken token = new UsersToken(id);
			dao.insertAll(c, token);
			c.commit();
			return new Response(200, token.getUsersToken());
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

	public static Response signup(String token,String fName, String lName, String logins, String passwords) {
		Connection c = null;
		try {
			DAOLina dao = DBConnect.getDAO();
			c = dao.connect();
			AdminsService.checkToken(c, token);
			Users user = new Users(fName, lName, logins, passwords);
			dao.insert(c, user);
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
