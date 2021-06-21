package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.lina.models.dao.DBConnect;

public class PortionsRoutes {
	private int idPortionsRoutes;
	private int idRoutes;
	private double debut;
	private double arrive;
	private int idEtats;

	public PortionsRoutes(){}
	public PortionsRoutes(int idRoutes,double debut,double arrive,int idEtats) throws Exception{
		this.setIdRoutes(idRoutes);
		this.setDebut(debut);
		this.setArrive(arrive);
		this.setIdEtats(idEtats);
	}
	public PortionsRoutes(int idPortionsRoutes,int idRoutes,double debut,double arrive,int idEtats) throws Exception{
		this.setIdPortionsRoutes(idPortionsRoutes);
		this.setIdRoutes(idRoutes);
		this.setDebut(debut);
		this.setArrive(arrive);
		this.setIdEtats(idEtats);
	}	
	public double getArrive() {
		return arrive;
	}

	public double getDebut() {
		return debut;
	}

	public int getIdEtats() {
		return idEtats;
	}

	public int getIdPortionsRoutes() {
		return idPortionsRoutes;
	}

	public int getIdRoutes() {
		return idRoutes;
	}

	public void setArrive(double arrive) throws Exception{
		if(arrive<debut) throw new Exception("Le kilométrage de fin doit être supérieur au kilometrage de début");
		this.arrive = arrive;
	}

	public void setDebut(double debut) throws Exception{
		if(debut<0)throw new Exception("Le kilometrage du début doit être positif");
		this.debut = debut;
	}

	public void setIdEtats(int idEtats) {
		this.idEtats = idEtats;
	}

	public void setIdPortionsRoutes(int idPortionsRoutes) {
		this.idPortionsRoutes = idPortionsRoutes;
	}

	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}
	public static LinkedList<PortionsRoutes> findByIdRoute(Connection c,int idRoutes)throws Exception{
		String sql="SELECT IDPORTIONSROUTES,IDROUTES,DEBUT,ARRIVE,IDETATS FORM PORTIONSROUTES WHERE IDROUTES = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		ResultSet rs = null;
		pst.setInt(1, idRoutes);
		try{
			rs = pst.executeQuery();
			LinkedList<PortionsRoutes> result = new LinkedList<PortionsRoutes>();
			while(rs.next())
			result.add(new PortionsRoutes(rs.getInt(1), idRoutes, rs.getDouble(3), rs.getDouble(4), rs.getInt(5)));
			return result;
		}catch(Exception ex){
			throw ex;
		}
	}
	public static LinkedList<PortionsRoutes> findByIdRoute(int idRoutes)throws Exception{
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return findByIdRoute(c, idRoutes);
		} catch (Exception e) {
			throw e;
		}finally{
			if(c!=null) c.close();
		}
	}
}
