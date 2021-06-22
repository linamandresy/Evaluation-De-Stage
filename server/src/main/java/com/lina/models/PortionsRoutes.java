package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.lina.models.dao.DBConnect;

public class PortionsRoutes {
	private int idPortionsRoutes;
	private int idRoutes;
	private double distance;
	private int idEtats;

	public PortionsRoutes(){}
	public PortionsRoutes(int idRoutes,double distance,int idEtats) throws Exception{
		this.setIdRoutes(idRoutes);
		this.setDistance(distance);
		this.setIdEtats(idEtats);
	}
	public PortionsRoutes(int idPortionsRoutes,int idRoutes,double distance,int idEtats) throws Exception{
		this.setIdPortionsRoutes(idPortionsRoutes);
		this.setIdRoutes(idRoutes);
		this.setDistance(distance);
		this.setIdEtats(idEtats);
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

	public void setIdEtats(int idEtats) {
		this.idEtats = idEtats;
	}

	public void setIdPortionsRoutes(int idPortionsRoutes) {
		this.idPortionsRoutes = idPortionsRoutes;
	}

	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}public double getDistance() {
		return distance;
	}
	public static LinkedList<PortionsRoutes> findByIdRoute(Connection c,int idRoutes)throws Exception{
		String sql="SELECT IDPORTIONSROUTES,IDROUTES,DISTANCE,IDETATS FORM PORTIONSROUTES WHERE IDROUTES = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		ResultSet rs = null;
		pst.setInt(1, idRoutes);
		try{
			rs = pst.executeQuery();
			LinkedList<PortionsRoutes> result = new LinkedList<PortionsRoutes>();
			while(rs.next())
			result.add(new PortionsRoutes(rs.getInt(1), idRoutes, rs.getDouble(3), rs.getInt(4)));
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
	
	public static PortionsRoutes findById(Connection c, int id) throws Exception {
		String sql = "SELECT IDPORTIONSROUTES,IDROUTES,DISTANCE,IDETATS FROM PORTIONSROUTES WHERE IDPORTIONSROUTES  = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			if (rs.next()) {
				return new PortionsRoutes(id, rs.getInt(2), rs.getDouble(3), rs.getInt(4));
			}
			throw new Exception("Portion inexistante");
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public static PortionsRoutes findById(int id) throws Exception {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return findById(c, id);
		} catch (Exception e) {
			throw e;
		} finally {
			if(c!=null) c.close();
		}
	}
}
