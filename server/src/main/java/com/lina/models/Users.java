package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lina.helpers.Encrypt;
import com.lina.models.dao.DBConnect;

public class Users {
	int idUsers;
	String fName;
	String lName;
	String logins;
	String passwords;

	public Users(){}
	public Users(int idUsers, String fName, String lName, String logins, String passwords) throws Exception{
		this.setIdUsers(idUsers);
		this.setfName(fName);
		this.setlName(lName);
		this.setLogins(logins);
		this.setPasswords(passwords);
	}

	public Users(String fName, String lName, String logins, String passwords) throws Exception{
		this.setIdUsers(idUsers);
		this.setfName(fName);
		this.setlName(lName);
		this.setLogins(logins);
		this.passwords = Encrypt.Sha1(passwords);
	}

	public int getIdUsers() {
		return idUsers;
	}

	public String getLogins() {
		return logins;
	}

	public String getPasswords() {
		return passwords;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public void setLogins(String logins) throws Exception{
		if(logins==null) throw new Exception("Champs obligatoire");
		this.logins = logins;
	}

	public void setPasswords(String passwords) throws Exception{
		if(passwords==null) throw new Exception("Champs obligatoire");
		this.passwords = passwords;
	}

	public void setfName(String fName) throws Exception{
		if(fName==null) throw new Exception("Champs obligatoire");
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public static int getId(Connection c, String login, String password) throws Exception {
		String sql = "SELECT IDUSERS FROM USERS WHERE LOGINS = ? AND PASSWORDS = ?";
		password = Encrypt.Sha1(password);
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setString(1, login);
		pst.setString(2, password);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			throw new Exception("Login ou mot de passe incorrecte");
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static int getId(String login, String password) throws Exception {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return getId(c, login, password);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (c != null)
				c.close();
		}
	}
}
