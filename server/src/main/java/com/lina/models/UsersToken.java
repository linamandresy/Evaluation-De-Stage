package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.lina.helpers.Encrypt;
import com.lina.models.dao.DBConnect;

public class UsersToken {
	private String usersToken;
	private int idUsers;
	private Timestamp expiration;
	public Timestamp getExpiration() {
		return expiration;
	}public int getIdUsers() {
		return idUsers;
	}public String getUsersToken() {
		return usersToken;
	}
	public void setExpiration(Timestamp expiration) {
		this.expiration = expiration;
	}
	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}public void setUsersToken(String usersToken) {
		this.usersToken = usersToken;
	}
	public static String encode(int id){
		return Encrypt.Sha1(String.valueOf(id).concat(String.valueOf(System.currentTimeMillis())));
	}
	public UsersToken(){}
	public UsersToken(int idUsers){
		this.idUsers = idUsers;
		String code = encode(idUsers);
		this.usersToken = Encrypt.Sha1(code);
		this.expiration = new Timestamp(System.currentTimeMillis()+3600000);
	}
	public UsersToken(String usersToken,int idUsers,Timestamp expiration){
		this.setUsersToken(usersToken);
		this.setIdUsers(idUsers);
		this.setExpiration(expiration);
	}
	public static UsersToken findUsersToken(Connection c,String token)throws Exception{
		String sql =" SELECT * FROM USERSTOKEN WHERE USERSTOKEN = ? AND EXPIRATION>=?";
		PreparedStatement pst = c.prepareStatement(sql);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		pst.setString(1, token);
		pst.setTimestamp(2, time);
		ResultSet rs = null;
		try  {
			rs = pst.executeQuery();
			if(rs.next()){
				return new UsersToken(rs.getString(1), rs.getInt(2), rs.getTimestamp(3));
			}
			throw new Exception("Token invalide");
		}catch(Exception ex){
			throw ex;
		}finally{
			if(rs!=null) rs.close();
		}
	}
	public static UsersToken findUsersToken(String token)throws Exception{
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return findUsersToken(c, token);
		} catch (Exception e) {
			throw e;
		}finally{
			if(c!=null) c.close();
		}

	}
}
