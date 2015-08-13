package org.pinae.nala.xb.resource.bean;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.xml.XmlObject;


public class PeopleInXml {
	private List<XmlObject> person = new ArrayList<XmlObject>();
	public void setPerson(XmlObject person){
		this.person.add(person);
	}
	public List<XmlObject> getPerson(){
		return person;
	}
}
