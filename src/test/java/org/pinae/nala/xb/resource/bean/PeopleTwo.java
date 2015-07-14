package org.pinae.nala.xb.resource.bean;

import org.pinae.nala.xb.xml.XMLObject;

public class PeopleTwo {
	
	public static Object getPeople(){
		PeopleInXml people = new PeopleInXml();
		people.setPerson(new XMLObject("<firstname>inter</firstname><lastname>hyg</lastname>"));
		people.setPerson(new XMLObject("<firstname>hyg</firstname><lastname>genggeng</lastname>"));
		return people;
	}
}
