package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.lina.models.dao.DBConnect;

public class Etats {
	private int idEtats;
	private String nomEtat;
	private double budget;
	private double delai;
	private double coef;
	private double unitesDistances;

	public double getUnitesDistances() {
		return unitesDistances;
	}

	public void setUnitesDistances(double unitesDistances) throws Exception {
		if (unitesDistances < 0)
			throw new Exception("Unité de distance négative");
		this.unitesDistances = unitesDistances;
	}

	public double getBudget() {
		return budget;
	}

	public double getCoef() {
		return coef;
	}

	public double getDelai() {
		return delai;
	}

	public int getIdEtats() {
		return idEtats;
	}

	public String getNomEtat() {
		return nomEtat;
	}

	public void setBudget(double budget) throws Exception {
		if (budget < 0)
			throw new Exception("Budget négatif : valeur impossible");
		this.budget = budget;
	}

	public void setCoef(double coef) throws Exception {
		if (coef < 0 || coef > 100)
			throw new Exception("Coefficient est un pourcentage de valeur entre 0 et 100");
		this.coef = coef;
	}

	public void setDelai(double delai) throws Exception {
		if (delai < 0)
			throw new Exception("Delai négatif impossible");
		this.delai = delai;
	}

	public void setIdEtats(int idEtats) {
		this.idEtats = idEtats;
	}

	public void setNomEtat(String nomEtat) {
		this.nomEtat = nomEtat;
	}

	public Etats() {
	}

	public Etats(String nomEtat, double budget, double delai, double coef, double unitesDistances) throws Exception {
		this.setNomEtat(nomEtat);
		this.setBudget(budget);
		this.setDelai(delai);
		this.setCoef(coef);
		this.setUnitesDistances(unitesDistances);
	}

	public Etats(int idEtats, String nomEtat, double budget, double delai, double coef, double unitesDistances)
			throws Exception {
		this.setIdEtats(idEtats);
		this.setNomEtat(nomEtat);
		this.setBudget(budget);
		this.setDelai(delai);
		this.setCoef(coef);
		this.setUnitesDistances(unitesDistances);
	}

	public static LinkedList<Etats> find(Connection c) throws Exception {
		String sql = "SELECT idEtats, nomEtat, budget, delai, coef, unitesDistances FROM ETATS";
		PreparedStatement pst = c.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			LinkedList<Etats> result = new LinkedList<Etats>();
			while (rs.next())
				result.add(new Etats(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5),
						rs.getDouble(6)));
			return result;
		} catch (Exception e) {
			throw e;
		}
	}
	public static LinkedList<Etats> find()throws Exception{
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			return find(c);
		} catch (Exception e) {
			throw e;
		}finally{
			if(c!=null) c.close();
		}
	}
}
