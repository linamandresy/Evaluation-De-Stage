package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.lina.helpers.Encrypt;
import com.lina.models.dao.DBConnect;

public class AdminsToken {
	
	private String AdminsToken;
	private int idAdmins;
	private Timestamp expiration;
	public Timestamp getExpiration() {
		return expiration;
	}public int getIdAdmins() {
		return idAdmins;
	}public String getAdminsToken() {
		return AdminsToken;
	}
	public void setExpiration(Timestamp expiration) {
		this.expiration = expiration;
	}
	public void setIdAdmins(int idAdmins) {
		this.idAdmins = idAdmins;
	}public void setAdminsToken(String AdminsToken) {
		this.AdminsToken = AdminsToken;
	}
	public static String encode(int id){
		return Encrypt.Sha1(String.valueOf(id).concat(String.valueOf(System.currentTimeMillis())));
	}
	public AdminsToken(){}
	public AdminsToken(int idAdmins){
		this.idAdmins = idAdmins;
		String code = encode(idAdmins);
		this.AdminsToken = Encrypt.Sha1(code);
		this.expiration = new Timestamp(System.currentTimeMillis()+3600000);
	}
	public AdminsToken(String AdminsToken,int idAdmins,Timestamp expiration){
		this.setAdminsToken(AdminsToken);
		this.setIdAdmins(idAdmins);
		this.setExpiration(expiration);
	}
	public static AdminsToken findAdminsToken(Connection c,String token)throws Exception{
		String sql =" SELECT * FROM ADMINSTOKEN WHERE ADMINSTOKEN = ? AND EXPIRATION>=?";
		PreparedStatement pst = c.prepareStatement(sql);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		pst.setString(1, token);
		pst.setTimestamp(2, time);
		ResultSet rs = null;
		try  {
			rs = pst.executeQuery();
			if(rs.next()){
				return new AdminsToken(rs.getString(1), rs.getInt(2), rs.getTimestamp(3));
			}
			throw new Exception("Token invalide");
		}catch(Exception ex){
			throw ex;
		}finally{
			if(rs!=null) rs.close();
		}
	}
	public static AdminsToken findAdminsToken(String token)throws Exception{
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return findAdminsToken(c, token);
		} catch (Exception e) {
			throw e;
		}finally{
			if(c!=null) c.close();
		}

	}
}
