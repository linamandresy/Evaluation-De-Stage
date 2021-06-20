package com.lina.models;

public class Routes {
	int idRoutes;
	int noRn;
	int idVilleDepart;
	int idVilleArrive;
	double distance;

	public Routes(){}
	public Routes(int idRoutes,int noRn,int idVilleDepart,int idVilleArrive,double distance)throws Exception{
		this.setIdRoutes(idRoutes);
		this.setDistance(distance);
		this.setIdVilleArrive(idVilleArrive);
		this.setIdVilleDepart(idVilleDepart);
		this.setNoRn(noRn);
	}
	public Routes(int noRn,int idVilleDepart,int idVilleArrive,double distance)throws Exception{
		this.setDistance(distance);
		this.setIdVilleArrive(idVilleArrive);
		this.setIdVilleDepart(idVilleDepart);
		this.setNoRn(noRn);
	}

	public void setDistance(double distance) throws Exception{
		if(distance<0)throw new Exception("Champs invalide");
		this.distance = distance;
	}

	public void setIdRoutes(int idRoutes) {
		this.idRoutes = idRoutes;
	}

	public void setIdVilleArrive(int idVilleArrive) {
		this.idVilleArrive = idVilleArrive;
	}

	public void setIdVilleDepart(int idVilleDepart) {
		this.idVilleDepart = idVilleDepart;
	}

	public void setNoRn(int noRn)throws Exception {
		if(noRn<1) throw new Exception("Champs invalide");
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
}