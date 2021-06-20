package com.lina.services;

import java.sql.Connection;

import com.lina.models.AdminsToken;
import com.lina.models.Villes;
import com.lina.models.dao.DAOLina;
import com.lina.models.dao.DBConnect;

public class VillesService {
	public static Response nouvelleVille(String token,String nomVille){
		Connection c = null;
		try{
			DAOLina dao = DBConnect.getDAO();
			Response res = AdminsService.checkToken(token);
			if(res.getStatus()==500)throw new Exception("Token invalide");
			c = dao.connect();
			DBConnect.getDAO().insert(c,new Villes(nomVille));
			c.commit();
			return new Response(200,"Ville Inseré");
		}catch(Exception ex){
			try {
				if(c!=null) c.rollback();
			} catch (Exception e) {}
			return new Response(500,ex.getMessage());
		}finally{
			try{
				if(c!=null) c.close();
			}catch(Exception ex){}
		}
	}
	public static Response listeVilles(){
		try{
			Object result = DBConnect.getDAO().find(Villes.class);
			return new Response(200,result);
		}catch(Exception ex){
			return new Response(500,ex.getMessage());
		}
	}
}
