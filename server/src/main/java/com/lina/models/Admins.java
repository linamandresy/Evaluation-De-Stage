package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lina.helpers.Encrypt;
import com.lina.models.dao.DBConnect;

public class Admins {
	int idAdmins;
	String logins;
	String passwords;

	public Admins() {}

	public Admins(String login, String password) throws Exception {
		this.setLogins(login);
		this.passwords = Encrypt.Sha1(password);
	}

	public Admins(int id, String login, String password) throws Exception {
		this.setIdAdmins(id);
		this.setLogins(logins);
		this.setPasswords(passwords);
	}

	public int getIdAdmins() {
		return idAdmins;
	}

	public String getLogins() {
		return logins;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setIdAdmins(int idAdmins) {
		this.idAdmins = idAdmins;
	}

	public void setLogins(String logins) throws Exception {
		if (logins == null)
			throw new Exception("Champ obligatoire");
		this.logins = logins;
	}

	public void setPasswords(String passwords) throws Exception {
		if (passwords == null)
			throw new Exception("Champ obligatoire");
		this.passwords = passwords;
	}

	public static int getId(Connection c, String login, String password) throws Exception {
		String sql = "SELECT IDADMINS FROM ADMINS WHERE LOGINS = ? AND PASSWORDS = ?";
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
