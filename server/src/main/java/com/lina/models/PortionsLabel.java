package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.lina.models.dao.DBConnect;

public class PortionsLabel extends PortionsRoutes {
	private String etatRoute;
	private double pu;
	private double delaiU;
	private double u=1;
	private double coef;
	public double getCoef() {
		return coef;
	}public void setCoef(double coef) {
		this.coef = coef;
	}
	public PortionsLabel() {
	}

	public PortionsLabel(int idPortionsRoutes, int idRoutes, double distance, int idEtats, String etatRoute,
			double pu, double delaiU, double u,double coef) throws Exception {
		super(idPortionsRoutes, idRoutes, distance, idEtats);
		this.setEtatRoute(etatRoute);
		this.setDelaiU(delaiU);
		this.setPu(pu);
		this.setU(u);
		this.setCoef(coef);
	}

	public void setEtatRoute(String etatRoute) {
		this.etatRoute = etatRoute;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public void setDelaiU(double delaiU) {
		this.delaiU = delaiU;
	}

	public void setU(double u) {
		if(u<=0) this.u=1;
		else this.u = u;
	}

	public String getEtatRoute() {
		return etatRoute;
	}

	public double getCout(){
		return this.pu*this.getDistance()/this.u;
	}
	public double getDelai(){
		return this.delaiU*this.getDistance()/this.u;
	}
	public static LinkedList<PortionsLabel> findLabelByIdRoute(Connection c,int idRoute)throws Exception{
		String sql="SELECT IDPORTIONSROUTES,IDROUTES,DISTANCE,ETATS.IDETATS,NOMETAT,BUDGET,DELAI,COEF,UNITESDISTANCES FROM PORTIONSROUTES JOIN ETATS ON PORTIONSROUTES.IDETATS=ETATS.IDETATS WHERE IDROUTES=?";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setInt(1, idRoute);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			LinkedList<PortionsLabel> result = new LinkedList<PortionsLabel>();
			while(rs.next())
				result.add(new PortionsLabel(rs.getInt(1), idRoute,rs.getDouble(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)));
			return result;
		} catch (Exception e) {
			throw e;
		}finally{
			if(rs!=null) rs.close();
		}
	}
	public static LinkedList<PortionsLabel> findLabelByIdRoute(int idRoute)throws Exception{
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return findLabelByIdRoute(c, idRoute);
		} catch (Exception e) {
			throw e;
		}finally{
			if(c!=null) c.close();
		}
	}
}