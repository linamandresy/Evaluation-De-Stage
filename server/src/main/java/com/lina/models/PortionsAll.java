package com.lina.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.lina.models.dao.DBConnect;

public class PortionsAll {
	private List<PortionsLabel> listPortions;
	private int noRn;

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

	public int findNoRn(Connection c, int idRoute) throws Exception {
		String sql = "SELECT NORN FROM ROUTES WHERE IDROUTES = ?";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setInt(1, idRoute);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
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
		this.noRn = this.findNoRn(c, idRoute);
		this.listPortions = PortionsLabel.findLabelByIdRoute(c, idRoute);
	}

	public PortionsAll(int idRoute) throws Exception {
		Connection c = null;
		try {
			c = DBConnect.getDAO().connect();
			this.noRn = this.findNoRn(c, idRoute);
			this.listPortions = PortionsLabel.findLabelByIdRoute(c, idRoute);
		} catch (Exception e) {
			throw e;
		} finally {
			if (c != null)
				c.close();
		}
	}
}
