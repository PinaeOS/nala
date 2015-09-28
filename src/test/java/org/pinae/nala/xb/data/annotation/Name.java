package org.pinae.nala.xb.data.annotation;

import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;

public class Name extends org.pinae.nala.xb.XmlObject {
	
	@Element(name="lastname")
	private String lastname;
	
	@Element(name="firstname")
	private String firstname;
	
	@Attribute(name="id")
	private String id;
	
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public String getLastname(){
		return lastname;
	}
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public String getFirstname(){
		return firstname;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}

}