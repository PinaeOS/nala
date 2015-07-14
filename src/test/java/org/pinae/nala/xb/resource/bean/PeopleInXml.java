package org.pinae.nala.xb.resource.bean;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.xml.XMLObject;


public class PeopleInXml {
	private List<XMLObject> person = new ArrayList<XMLObject>();
	public void setPerson(XMLObject person){
		this.person.add(person);
	}
	public List<XMLObject> getPerson(){
		return person;
	}
}
