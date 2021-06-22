package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.lina.models.dao.DBConnect;

public class PortionsAll {
	private List<PortionsLabel> listPortions;
	private int noRn;
	private double distance;
	private boolean valid;
	private String villeDebut;
	private String villeFin;
	public List<String> getColor(){
		List<String> result = new LinkedList<String>();
		for(int i = 0 ; i<listPortions.size();i++){
			int a = listPortions.get(i).getIdEtats();
			switch (a) {
				case 1:
					result.add("bg-success");
					break;
				case 2:
					result.add("bg-info");
					break;
				case 3:
					result.add("bg-warning");
					break;
				case 4:
					result.add("bg-danger");
					break;
				default:
					break;
			}
		}
		return result;
	}
	public List<Double> getPart(){
		List<Double> result = new LinkedList<Double>();
		for(int i = 0 ; i<listPortions.size();i++){
			result.add(100*listPortions.get(i).getDistance()/this.getSumDistance());
		}
		return result;
	}
	public String getVilleDebut() {
		return villeDebut;
	}
	public void setVilleDebut(String villeDebut) {
		this.villeDebut = villeDebut;
	}
	public String getVilleFin() {
		return villeFin;
	}
	public void setVilleFin(String villeFin) {
		this.villeFin = villeFin;
	}
	public double getDistance() {
		return distance;
	}
	public boolean getValid(){
		return valid;	
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public List<PortionsLabel> getListPortions() {
		return listPortions;
	}

	public int getNoRn() {
		return noRn;
	}

	public void setNoRn(int noRn) {
		this.noRn = noRn;
	}

	public void setListPortions(LinkedList<PortionsLabel> listPortions) {
		this.listPortions = listPortions;
	}

	public double getMontantTotal() {
		int result = 0;
		for (int i = 0; i < this.listPortions.size(); i++)
			result += this.listPortions.get(i).getCout();
		return result;
	}

	public double getDelaiTotal() {
		double result = 0;
		for (int i = 0; i < this.listPortions.size(); i++)
			result += this.listPortions.get(i).getDelai();
		return result;
	}

	public void findParams(Connection c, int idRoute) throws Exception {
		String sql = "SELECT NORN,DISTANCE,VALID,DEPART.NOMVILLES DEPART,ARRIVE.NOMVILLES ARRIVE FROM ROUTES JOIN VILLES AS DEPART ON ROUTES.IDVILLEDEPART=DEPART.IDVILLES JOIN VILLES AS ARRIVE ON ROUTES.IDVILLEARRIVE=ARRIVE.IDVILLES WHERE IDROUTES = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setInt(1, idRoute);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			if (rs.next()) {
				this.noRn=rs.getInt(1);
				this.distance=rs.getDouble(2);
				this.valid=rs.getBoolean(3);
				this.villeDebut=rs.getString(4);
				this.villeFin=rs.getString(5);
				return;
			}
			throw new Exception("Route introuvable");
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null)
				rs.close();
		}
	}

	public PortionsAll(Connection c, int idRoute) throws Exception {
		this.findParams(c, idRoute);
		this.listPortions = PortionsLabel.findLabelByIdRoute(c, idRoute);
	}

	public PortionsAll(int idRoute) throws Exception {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			this.findParams(c, idRoute);
			this.listPortions = PortionsLabel.findLabelByIdRoute(c, idRoute);
		} catch (Exception e) {
			throw e;
		} finally {
			if (c != null)
				c.close();
		}
	}
	public double getSumDistance(){
		double result = 0;
		for(int i=0;i<listPortions.size();i++)
			result+=listPortions.get(i).getDistance();
		return result;
	}
}
