package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.lina.models.dao.DBConnect;

public class RoutesLabel extends Routes {
	private String villeDebut;
	private String villeFin;

	public String getVilleDebut() {
		return villeDebut;
	}

	public String getVilleFin() {
		return villeFin;
	}

	public void setVilleDebut(String villeDebut) {
		this.villeDebut = villeDebut;
	}

	public void setVilleFin(String villeFin) {
		this.villeFin = villeFin;
	}

	public RoutesLabel() {
	}

	public RoutesLabel(int idRoutes, int noRn, int idVilleDepart, int idVilleArrive, double distance, String debut,
			String fin) throws Exception {
		super(idRoutes, noRn, idVilleDepart, idVilleArrive, distance);
		this.setVilleDebut(debut);
		this.setVilleFin(fin);
	}

	public static LinkedList<RoutesLabel> find(Connection c)throws Exception{
		String sql="SELECT  ROUTES.IDROUTES ,ROUTES.NORN ,ROUTES.IDVILLEDEPART ,ROUTES.IDVILLEARRIVE ,ROUTES.DISTANCE ,D.NOMVILLES DEPART ,F.NOMVILLES ARRIVE FROM ROUTES JOIN VILLES D ON ROUTES.IDVILLEDEPART=D.IDVILLES JOIN VILLES F ON ROUTES.IDVILLEARRIVE=F.IDVILLES";
		PreparedStatement pst = c.prepareStatement(sql);
		ResultSet rs = null;
		try{
			rs = pst.executeQuery();
			LinkedList<RoutesLabel> result = new LinkedList<RoutesLabel>();
			while(rs.next()) 
				result.add(new RoutesLabel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getString(6), rs.getString(7)));
			return result;
		}catch(Exception ex){
			throw ex;
		}finally{
			if(rs!=null) rs.close();
		}
	}
	public static LinkedList<RoutesLabel> find()throws Exception{
		Connection c = null;
		try{
			c=DBConnect.getDAO().connect();
			return find(c);
		}catch(Exception ex){
			throw ex;
		}finally{
			if(c!=null) c.close();
		}
	}
}
