package org.pinae.nala.xb.resource.annotation;

import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;
import org.pinae.nala.xb.annotation.ElementValue;

public class Telephone extends org.pinae.nala.xb.NalaObject {
	@Element(name="mobile")
	private Mobile mobile;
	
	@Element(name="office")
	private String office;
	
	@Element(name="id")
	private String id;
	
	@Element(name="home")
	private String home;
	
	@ElementValue
	private String value;
	
	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public class Mobile extends org.pinae.nala.xb.NalaObject {
		@Attribute(name="net")
		private String net;
		
		@ElementValue
		private String number;
		
		public String getNet() {
			return net;
		}
		public void setNet(String net) {
			this.net = net;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		
	}

}