package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lina.models.dao.DBConnect;

public class Routes {
	int idRoutes;
	int noRn;
	int idVilleDepart;
	int idVilleArrive;
	double distance;
	boolean valid;

	public Routes() {
	}

	public Routes(int idRoutes, int noRn, int idVilleDepart, int idVilleArrive, double distance, boolean valid)
			throws Exception {
		this.setIdRoutes(idRoutes);
		this.setDistance(distance);
		this.setIdVilleArrive(idVilleArrive);
		this.setIdVilleDepart(idVilleDepart);
		this.setNoRn(noRn);
		this.setValid(valid);
	}

	public Routes(int noRn, int idVilleDepart, int idVilleArrive, double distance) throws Exception {
		this.setDistance(distance);
		this.setIdVilleArrive(idVilleArrive);
		this.setIdVilleDepart(idVilleDepart);
		this.setNoRn(noRn);
		this.setValid(false);
	}

	public boolean getValid() {
		return this.valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void setDistance(double distance) throws Exception {
		if (distance < 1)
			throw new Exception("Champs invalide");
		this.distance = distance;
	}

	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}

	public void setIdVilleArrive(int idVilleArrive) throws Exception {
		if (idVilleArrive == 0)
			throw new Exception("Veuillez choisir une valeur");
		this.idVilleArrive = idVilleArrive;
	}

	public void setIdVilleDepart(int idVilleDepart) throws Exception {
		if (idVilleDepart == 0)
			throw new Exception("Veuillez choisir une valeur");
		if (idVilleDepart == idVilleArrive)
			throw new Exception("Ville de départ et ville d'arrivé confondu");
		this.idVilleDepart = idVilleDepart;
	}

	public void setNoRn(int noRn) throws Exception {
		if (noRn < 1)
			throw new Exception("Champs invalide");
		this.noRn = noRn;
	}

	public double getDistance() {
		return distance;
	}

	public int getIdRoutes() {
		return idRoutes;
	}

	public int getIdVilleArrive() {
		return idVilleArrive;
	}

	public int getIdVilleDepart() {
		return idVilleDepart;
	}

	public int getNoRn() {
		return noRn;
	}

	public static void valider(Connection c, int idRoute) throws Exception {
		String sql = "SELECT ROUTES.IDROUTES,ROUTES.DISTANCE, D2 FROM ROUTES JOIN (SELECT IDROUTES,SUM(PORTIONSROUTES.DISTANCE) D2 FROM PORTIONSROUTES WHERE IDROUTES = ? GROUP BY IDROUTES)D ON ROUTES.IDROUTES=D.IDROUTES WHERE ROUTES.DISTANCE =D.D2 AND ROUTES.IDROUTES = ?";
		PreparedStatement pst1 = c.prepareStatement(sql);
		pst1.setInt(1, idRoute);
		pst1.setInt(2, idRoute);
		if (!pst1.executeQuery().next())
			throw new Exception("Portions incohérente : Validation impossible");
		sql = "UPDATE ROUTES SET VALID='T' WHERE IDROUTES = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setInt(1, idRoute);
		pst.executeUpdate();
	}

	public static void valider(int idRoute) throws Exception {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			valider(c, idRoute);
		} catch (Exception e) {
			throw e;
		} finally {
			if (c != null)
				c.close();
		}
	}

}