package org.pinae.nala.xb.resource.bean;
public class Telephone extends org.pinae.nala.xb.NalaObject {
	private Mobile mobile;
	private Office office;
	private String id;
	private Home home;
	public void setMobile(Mobile mobile){
		this.mobile = mobile;
	}
	public Mobile getMobile(){
		return mobile;
	}
	public void setOffice(Office office){
		this.office = office;
	}
	public Office getOffice(){
		return office;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setHome(Home home){
		this.home = home;
	}
	public Home getHome(){
		return home;
	}
	
	public class Home extends org.pinae.nala.xb.NalaObject {

	}
	
	public class Mobile extends org.pinae.nala.xb.NalaObject {
		private String net;
		public String getNet() {
			return net;
		}
		public void setNet(String net) {
			this.net = net;
		}
	}
	
	public class Office extends org.pinae.nala.xb.NalaObject {

	}
}