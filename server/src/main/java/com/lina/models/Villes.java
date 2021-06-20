package com.lina.models;

public class Villes {
	private int idVilles;
	private String nomVilles;

	public void setIdVilles(int idVilles) {
		this.idVilles = idVilles;
	}

	public void setNomVilles(String nomVilles)throws Exception {
		if(nomVilles==null) throw new Exception("Champs Obligatoire");
		this.nomVilles = nomVilles;
	}

	public int getIdVilles() {
		return idVilles;
	}

	public String getNomVilles() {
		return nomVilles;
	}
	public Villes(){}
	public Villes(String nomVilles)throws Exception{
		this.setNomVilles(nomVilles);
	}
	public Villes(int idVilles,String nomVilles)throws Exception{
		this.setIdVilles(idVilles);
		this.setNomVilles(nomVilles);
	}
}
